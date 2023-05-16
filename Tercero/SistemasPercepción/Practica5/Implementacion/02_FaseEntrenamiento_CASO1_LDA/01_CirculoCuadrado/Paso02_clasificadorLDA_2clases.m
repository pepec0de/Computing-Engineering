clc, clear;
addpath('../../02_Funciones/');
%addpath('../../01_GeneracionDatos/Funciones/');

%% CARGAMOS LOS  DATOS DE INTERES

load('DatosGenerados\espacio_ccas_circ_cuad.mat');

%% CLASIFICADOR LDA

[d1, d2, d12, coeficientes_d12] = funcion_calcula_hiperplanoLDA_2Clases_2_3_Dim(XoI, YoI);

%% Representacion

x1 = linspace(min(XoI(1, :)), max(XoI(1, :)), size(XoI, 1));
x2 = x1';
A = coeficientes_d12(1);
B = coeficientes_d12(2);
C = coeficientes_d12(3);
D = coeficientes_d12(4);

x3 = -(A*x1 + B*x2 + D)/C;

funcion_representa_datos(XoI, YoI, 1:dim, datosProblemaOI);
hold on;
surf(x1,x2, x3), legend([datosProblemaOI.clases "d12 LDA"]);