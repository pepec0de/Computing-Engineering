function idx = funcion_calcula_agrupacion(X, centroides)
%FUNCION_CALCULA_AGRUPACION idx = funcion_calcula_agrupacion(X, centroides)
%   Objetivo: genera la agrupación de los datos X de acuerdo a centroides.
%   Para ello, se debe asignar cada muestra de X al centroide más próximo 
%   según distancia Euclidea.

[n, ~] = size(X);
idx = zeros(n, 1);
[k, ~] = size(centroides);

for x = 1:n
    distMin = Inf;
    agrup = -1;
    for a = 1:k
        dist = distancia(X(x, :), centroides(a, :));
        if dist < distMin
            distMin = dist;
            agrup = a;
        end
    end
    idx(x) = agrup;
end

end
