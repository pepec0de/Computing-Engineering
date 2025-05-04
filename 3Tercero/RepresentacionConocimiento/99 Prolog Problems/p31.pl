/*
Determine whether a given integer number is prime.
    Example:
    ?- is_prime(7).
    Yes
*/

is_prime(1).
is_prime(N) :- checkPrime(N, 2).

checkPrime(N, N).
checkPrime(N, Cont) :- Cont < N, N mod Cont =\= 0, Cont2 is Cont + 1, checkPrime(N, Cont2).
