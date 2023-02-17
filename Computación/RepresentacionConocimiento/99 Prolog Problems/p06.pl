/*
    Find out whether a list is a palindrome.
    A palindrome can be read forward or backward; e.g. [x,a,m,a,x].
    palindrome(+Lista)
*/

palindrome([_]).
palindrome([Cab | Resto]) :- last(Resto, Cab), eliminarUltimo(Resto, NL), palindrome(NL).

eliminarUltimo([_], []).
%eliminarUltimo([Cab | Resto], NX) :- eliminarUltimo(Resto, X), append([Cab], X, NX).
eliminarUltimo([Cab | Resto], [Cab | NX]) :- eliminarUltimo(Resto, NX).