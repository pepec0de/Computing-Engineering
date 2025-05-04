/*
Remove the K'th element from a list.
    Example:
    ?- remove_at(X,[a,b,c,d],2,R).
    X = b
    R = [a,c,d]
*/

remove_at(H, [H | Resto], 1, Resto).
remove_at(X, [H | Resto], N, [H | Result]) :- N1 is N - 1, remove_at(X, Resto, N1, Result).