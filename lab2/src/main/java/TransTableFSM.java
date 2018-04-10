import java.util.ArrayList;

public class TransTableFSM extends FSM {
    private ArrayList<Transition> transitions = new ArrayList<>();

    TransTableFSM() {
        //q0
        transitions.add(new Transition(States.q0, Events.PLUS, States.q1));
        transitions.add(new Transition(States.q0, Events.MINUS, States.q2));
        transitions.add(new Transition(States.q0, Events.AG, States.ERROR));
        transitions.add(new Transition(States.q0, Events.BIG_DIGIT, States.ERROR));
        transitions.add(new Transition(States.q0, Events.SMALL_DIGIT, States.ERROR));
        transitions.add(new Transition(States.q0, Events.ANY, States.ERROR));
        //q1
        transitions.add(new Transition(States.q1, Events.PLUS, States.ERROR));
        transitions.add(new Transition(States.q1, Events.MINUS, States.ERROR));
        transitions.add(new Transition(States.q1, Events.AG, States.ERROR));
        transitions.add(new Transition(States.q1, Events.BIG_DIGIT, States.q3));
        transitions.add(new Transition(States.q1, Events.SMALL_DIGIT, States.ERROR));
        transitions.add(new Transition(States.q1, Events.ANY, States.ERROR));
        //q2
        transitions.add(new Transition(States.q2, Events.PLUS, States.ERROR));
        transitions.add(new Transition(States.q2, Events.MINUS, States.ERROR));
        transitions.add(new Transition(States.q2, Events.AG, States.ERROR));
        transitions.add(new Transition(States.q2, Events.BIG_DIGIT, States.q3));
        transitions.add(new Transition(States.q2, Events.SMALL_DIGIT, States.ERROR));
        transitions.add(new Transition(States.q2, Events.ANY, States.ERROR));
        //q3
        transitions.add(new Transition(States.q3, Events.PLUS, States.ERROR));
        transitions.add(new Transition(States.q3, Events.MINUS, States.q6));
        transitions.add(new Transition(States.q3, Events.AG, States.q5));
        transitions.add(new Transition(States.q3, Events.BIG_DIGIT, States.q3));
        transitions.add(new Transition(States.q3, Events.SMALL_DIGIT, States.q4));
        transitions.add(new Transition(States.q3, Events.ANY, States.ERROR));
        //q4
        transitions.add(new Transition(States.q4, Events.PLUS, States.ERROR));
        transitions.add(new Transition(States.q4, Events.MINUS, States.q6));
        transitions.add(new Transition(States.q4, Events.AG, States.ERROR));
        transitions.add(new Transition(States.q4, Events.BIG_DIGIT, States.ERROR));
        transitions.add(new Transition(States.q4, Events.SMALL_DIGIT, States.q4));
        transitions.add(new Transition(States.q4, Events.ANY, States.ERROR));
        //q5
        transitions.add(new Transition(States.q5, Events.PLUS, States.ERROR));
        transitions.add(new Transition(States.q5, Events.MINUS, States.q6));
        transitions.add(new Transition(States.q5, Events.AG, States.q5));
        transitions.add(new Transition(States.q5, Events.BIG_DIGIT, States.ERROR));
        transitions.add(new Transition(States.q5, Events.SMALL_DIGIT, States.ERROR));
        transitions.add(new Transition(States.q5, Events.ANY, States.ERROR));
    }

    @Override
    protected States nextState(Events events) {
        States result = States.q0;
        for (Transition cur : transitions)
            if (cur.startStates == currentStates && cur.trigger == events)
                result = cur.endStates;
        return result;
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
