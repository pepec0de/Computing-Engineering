function [YQDA, d] = funcion_aplica_QDA(X, vectorMedias,...
    matricesCovarianzas, probabilidadPriori, valoresClases)
%FUNCION_APLICA_QDA esta función utiliza la información generada por la
% función anterior para predecir la clase del conjunto de instancias dadas
% por X, Y_QDA. Los valores de codificación que utiliza para asignar las
% clases son los que se especifican en la variable de entrada
% valoresClases. Además, la función devuelve el resultado de la evaluación
% de las funciones de decisión del clasificador QDA para cada instancia de
% X que motiva la predicción de la clase.

[n, p] = size(X);
YQDA = zeros(n, 1);
d = zeros(n, 1);
nClases = length(valoresClases);

% Declaracion de p variables simbolicas
Xsym = sym('Xsym', [p 1]);

% Obtenemos las funciones de decision de cada clase
dClases = [];
for i = 1:nClases
    Mi = vectorMedias(i, :)';
    MCovi = matricesCovarianzas(:, :, i);
    Probai = probabilidadPriori(i);
    dClases = [dClases; expand(-0.5*(Xsym - Mi)' * inv(MCovi) * (Xsym - Mi) -0.5*log(det(MCovi))...
    + log(Probai) )];
end

% Vector auxiliar para obtener el valor de decision de todas las clases
valoresClases = zeros(nClases, 1);
for i = 1:n
    % Cargamos la muestra en Xsym
    for compo = 1:p
        Xsym(compo) = X(i, compo);
    end

    for clase = 1:nClases
        valoresClases(clase) = eval(dClases(clase));
    end

    [valoresOrd, idxs] = sort(valoresClases, 'descend');
    YQDA(i) = valoresClases(idxs(1));
    d(i) =  valoresOrd(1);
end

end

