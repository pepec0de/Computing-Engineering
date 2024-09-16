clc, clear;
addpath('01_GeneracionDatos\Funciones\')
load('01_GeneracionDatos\DatosGenerados\medias_desviaciones_estandarizacion.mat');
addpath('Imagenes\Test\')


%% Inicializamos los conjuntos X e Y

% 1.1- BINARIZAR CON METODOLOGÍA DE SELECCIÓN AUTOMÁTICA DE UMBRAL
% Genera: Ibin. Hay que tener programadas las funciones de elección de
% umbral vistas en el tema anterior: 
% funcion_min_entre_max, funcion_isodata, funcion_otsu.

% TODO: Meterle un bucle para todo el fichero
I = imread('Test1.JPG');
umbral = graythresh(I)*255; 
% umbral = funcion_otsu(imhist(I));

% Los objetos a seleccionar son más oscu    ros que el fondo
Ibin = I <= umbral;

% Más claros que el fondo
%Ibin = I >= umbral;

% 1.2.- ELIMINAR POSIBLES COMPONENTES CONECTADAS RUIDOSAS:
% Para ello, se debe programar la siguiente funcion:
% IbinFilt = funcion_elimina_regiones_ruidosas(Ibin);

IbinFilt = Ibin;
%IbinFilt = funcion_elimina_regiones_ruidosas(Ibin);
%figure, imshow(IbinFilt);


% 1.3.- ETIQUETAR.
% Genera matriz etiquetada Ietiq y número N de agrupaciones conexas

[IEtiq, N] = bwlabel(IbinFilt);

% 1.4.- CALCULAR TODOS LOS DESCRIPORES DE CADA AGRUPACIÓN CONEXA
% Genera Ximagen - matriz de N filas y 23 columnas (los 23 descriptores
% generados en el orden indicado en la práctica)
% XImagen = funcion_calcula_descriptores_imagen(Ietiq,N);

XTest = funcion_calcula_descriptores_imagen(IEtiq, N);

%% Estandarizacion de X

Z = zeros(size(XTest));

numDescriptores = size(XTest, 2);
for i = 1:numDescriptores
    mu = medias_desviaciones(1, i);
    sigma = medias_desviaciones(2, i);
    Z(:, i) = (XTest(:, i) - mu) / sigma;
end

XTest = Z;

%% Guardado
save('conjunto_datos_test.mat', 'XTest', 'I', 'IEtiq');
