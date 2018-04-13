import java.util.ArrayList;

public class StateFSM extends FSM {
    private static ArrayList<State> list = new ArrayList<>();
    static {
        list.add(new q0());
        list.add(new q1());
        list.add(new q2());
        list.add(new q3());
        list.add(new q4());
        list.add(new q5());
        list.add(new q6());
    }
    @Override
    protected States nextState(Events events) {
        State state = setState(currentStates);
        return state.nextState(events);
    }

    private State setState(States states) {
        switch (states) {
            case q0: return list.get(0);
            case q1: return list.get(1);
            case q2: return list.get(2);
            case q3: return list.get(3);
            case q4: return list.get(4);
            case q5: return list.get(5);
            default:
                return list.get(6);
        }
    }

    static abstract class State {
        abstract States nextState(Events events);
    }
    static class q0 extends State {
        States nextState(Events events) {
            if (events == Events.PLUS) return States.q1;
            return (events == Events.MINUS) ? States.q2 : States.ERROR;
        }
    }

    static class q1 extends State {
        States nextState(Events events) {
            return  (events == Events.BIG_DIGIT) ? States.q3 : States.ERROR;
        }
    }

    static class q2 extends State {
        States nextState(Events events) {
            return  (events == Events.BIG_DIGIT) ? States.q3 : States.ERROR;
        }
    }

    static class q3 extends State {
        States nextState(Events events) {
            if (events == Events.BIG_DIGIT) return States.q3;
            if (events == Events.SMALL_DIGIT) return States.q4;
            if (events == Events.AG) return States.q5;
            return (events == Events.MINUS) ? States.q6 : States.ERROR;
        }
    }

    static class q4 extends State {
        States nextState(Events events) {
            if (events == Events.SMALL_DIGIT) return States.q4;
            return (events == Events.MINUS) ? States.q6 : States.ERROR;
        }
    }

    static class q5 extends State {
        States nextState(Events events) {
            if (events == Events.AG) return States.q5;
            return (events == Events.MINUS) ? States.q6 : States.ERROR;
        }
    }
    static class q6 extends State {
        States nextState(Events events) {
            return States.ERROR;
        }
    }
}

