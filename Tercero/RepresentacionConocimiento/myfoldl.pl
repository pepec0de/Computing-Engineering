/*

myfoldl(Goal, List, Init, R).

    es cierto si R unifica con el resultado de encadenar los Objetivos de la siguiente forma


*/

myfoldl(_, [], Init, Init).
myfoldl(Goal, [E], Ini, R) :- call(Goal, E, Ini, R).

myfoldl(Goal, [Cab | Resto], Ini, R2) :-
    myfoldl(Goal, Resto, Ini, R),
    call(Goal, Cab, R, R2).