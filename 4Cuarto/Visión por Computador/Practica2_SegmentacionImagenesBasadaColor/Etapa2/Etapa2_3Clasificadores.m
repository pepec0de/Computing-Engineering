clc, clear
close all

load("../Etapa1/VariablesEtapa1.mat");
load("VariablesEtapa2.mat");
load("NN/RedNeuronal.mat");
%%%% PASO 2.1: SEGMENTACIÓN DEL COLOR ROJO FRESA

%{
R G B H S I Y U V L a b
1 2 3 4 5 6 7 8 9 0 1 2
%}

% Acierto = Número de pixeles correctamente detectados (rojo fresa + fondo) 
%           ////////////////////////////////////////// 
%           Número total de píxeles de la imagen.
tablaAcc = array2table(zeros(N_DESC, length(CLASIFICADORES)), 'VariableNames', CLASIFICADORES, 'RowNames', NOMBRES_DESC);

% Sensibilidad = Número de pixeles rojo fresa correctamente detectados
%                /////////////////////////////////////////////////////
%               Número total de píxeles rojo fresa presentes en la imagen.
tablaSe = tablaAcc;

% Especificidad = Número de píxeles de fondo correctamente detectados
%                 ///////////////////////////////////////////////////
%                 Número total de píxeles de fondo.
%
tablaSp = tablaAcc;

%% Clasificador. Distancia Mahalanobis

YTrain = CodifValoresColores == 255;

for i_desc = 1:N_DESC-1
    XTrain = ValoresColores(:, descriptores{i_desc});
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
    accs = zeros(1, n_umbrales);
    ses = zeros(1, n_umbrales);
    sps = zeros(1, n_umbrales);

    for u = 1:n_umbrales
        YPred_mahal = distancia < umbrales(u);
        %accs(u) = sum(YPred_mahal == YTrain) / size(YTrain, 1);
        [Se, Sp, Acc, ~] = funcion_metricas(YPred_mahal, YTrain);
        accs(u) = Acc;
        sps(u) = Sp;
        ses(u) = Se;
    end
    
    [Acc, posMaxAcc] = max(accs);

    tablaAcc{i_desc, 1} = Acc;
    % DUDA -> Usamos la misma posicion de posMaxAcc?
    tablaSp{i_desc, 1} = sps(posMaxAcc);
    tablaSe{i_desc, 1} = ses(posMaxAcc);
end

%% KNN

% DUDA como seleccionar k

YTrain = CodifValoresColores == 255;

for i_desc = 1:N_DESC
    XTrain = ValoresColores(:, descriptores{i_desc});
    knn5 = fitcknn(XTrain, YTrain, 'NumNeighbors', 5);
    YPred_knn5 = predict(knn5, XTrain);
    [Se, Sp, Acc, ~] = funcion_metricas(YPred_knn5, YTrain);

    tablaAcc{i_desc, 2} = Acc;
    tablaSp{i_desc, 2} = Sp;
    tablaSe{i_desc, 2} = Se;
end

%% SVM

YTrain = CodifValoresColores == 255;
for i_desc = 1:N_DESC
    XTrain = ValoresColores(:, descriptores{i_desc});
    svm = fitcsvm(XTrain, YTrain); 
    YPred_svm = predict(svm, XTrain);
    [Se, Sp, Acc, ~] = funcion_metricas(YPred_svm, YTrain);

    tablaAcc{i_desc, 3} = Acc;
    tablaSp{i_desc, 3} = Sp;
    tablaSe{i_desc, 3} = Se;
end

%% NEURONAL NETWORK

YTrain = CodifValoresColores == 255;
for i_desc = 1:N_DESC
    XTrain = ValoresColores(:, descriptores{i_desc});
    YPred_nn = sim(NNS{i_desc}, XTrain')' > 0.50001;
    [Se, Sp, Acc, ~] = funcion_metricas(YPred_nn, YTrain);

    tablaAcc{i_desc, 4} = Acc;
    tablaSp{i_desc, 4} = Sp;
    tablaSe{i_desc, 4} = Se;
end

%% GUARDAR

save("ResultadosClasificadores.mat", "tablaAcc", "tablaSe", "tablaSp");
