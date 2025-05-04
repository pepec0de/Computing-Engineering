/*
    element_at(-X, +Lista, +N)

    The first element in the list is number 1.
        Example:
        ?- element_at(X,[a,b,c,d,e],3).
        X = c
*/

element_at(X, [X | _], 1).
element_at(X, [_ | R], N) :- NX is N - 1, element_at(X, R, NX). 