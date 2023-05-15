clc, clear;

addpath('Funciones01');
addpath('Funciones02');
load('DatosGenerados\conjunto_datos_estandarizados.mat');
load('DatosGenerados\datosProblema.mat');
%% Vector de valoresJ de todas las combinaciones de descriptores

[numMuestras, numDescriptores] = size(Z);

combinaciones = nchoosek(1:numDescriptores, 3);
nCombis = length(combinaciones);

valoresJ = zeros(nCombis, 1);

for i = 1:nCombis
    XoI = Z(:, combinaciones(i, :));
    valoresJ(i) = indiceJ(XoI, Y);
end

[valoresJOrd, idxs] = sort(valoresJ, 'descend');

%% Seleccionar el conjunto de descriptores

mejorCombi = combinaciones(idxs(1), :);

%funcion_representa_datos(Z, Y, mejorCombi, datosProblema);