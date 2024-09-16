/*
Rotate a list N places to the left.
    Examples:
    ?- rotate([a,b,c,d,e,f,g,h],3,X).
    X = [d,e,f,g,h,a,b,c]

    ?- rotate([a,b,c,d,e,f,g,h],-2,X).
    X = [g,h,a,b,c,d,e,f]

    Hint: Use the predefined predicates length/2 and append/3, as well as the result of problem P17.
*/

rotate([], _, []).
rotate(L, 0, L).

rotate(Lista, N, X) :- N > 0, split(Lista, N, L1, L2), append(L2, L1, X).
rotate(Lista, N, X) :- N < 0, length(Lista, Len), N1 is Len + N, split(Lista, N1, L1, L2), append(L2, L1, X).

/*
    ?- split([a,b,c,d,e,f,g,h,i,k],3,L1,L2).
    L1 = [a,b,c]
    L2 = [d,e,f,g,h,i,k]
*/

split(L, 0, [], L).

split([Cab | Resto], N, [Cab | L1], L2) :- N > 0, N1 is N - 1, split(Resto, N1, L1, L2).
