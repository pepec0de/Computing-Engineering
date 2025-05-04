clc, clear;
addpath('..\..\02_Funciones\');

%% CARGAMOS LOS DATOS DE INTERES

load('DatosGenerados\espacio_ccas_circ_trian.mat');
load('..\..\conjunto_datos_test.mat');
load('..\01_knn_CirculosTringulos_Cuadrados\DatosGenerados\knn_YTest.mat');

circ_trian = 8;
XTestOI = XTest(YTest == circ_trian, espacioCcas);
%% Valores clasificador LDA

[vectorMedias, matrizCovarianza, probabilidadPriori] = funcion_ajusta_LDA(XoI, YoI);

%% Clasificador LDA

[YLDA, d] = funcion_aplica_LDA(XTestOI, vectorMedias, matrizCovarianza,...
    probabilidadPriori, unique(datosProblemaOI.codificacion));

YTest(YTest == circ_trian, :) = YLDA(:);