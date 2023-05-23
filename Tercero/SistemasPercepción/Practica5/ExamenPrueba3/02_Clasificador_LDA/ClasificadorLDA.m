%% CLASIFICADOR A VS D

clc, clear;

load('../01_GeneracionDatos/DatosGenerados/datosProblema.mat');
load('../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat');
addpath('../02_Funciones/');
addpath('../01_GeneracionDatos/Funciones/');

%% GENERACION XOI

X = Z;

[numDatos, numDescriptores] = size(X);
codifClases = unique(Y);
clasesOI = [1, 4];
codifClasesOI = codifClases(clasesOI);

filasOI = false(numDatos, 1);
for i = 1:length(clasesOI)
    filasOI = or(filasOI, Y == codifClasesOI(i));
end

XoI = X(filasOI, 1:numDescriptores-1);
YoI = Y(filasOI);
dim = 3;
[espacioCcas, JespacioCcas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

XoI = XoI(:, espacioCcas);

%% Definicion datosProblema
datosProblemaOI.descriptores = datosProblema.descriptores(espacioCcas);
datosProblemaOI.clases = datosProblema.clases(clasesOI);
datosProblemaOI.simbolos = datosProblema.simbolos(clasesOI);
datosProblemaOI.codificacion = datosProblema.codificacion(clasesOI);

funcion_representa_datos(XoI, YoI, 1:dim, datosProblemaOI);

%% Clasificador LDA
[d1, d2, d12, coeficientes_d12] = funcion_calcula_hiperplanoLDA_2Clases_2_3_Dim(XoI, YoI);


%% Guardamos los datos
save('DatosGenerados\LDA_A_D.mat', 'd12', 'coeficientes_d12',...
    'espacioCcas', 'XoI', 'YoI', 'datosProblemaOI');