package dk.cphbusiness.mal.state;

public interface SyntaxTree {
  State analyse(State condition);
  }

/*
  public static void main(String[] args) {
    State P = new XState(...); // precondition
    State Q = new XState(...); // postcondition
    SyntaxtTree C = new XSyntaxTree(...) // commands / code / program
    State S = C.analyse(P);
    if (S.subStateOf(Q) {
      System.out.println("Hurra!");
      }
    else {
      System.out.println("Øv!");
      }

  }

 */