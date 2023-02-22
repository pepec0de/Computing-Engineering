/*
If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.

Example:
?- compress([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
X = [a,b,c,a,d,e]
*/

compress([], []).
compress([X], [X]).
compress([H, H | Resto], X) :- compress([H | Resto], X).
compress([H1, H2 | Resto], [H1 | X]) :- H1 \= H2, compress([H2 | Resto], X).
