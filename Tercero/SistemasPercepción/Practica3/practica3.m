clearvars
clc

video = videoinput('winvideo', 1, 'YUY2_320x240');
get(video);
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat = 3;
video.FramesPerTrigger = 3;
video.FrameGrabInterval = 3;

%% EJERCICIO 1
I = getsnapshot(video);
[Fil, Col, ~] = size(I);

Iint = (double(I(:, :, 1)) + double(I(:, :, 2)) + double(I(:, :, 3))) / 3;
umbral = [70, 140, 210];

ImgBins = logical(zeros([Fil, Col, 3]));
for i = 1:3
    ImgBins(:, :, i) = Iint > umbral(i);
end

% Representacion
subplot(2, 2, 1), imshow(I), title('Imagen Original');
for i = 1:3
    subplot(2, 2, i+1), imshow(my_visualiza(I, ImgBins(:, :, i), [255, 255, 0], false)),
    title(['Imagen con pixeles > ', num2str(umbral(i))]);
end

%% EJERCICIO 2

[IEtiq, N] = bwlabel(ImgBins(:, :, 1));
for i = 1 : 3
    % Obtenemos la matriz etiquetada
    
    

end



%% EJERCICIO 3
Iad = I;

for gamma = 4:-0.05:0
    imshow(imadjust(Iad, [], [], gamma));
end

%% EJERCICIO 4
