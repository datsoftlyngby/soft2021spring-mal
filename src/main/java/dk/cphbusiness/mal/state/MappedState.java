package dk.cphbusiness.mal.state;

import java.util.HashMap;
import java.util.Map;

public class MappedState implements State {
  private Map<String, ValueSet> variables = new HashMap<>();

  public MappedState() { }

  public void put(String variableName, ValueSet values) {
    throw new UnsupportedOperationException();
    }

  public void put(String variableName) {
    // all values
    }

  @Override
  public ValueSet valuesOf(String variableName) {
    return null;
    }

  @Override
  public boolean subStateOf(State other) {
    return false;
    }

  }
