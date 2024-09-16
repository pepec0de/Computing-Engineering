function funcion_representa_frontera(...
    X, Y, nombresProblema, coeficientes, varargin)
%FUNCION_REPRESENTA_MUESTRAS_CLASIFICACION_BINARIA_FRONTERA Summary of this function goes here
%   Detailed explanation goes here

% REPRESENTA X E Y Y ADEMAS EL HIPERPLANO

% USA NARGIN PARA COMPROBAR EL NUMERO DE PARAMETROS QUE SE LE PASA
dim = length(nombresProblema.descriptores);

funcion_representa_datos(X, Y, 1:dim, nombresProblema);
if nargin == 4
    % Representa datos e hiperplano
    x1 = linspace(min(X(1, :))-1, max(X(1, :))+1, size(X, 1));
    A = coeficientes(1);
    B = coeficientes(2);
    C = coeficientes(3);
    if dim == 2
        x2 = -(A*x1 + C)/B;
        hold on;
        plot(x1, x2);
    else
        D = coeficientes(4);
        x2 = x1';
        x3 = -(A*x1 + B*x2 + D)/C;
        hold on;
        surf(x1,x2, x3);
    end
    legend([nombresProblema.clases, "Plano de separación de clases"]);
end
end

