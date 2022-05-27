package callcenter;

import java.util.ArrayList;
import java.util.List;

public class CallCenterRunnerApplication {

    public static void main(String[] args) {
        CallDispatcher callDispatcher = new CallDispatcher(40, 10, 2);
        int numOfCalls = 120;

        List<Call> calls = new ArrayList<>();

        for (int i = 0; i < numOfCalls; i++) {

            // Create the caller
            Caller caller = new Caller();
            caller.setName("Caller " + i);

            // Create the call for the caller
            Call call = new Call(caller);
            calls.add(call);

            // Dispatch the call
            callDispatcher.dispatchCall(call);

            // Simulating freeing up the call responder, so that they can take the call when they become free
            if (i != 0 && i % 10 == 0) {
                calls.get(0).getCallResponder().endCall();
            }
        }
    }
}
