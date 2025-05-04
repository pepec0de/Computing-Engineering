function funcion_representa_datos(X,Y, espacioCcas, datosProblema)
%FUNCION_REPRESENTA_DATOS Summary of this function goes here
%   Detailed explanation goes here

nClases = length(datosProblema.clases);
nCcas = length(espacioCcas);
for i = 1:nClases
    XoI = X(Y == datosProblema.codificacion(i), espacioCcas);
    if (nCcas == 2)
        plot(XoI(:, 1), XoI(:, 2), datosProblema.simbolos{i}), hold on;
    elseif (nCcas == 3)
        plot3(XoI(:, 1), XoI(:, 2), XoI(:, 3), datosProblema.simbolos{i}), hold on;
        zlabel(datosProblema.descriptores{espacioCcas(3)});
    end
end

legend(datosProblema.clases);
xlabel(datosProblema.descriptores{espacioCcas(1)});
ylabel(datosProblema.descriptores{espacioCcas(2)});

hold off;
