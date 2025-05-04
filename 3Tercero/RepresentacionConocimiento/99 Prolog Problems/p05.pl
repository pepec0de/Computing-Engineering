/*
    myreverse(-R, +Lista).
    Reverse a list
    myreverse(X, []).
      X = [].
*/

myreverse([], []).
myreverse(NX, [Cab | Resto]) :- myreverse(X, Resto), append(X, [Cab], NX).
