/*
Create a list containing all integers within a given range.
    Example:
    ?- range(4,9,L).
    L = [4,5,6,7,8,9]
*/

range(X, X, [X]).
range(A, B, [A | L]) :- A1 is A + 1, A < B, range(A1, B, L).