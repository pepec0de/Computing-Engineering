/*
If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.

Example:
?- compress([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
X = [a,b,c,a,d,e]
*/

compress([X], [X]).
compress([C1, C2 | Resto], NX) :- compress(Resto, X), C1 == C2. 