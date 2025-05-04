/*
    insercion(Lista, ListaOrden).
*/

ordenada([]).
ordenada([_]).
ordenada([E1, E2 | Resto]) :- E1 < E2, ordenada([E2 | Resto]).

insercion(Lista, Lista) :- ordenada(Lista).

insertar_orden(X, [], [X]).
insertar_orden(E, [Cab | Resto], [Cab | R]) :- E > Cab, insertar_orden(E, Resto, R).
insertar_orden(E, [Cab | Resto], [E, Cab | Resto]) :- E =< Cab.