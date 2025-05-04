/*
Run-length encoding of a list.
    Use the result of problem P09 to implement the so-called run-length encoding data compression method. Consecutive duplicates of elements are encoded as terms [N,E] where N is the number of duplicates of the element E.

    Example:
    ?- encode([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
    X = [[4,a],[1,b],[2,c],[2,a],[1,d],[4,e]]
*/

% Caso base
my_encode([], []).
my_encode([X], [[X]]).

my_encode(L, X) :- packEncode(L, 1, X).

/*
    packEncode(+ListaOriginal, +N, -ListaDeListas).

    Es cierto cuando ListaDeListas unifica con una lista que contiene listas agrupando todos los elementos repetidos de ListaOriginal
*/

% Caso base
packEncode([], _, []).
packEncode([E], N, [ [N, E] ]). 

packEncode([H, H | Resto], N, X) :- N1 is N + 1, packEncode([H | Resto], N1, X).
packEncode([H1, H2 | Resto], N, [[N, H1] | X]) :- H1 \= H2, packEncode([H2 | Resto], 1, X).
