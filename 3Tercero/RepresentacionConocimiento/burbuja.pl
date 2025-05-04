/*
    burbuja(?L, ?LOrden).
*/
ordenada([]).
ordenada([_]).
ordenada([E1, E2 | Resto]) :- E1 < E2, ordenada([E2 | Resto]).

burbuja(Lista, Lista) :- ordenada(Lista).
burbuja(Lista, R) :- append(L1, [E1, E2 | L2], Lista), E1 > E2, append(L1, [E2, E1 | L2], Lista2), burbuja(Lista2, R).