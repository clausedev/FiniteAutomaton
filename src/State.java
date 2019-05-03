public class State {


    private boolean startingState = false;
    private boolean finalState = false;

    public boolean isFinalState() {
        return finalState;
    }

    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }

    public boolean isStartingState() {
        return startingState;
    }

    public void setStartingState(boolean startingState) {
        this.startingState = startingState;
    }


}
