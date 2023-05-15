clc, clear;

addpath('Funciones');
load('DatosGenerados\conjunto_datos_estandarizados.mat');
load('DatosGenerados\datosProblema.mat');

%% Seleccionar el conjunto de descriptores con mejor valorJ

[espacioCcas, valorJ] = funcion_selecciona_vector_ccas(Z, Y, 3);
funcion_representa_datos(Z, Y, espacioCcas, datosProblema);