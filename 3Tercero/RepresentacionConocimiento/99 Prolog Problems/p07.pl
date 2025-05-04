/*
Transform a list, possibly holding lists as elements into a `flat' list by replacing each list with its elements (recursively).

Example:
?- my_flatten([a, [b, [c, d], e]], X).
X = [a, b, c, d, e]

Hint: Use the predefined predicates is_list/1 and append/3
*/

my_flatten([], []).
my_flatten([Cab | Resto], [Cab | X]) :- not(is_list(Cab)), my_flatten(Resto, X).
my_flatten([Cab | Resto], R) :- is_list(Cab), my_flatten(Cab, L1), my_flatten(Resto, L2), append(L1, L2, R).