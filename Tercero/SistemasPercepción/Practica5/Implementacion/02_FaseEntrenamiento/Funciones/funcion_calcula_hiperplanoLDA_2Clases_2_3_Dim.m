function [d1, d2, d12, coeficientes_d12] = funcion_calcula_hiperplanoLDA_2Clases_2_3_Dim(X, Y)
%FUNCION_CALCULA_HIPERPLANOLDA_2CLASES_2_3_DIM Summary of this function goes here
%   Detailed explanation goes here

datos = X;

X = [x1; x2];
numAtributos = size(datos, 2);
if numAtributos == 3
    x3 = sym('x3', 'real');
    X = [X; x3];
end

M1 = M(1, :)';
d1 = expand( -0.5*(X - M1));