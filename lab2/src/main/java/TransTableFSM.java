import java.util.ArrayList;

public class TransTableFSM extends FSM {
    private ArrayList<Transition> transitions = new ArrayList<>();

    TransTableFSM() {
        transitions.add(new Transition(States.q0, Events.PLUS, States.q1));
        transitions.add(new Transition(States.q0, Events.MINUS, States.q2));
        transitions.add(new Transition(States.q1, Events.BIG_DIGIT, States.q3));
        transitions.add(new Transition(States.q2, Events.BIG_DIGIT, States.q3));
        transitions.add(new Transition(States.q3, Events.MINUS, States.q6));
        transitions.add(new Transition(States.q3, Events.AG, States.q5));
        transitions.add(new Transition(States.q3, Events.BIG_DIGIT, States.q3));
        transitions.add(new Transition(States.q3, Events.SMALL_DIGIT, States.q4));
        transitions.add(new Transition(States.q4, Events.MINUS, States.q6));
        transitions.add(new Transition(States.q4, Events.SMALL_DIGIT, States.q4));
        transitions.add(new Transition(States.q5, Events.MINUS, States.q6));
        transitions.add(new Transition(States.q5, Events.AG, States.q5));
    }

    @Override
    protected States nextState(Events events) {
        for (Transition cur : transitions)
            if (cur.startStates == currentStates && cur.trigger == events)
                return cur.endStates;
        return States.ERROR;
    }
    public class Transition {
        States startStates;
        Events trigger;
        States endStates;

        Transition(States startStates, Events trigger, States endStates) {
            this.endStates = endStates;
            this.startStates = startStates;
            this.trigger = trigger;
        }
    }
}
