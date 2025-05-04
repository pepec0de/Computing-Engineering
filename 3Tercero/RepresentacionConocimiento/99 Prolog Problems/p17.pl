/*
Split a list into two parts; the length of the first part is given.
    Do not use any predefined predicates.

    Example:
    ?- split([a,b,c,d,e,f,g,h,i,k],3,L1,L2).
    L1 = [a,b,c]
    L2 = [d,e,f,g,h,i,k]
*/

split(L, 0, [], L).

split([Cab | Resto], N, [Cab | L1], L2) :- N > 0, N1 is N - 1, split(Resto, N1, L1, L2).
