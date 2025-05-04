function [vectorMedias, matricesCovarianzas, probabilidadPriori] = funcion_ajusta_QDA(X, Y)
%FUNCION_AJUSTA_QDA dado un conjunto de datos X-Y, la función calcula los
% vectores de medias, matrices de covarizanzas y probabilidades a priori
% para cada una de las clases del problema (dadas por los valores 
% distintos de Y).

[n, p] = size(X);
codifClases = unique(Y);
nClases = length(codifClases);

nK = zeros(nClases, 1);
vectorMedias = zeros(nClases, p);
matricesCovarianzas = zeros(p, p, nClases);
probabilidadPriori = zeros(nClases, 1);

for i = 1:nClases
    % Calculo nk
    bin = Y == codifClases(i);
    nK(i) = sum(bin);

    % Calcula vectorMedias
    vectorMedias(i, :) = sum(X(bin, :))/nK(i);

    % Calcula matrices covarianzas
    matricesCovarianzas(:, :, i) = cov(X(bin, :));

    % Calculo probabilidadPrior
    probabilidadPriori(i) = nK(i) / n;
end

end