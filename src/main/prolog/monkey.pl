% The great monkey program

move(
	state(From, onfloor, AnyWhere, H),
	walk(From, To),
	state(To, onfloor, AnyWhere, H)
	).

move(
	state(From, onfloor, From, H),
	push(From, To),
	state(To, onfloor, To, H)
	).

move(
	state(SomeWhere, onfloor, SomeWhere, H),
	climb,
	state(SomeWhere, onbox, SomeWhere, H)
	).

move(
	state(middle, onbox, middle, hasnot),
	grasp,
	state(middle, onbox, middle, has)
	).

canget(_, state(_, _, _, has), []).
canget(TimeToLive, SomeState, [How | Path]) :-
	TimeToLive > 0,
	move(SomeState, How, IntermediateState),
	canget(TimeToLive - 1, IntermediateState, Path).
%	Time is TimeToLive,
%	write(Time/How), nl.

canget(MaxTime, TimeToLive, InitialState, Path) :-
	MaxTime > TimeToLive,
	canget(TimeToLive, InitialState, Path).

canget(MaxTime, TimeToLive, InitialState, Path) :-
	canget(MaxTime, TimeToLive + 1, InitialState, Path).

canget(InitialState, Path) :-
	canget(10, 0, InitialState, Path).
