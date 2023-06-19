function [IEtiq, N] = funcion_etiquetar(Ib)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

[nFilas, nCol] = size(Ib);
IEtiq = double(Ib);

%%% INICIALIZACION
N = 1;
% Etiquetamos cada pixel en nivel alto con una etiqueta unica
for i = 1:nFilas
    for j = 1:nCol
        if IEtiq(i, j) == 1
            IEtiq(i, j) = N;
            N = N + 1;
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
                % Consultamos las etiquetas de los vecinos (conectividad 4)
                vecinos = conectividad4(IEtiq, i, j);
                etiqMin = min(vecinos);
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
                % Consultamos las etiquetas de los vecinos (conectividad 4)
                vecinos = conectividad4(IEtiq, i, j);
                etiqMin = min(vecinos);
                if (IEtiq(i, j) ~= etiqMin)
                    IEtiq(i, j) = etiqMin;
                    cambio = true;
                end
                
            end
        end
    end
end

% Limpiamos arrays
etiquetas = unique(IEtiq);
etiquetas(etiquetas == 0) = [];
N = length(etiquetas);
for i = 1:N
    IEtiq(IEtiq == etiquetas(i)) = i;
end

end

function vecinos = conectividad4(IEtiq, x, y)

vecinos = zeros(4, 1);
c = 1;
[nFil, nCol] = size(IEtiq);

for i = x - 1 : 2 : x + 1
    % Check bounds
    if i >= 1 && i <= nFil
        % Check etiqueta is not 0
        if IEtiq(i, y) ~= 0
            vecinos(c) = IEtiq(i, y);
            c = c + 1;
        end
    end
end

for i = y - 1 : 2 : y + 1
    % Check bounds
    if i >= 1 && i <= nCol
        % Check etiqueta is not 0
        if IEtiq(x, i) ~= 0
            vecinos(c) = IEtiq(x, i);
            c = c + 1;
        end
    end
end
vecinos(vecinos == 0) = [];

end
