clc, clear;

load('DatosGenerados\espacio_ccas_CEFG.mat');
addpath('..\02_Funciones')
%% CLASIFICADOR QDA


[vectorMedias, matricesCovarianzas, probabilidadPriori] = funcion_ajusta_QDA(XoI, YoI);

[YQDA, d] = funcion_aplica_QDA(XoI, vectorMedias, matricesCovarianzas, ...
    probabilidadPriori, datosProblemaOI.codificacion);
