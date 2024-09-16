/*
Drop every N'th element from a list.
    Example:
    ?- drop([a,b,c,d,e,f,g,h,i,k],3,X).
    X = [a,b,d,e,g,h,k]
*/

drop([], _, []).
drop(Lista, N, X) :- dropAtN(Lista, N, N, X).

dropAtN([], _, _, []).
dropAtN([_ | Resto], 1, Nth, X) :- dropAtN(Resto, Nth, Nth, X).
dropAtN([Cab | Resto], N, Nth, [Cab | X]) :- N \= 1, N1 is N - 1, dropAtN(Resto, N1, Nth, X).
