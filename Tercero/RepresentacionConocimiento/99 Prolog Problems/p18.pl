/*
Extract a slice from a list.
    Given two indices, I and K, the slice is the list containing the elements 
    between the I'th and K'th element of the original list (both limits included).
    Start counting the elements with 1.

    Example:
    ?- slice([a,b,c,d,e,f,g,h,i,k], 3, 7, X).
    X = [c,d,e,f,g]
*/

slice(_, 1, 0, []).
slice([_ | Lista], I, J, Result) :- I > 1, I1 is I - 1, J1 is J - 1, slice(Lista, I1, J1, Result).
slice([H | Lista], 1, J, [H | Result]) :- J1 is J - 1, slice(Lista, 1, J1, Result).