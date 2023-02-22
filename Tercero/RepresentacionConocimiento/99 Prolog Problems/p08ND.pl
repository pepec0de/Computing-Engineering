/*
If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.

Example:
?- compress([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
X = [a,b,c,a,d,e]
*/

compress([], []).
compress([X], [X]).
compress([H | Resto], [H | X]) :- not(my_first(Resto, H)), compress(Resto, X).

my_first([X | _], X).