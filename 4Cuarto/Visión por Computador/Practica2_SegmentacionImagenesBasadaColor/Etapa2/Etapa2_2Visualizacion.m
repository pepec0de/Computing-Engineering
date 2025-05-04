clc, clear
close all

load("../Etapa1/VariablesEtapa1.mat");
load("VariablesEtapa2.mat");
load("NN/RedNeuronal.mat");
addpath("../Etapa1/Material_Imagenes/02_MuestrasRojo/");

clear mejor_J mejor_vct_caracs mejor_J_3caracs mejor_vct_3caracs ...
    N_DESC

% Visualización

%% Mahalanobis

XTrain = ValoresColores(:, descriptores{1});
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

% Sacamos el umbral con ruido
conjuntoSeg = XTrain(YTrain, :);
NumValoresConjuntoSeg = size(conjuntoSeg, 1);
distanciasConjuntoSeg = zeros(NumValoresConjuntoSeg, 1);
for j=1:NumValoresConjuntoSeg
    X = conjuntoSeg(j, :);
    distanciasConjuntoSeg(j, 1) = sqrt((X-Centro)*pinv(Mcov)*(X-Centro)');
end
umbralConRuido = max(distanciasConjuntoSeg);

% Sacamos el umbral sin ruido
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
accs = zeros(1, n_umbrales);

for u = 1:n_umbrales
    YPred_mahal = distancia < umbrales(u);
    %accs(u) = sum(YPred_mahal == YTrain) / size(YTrain, 1);
    [~, ~, Acc, ~] = funcion_metricas(YPred_mahal, YTrain);
    accs(u) = Acc;
end

[~, posMaxAcc] = max(accs);
Umbral = umbrales(posMaxAcc);

knn5 = fitcknn(XTrain, YTrain, 'NumNeighbors', 5);
svm = fitcsvm(XTrain, YTrain);

for img = 1:2
    I = imread(['EvRojo' int2str(img) '.tif']);
    IGold = imread(['EvRojo' int2str(img) '_Gold.tif']);
    I = imresize(I, 0.5);
    Ibin_mahal = false(size(I, 1:2));

    for i = 1:size(I, 1)
        for j = 1:size(I, 2)
            Pixel = double(I(i, j, :));
            Pixel = Pixel(:)'/255;
            distanciaMahal = sqrt((Pixel-Centro)*pinv(Mcov)*(Pixel-Centro)');
            Ibin_mahal(i, j) = distanciaMahal < Umbral;
        end
    end
    R = double(I(:, :, 1))/255;
    G = double(I(:, :, 2))/255;
    B = double(I(:, :, 3))/255;
    XTest = [R(:), G(:), B(:)];

    YTest_knn = predict(knn5, XTest);
    YTest_nn = sim(NNS{1}, XTest')' > 0.50001;
    YTest_svm = predict(svm, XTest);
    
    [N, M] = size(R, 1:2);
    Ibin_knn = funcion_y_a_mat(YTest_knn, N, M);
    Ibin_nn = funcion_y_a_mat(YTest_nn, N, M);
    Ibin_svm = funcion_y_a_mat(YTest_svm, N, M);

    figure, subplot(5, 2, 1);
    imshow(I), title('Original'), hold on;
    subplot(5, 2, 2);
    imshow(IGold), title('Original'), hold on;

    subplot(5, 2, 3);
    funcion_visualiza(I, Ibin_mahal, [255 255 0], true); title('Mahalanobis'), hold on;
    subplot(5, 2, 4);
    imshow(Ibin_mahal), title('Mahalanobis'), hold on;
    
    subplot(5, 2, 5);
    funcion_visualiza(I, Ibin_knn, [255 255 0], true); title('KNN'), hold on;
    subplot(5, 2, 6);
    imshow(Ibin_knn), title('KNN'), hold on;
    
    subplot(5, 2, 7);
    funcion_visualiza(I, Ibin_nn, [255 255 0], true); title('NN'), hold on;
    subplot(5, 2, 8);
    imshow(Ibin_nn), title('NN'), hold on;

    subplot(5, 2, 9);
    funcion_visualiza(I, Ibin_svm, [255 255 0], true); title('SVM'), hold on;
    subplot(5, 2, 10);
    imshow(Ibin_svm), title('SVM'), hold on;

end