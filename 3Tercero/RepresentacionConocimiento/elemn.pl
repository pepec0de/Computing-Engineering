/*
    elemento_enesimo(+Pos, +Lista, -Elem) que es cierto cuando Elem unifica con el elemento que ocupa la posicion pos
*/

elemn0(0, [Cab | _], Cab).
elemn0(Pos, [_ | Resto], X) :- Pos > 0, NP is Pos - 1, elemn0(NP, Resto, X).
&elemn0(Pos, [_ | Resto], Elem) :- elemn0(NP, Resto, Elem), Pos is NP + 1.