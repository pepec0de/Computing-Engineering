clc, clear, close all

%%

I = imread('P3.tif');
figure, imshow(I);

h = imhist(I);

Ieq = funcion_EcualizaImagen(I);
figure, imshow(Ieq);

std(double(Ieq(:)))