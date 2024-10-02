clc, clear
close all

load("../Etapa1/VariablesEtapa1.mat");
load("VariablesEtapa2.mat");
addpath("../FuncionesMatlabMaterialAyuda");
%%%% PASO 2.1: SEGMENTACIÓN DEL COLOR ROJO FRESA

%{
R G B H S I Y U V L a b
1 2 3 4 5 6 7 8 9 0 1 2
%}

NOMBRES = 'RGBHSIYUVLab';
CLASIFICADORES = {'Mahalanobis' 'KNN' 'NN'};

%% Clasificador. Distancia Mahalanobis
% descriptores ->  RGB, Lab, mejor 3 caracs, mejor any caracs
descriptores = {[1 2 3], [10 11 12], mejor_vct_3caracs}; %, mejor_vct_caracs
N_DESC = length(descriptores);
NOMBRES_DESC = {1, N_DESC};
for i_desc = 1:N_DESC
    NOMBRES_DESC{i_desc} = NOMBRES(descriptores{i_desc});
end

tablaAcc = array2table(zeros(length(CLASIFICADORES), N_DESC), 'VariableNames', CLASIFICADORES, 'RowNames', NOMBRES_DESC);

tablaSe = tablaAcc;
tablaSp = tablaAcc;

for i_desc = 1:N_DESC
    XTrain = ValoresColores(:, descriptores{i_desc});
    YTrain = CodifValoresColores == 255;
    Centro = mean(XTrain(YTrain, :));
    Mcov = cov(XTrain(YTrain, :));
    
    NumValores = size(XTrain, 1);
    distancia = zeros(NumValores, 1);
    for j=1:NumValores
        X = XTrain(j, :);
        distancia(j, 1) = sqrt((X-Centro)*pinv(Mcov)*(X-Centro)');
    end
    
    % Distancias conjunto seguimiento
    conjuntoSeg = XTrain(YTrain, :);
    NumValoresConjuntoSeg = size(conjuntoSeg, 1);
    distanciasConjuntoSeg = zeros(NumValoresConjuntoSeg, 1);
    for j=1:NumValoresConjuntoSeg
        X = conjuntoSeg(j, :);
        distanciasConjuntoSeg(j, 1) = sqrt((X-Centro)*pinv(Mcov)*(X-Centro)');
    end
    umbralConRuido = max(distanciasConjuntoSeg);
    
    conjuntoFondo = XTrain(~YTrain, :);
    NumValoresConjuntoFondo = size(conjuntoFondo, 1);
    distanciasConjuntoFondo = zeros(NumValoresConjuntoFondo, 1);
    for j=1:NumValoresConjuntoFondo
        X = conjuntoFondo(j, :);
        distanciasConjuntoFondo(j, 1) = sqrt((X-Centro)*pinv(Mcov)*(X-Centro)');
    end
    umbralSinRuido = min(distanciasConjuntoFondo);
    
    umbrales = umbralSinRuido:0.1:umbralConRuido;
    n_umbrales = size(umbrales, 2);
    accuracies = zeros(1, n_umbrales);
    
    for u = 1:n_umbrales
        YPred_mahal = distancia < umbrales(u);
        accuracies(u) = sum(YPred_mahal == YTrain) / size(YTrain, 1);
    end

    tablaAcc{i_desc, 1} = max(accuracies);
end

%% REPRESENTAMOS
[~, posMaxAcc] = max(accuracies);

Umbral = umbrales(posMaxAcc);
YPred_mahal = distancia < Umbral;
datosMahal = XTrain(YPred_mahal, :);

figure, hold on;
% Centroide y  Rojo fresa
PoI = XTrain(YTrain, :);
plot3(Centro(1), Centro(2), Centro(3), 'ob'), hold on;
plot3(PoI(:, 1), PoI(:, 2), PoI(:, 3), '.r'), hold on;

% Otros
PoI = XTrain(~YTrain, :);
plot3(PoI(:, 1), PoI(:, 2), PoI(:, 3), '.k'), hold on;

plot3(datosMahal(:, 1), datosMahal(:, 2), datosMahal(:, 3), '+g'),
legend('Centroide', 'Rojo fresa', 'Otro'),
title(["[cols = RSL] Clasificador Distancia Mahal. umbral = " num2str(Umbral)]),
xlabel('R'), ylabel('S'), zlabel('L');

%% NEURONAL NETWORK

load("NN/RedNeuronal.mat");

XTrain = ValoresColores;

YPred_nn = logical(round(sim(net,XTrain')));

%% SVM
XTrain = ValoresColores;
YTrain = CodifValoresColores == 255;

svm = fitcsvm(XTrain, YTrain); 

YPred_svm = predict(svm, XTrain);
%% KNN

knn5 = fitcknn(XTrain, YTrain, 'NumNeighbors', 5);
YPred_knn5 = predict(knn5, XTrain);

% accuracy -> sum(YTrain == YPred)/size(YTrain, 1)