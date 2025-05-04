function centroides = funcion_calcula_centroides(X, idx)
%FUNCION_CALCULA_CENTROIDES centroides = funcion_calcula_centroides(X, idx)
%   Objetivo: calcular la matriz de centroides de los datos X agrupados
%   según idx.

[n, p] = size(X);
k = max(unique(idx));
centroides = zeros([k, p]);

for a = 1:k
    cluster = X(idx == a, :);
    mCluster = mean(cluster);

    centroid = -1;
    distMin = Inf;
    for x = 1:n
        dist = distancia(X(x, :), mCluster);
        if dist < distMin
            centroid = x;
            distMin = dist;
        end
    end

    centroides(a, :) = X(centroid, :);
end

end
