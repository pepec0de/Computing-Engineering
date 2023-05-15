function [vectorMedias, matrizCovarianza, probabilidadPriori] = funcion_ajusta_LDA(X, Y)
%FUNCION_AJUSTA_LDA Summary of this function goes here
%   Detailed explanation goes here

[n, ~] = size(X);
codifClases = unique(Y);
nClases = length(codifClases);

nK = zeros(nClases, 1);
vectorMedias = zeros(nClases, 1);
matrizCovarianza = cov(X);
probabilidadPriori = zeros(nClases, 1);

for i = 1:nClases
    % Calculo nk
    bin = Y == codifClases(i);
    nK(i) = sum(bin);

    % Calcula vectorMedias
    vectorMedias(i) = sum(X(bin, :))/nK;

    % Calculo probabilidadPrior
    probabilidadPriori(i) = nK / n;
end

end

