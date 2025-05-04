function [idx, centroides] = funcion_kmeans(X, k)
%FUNCION_KMEANS [idx, centroides] = funcion_kmeans(X, k)
%   X: matriz de n filas y p columnas; cada fila almacena los valores de
%       los atributos de una determinada muestra (cada atributo se corresponde con una columna)

%   K: número de agrupaciones o clases en las que queremos dividir los datos.

%   idx: vector columna con tantas filas como observaciones haya en X (matriz nx1). Cada fila 
%       contiene la etiqueta asignada a la clase o agrupación a la que se asocia la muestra de 
%       la fila correspondiente de X. Los valores de estas etiquetas para identificar la clase 
%       son de 1 a K (números enteros).

%   centroides: matriz de K filas (número de agrupaciones) y p columnas (número de atributos que
%       describen las muestras). Contiene las coordenadas del centroide (en el espacio de observaciones
%       definido por los atributos) de cada agrupación final que generada por el algoritmo.

% Inicializacion de variables
[n, ~] = size(X);
%idx = zeros(N, 1);

% Obtenemos k posiciones aleatorias 1-N
randIdx = my_randsample(n, k);
% Obtenemos nuestra muestra aleatoria de centroides
centroides = X(randIdx, :);

idx = funcion_calcula_agrupacion(X, centroides);
cambio = true;
while cambio
    centroides = funcion_calcula_centroides(X, idx);
    
    newIdx = funcion_calcula_agrupacion(X, centroides);

    if my_compara_matrices(idx, newIdx)
        cambio = false;
    else
        idx = newIdx;
    end
end

end