package dk.cphbusiness.mal.state;

public class UnionValueSet implements ValueSet{
  private final ValueSet left;
  private final ValueSet right;

  public UnionValueSet(ValueSet left, ValueSet right) {
    this.left = left;
    this.right = right;
    }

  @Override
  public boolean canBeUndefined() {
    return left.canBeUndefined() || right.canBeUndefined();
    }

  @Override
  public boolean contains(int value) {
    return left.contains(value) || right.contains(value);
    }

  }
