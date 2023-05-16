clc, clear;
addpath('../../02_Funciones/');
%addpath('../../01_GeneracionDatos/Funciones/');

%% CARGAMOS LOS  DATOS DE INTERES

load('DatosGenerados\espacio_ccas_circ_cuad.mat');

%% CLASIFICADOR QDA


[vectorMedias, matricesCovarianzas, probabilidadPriori] = funcion_ajusta_QDA(XoI, YoI);

[YQDA, d] = funcion_aplica_QDA(XoI, vectorMedias, matricesCovarianzas,...
    probabilidadPriori, datosProblemaOI.codificacion);