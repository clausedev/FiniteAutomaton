import org.junit.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.LinkedList;

public class MainTest extends TestCase {

    @Test
    public void testRunTest () {

        this.setDFA();
        String str;

        String test_1 = "110";
        String test_2 = "1010";
        String test_3 = "1011";

        str = this.getResults(test_1);
        System.out.println( str );
        Assert.assertEquals("output for state of S0 = 0", str);

        str = this.getResults(test_2);
        System.out.println( str );
        Assert.assertEquals("output for state of S1 = 0", str);

        str = this.getResults(test_3);
        System.out.println( str );
        Assert.assertEquals("output for state of S2 = 1", str);

    }

    // Init DFA
    DFA<Integer> dfa = new DFA<Integer>();

    State S0 = new State();
    State S1 = new State();
    State S2 = new State();

    void setDFA() {

        dfa.addToAlphabet(0);
        dfa.addToAlphabet(1);

        // Set first state
        S0.setStartingState(true);
        S0.setFinalState(true);
        dfa.addToStates(S0);

        // Set second state
        S1.setFinalState(true);
        dfa.addToStates(S1);

        // Set third state
        S2.setFinalState(true);
        dfa.addToStates(S2);

        // Set state transition table
        Transition<Integer> t1 = new Transition<Integer>(S0, 0, S0);
        dfa.addTransition(t1);
        Transition<Integer> t2 = new Transition<Integer>(S0, 1, S1);
        dfa.addTransition(t2);
        Transition<Integer> t3 = new Transition<Integer>(S1, 0, S2);
        dfa.addTransition(t3);
        Transition<Integer> t4 = new Transition<Integer>(S1, 1, S0);
        dfa.addTransition(t4);
        Transition<Integer> t5 = new Transition<Integer>(S2, 0, S1);
        dfa.addTransition(t5);
        Transition<Integer> t6 = new Transition<Integer>(S2, 1, S2);
        dfa.addTransition(t6);
    }

    public String getResults(String str) {

        State state = getState(str);
        Assert.assertTrue(state.isFinalState());

        if (state.isFinalState()){
            String output = dfa.getOutput();
            if (state == S1){
                return "output for state of S1 = " + output;
            }else if (state == S2){
                return "output for state of S2 = " + output;
            }else{
                return "output for state of S0 = " + output;
            }
        }else{
            return "this will never happen with our above machine as all state can be final state";
        }
    }


    public State getState(String str) {
        return dfa.calculateFinalState(S0, toList(str));
    }


    public LinkedList<Integer> toList(String input){
        LinkedList<Integer> binaryList = new LinkedList<Integer>();
        for (char ch :input.toCharArray()){
            binaryList.add(Integer.parseInt(String.valueOf(ch)));
        }
        return binaryList;
    }

}