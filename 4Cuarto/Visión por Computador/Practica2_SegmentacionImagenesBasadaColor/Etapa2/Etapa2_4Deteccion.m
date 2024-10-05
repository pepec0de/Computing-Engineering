clc, clear

addpath("../Etapa1/Material_Imagenes/03_MuestrasFresas/");
load("../Etapa1/VariablesEtapa1.mat");
%load("VariablesEtapa2.mat");

knn5_Rojo = fitcknn(ValoresColores(:, 1:3), CodifValoresColores == 255, 'NumNeighbors', 5);
knn5_Verde = fitcknn(ValoresColores(:, 1:3), CodifValoresColores == 128, 'NumNeighbors', 5);

AMARILLO = [255, 255, 0];
ROJO = [255, 0, 0];

% ETAPA 2: DETECCIÓN DE FRESAS Y MEDIDA DEL GRADO DE MADUREZ
% Detección de fresas rojas y medida de su grado de madurez

I = imread('SegFresas1.tif');
Ibin_Gold = imread('SegFresas1_Gold.tif') == 255;

% Obtenemos XTest (RGB) y YTrain
R = double(I(:, :, 1))/255;
G = double(I(:, :, 2))/255;
B = double(I(:, :, 3))/255;
XTest = [R(:), G(:), B(:)];
clear R G B

% 1.- Detectar los píxeles rojo fresa.

YPred_Rojo = predict(knn5_Rojo, XTest);
[N, M] = size(I, 1:2);
IbinRojo = funcion_y_a_mat(YPred_Rojo, N, M);

% 2.- Detectar los píxeles verde fresa.

YPred_Verde = predict(knn5_Verde, XTest);
IbinVerde = funcion_y_a_mat(YPred_Verde, N, M);

% Tiene que tener un número mínimo de píxeles detectados rojos.
% usamos bwareaopen() para eliminar los clusters de rojo pequeños
% mas pequeños que 100 pixeles

% DUDA: hacer esto aqui??
IbinRojo = bwareaopen(IbinRojo, 100);
IbinVerde = bwareaopen(IbinVerde, 100);
%% 3.- Visualizar sobre la imagen original los resultados obtenidos 
%   (en rojo, la detección del rojo fresa; en amarillo, la detección del verde fresa).

I_deteccion = I;

for i = 1:N
    for j = 1:M
        if IbinRojo(i, j)
            I_deteccion(i, j, :) = ROJO;
        elseif IbinVerde(i, j)
            I_deteccion(i, j, :) = AMARILLO;
        end

    end
end
figure, imshow(I_deteccion), title('Detección de pixeles');
%% 4.- Detección de fresas rojas presentes y medida del grado de madurez. 

Ibin = or(IbinRojo, IbinVerde);

%funcion_visualiza(I, Ibin, AMARILLO, true);
% La fresa roja es el resultado de la conexión de los objetos detectados
% como rojo fresa y aquellos detectados como verde fresa

% 5.- Visualizar cada fresa roja detectada en una ventana tipo figure, 
%   en rojo los píxeles rojos y en amarillo los píxeles verdes.

[Ietiq, n_fresas] = bwlabel(Ibin);

figure, subplot(n_fresas+1, 1, 1), imshow(I), title("Original"), hold on;
for e = 1:n_fresas

    IbinFresa = Ietiq == e;

    % Step 5: Get the bounding box for the labeled region
    stats = regionprops(IbinFresa, 'BoundingBox');
    bbox = round(stats.BoundingBox);  % Get the bounding box [x, y, width, height]
    
    % Step 6: Crop the original image using the bounding box
    xStart = bbox(1);
    yStart = bbox(2);
    width = bbox(3);
    height = bbox(4);
    
    % Contamos los pixeles rojos
    region_rojo = IbinRojo(yStart : yStart + width, xStart : xStart + height);
    region_verde = IbinVerde(yStart : yStart + width, xStart : xStart + height);
    n_pix_rojos = sum(region_rojo(:));
    % DUDA: así o con sum(IbinFresa(:))
    n_pix_total = n_pix_rojos + sum(region_verde(:));

    % Crop the original image based on the bounding box
    margin = 10;
    I_fresa = imcrop(I, [xStart - margin, yStart - margin, width + margin*2, height + margin*2]);
    
    % Optional: Display each sub-image
    subplot(n_fresas+1, 1, e+1), imshow(I_fresa), title(['Fresa ' num2str(e)...
        ' Grado de madurez: ' num2str(n_pix_rojos/n_pix_total, 3)]), hold on;
end