public abstract class FSM {
    protected States currentStates;

    FSM() {
        currentStates = States.q0;
    }

    private Events recognizeEvent(char ch) {
        if (ch == '+') return Events.PLUS;
        if (ch == '-') return Events.MINUS;
        if (ch >= '5' && ch <= '9') return Events.BIG_DIGIT;
        if (ch >= '0' && ch <= '4') return Events.SMALL_DIGIT;
        if (ch == 'A' || ch == 'G') return Events.AG;
        return Events.ANY;
    }

    public boolean scan(String str) {
        currentStates = States.q0;
        for (char ch : str.toCharArray()) {
            if (currentStates == States.ERROR) return false;
            Events events = recognizeEvent(ch);
            currentStates = nextState(events);
        }
        return currentStates == States.q6;
    }

    protected abstract States nextState(Events events);
}
