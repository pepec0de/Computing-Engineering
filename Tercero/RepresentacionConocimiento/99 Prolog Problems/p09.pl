/*
Pack consecutive duplicates of list elements into sublists.
If a list contains repeated elements they should be placed in separate sublists.

    Example:
    ?- my_pack([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
    X = [[a,a,a,a],[b],[c,c],[a,a],[d],[e,e,e,e]]
*/

my_pack([X], [[X]]).
my_pack([H, H | Resto], X) :- my_pack([H | Resto], X).

my_pack([H1, H2 | Resto], [Lista2 | X]) :- H1 \= H2, insertarEenLista(H1, Lista, Lista2), my_pack([H2 | Resto], [Lista | X]).


/*
	hacerLista(+E, +N, -L)
	
*/
hacerLista(E, 0, []).
hacerLista(E, N, [E | L]) :- N1 is N - 1, hacerLista(E, N1, L).
