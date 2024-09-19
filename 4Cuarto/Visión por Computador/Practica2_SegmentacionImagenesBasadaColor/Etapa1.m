%% PLOT DE IMAGENES
clear
close all

dir = 'Material_Imagenes/01_MuestrasColores/';
nImgs = 3;

Imgs = cell(1, nImgs);
ImgsBin = cell(1, nImgs);

figure, hold on
for i = 1:nImgs
    nombre = ['Color' int2str(i)];
    Imgs{i} = imread([dir nombre '.jpeg']);
    subplot(2, 3, i);
    imshow(Imgs{i});
    title(nombre), hold on
    
    ImgsBin{i} = imread([dir 'Color' int2str(i) '_MuestraColores.tif']);
    subplot(2, 3, i+3);
    imshow(ImgsBin{i});
    title(nombre), hold on
end

%% OBTENCION DE COMPONENTES R, G, B, H, S, I, Y, U, V, L, a, b
%for i = 1:nImgs
i = 1;
Id = double(Imgs{i});
R = Id(:, :, 1);
G = Id(:, :, 2);
B = Id(:, :, 3);
Img_HSV = double(rgb2hsv(Imgs{i}));
H = Img_HSV(:, :, 1);
S = Img_HSV(:, :, 2);

% TODO: I (HSI) y YUV
I = (R + G + B)/3;

% IMPORTANTE: normalizar primero RGB
R = R/255;
G = G/255;
B = B/255;

Y = 0.299*R + 0.587*G + 0.114*B;
U = 0.492*(B - Y);
V = 0.877*(R - Y);
%-------

Img_Lab = double(rgb2lab(Imgs{i}));
L = Img_Lab(:, :, 1);
a = Img_Lab(:, :, 2);
b = Img_Lab(:, :, 3);

% NORMALIZAR (RGB ya normalizado)
H = H/360;
S = normalizar(S);
I = I/255;
Y = normalizar(Y);
U = normalizar(U);
V = normalizar(V);

L = normalizar(L);
a = normalizar(a);
b = normalizar(b);


%end

