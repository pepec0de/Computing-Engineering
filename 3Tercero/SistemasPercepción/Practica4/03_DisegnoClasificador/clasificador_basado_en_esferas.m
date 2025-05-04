%% 3.1
% DISEÑO Y ENTRENAMIENTO DE ALGORITMO DE CLASIFICACIÓN DE PÍXELES EN DOS CLASES: 
% PÍXEL DEL COLOR DE SEGUIMIENTO / PÍXEL QUE NO ES DEL COLOR DE SEGUIMIENTO
clc, clear;

addpath("Funciones\");
addpath("..\02_Extraer_Representar_Datos\Funciones\");
load("..\02_Extraer_Representar_Datos\VariablesGeneradas\ConjuntoDatosSinOutliers.mat", "Xsin", "Ysin");

%% Obtenemos el color medio de nuestro objeto
conjuntoSeg = Xsin(Ysin == 1, 2:4);
conjuntoFondo = Xsin(Ysin == 0, 2:4);
centro = round(mean(conjuntoSeg));

%% GENERACION DE ESFERA UNICA
%% 1. Con ruido (radio desde centro hasta punto del objeto más alejado)
colorLejano = conjuntoSeg(1, :);
radioConRuido = distancia(colorLejano, centro);
for i = 2:size(conjuntoSeg, 1)
    dist = distancia(conjuntoSeg(i, :), centro);
    if radioConRuido < dist
        colorLejano = conjuntoSeg(i, :);
        radioConRuido = dist;
    end
end

representa_datos_color(Xsin, Ysin, "Modelo de esfera de radio con ruido"), hold on,
representa_esfera(radioConRuido, centro);

%% 2. Sin ruido (radio desde centro hasta punto del fondo más cercano)
colorFondoCercano = conjuntoFondo(1, :);
radioSinRuido = distancia(colorFondoCercano, centro);

for i = 2:size(conjuntoFondo, 1)
    dist = distancia(conjuntoFondo(i, :), centro);
    if radioSinRuido > dist
        colorFondoCercano = conjuntoFondo(i, :);
        radioSinRuido = dist;
    end
end

representa_datos_color(Xsin, Ysin, "Modelo de esfera de radio sin ruido"), hold on,
representa_esfera(radioSinRuido, centro);

%% GENERACION DE VARIAS ESFERAS
%% CALCULO KMEANS
k = 4;
[idx, centroides] = funcion_kmeans(conjuntoSeg, k);

% REPRESENTACION
representa_datos_color(Xsin, Ysin, "Modelo de esferas k-means"), hold on,
representa_agrupacion(conjuntoSeg, idx, k), hold on;

datos_esferas = zeros(k, 3);
for i = 1:k
    plot3(centroides(i, 1), centroides(i, 2), centroides(i, 3), '*r');

    % Esfera
    datos_esferas(i, :) = calcula_datos_esfera(centroides(i, :), conjuntoSeg(idx == i, :), conjuntoFondo);
end

% 1. Con ruido
representa_datos_color(Xsin, Ysin, "Modelo de esferas radio con ruido"), hold on,
representa_agrupacion(conjuntoSeg, idx, k), hold on;
for i = 1:k
    representa_esfera(datos_esferas(i, 1), centroides(i, :));
end

% 2. Sin ruido
representa_datos_color(Xsin, Ysin, "Modelo de esferas radio sin ruido"), hold on,
representa_agrupacion(conjuntoSeg, idx, k), hold on;
for i = 1:k
    representa_esfera(datos_esferas(i, 2), centroides(i, :));
end

% 3. Compromiso
representa_datos_color(Xsin, Ysin, "Modelo de esferas radio compromiso"), hold on,
representa_agrupacion(conjuntoSeg, idx, k), hold on;
for i = 1:k
    representa_esfera(datos_esferas(i, 3), centroides(i, :));
end

%% GUARDAR DATOS

datos_multiples_esferas = zeros(k, 6);
for i = 1:k
    datos_multiples_esferas(i, 1:3) = centroides(i, :);
    datos_multiples_esferas(i, 4:6) = datos_esferas(i, :);
end

save("VariablesGeneradas\datos_multiples_esferas.mat", 'datos_multiples_esferas');