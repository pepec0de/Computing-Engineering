%% EJERCICIO 1
imfinfo("ImagenBinaria.tif")
Imagen1 = imread("ImagenBinaria.tif");
imtool(Imagen1)

%% EJERCICIO 2
Mbin = logical(double(Imagen1)/255);

%% EJERCICIO 3

[ImgEtiq, N] = my_etiquetar(Mbin);

%% EJERCICIO 4