function posOutliers = funcion_detecta_outliers_clase_interes(X, Y, posClaseInteres)
%FUNCION_DETECTA_OUTLIERS_CLASE_INTERES
% X,Y: conjunto de datos entrada-salida
% posClaseInteres: posición del valor de codificación de la clase de
%   interés en el vector de valores únicos ordenados de Y
% posOutliers: posiciones de los valores anómalos en el conjunto de datos

n = size(X, 1);
posOutliers = false(n, 1);

clases = unique(Y);
CoI = clases(posClaseInteres);

% Seleccionamos de 2:4 ya que la columna 1 es el codigo de la imagen
Xrgb = X(:, 2:4);

q1 = prctile(Xrgb, 25);
q3 = prctile(Xrgb, 75);
iqr = q3 - q1;
rango_outlier = [q1 - (1.5*iqr); q3 + (1.5*iqr)];

for i = 1:n
    % Comprobamos que la instancia sea de la clase de interes
    if Y(i) == CoI
        for j = 1:3
            if Xrgb(i, j) < rango_outlier(1, j) || Xrgb(i, j) > rango_outlier(2, j)
                posOutliers(i) = true;
                break
            end
        end
    end
end
end

