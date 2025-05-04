%% Almacenar fotos de calibracion

clc, clearvars;
video = videoinput('winvideo', '1','YUY2_320x240');
video.TriggerRepeat = inf;
video.ReturnedColorSpace = 'rgb';

NImagenes = 15;

start(video)
preview(video)

ImagenesEntrenamiento_Calibracion = uint8(zeros([240, 320, 3, NImagenes]));
disp('5 segundos para comenzar a capturar fotos');
pause(5);

for i = 1:NImagenes
    I = getsnapshot(video);
    ImagenesEntrenamiento_Calibracion(:, :, :, i) = I;
    pause(2);
end

save(['MaterialGenerado\', 'ImagenesEntrenamiento_Calibracion.mat'], 'ImagenesEntrenamiento_Calibracion');
stop(video)
close all;
closepreview(video);

%% Almacenar video

clc, clearvars;
video = videoinput('winvideo', '1','YUY2_320x240');
video.ReturnedColorSpace = 'rgb';
aviobj = VideoWriter("MaterialGenerado\video.avi");
aviobj.FrameRate = 15;
video.TriggerRepeat = inf;
video.FrameGrabInterval = 2;

set(video, 'LoggingMode', 'memory');
open(aviobj)
start(video)
preview(video);
pause(5);
while (video.FramesAcquired < 150)
    I = getdata(video, 1);
    writeVideo(aviobj, I);
end
stop(video)
close(aviobj)
closepreview(video);