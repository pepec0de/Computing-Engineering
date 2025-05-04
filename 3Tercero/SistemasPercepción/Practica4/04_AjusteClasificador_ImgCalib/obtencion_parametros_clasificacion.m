clc, clear;
close all
addpath("..\01_GeneracionMaterial\MaterialGenerado\");
addpath("Funciones\");
load("..\03_DisegnoClasificador\VariablesGeneradas\datos_multiples_esferas.mat");
CENTRO = 1:3;
CONRUIDO = 4;
SINRUIDO = 5;
COMPROMISO = 6;
RADIO = COMPROMISO;
K = size(datos_multiples_esferas, 1);

%% 3.2.1

% - Procedimiento de ajuste de umbrales de distancia: para cada una de las imágenes de 
% calibración y varios valores posibles de umbrales de distancia

load("ImagenesEntrenamiento_Calibracion.mat");
Imagenes = ImagenesEntrenamiento_Calibracion;
clear ImagenesEntrenamiento_Calibracion;

n = size(Imagenes, 4);

for i = 1:n
    I = Imagenes(:, :, :, i);

    % RADIO de COMPROMISO
    Ib = calcula_deteccion_multiples_esferas_imagen(I, datos_multiples_esferas, COMPROMISO);

    figure, funcion_visualiza(I, Ib, [255, 0, 0]);
end

%% 3.2.2

% Procedimiento de ajuste umbral de conectividad: para cada una de las imágenes de 
% calibración y varios valores posibles de umbral de conectividad
for i = 1:size(Imagenes, 4)
    I = Imagenes(:, :, :, i);
    figure, imshow(I), title(i);
end

%% Hacemos la seccion del roipoly
I = Imagenes(:, :, :, 1); % Imagen con el objeto más pequeño
Ib = roipoly(I);
numPixelesObj = sum(Ib(:));

umbralConectividad = round([0.25 0.50 0.75]*numPixelesObj);

%% Representacion de los umbrales
for i = 1:n
    figure
    I = Imagenes(:, :, :, i);

    subplot(2, 2, 1), imshow(I), title(i);

    for j = 1:length(umbralConectividad)
        Ib = calcula_deteccion_multiples_esferas_imagen(I, datos_multiples_esferas, COMPROMISO);

        IEtiqFilt = bwareaopen(Ib, umbralConectividad(j));

        subplot(2, 2, j+1), imshow(IEtiqFilt)

        title(['Elimina ' num2str(umbralConectividad(j)/numPixelesObj, "%4.2f")]);
    end

end

%% Tercer umbral seleccionado

umbral_conectividad = umbralConectividad(3);
save("VariablesGeneradas\parametros_clasificador.mat", 'umbral_conectividad', 'datos_multiples_esferas', 'RADIO');

