/*

comprime(+Lista, -R)


*/

comprime([], []).
comprime([Cab], [(Cab, 1)]).
comprime([Cab, Cab | Resto], [(Cab, N2) | R] ) :- comprime([Cab | Resto], [(Cab, N) | R]), N2 is N + 1.
comprime([H1, H2 | Resto], [(H1, 1) | X]) :- H1 \= H2, comprime([H2 | Resto], X).


masveces(L, E, N) :- comprime(L, LC), nmayor(LC, E, N).

nmayor([(E, N)], E, N). 
nmayor([(_, N) | Resto], E2, N2) :- nmayor(Resto, E2, N2),  N2 > N.
nmayor([(E, N) | Resto], E, N) :- nmayor(Resto, _, N2), N2 < N.

nmayor([(E, N) | Resto], E, N) :- nmayor([(E, N) | Resto], _, N2), N2 == N.