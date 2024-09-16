function [IEtiq, N] = my_etiquetar(Ib)

[nFilas, nCol] = size(Ib);
IEtiq = double(Ib);


%%% INICIALIZACION
ne = 1;
% Etiquetamos cada pixel en nivel alto con una etiqueta unica
for i = 1:nFilas
    for j = 1:nCol
        if IEtiq(i, j) ~= 0
            IEtiq(i, j) = ne;
            ne = ne + 1;
        end

    end
end

cambio = true;
while cambio
    cambio = false;
    %%% PASADA ARRIBA-ABAJO
    for i = 1:nFilas
        for j = 1:nCol
            if IEtiq(i, j) > 1
                % Consultamos las etiquetas de los vecinos
                vEtiq = getEtiquetasVecinos(IEtiq, i, j);
                etiqMin = min(vEtiq);
                if (IEtiq(i, j) ~= etiqMin)
                    IEtiq(i, j) = etiqMin;
                    cambio = true;
                end
                
            end
        end
    end
    
    %%% PASADA ABAJO-ARRIBA
    for i = nFilas:1
        for j = nCol:1
            if IEtiq(i, j) > 1
                % Consultamos las etiquetas de los vecinos
                vEtiq = getEtiquetasVecinos(IEtiq, i, j);
                etiqMin = min(vEtiq);
                if (IEtiq(i, j) ~= etiqMin)
                    IEtiq(i, j) = etiqMin;
                    cambio = true;
                end
                
            end
        end
    end
end


end

