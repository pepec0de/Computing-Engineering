addpath('..\funciones\');

clearvars
clc

video = videoinput('winvideo', 1, 'YUY2_320x240');
get(video);
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat = 3;
video.FramesPerTrigger = 3;
video.FrameGrabInterval = 3;
color = [255, 255, 0];
%% EJERCICIO 1
I = getsnapshot(video);

Iint = (double(I(:, :, 1)) + double(I(:, :, 2)) + double(I(:, :, 3))) / 3;
umbral = [70, 140, 210];

[Fil, Col, ~] = size(I);
ImgBins = zeros([Fil, Col, 3]);
for i = 1:3
    ImgBins(:, :, i) = Iint > umbral(i);
end

% Representacion
subplot(2, 2, 1), imshow(I), title('Imagen Original');
for i = 1:3
    subplot(2, 2, i+1), imshow(my_visualiza(I, ImgBins(:, :, i), color, false)),
    title(['Imagen con pixeles > ', num2str(umbral(i))]);
end

%% EJERCICIO 2

subplot(2, 2, 1), imshow(I), title('Imagen Original');

for i = 1:3
    % Obtenemos la matriz etiquetada
    [IEtiq, N] = bwlabel(ImgBins(:, :, i));
    Areas = zeros(N, 1);
    Centroides = zeros(N, 2);
    for j = 1:N
        Props = regionprops(IEtiq == j, 'Area', 'Centroid');
        Areas(j) = Props.Area;
        Centroides(j, :) = Props.Centroid;
    end

    AreasOrd = sort(Areas, 'descend');
    pixMin = AreasOrd(5);
    IbFilt = filtrar_objetos(ImgBins(:, :, i), pixMin);

    subplot(2, 2, i+1), imshow(my_visualiza(I, IbFilt, color, false));
end



%% EJERCICIO 3
Iad = I;

for gamma = 4:-0.05:0
    imshow(imadjust(Iad, [], [], gamma));
end

%% EJERCICIO 4
