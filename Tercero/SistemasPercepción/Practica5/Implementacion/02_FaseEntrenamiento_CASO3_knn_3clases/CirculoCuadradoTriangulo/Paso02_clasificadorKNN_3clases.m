clc, clear;
addpath('../../02_Funciones/');
%addpath('../../01_GeneracionDatos/Funciones/');

%% CARGAMOS LOS  DATOS DE INTERES

load('DatosGenerados\espacio_ccas_circ_cuad.mat');
load('DatosGenerados\conjunto_datos_test.mat');

XTest = XTest(:, espacioCcas);
%% CLASIFICADOR KNN
k = 5;

YTest = funcion_knn(XTest, XoI, YoI, k);
