clc, clear, close all

addpath("../Etapa1/Material_Imagenes/03_MuestrasFresas/");
load("../Etapa1/VariablesEtapa1.mat");
%load("VariablesEtapa2.mat");

YTrain = double(CodifValoresColores == 255);
YTrain = YTrain + double(CodifValoresColores == 128)*2;
knn5 = fitcknn(ValoresColores(:, 1:3), YTrain, 'NumNeighbors', 5);

Colores = [[255, 0, 0]; [255, 255, 0]];

% ETAPA 2: DETECCIÓN DE FRESAS Y MEDIDA DEL GRADO DE MADUREZ
% Detección de fresas rojas y medida de su grado de madurez

for img = 1:3
    I = imread(['SegFresas' num2str(img) '.tif']);
    Ibin_Gold = imread(['SegFresas' num2str(img) '_Gold.tif']) == 255;
    
    % Obtenemos XTest (RGB) y YTrain
    R = double(I(:, :, 1))/255;
    G = double(I(:, :, 2))/255;
    B = double(I(:, :, 3))/255;
    XTest = [R(:), G(:), B(:)];
    clear R G B
    
    % 1.- Detectar los píxeles rojo fresa.
    
    YPred = predict(knn5, XTest);
    [N, M] = size(I, 1:2);
    Ipred = funcion_y_a_mat(YPred, N, M);
    
    % Tiene que tener un número mínimo de píxeles detectados rojos.
    % usamos bwareaopen() para eliminar los clusters de rojo pequeños
    % mas pequeños que 100 pixeles
    Ibin = bwareaopen(Ipred, 150);
    Ipred = Ipred .* Ibin;
    
    % 3.- Visualizar sobre la imagen original los resultados obtenidos 
    %   (en rojo, la detección del rojo fresa; en amarillo, la detección del verde fresa).
    
    figure, visualiza_vec(I, Ipred, Colores, true); title('Detección de pixeles');
    
    % 4.- Detección de fresas rojas presentes y medida del grado de madurez. 
    
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
        Ipred_crop = Ipred(yStart : yStart + height, xStart : xStart + width);
        region_rojo = Ipred_crop == 1;
        region_verde = Ipred_crop == 2;
        n_pix_rojos = sum(region_rojo(:));
        % DUDA: así o con sum(IbinFresa(:))
        n_pix_total = n_pix_rojos + sum(region_verde(:));
        grado_madurez = n_pix_rojos/n_pix_total;
        % DUDA esta bien esto???
        if grado_madurez < 0.1
            continue
        end

        % Crop the original image based on the bounding box
        I_fresa = imcrop(I, [xStart, yStart, width, height]);
        
        % Optional: Display each sub-image
        subplot(n_fresas+1, 1, e+1);
        visualiza_vec(I_fresa, Ipred_crop, Colores, true); 
        %imshow(I_fresa);
        title(['Fresa ' num2str(e) ' Grado de madurez: '...
            num2str(grado_madurez, 3)]), hold on;
    end
end    