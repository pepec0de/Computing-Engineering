/*
    Insert an element at a given position into a list.
    Example:
    ?- insert_at(alfa,[a,b,c,d],2,L).
    L = [a,alfa,b,c,d]
*/

insert_at(X, L, 1, [X | L]).
insert_at(X, [H | Resto], N, [H | L]) :- N1 is N - 1, insert_at(X, Resto, N1, L).