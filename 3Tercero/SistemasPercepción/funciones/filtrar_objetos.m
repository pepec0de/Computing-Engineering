function IbFilt = filtrar_objetos(Ib, numPix)

IbFilt = zeros(size(Ib));
[IEtiq, N] = my_etiquetar(Ib);
Areas = calcula_areas(IEtiq, N);
[~, SZ] = size(Areas);

for i = 1:SZ
    if Areas(i) >= numPix
        IbFilt = IbFilt + (IEtiq == N(i));
    end
end
end

