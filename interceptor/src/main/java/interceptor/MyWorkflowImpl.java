package interceptor;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class MyWorkflowImpl implements MyWorkflow {
    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(60))
            .build();
    private final MyActivity activity = Workflow.newActivityStub(MyActivity.class, options);
    @Override
    public void starto() {
        log.info("Workflow was startled");
        activity.act();
        log.info("Workflow has ended");
    }
}
