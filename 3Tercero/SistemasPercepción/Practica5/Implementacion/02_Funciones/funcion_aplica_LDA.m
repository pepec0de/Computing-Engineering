function [YLDA, d] = funcion_aplica_LDA(...
    X, vectorMedias, matrizCovarianza, probabilidadPriori, valoresClases)
%FUNCION_APLICA_LDA Summary of this function goes here
%   Detailed explanation goes here

[n, p] = size(X);
YLDA = zeros(n, 1);
d = zeros(n, 1);
nClases = length(valoresClases);

% Declaracion de p variables simbolicas
Xsym = sym('Xsym', [p 1]);

% Vector auxiliar para obtener el valor de decision de todas las clases
evalClases = zeros(nClases, 1);
for i = 1:n
    % Cargamos la muestra en Xsym
    % No se puede hacer Xsym(:) = X(i, :);
    for compo = 1:p
        Xsym(compo) = X(i, compo);
    end
    
    % Obtenemos las funciones de decision de cada clase
    dClases = [];
    for clase = 1:nClases
        Mi = vectorMedias(clase, :)';
        Probai = probabilidadPriori(clase);
        dClases = [dClases; expand(-0.5*(Xsym - Mi)' * inv(matrizCovarianza)...
            * (Xsym - Mi) + log(Probai) )];
        evalClases(clase) = eval(dClases(clase));
    end

    [evalClasesOrd, idxs] = sort(evalClases, 'descend');
    YLDA(i) = valoresClases(idxs(1));
    d(i) =  evalClasesOrd(1);
end
end