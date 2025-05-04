/*
    len(-X, +Lista).
    Numero de elementos de una Lista
    len(X, []).
      X = 0.
*/

len(0, []).
len(NX, [_ | R]) :- len(X, R), NX is X + 1.