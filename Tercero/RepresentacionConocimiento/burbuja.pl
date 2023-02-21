/*
    burbuja(?L, ?LOrden).

*/
ordenada([]).
ordenada([_]).
ordenada([E1, E2 | Resto]) :- E1 < E2, bur([E2 | Resto]).

%burbuja([], []).
%burbuja([X], [X]).
%burbuja([E1, E2 | Resto], [E1 | LOrden]) :- E1 < E2, burbuja([E2 | Resto], LOrden).
%burbuja([E1, E2 | Resto], [E2 | LOrden]) :- E1 > E2, burbuja([E1 | Resto], LOrden).

burbuja(Lista, Lista) :- ordenada(Lista).
burbuja(Lista, Lista2) :- append(L1, [E1, E2 | L2], Lista),
                        E1 > E2,
                        append(L1, [E2, E1 | L2], Lista2).
                        
burbuja([E1, E2 | Resto], [E1 | LOrden]) :- E1 < E2, burbuja([E2 | Resto], LOrden).