clc, clearvars;
Ruta = '..\..\..\..\DatosPracticaSP\';
addpath(Ruta);

Fotos = load([Ruta 'FotosCalibracion.mat']).FotosCalibracion;

%%%% 2.1.1
%% Generacion de RoI del objeto

N = 18;
RoI = logical(zeros(240, 320, N));

for i = 1:N
    RoI(:, :, i) = roipoly(Fotos(:, :, :, i));
end

save([Ruta 'FotosRoI.mat'], 'RoI');

%% Obtencion de DatosColor de los valores de RoI
% DatosColor matriz de Filas?x4 Columnas (Identificador, R, G, B)
DatosColor = uint8(zeros(1, 4));

for i = 1:N
    [Fil, Col] = find(RoI(:, :, i) == 1);
    
end


%%

