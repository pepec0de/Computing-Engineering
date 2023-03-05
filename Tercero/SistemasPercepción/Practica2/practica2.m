%% EJERCICIO 1
imfinfo("ImagenBinaria.tif")
Imagen1 = imread("ImagenBinaria.tif");
imtool(Imagen1)

%% EJERCICIO 2
Mbin = logical(double(Imagen1)/255);

%% EJERCICIO 3

%% Etiquetar
[ImgEtiq, N] = my_etiquetar(Mbin);

[~, SZ] = size(N);

%% Visualizar
Imagen2 = uint8(zeros(size(Imagen1)));
% Empezamos por 2 porque el primero es 0
for i = 1:SZ
    color = [uint8(mod(rand()*1000, 255) + 1), uint8(mod(rand()*1000, 255) + 1), uint8(mod(rand()*1000, 255) + 1)];
    binColor = N(i) == ImgEtiq;
    Imagen2 = Imagen2 + my_visualiza(uint8(binColor)*255, binColor, color, false);
end

imtool(Imagen2)

%% EJERCICIO 4

Areas = calcula_areas(ImgEtiq, N);
Centroides = calcula_centroides(ImgEtiq, N);

Imagen3 = Imagen1;
imshow(Imagen3), hold on, plot(Centroides(5, 1), Centroides(5, 2), 'r*');

