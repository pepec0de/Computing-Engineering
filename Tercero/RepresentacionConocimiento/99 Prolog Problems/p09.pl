/*
Pack consecutive duplicates of list elements into sublists.
If a list contains repeated elements they should be placed in separate sublists.

    Example:
    ?- pack([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
    X = [[a,a,a,a],[b],[c,c],[a,a],[d],[e,e,e,e]]
*/

my_pack([X], [[X]]).
my_pack([H | Resto], [Lista | X]) :- hacerLista(H, Resto, Lista, Resto2), my_pack(Resto2, X).

hacerLista(H, [H | Resto], [H | Lista], Resto) :- hacerLista(H, Resto, Lista, Resto).