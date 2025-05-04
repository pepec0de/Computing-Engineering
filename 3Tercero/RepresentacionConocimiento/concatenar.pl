/*
    concatenar(+Lista1, +Lista2, R)
*/

concatenar([], L, L).
concatenar([Cab | Resto], L, [Cab | R]) :- concatenar(Resto, L, R).