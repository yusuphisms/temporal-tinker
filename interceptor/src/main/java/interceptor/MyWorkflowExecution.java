package interceptor;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyWorkflowExecution {

    public static void main(String[] args) throws Exception {

        // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Define our workflow unique id
        final String WORKFLOW_ID = "MyWorkflowId";

        /*
         * Set Workflow options such as WorkflowId and Task Queue so the worker knows where to list and which workflows to execute.
         */
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(WORKFLOW_ID)
                .setTaskQueue(Shared.DEFAULT_TASK_QUEUE)
                .build();

        // Create the workflow client stub. It is used to start our workflow execution.
        MyWorkflow workflow = client.newWorkflowStub(MyWorkflow.class, options);

        /*
         * Execute our workflow and wait for it to complete. The call to our workflow method is
         * synchronous.
         *
         */
        workflow.starto();

        String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();
        // Display workflow execution results
        log.info("This is the workflow id: {}", workflowId);
        System.exit(0);
    }
}