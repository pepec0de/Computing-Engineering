function YTest = funcion_knn(XTest, XTrain, YTrain, k)
%FUNCION_KNN Summary of this function goes here
%   Detailed explanation goes here

NTest = size(XTest, 1);
NTrain = size(XTrain, 1);
codifClases = unique(YTrain);
nClases = length(codifClases);

copia_XTRAIN = repmat(XTrain, NTest, 1);

distancias = zeros(NTest, NTrain);
contClases = zeros(nClases, 1);
YTest = zeros(NTest, 1);
for i = 1:NTest
    rango = ((i-1) * NTrain) + 1 : i * NTrain;
    distancias_act = (copia_XTRAIN(rango, :) - XTest(i, :)) .^2;
    distancias(i, :) = sum(distancias_act');

    [~, idxs] = sort(distancias(i, :));
    knn = idxs(1:k);
    YoI = YTrain(knn, :);
    for j = 1:nClases
        contClases(j) = sum(YoI == codifClases(j));
    end
    YTest(i) = codifClases(contClases == max(contClases));
end


end