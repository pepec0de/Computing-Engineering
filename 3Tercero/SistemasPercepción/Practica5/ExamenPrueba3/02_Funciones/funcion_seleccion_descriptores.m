function [XoI, YoI, espacioCcas, JespacioCcas, nombresProblemaOI] =...
    funcion_seleccion_descriptores(X, Y, nombresProblema, clasesOI, dim)
%FUNCION_SELECCION_DESCRIPTORES Summary of this function goes here
%   Detailed explanation goes here

[numDatos, numDescriptores] = size(X);
codifClases = unique(Y);
codifClasesOI = codifClases(clasesOI);

filasOI = false(numDatos, 1);
for i = 1:length(clasesOI)
    filasOI = or(filasOI, Y == codifClasesOI(i));
end

XoI = X(filasOI, 1:numDescriptores-1);
YoI = Y(filasOI);

[espacioCcas, JespacioCcas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

XoI = XoI(:, espacioCcas);

nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases(clasesOI);
nombresProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);
nombresProblemaOI.codificacion = nombresProblema.codificacion(clasesOI);

end

