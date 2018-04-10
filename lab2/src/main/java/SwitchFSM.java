public class SwitchFSM extends FSM {
    @Override
    protected States nextState(Events events) {
        switch (currentStates) {
            case q0: {
                if (events == Events.PLUS) return States.q1;
                return (events == Events.MINUS) ? States.q2 : States.ERROR;
            }
            case q1:
            case q2:
                return  (events == Events.BIG_DIGIT) ? States.q3 : States.ERROR;
            case q3: {
                if (events == Events.BIG_DIGIT) return States.q3;
                if (events == Events.SMALL_DIGIT) return States.q4;
                if (events == Events.AG) return States.q5;
                return (events == Events.MINUS) ? States.q6 : States.ERROR;
            }
            case q4: {
                if (events == Events.SMALL_DIGIT) return States.q4;
                return (events == Events.MINUS) ? States.q6 : States.ERROR;
            }
            case q5: {
                if (events == Events.AG) return States.q5;
                return (events == Events.MINUS) ? States.q6 : States.ERROR;
            }
            case q6:
            default:
                return States.ERROR;
        }
    }
}
