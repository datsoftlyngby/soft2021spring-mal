package dk.cphbusiness.mal.state;

public class ConjunctionState implements State {
  private State left;
  private State right;

  public ConjunctionState(State left, State right) {
    this.left = left;
    this.right = right;
    }

  @Override
  public boolean subStateOf(State other) {
    throw new UnsupportedOperationException();
    }

  @Override
  public ValueSet valuesOf(String variableName) {
    throw new UnsupportedOperationException();
    }
  }
