function IbFilt = filtrar_objetos(IEtiq, N, numPix)
% Da el resultadocon los objetos de una imagen de area igual o mayor a la
% indicada
IbFilt = zeros(size(IEtiq));
Areas = calcula_areas(IEtiq, N);

for i = 1:length(Areas)
    if Areas(i) >= numPix
        IbFilt = IbFilt + (IEtiq == i);
    end
end
end

