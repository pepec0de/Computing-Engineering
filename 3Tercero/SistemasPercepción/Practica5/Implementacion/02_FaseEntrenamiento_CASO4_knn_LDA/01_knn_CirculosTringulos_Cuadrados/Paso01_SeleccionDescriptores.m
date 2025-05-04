%% CIRCULOS-TRIANGULOS VS CUADRADOS

clc, clear;
% Carga de datos

load('../../01_GeneracionDatos/DatosGenerados/datosProblema.mat');
load('../../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat');
addpath('../../02_Funciones/');
addpath('../../01_GeneracionDatos/Funciones/');

%% GENERACION XOI

X = Z;
clear Z;

[numDatos, numDescriptores] = size(X);
codifClases = unique(Y);

circ_trian = codifClases(1);
triangulo = codifClases(3);

clasesOI = [1, 2];

XoI = X(:, 1:numDescriptores-1);
YoI = Y;
YoI(YoI == triangulo) = circ_trian;
% 4 DESCRIPTORES
dim = 4;
[espacioCcas, JespacioCcas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

XoI = XoI(:, espacioCcas);

%% Definicion datosProblema
datosProblemaOI.descriptores = datosProblema.descriptores(espacioCcas);
datosProblemaOI.clases = datosProblema.clases(clasesOI);
datosProblemaOI.simbolos = datosProblema.simbolos(clasesOI);
datosProblemaOI.codificacion = datosProblema.codificacion(clasesOI);

%% Guardamos los datos

save('DatosGenerados\espacio_ccas.mat', 'espacioCcas', 'JespacioCcas', 'XoI', 'YoI', 'dim', 'datosProblemaOI');