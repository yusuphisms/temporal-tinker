package interceptor;

import io.temporal.common.interceptors.WorkflowOutboundCallsInterceptor;
import io.temporal.common.interceptors.WorkflowOutboundCallsInterceptorBase;
import io.temporal.workflow.CompletablePromise;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyOutboundBase extends WorkflowOutboundCallsInterceptorBase {
    private final CompletablePromise<Object> asyncResult = Workflow.newPromise();

    public MyOutboundBase(WorkflowOutboundCallsInterceptor next) {
        super(next);
    }

    @Override
    public <R> ActivityOutput<R> executeActivity(ActivityInput<R> input) {
        // input has the activity, it's possible to filter which activities this applies to.
        // Wonder if it's posible to also apply the filter to a Task Queue specifically with worker factory setup.
        log.info("Interceptor is running.\n {}\n {}\n {}\n {}\n {}\n {}",
                input.getActivityName(),
                input.getArgs(),
                input.getResultType(),
                input.getResultClass(),
                input.getHeader().getValues(),
                input.getOptions()
        );
        final var result = MyOutboundBase.super.executeActivity(input);
        log.info("Result was: {}", result.getActivityId());
        result
                .getResult()
                .handle((good, baad) -> {
                    log.info("what is goodness? {}", good);
                    if (baad == null) {
                        log.info("no good deed goes unpunished");
                        final var execResult = MyOutboundBase.super.executeActivity(new ActivityInput<>("Encore",
                                Object.class, Void.TYPE, null, input.getOptions(), input.getHeader()));
                        asyncResult.completeFrom(execResult.getResult());
                        return execResult.getResult().exceptionally(throwable -> {
                                    log.info("no good deed will i do, agaaiiiiiin");
                                    return throwable;
                                })
                                .thenApply(r -> {
                                    log.info("so be it then.");
                                    return null;
                                });
                    }
                    return asyncResult.completeExceptionally(baad);
                });
        // Using asyncResult here allows it to wait until asyncResult (Encore) is complete
        return new ActivityOutput(result.getActivityId(), asyncResult);
    }
}
