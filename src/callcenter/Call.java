package callcenter;

public class Call {

    private final Caller caller;
    private CallResponder callResponder;

    public Call(Caller caller) {
        this.caller = caller;
    }

    public Caller getCaller() {
        return caller;
    }

    public CallResponder getCallResponder() {
        return callResponder;
    }

    public void setCallResponder(CallResponder callResponder) {
        this.callResponder = callResponder;
        callResponder.receiveCall(this);
    }

    public String toString() {
        return "Call from " + caller.getName() + " assigned to responder " + callResponder;
    }
}
