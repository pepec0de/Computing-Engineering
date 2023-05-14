function X = funcion_calcula_descriptores_imagen(IEtiq, N)
%FUNCION_CALCULA_DESCRIPTORES_IMAGEN XImagen = N*23
% 23 Descriptores:
% Compacticidad: 1
% Excentricidad: 2
% Solidez_CHull(Solidity): 3
% Extension_BBox(Extent): 4
% Extension_BBox(Invariante Rotacion): 5
% Hu1-Hu7: 6-12
% DF1-DF10: 13-22
% NumEuler: 23
X = zeros(N, 23);

COMPACTICIDAD      = 1;
EXCENTRICIDAD      = 2;
SOLIDEZ            = 3;
EXTENSION_BB       = 4;
EXTENSION_BB_INV   = 5;
HU                 = 6:12;
DF                 = 13:22;
EULER              = 23;

momentos_hu = funcion_calcula_Hu_objetos(IEtiq, N);
ext_inv     = funcion_calcula_extent_objetos(IEtiq, N);
desc_four   = funcion_calcula_DF_objetos(IEtiq, N);

X(:, HU) = momentos_hu;
X(:, EXTENSION_BB_INV) = ext_inv;
X(:, DF) = desc_four;

for i = 1:N
    Ib = IEtiq == i;
    
    propiedades = regionprops(Ib, 'Solidity', 'Extent', 'EulerNumber', 'Eccentricity', 'Area');
    X(i, EULER)          = propiedades.EulerNumber;
    X(i, EXTENSION_BB)   = propiedades.Extent;
    X(i, SOLIDEZ)        = propiedades.Solidity;
    X(i, EXCENTRICIDAD)  = propiedades.Eccentricity;
    X(i, COMPACTICIDAD) = sum(sum(Ib)).^2 / propiedades.Area;
end

end

