package dk.cphbusiness.mal.state;

public interface ValueSet {
  boolean canBeUndefined();
  boolean contains(int value);
  default boolean subsetOf(ValueSet other) {
    return false;
    }
  default ValueSet union(ValueSet other) {
    return new UnionValueSet(this, other);
    }
  // ...
  }
