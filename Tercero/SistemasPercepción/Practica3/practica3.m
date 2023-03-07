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

%%% Duda : como hago para poner todas las matrices en un solo vector
Iint = (double(I(:, :, 1)) + double(I(:, :, 2)) + double(I(:, :, 3))) / 3;
umbral = [70, 140, 210];
Img1 = my_visualiza(I, Iint > 70, [0, 255, 255], false);
Img2 = my_visualiza(I, Iint > 140, [0, 255, 255], false);
Img3 = my_visualiza(I, Iint > 210, [0, 255, 255], false);

% Representacion
subplot(2, 2, 1), imshow(I);
subplot(2, 2, 2), imshow(Img1);
subplot(2, 2, 3), imshow(Img2);
subplot(2, 2, 4), imshow(Img3);


%% EJERCICIO 2
