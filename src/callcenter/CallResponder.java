package callcenter;

public class CallResponder {

    private final String employeeId;
    private final CallResponderType callResponderType;
    private final CallDispatcher callDispatcher;
    private Call currentCall;

    public CallResponder(CallResponderType type, String employeeId, CallDispatcher callDispatcher) {
        this.callResponderType = type;
        this.employeeId = employeeId;
        this.callDispatcher = callDispatcher;
    }

    public void receiveCall(Call call) {
        currentCall = call;
    }

    public void endCall() {
        currentCall = null;
        // If there are calls in the queue, go ahead and auto assign it
        assignCall();
    }

    public void escalate() {
        callDispatcher.escalate(currentCall, callResponderType.level + 1);
    }

    public void assignCall() {
        if (currentCall == null) {
            callDispatcher.assignCallFromQueue(this);
        }
    }

    public boolean isFree() {
        return currentCall == null;
    }

    public String toString() {
        return employeeId + "-" + callResponderType;
    }
}
