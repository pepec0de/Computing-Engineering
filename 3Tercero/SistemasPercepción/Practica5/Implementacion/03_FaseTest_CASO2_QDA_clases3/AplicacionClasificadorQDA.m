clc, clear;

addpath('..\02_Funciones\');
load('..\conjunto_datos_test.mat');
load('..\01_GeneracionDatos\DatosGenerados\conjunto_datos_estandarizados.mat');
load('..\02_FaseEntrenamiento_CASO2_QDA_3clases\DatosGenerados\QDA_circ_cuad_trian.mat');

%% CARGAMOS LOS VALORES DE ENTRENAMIENTO PARA CALCULAR QDA

numMuestras = size(XTest, 1);
XTest = XTest(:, espacioCcas);
codifClases = unique(datosProblemaOI.codificacion);
[YQDA, d] = funcion_aplica_QDA(XTest, vectorMedias, matricesCovarianzas,...
    probabilidadPriori, codifClases);

color = [0 255 0];
for i = 1:numMuestras
    YiOI = YQDA(i);
    posClaseOI = find(ismember(codifClases, YiOI));

    claseOI = datosProblemaOI.clases{posClaseOI};
    reconocimiento = ['Reconocimiento objeto: ' claseOI];

    Ib = IEtiq == i;

    figure, funcion_visualiza(I, Ib, color), title(reconocimiento);
end