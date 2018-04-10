public class StateFSM extends FSM {
    @Override
    protected States nextState(Events events) {
        State state = setState(currentStates);
        return state.nextState(events);
    }

    private State setState(States states) {
        switch (states) {
            case q0: return new q0();
            case q1: return new q1();
            case q2: return new q2();
            case q3: return new q3();
            case q4: return new q4();
            case q5: return new q5();
            case q6: return new q6();
            default:
                return new Error();
        }
    }

    abstract class State {
        abstract States nextState(Events events);
    }
    class q0 extends State {
        States nextState(Events events) {
            if (events == Events.PLUS) return States.q1;
            return (events == Events.MINUS) ? States.q2 : States.ERROR;
        }
    }

    class q1 extends State {
        States nextState(Events events) {
            return  (events == Events.BIG_DIGIT) ? States.q3 : States.ERROR;
        }
    }

    class q2 extends State {
        States nextState(Events events) {
            return  (events == Events.BIG_DIGIT) ? States.q3 : States.ERROR;
        }
    }

    class q3 extends State {
        States nextState(Events events) {
            if (events == Events.BIG_DIGIT) return States.q3;
            if (events == Events.SMALL_DIGIT) return States.q4;
            if (events == Events.AG) return States.q5;
            return (events == Events.MINUS) ? States.q6 : States.ERROR;
        }
    }

    class q4 extends State {
        States nextState(Events events) {
            if (events == Events.SMALL_DIGIT) return States.q4;
            return (events == Events.MINUS) ? States.q6 : States.ERROR;
        }
    }

    class q5 extends State {
        States nextState(Events events) {
            if (events == Events.AG) return States.q5;
            return (events == Events.MINUS) ? States.q6 : States.ERROR;
        }
    }
    class q6 extends State {
        States nextState(Events events) {
            return States.q6;
        }
    }

    class Error extends State {
        States nextState(Events events) {
            return States.ERROR;
        }
    }
}

