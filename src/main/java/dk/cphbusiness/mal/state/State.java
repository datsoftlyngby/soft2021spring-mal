package dk.cphbusiness.mal.state;

public interface State {
  ValueSet valuesOf(String variableName);
  boolean subStateOf(State other);

  default State conjunction(State other) {  // fancy ord for and
    return new ConjunctionState(this, other);
    }
  // State disjunction(State other); // fancy ord for or
  }
