package dk.cphbusiness.mal.state;

/*

  P = [
    X € { 1... },
    Y € {?},
    Z € {?, 7...17}
    ]

  { 1, 2, 3 } ⊆ { 1, 2, 3, 4 }     5 <= 7
  { 1, 2, 3, 4 } ⊈ { 1, 2, 3 }     7 </= 5 ( 7 > 5 )

  { 1, 2, 3 } ⊈ { 2, 3, 4 }        ?

  Max: { 1, 2, 3, 4 }
  Min: { 2, 3 }

  Top: U
  Bot: Ø

  { P } C { Q }

  analyse(C, P) -> S

  S ⪯ Q

 */


public interface State {
  ValueSet valuesOf(String variableName);
  boolean subStateOf(State other);

  default State conjunction(State other) {  // fancy ord for and
    return new ConjunctionState(this, other);
    }
  // State disjunction(State other); // fancy ord for or
  }
