/*
    Generate a random permutation of the elements of a list.
    Example:
    ?- rnd_permu([a,b,c,d,e,f],L).
    L = [b,a,d,c,e,f]

    Hint: Use the solution of problem P23.
*/

rnd_permu(L, Result) :- length(L, Length), rnd_select(L, Length, Result).

/*
    Extract a given number of randomly selected elements from a list.
    The selected items shall be put into a result list.
    Example:
    ?- rnd_select([a,b,c,d,e,f,g,h],3,L).
    L = [e,d,a]

    Hint: Use the built-in random number generator random/2 and the result of problem P20.
*/

rnd_select(_, 0, []).

rnd_select(L, N, [X | Resto]) 
    :- 
length(L, Length), 
random(R), Rnd is round(R*10000), RndPos is Rnd mod Length + 1,
remove_at(X, L, RndPos, Result),
N1 is N - 1, N > 0, rnd_select(Result, N1, Resto).

/*
Remove the K'th element from a list.
    Example:
    ?- remove_at(X,[a,b,c,d],2,R).
    X = b
    R = [a,c,d]
*/

remove_at(H, [H | Resto], 1, Resto).
remove_at(X, [H | Resto], N, [H | Result]) :- N1 is N - 1, remove_at(X, Resto, N1, Result).