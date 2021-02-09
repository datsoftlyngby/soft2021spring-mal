parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).

female(pam).
female(liz).
female(pat).
female(ann).

male(jim).
male(tom).
male(bob).

sister(X, S) :-
  parent(P, X),
  parent(P, S),
  X \== S,
  female(S).

father(F,C) :-
  parent(F,C),
  male(F).

grandfather(GF, C) :-
  parent(P, C),
  father(GF, P).
