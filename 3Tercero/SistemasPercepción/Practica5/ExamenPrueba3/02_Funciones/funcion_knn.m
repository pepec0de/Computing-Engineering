function YTest = funcion_knn(XTest, XTrain, YTrain, k)
%FUNCION_KNN Summary of this function goes here

NTest = size(XTest,1);
YTest = zeros(NTest,1);

NTrain = size(XTrain, 1);

for i=1:NTest
    P = XTest(i, :)';
    NP = XTrain';
    P_amp = repmat(P, 1, NTrain);
    vectorDistancia = sqrt(sum((P_amp - NP).^2));

    [~, idxs] = sort(vectorDistancia);
    % Seleccionamos las clases de los KVecinos mas cercanos
    ClasesKVecinos = YTrain(idxs(1:k));
    YTest(i) = mode(ClasesKVecinos);
end

end