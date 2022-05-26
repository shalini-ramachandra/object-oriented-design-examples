package callcenter;

public enum CallResponderType {

    RESPONDER(0), MANAGER(1), DIRECTOR(2);

    /** The level of the call responder. 0 being the least and 2 being the highest */
    public final int level;

    CallResponderType(int level) {
        this.level = level;
    }
}
