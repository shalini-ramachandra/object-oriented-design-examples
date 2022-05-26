package callcenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The call handler which dispatches an incoming call
 */
public class CallDispatcher {

    // List of available responders
    private final List<CallResponder>[] allResponders;

    // A queue of all calls that are waiting for responders
    private final Queue<Call> callQueue;

    /**
     * Initialize the different call responders
     *
     * @param responderCount The number of responders
     * @param managerCount   The number of managers
     * @param directorCount  The number of directors
     */
    public CallDispatcher(int responderCount, int managerCount, int directorCount) {
        allResponders = new List[3];
        allResponders[0] = createCallResponders(CallResponderType.RESPONDER, responderCount);
        allResponders[1] = createCallResponders(CallResponderType.MANAGER, managerCount);
        allResponders[2] = createCallResponders(CallResponderType.DIRECTOR, directorCount);

        callQueue = new LinkedList<>();
    }

    /**
     * Create the list of responders of the given type
     *
     * @param responderType The type of responder
     * @param count         The number of responders to create
     * @return The list of responders
     */
    private List<CallResponder> createCallResponders(CallResponderType responderType, int count) {
        List<CallResponder> callResponders = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            CallResponder callResponder = new CallResponder(responderType, String.valueOf(i), this);
            callResponders.add(callResponder);
        }

        return callResponders;
    }

    /**
     * Dispatch an incoming call
     *
     * @param call The incoming call
     */
    public void dispatchCall(Call call) {
        // Start by dispatching to the lowest level of responder
        dispatchCall(call, CallResponderType.RESPONDER.level);
    }

    /**
     * Dispatch an incoming call to a responder at a particular level if available, else escalate
     *
     * @param call           The incoming call
     * @param responderLevel The level of the responder to choose from
     */
    private void dispatchCall(Call call, int responderLevel) {

        // TODO - Optimize this instead of having to go through each responder and checking if he/she is free
        for (CallResponder callResponder : allResponders[responderLevel]) {
            // Check if any call responder is free
            if (callResponder.isFree()) {
                call.setCallResponder(callResponder);
                System.out.println(call);
                return;
            }
        }

        // Escalate to next level
        escalate(call, responderLevel + 1);
    }

    public void escalate(Call call, int nextResponderLevel) {

        if (nextResponderLevel == allResponders.length) {
            // We are at the top most level and there are no more responders available. So put the call on wait
            System.out.println("Caller " + call.getCaller().getName() + ". Please wait in queue. All our agents are busy");
            callQueue.add(call);
        } else {
            // Escalate and dispatch the call to the next level
            dispatchCall(call, nextResponderLevel);
        }
    }

    /**
     * If a responder becomes free, assign a call from the queue if callers are waiting. Don't wait for the next call to
     * arrive and then be dispatched.
     *
     * @param callResponder The call responder who is free.
     */
    public void assignCallFromQueue(CallResponder callResponder) {
        if (!callQueue.isEmpty()) {
            // Assigning call from queue
            Call call = callQueue.remove();
            call.setCallResponder(callResponder);
            System.out.println("Assigning call queue --> " + call);
            callResponder.receiveCall(call);
        }
    }
}
