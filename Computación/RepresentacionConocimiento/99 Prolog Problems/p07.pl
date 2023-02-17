/*
Transform a list, possibly holding lists as elements into a `flat' list by replacing each list with its elements (recursively).

Example:
?- my_flatten([a, [b, [c, d], e]], X).
X = [a, b, c, d, e]

Hint: Use the predefined predicates is_list/1 and append/3
*/

my_flatten([], []).
my_flatten([Cab | Resto], NX) :- my_flatten(Resto, X), append(X, [Cab | Resto], NX).