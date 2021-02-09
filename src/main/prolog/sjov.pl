insert(E, L, [E | L]).

rem(E, [E | R1], R1).
rem(E, [F | R1], [F | R2]) :- rem(E, R1, R2).
