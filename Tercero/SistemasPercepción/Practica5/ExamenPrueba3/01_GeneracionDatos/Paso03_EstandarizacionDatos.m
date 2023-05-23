clc, clear;

load('DatosGenerados/conjunto_datos.mat');
load('DatosGenerados/datosProblema.mat');
%% Matriz con media y desviacion tipica de cada descriptor

medias_desviaciones = [mean(X); std(X)];

% Asignamos manualmente los valores del descriptor de Euler para que no
% afecte
[numMuestras, numDescriptores] = size(X);
medias_desviaciones(:, numDescriptores) = [0; 1];

%% Estandarizacion de X

Z = zeros(size(X));
for i = 1:numDescriptores
    mu = medias_desviaciones(1, i);
    sigma = medias_desviaciones(2, i);
    Z(:, i) = (X(:, i) - mu) / sigma;
end

%% Guardamos datos

save('DatosGenerados\conjunto_datos_estandarizados.mat', 'Z', 'Y');
save('DatosGenerados\medias_desviaciones_estandarizacion.mat', 'medias_desviaciones');

%% Seleccionar el conjunto de descriptores con mejor valorJ
%load('DatosGenerados\conjunto_datos_estandarizados.mat');
[espacioCcas, valorJ] = funcion_selecciona_vector_ccas(Z, Y, 3);
funcion_representa_datos(Z, Y, espacioCcas, datosProblema);

