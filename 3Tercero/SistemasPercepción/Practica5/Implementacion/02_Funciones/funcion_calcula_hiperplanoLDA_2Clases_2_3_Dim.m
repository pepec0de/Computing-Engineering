function [d1, d2, d12, coeficientes_d12] = funcion_calcula_hiperplanoLDA_2Clases_2_3_Dim(X, Y)
%FUNCION_CALCULA_HIPERPLANOLDA_2CLASES_2_3_DIM Summary of this function goes here
%   Detailed explanation goes here

x1 = sym('x1', 'real');
x2 = sym('x2', 'real');

Xsym = [x1; x2];
numAtributos = size(X, 2);
if numAtributos == 3
    x3 = sym('x3', 'real');
    Xsym = [Xsym; x3];
end

[M, MCov, PPriori] = funcion_ajusta_LDA(X, Y);

M1 = M(1, :)';
Pi1 = PPriori(1);
M2 = M(2, :)';
Pi2 = PPriori(2);


d1 = expand( -0.5*(Xsym - M1)' * inv(MCov) * (Xsym - M1 ) + log(Pi1) );
d2 = expand( -0.5 * (Xsym - M2)' * inv(MCov) * (Xsym- M2) + log(Pi2) );


d12 = d1 - d2;

if numAtributos == 2
    x1 = 0; x2 = 0; C = eval(d12);
    x1 = 0; x2 = 1; B = eval(d12);
    x1 = 1; x2 = 0; A = eval(d12);
    coeficientes_d12 = [A, B, C];
else
    x1 = 0; x2 = 0; x3 = 0; D = eval(d12);
    x1 = 0; x2 = 0; x3 = 1; C = eval(d12);
    x1 = 0; x2 = 1; x3 = 0; B = eval(d12);
    x1 = 1; x2 = 0; x3 = 0; A = eval(d12);		
    coeficientes_d12 = [A, B, C, D];
end
