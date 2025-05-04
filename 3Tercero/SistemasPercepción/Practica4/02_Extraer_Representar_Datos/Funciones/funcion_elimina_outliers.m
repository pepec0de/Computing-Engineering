function [XwOut, YwOut] = funcion_elimina_outliers(X, Y, posClaseInteres)
%FUNCION_ELIMINA_OUTLIERS Aplica la funcion_detecta_outliers_clase_interes(X, Y, posClaseInteres)

posOutliers = funcion_detecta_outliers_clase_interes(X, Y, posClaseInteres);

XwOut = X;
YwOut = Y;

XwOut(posOutliers, :) = [];
YwOut(posOutliers, :) = [];
end

