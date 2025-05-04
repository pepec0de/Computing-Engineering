/*
Decode a run-length encoded list.
    Given a run-length code list generated as specified in problem P11. Construct its uncompressed version.

    my_decode(+ListaEncoded, -ListaDesencoded).

    Example:
    ?- my_decode([[4,a],b,[2,c],[2,a],d,[4,e]],X).
    X = [a,a,a,a,b,c,c,a,a,d,e,e,e,e]
*/

my_decode([], []).
my_decode([Cab | Resto], NX) :- addToList(Cab, X, NX), my_decode(Resto, X).

/*
    addToList(+Formato, +Lista, -R).
    es cierto si -R unifica con el formato que se indica en +Formato ([N, E] ó E) concatenado con +Lista.
*/

addToList(E, X, [E | X]) :- not(is_list(E)).
addToList([0, _], X, X).
addToList([N, E], L, [E | X]) :- N > 0, N1 is N - 1, addToList([N1, E], L, X).
