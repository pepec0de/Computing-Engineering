/*
    last_but_one(-X, +List)
        last_but_one(X, [1, 2])
        X = 1
*/

last_but_one(X, [X, _]).
last_but_one(X, [_ | Resto]) :- last_but_one(X, Resto).