/*
Duplicate the elements of a list.
    Example:
    ?- dupli([a,b,c,c,d],X).
    X = [a,a,b,b,c,c,c,c,d,d]
*/

dupli([], []).
dupli([H | Resto], [H, H | X]) :- dupli(Resto, X).