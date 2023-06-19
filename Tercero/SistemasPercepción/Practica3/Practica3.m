clc, clear;

video = videoinput('winvideo', 1, 'YUY2_320x240');
get(video);
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat = 3;
video.FramesPerTrigger = 3;
video.FrameGrabInterval = 3;
color = [255, 255, 0];

%% EJERCICIO 1

I = getsnapshot(video);

figure, subplot(2, 2, 1), imshow(I), title("Imagen original");

umbral = [70, 140, 210];
n = length(umbral);

% Almacenar imagenes
ImgBins = zeros(size(I, 1), size(I, 2), n);

Iint = (double(I(:, :, 1)) + double(I(:, :, 2)) + double(I(:, :, 3))) / 3;

for i = 1:n
    Ib = Iint > umbral(i);
    ImgBins(:, :, i) = Ib;
    subplot(2, 2, i+1), funcion_visualiza(I, Ib, color);
    title(["Imagen con umbral > " num2str(umbral(i))]);
end

%% EJERCICIO 2

for i = 1:n
    [IEtiq, N] = funcion_etiquetar(ImgBins(:, :, i));
    areas = calcula_areas(IEtiq, N);
    centroides = calcula_centroides(IEtiq, N);

    [areasOrd, idxs] = sort(areas, 'descend');
    
    Nmayores = 5;
    if Nmayores > length(areasOrd)
        Nmayores = length(areasOrd);
    end

    if Nmayores == 0
        break
    end

    areaNmayor = areasOrd(Nmayores);

    IbFiltrado = filtrar_objetos(IEtiq, N, areaNmayor);

    figure(), funcion_visualiza(I, IbFiltrado, color);
    title([num2str(Nmayores) ' areas mayores seleccionadas']), hold on;
    for j = 1:Nmayores
        centroide = centroides(idxs(j), :);
        if j == 1
            plot(centroide(1), centroide(2), '*b');
        else
            plot(centroide(1), centroide(2), '*r');
        end
    end
    hold off;
end

%% EJERCICIO 3

clc, clear;
imaqhwinfo('winvideo');
video = videoinput('winvideo', 1, 'YUY2_320x240');
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat = inf;
video.FrameGrabInterval = 2;
start(video);

TIEMPO=[];
figure(), hold on;
Gamma = 4;
while (Gamma > 0)
    [I, TIME, METADATA] = getdata(video,1);
    TIEMPO = [TIEMPO ; TIME METADATA.AbsTime];
    J = imadjust(I, [], [], Gamma);
    imshow(J);
    Gamma = Gamma - 0.05;
end
stop(video)
close all;

%% EJERCICIO 4

clc, clear;
imaqhwinfo('winvideo');
video = videoinput('winvideo', 1, 'YUY2_320x240');
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat = inf;
video.FrameGrabInterval = 2;
start(video);

umbral = 0;

figure;
while umbral < 255
    I = getdata(video, 1);
    I(I < umbral) = 0;
    imshow(I);
    umbral = umbral + 1;
end

stop(video);
close all;

%% EJERCICIO 5.1

clc, clear;
imaqhwinfo('winvideo');
video = videoinput('winvideo', 1, 'YUY2_320x240');
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat = inf;
video.FrameGrabInterval = 2;
start(video);

figure;
I1 = getdata(video, 1);
while video.FramesAcquired < 500
    I2 = getdata(video, 1);
    imshow(imabsdiff(I1, I2));
    I1 = getdata(video, 1);
end

stop(video);
close all;

%% EJERCICIO 5.2

clc, clear;
imaqhwinfo('winvideo')
video = videoinput('winvideo', 1, 'YUY2_320x240');
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat = inf;
video.FrameGrabInterval = 2;
start(video);

figure;
I1 = getdata(video, 1);
while video.FramesAcquired < 100
    I2 = getdata(video, 1);
    I = imabsdiff(I1, I2);
    I(I < 100) = 0;
    imshow(I);
    I1 = getdata(video, 1);
end

stop(video);
close all;

%% EJERCICIO 5.3

clc, clear;
imaqhwinfo('winvideo')
video = videoinput('winvideo', 1, 'YUY2_320x240');
video.ReturnedColorSpace = 'grayscale';
video.TriggerRepeat = inf;
video.FrameGrabInterval = 4;
start(video);

figure;
I1 = getdata(video, 1);
while video.FramesAcquired < 200
    I2 = getdata(video, 1);
    I = imabsdiff(I1, I2);
    Ib = I > 100;
    [IEtiq, N] = bwlabel(Ib);
    areas = calcula_areas(IEtiq, N);
    centroides = calcula_centroides(IEtiq, N);
    [areasOrd, idxs] = sort(areas, 'descend');
    imshow(I);
    if length(idxs) >= 1
        centroide = centroides(idxs(1), :);
        hold on, plot(centroide(1), centroide(2), '*r');
    end

    I1 = getdata(video, 1);
end

stop(video);
close all;