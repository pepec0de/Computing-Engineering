/*
Duplicate the elements of a list a given number of times.
    Example:
    ?- dupli([a,b,c],3,X).
    X = [a,a,a,b,b,b,c,c,c]

*/

dupli([], _, []).
dupli([H | Resto], N, NX) :- addntimes(H, N, L), append(L, X, NX), dupli(Resto, N, X).

addntimes(_, 0, []).
addntimes(E, N, [E | L]) :- N1 is N - 1, N > 0, addntimes(E, N1, L).