clc, clear;

addpath('..\02_Funciones\');
load('..\conjunto_datos_test.mat');
load('..\01_GeneracionDatos\DatosGenerados\conjunto_datos_estandarizados.mat');
load('..\02_FaseEntrenamiento_CASO3_knn_3clases\DatosGenerados\espacio_ccas.mat');

%% CLASIFICADOR KNN

XTest = XTest(:, espacioCcas);
k = 5;
YTest = funcion_knn(XTest, XoI, YoI, k);