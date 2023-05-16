clc, clear;
addpath('../../02_FaseEntrenamiento/Funciones/');
%addpath('../../01_GeneracionDatos/Funciones/');

%% CARGAMOS LOS  DATOS DE INTERES

load('DatosGenerados\espacio_ccas_circ_cuad.mat');

%% CLASIFICADOR LDA

[d1, d2, d12, coeficientes_d12] = funcion_calcula_hiperplanoLDA_2Clases_2_3_Dim(XoI, YoI);

%% Representacion

funcion_representa_datos(XoI, YoI, 1:dim, datosProblemaOI);
hold on;
x1 = linspace(min(XoI(1, :)), max(XoI(1, :)), size(XoI, 1));
x2 = x1';
x3 = (-coeficientes_d12(1)*x1 -coeficientes_d12(2)*x2 - coeficientes_d12(4))/coeficientes_d12(3);

surf(x1,x2, x3);