function hu = funcion_calcula_Hu_objetos(IEtiq, N)
%FUNCION_CALCULA_HU_OBJETOS Summary of this function goes here
%   Detailed explanation goes here

hu = zeros(N, 7);
for i = 1:N
    Ibin = IEtiq == i;
    % se hace la traspuesta ya que es vector columna
    huObj = Funcion_Calcula_Hu(Ibin)';
    hu(i, :) = huObj;
end
end

