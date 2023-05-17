clc, clear;

addpath('..\02_Funciones\');
load('DatosGenerados\conjunto_datos_test.mat');

%% CARGAMOS LOS VALORES DE ENTRENAMIENTO PARA CALCULAR QDA
load('..\02_FaseEntrenamiento_CASO2_QDA\CirculoCuadradoTriangulo\DatosGenerados\QDA_circ_cuad_trian.mat');

XTest = XTest(:, espacioCcas);
codifClases = unique(datosProblemaOI.codificacion);
[YQDA, d] = funcion_aplica_QDA(XTest, vectorMedias, matricesCovarianzas,...
    probabilidadPriori, codifClases);

color = [0 255 0];
for i = 1:numobjetos
    YiOI = YQDA(i);
    posClaseOI = find(ismember(codifClases, YiOI));

    claseOI = datosProblemaOI.clases{posClaseOI};
    reconocimiento = ['Reconocimeinto objeto: ' claseOI];

    Ib = IEtiq == i;

    figure, funcion_visualiza(I, Ib, color), title(reconocimiento);
end