clc, clear;
addpath('../../02_FaseEntrenamiento/Funciones/');

%% CARGAMOS LOS  DATOS DE INTERES
nombreFichero = 'espacio_ccas_circ_cuad.mat';
load(nombresFichero);

clear nombreFichero

%% CLASIFICADOR LDA

[d1, d2, d12, coeficientes_d12] = funcion_calcula_hiperplanoLDA_2Clases_2_3_Dim(XoI, YoI);