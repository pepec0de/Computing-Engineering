clc, clear;
addpath('../../02_Funciones/');

%% CARGAMOS LOS  DATOS DE INTERES

load('DatosGenerados\espacio_ccas.mat');
load('..\..\conjunto_datos_test.mat');

XTest = XTest(:, espacioCcas);
%% CLASIFICADOR KNN CIRCULOS-TRIANGULOS VS CUADRADOS
k = 5;

YTest = funcion_knn(XTest, XoI, YoI, k);

%% Guardamos datos
save('DatosGenerados\knn_YTest.mat', 'YTest', 'k');