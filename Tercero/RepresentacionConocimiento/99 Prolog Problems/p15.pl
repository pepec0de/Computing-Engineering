/*
Duplicate the elements of a list a given number of times.
    Example:
    ?- dupli([a,b,c],3,X).
    X = [a,a,a,b,b,b,c,c,c]

*/

dupli([H | Resto])

addntimes(N, E, Lista) :- 