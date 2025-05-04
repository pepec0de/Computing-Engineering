/*
    quicksort(+Lista, -R).
    
    es cierto si R unifica con una lista que contiene los elementos de Lista ordenados de menor a mayor por el metodo Quicksort
*/

quicksort([], []).
quicksort([Cab | Resto], R) :-
    divide(Cab, Resto, Me, Ma),
    quicksort(Me, RMe),
    quicksort(Ma, RMa),
    append(RMe, [Cab | RMa], R).

/*
    divide(+Piv, +Lista, -Me, -Ma).

*/

divide(_, [], [], []).
divide(Piv, [Cab | Resto], [Cab | Me], Ma) :- Cab <= Piv, divide(Piv, Resto, Me, Ma).
divide(Piv, [Cab | Resto], Me, [Cab | Ma]) :- Cab > Piv, divide(Piv, Resto, Me, Ma).
