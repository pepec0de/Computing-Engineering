clc, clear;

Ruta = "..\01_GeneracionMaterial\MaterialGenerado\";
addpath(Ruta);
addpath('Funciones\')

load("ImagenesEntrenamiento_Calibracion.mat", 'ImagenesEntrenamiento_Calibracion');
FotosCalibracion = ImagenesEntrenamiento_Calibracion;
clear ImagenesEntrenamiento_Calibracion Ruta;
%% 2.1.1 Generacion de RoIs del objeto 
[~, ~, ~, N] = size(FotosCalibracion);

RoIObjeto = false(240, 320, N);

for i = 1:N
    RoIObjeto(:, :, i) = roipoly(FotosCalibracion(:, :, :, i));
end

save("VariablesGeneradas\RoIsObjeto.mat", "RoIObjeto");

%% Obtencion de DatosColor de los valores de RoI
% DatosColor matriz de ?x4 Columnas (Id Imagen, R, G, B)
DatosColor = [];
for i = 1:N
    R = FotosCalibracion(:, :, 1, i); 
    G = FotosCalibracion(:, :, 2, i);
    B = FotosCalibracion(:, :, 3, i);
    Ib = RoIObjeto(:, :, i);
    Ri = R(Ib); Gi = G(Ib); Bi = B(Ib);
    DatosColor = [DatosColor; ones(size(Ri))*i, Ri, Gi, Bi];
end

save("VariablesGeneradas\DatosColor.mat", "DatosColor");

%% 2.1.2 Generacion de RoIs del fondo
RoIFondo = false(240, 320, N);

for i = 1:N
    RoIFondo(:, :, i) = roipoly(FotosCalibracion(:, :, :, i));
end

save("VariablesGeneradas\RoIsFondo.mat", "RoIFondo");

%% Obtencion de DatosFondo a partir de RoIFondo

DatosFondo = [];
for i = 1:N
    R = FotosCalibracion(:, :, 1, i);
    G = FotosCalibracion(:, :, 2, i);
    B = FotosCalibracion(:, :, 3, i);
    Ib = RoIFondo(:, :, i);
    Ri = R(Ib); Gi = G(Ib); Bi = B(Ib);
    DatosFondo = [DatosFondo; ones(size(Ri))*i, Ri, Gi, Bi];
end

save("VariablesGeneradas\DatosFondo.mat", "DatosFondo");

%% 2.1.3 Generacion de conjuntos X e Y

X = [DatosColor; DatosFondo];

[NColor, ~] = size(DatosColor);
[NFondo, ~] = size(DatosFondo);
Y = [ones(NColor, 1); zeros(NFondo, 1)];

save("VariablesGeneradas\ConjuntoDatos.mat", "X", "Y");

%% 2.2.1 Visualización del conjunto de datos
clc, clear;

load("VariablesGeneradas\ConjuntoDatos.mat", "X", "Y");
representa_datos_color(X, Y, "Conjunto de Datos con outliers");

%% 2.3 Borrado y representacion del conjunto de datos sin outliers

[Xsin, Ysin] = funcion_elimina_outliers(X, Y, 2);
representa_datos_color(Xsin, Ysin, "Conjunto de datos sin outliers");

save("VariablesGeneradas\ConjuntoDatosSinOutliers.mat", "Xsin", "Ysin");

