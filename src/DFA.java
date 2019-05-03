import java.util.HashSet;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

public class DFA<T> {
    private Set<T> alphabet = new HashSet<T>();
    private Set<State> states = new HashSet<State>();
    private Set<Transition<T>> transitionFunction = new HashSet<Transition<T>>();
    private String output;

    public void addToAlphabet(T symbol) {
        alphabet.add(symbol);
    }

    public String getOutput() {
        return output;
    }

    public void addToStates(State state){
        states.add(state);
    }

    public void addTransition(Transition<T> transition) throws IllegalArgumentException{
        // no 2 outputs for same input+symbol
        if(transitionFunction.stream()
                .noneMatch(t -> t.getInputState().equals(transition.getInputState()) &&
                        t.getSymbol().equals(transition.getSymbol()))){
            transitionFunction.add(transition);
        } else {
            throw new IllegalArgumentException();
        }
    }


    public State calculateFinalState(State state, Queue<T> symbol)
            throws IllegalStateException, IllegalArgumentException {

        if(symbol.isEmpty() && state.isFinalState()){
            return state;
        }

        if(!alphabet.contains(symbol.peek())){
            throw new IllegalArgumentException();
        }

        Optional<State> nextState = getNextState(state, symbol.poll());
        if(nextState.isPresent()){
            return calculateFinalState(nextState.get(), symbol);
        }
        throw new IllegalStateException();
    }


    private Optional<State> getNextState(State state, T alphabet){
        output = alphabet.toString();
        return transitionFunction.stream().filter(t -> t.getInputState().equals(state) &&
                 t.getSymbol().equals(alphabet)).map(t -> t.getOutputState()).findFirst();
    }

}