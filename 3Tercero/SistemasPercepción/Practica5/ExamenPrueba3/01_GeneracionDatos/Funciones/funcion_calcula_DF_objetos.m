function X = funcion_calcula_DF_objetos(IEtiq, N)
%FUNCION_CALCULA_DF_OBJETOS Summary of this function goes here
%   Detailed explanation goes here

X = zeros(N, 10);
for i = 1:N
    Iobj = IEtiq == i;
    X(i, :) = Funcion_Calcula_DF(Iobj, 10)';
end

end

