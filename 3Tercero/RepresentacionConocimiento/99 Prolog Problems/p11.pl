/*
Modified run-length encoding.
    Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied into the result list. Only elements with duplicates are transferred as [N,E] terms.

    Example:
    ?- encode2([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
    X = [[4,a],b,[2,c],[2,a],d,[4,e]]
*/

% Caso base
my_encode2([], []).
my_encode2([X], [[X]]).

my_encode2(L, X) :- packEncode2(L, 1, X).

/*
    packEncode(+ListaOriginal, +N, -ListaDeListas).

    Es cierto cuando ListaDeListas unifica con una lista que contiene listas agrupando todos los elementos repetidos de ListaOriginal
*/

% Caso base
packEncode2([], _, []).
packEncode2([E], 1, [E]).
packEncode2([E], N, [ [N, E] ]) :- N > 1.

packEncode2([H, H | Resto], N, X) :- N1 is N + 1, packEncode2([H | Resto], N1, X).
packEncode2([H1, H2 | Resto], 1, [H1 | X]) :- H1 \= H2, packEncode2([H2 | Resto], 1, X).
packEncode2([H1, H2 | Resto], N, [[N, H1] | X]) :- N > 1, H1 \= H2, packEncode2([H2 | Resto], 1, X).
