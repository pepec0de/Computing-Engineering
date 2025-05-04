/*
    primos(A, B, R).
*/

esprimo(N) :- N > 1, N1 is N - 1, calcula_primo(N, N1).

calcula_primo(_, 1).
calcula_primo(N, N1) :- N1 > 1, 0 =\= mod(N, N1), N2 is N1 - 1, calcula_primo(N, N2).

primos(N, N, []).
primos(N, N, [N]) :- esprimo(N).
primos(No, Nf, [No | R]) :- No < Nf, esprimo(No), No1 is No + 1, primos(No1, Nf, R).
primos(No, Nf, R) :-        No < Nf, not(esprimo(No)), No1 is No + 1, primos(No1, Nf, R).