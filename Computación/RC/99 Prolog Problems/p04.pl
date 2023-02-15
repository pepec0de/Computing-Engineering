/*
    len(-X, +Lista).
    Numero de elementos de una Lista
    len(X, []).
      X = 0.
*/

len(0, []).
len((X+1), [_ | R]) :- len(X, R).