/*
Lotto: Draw N different random numbers from the set 1..M.
    The selected numbers shall be put into a result list.
    Example:
    ?- lotto(6,49,L).
    L = [23,1,17,33,21,37]

    Hint: Combine the solutions of problems P22 and P23.
*/

lotto(N, M, L) :- range(1, M, LInt), rnd_select(LInt, N, L).

/*
Create a list containing all integers within a given range.
    Example:
    ?- range(4,9,L).
    L = [4,5,6,7,8,9]
*/

range(X, X, [X]).
range(A, B, [A | L]) :- A1 is A + 1, A < B, range(A1, B, L).

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