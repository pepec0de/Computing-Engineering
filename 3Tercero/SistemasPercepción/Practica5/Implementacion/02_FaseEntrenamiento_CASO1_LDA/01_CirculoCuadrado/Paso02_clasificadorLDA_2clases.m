clc, clear;
addpath('../../02_Funciones/');
%addpath('../../01_GeneracionDatos/Funciones/');

%% CARGAMOS LOS  DATOS DE INTERES

load('DatosGenerados\espacio_ccas_circ_cuad.mat');

%% CLASIFICADOR LDA

[d1, d2, d12, coeficientes_d12] = funcion_calcula_hiperplanoLDA_2Clases_2_3_Dim(XoI, YoI);

%% Representacion

funcion_representa_muestras_clasificacion_binaria_frontera(XoI, YoI, datosProblemaOI, coeficientes_d12);


%% Guardamos los datos
save('DatosGenerados\LDA_circ_cuad.mat', 'd12', 'coeficientes_d12',...
    'espacioCcas', 'XoI', 'YoI')