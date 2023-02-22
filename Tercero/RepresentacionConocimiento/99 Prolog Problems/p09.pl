/*
Pack consecutive duplicates of list elements into sublists.
If a list contains repeated elements they should be placed in separate sublists.

    Example:
    ?- pack([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
    X = [[a,a,a,a],[b],[c,c],[a,a],[d],[e,e,e,e]]
*/

my_pack([X], [[X]]).
my_pack([H | Resto], [Lista | X]) :- .