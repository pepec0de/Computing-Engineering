/*
    mylast(-X, +Lista)
    find the last element of a list

    Example:
    ?- mylast(X,[a,b,c,d]).
    X = d
*/


mylast(X, [X]).
mylast(X, [_ | Resto]) :- mylast(X, Resto).