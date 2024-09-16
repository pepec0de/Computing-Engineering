/*
my_pack(+Lista, -X).

Pack consecutive duplicates of list elements into sublists.
If a list contains repeated elements they should be placed in separate sublists.

    Example:
    ?- my_pack([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
    X = [[a,a,a,a],[b],[c,c],[a,a],[d],[e,e,e,e]]

Es cierto cuando X unifica con una lista que contiene listas agrupando todos los elementos repetidos

*/

% Caso base
my_pack([], []).
my_pack([X], [[X]]).

my_pack(L, X) :- packInList(L, [], X).

/*
    packInList(+ListaOriginal, +ListaAGenerar, -ListaDeListas).

    Es cierto cuando ListaDeListas unifica con una lista que contiene listas agrupando todos los elementos repetidos de ListaOriginal
*/

% Caso base
packInList([], _, []).
packInList([E], Lista, [ [E | Lista] ]). 

packInList([H, H | Resto], L, X) :- packInList([H | Resto], [H | L], X).
packInList([H1, H2 | Resto], Lista, [[H1 | Lista] | X]) :- H1 \= H2, packInList([H2 | Resto], [], X).