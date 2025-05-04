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
ValoresColores = [];
CodifValoresColores = [];

for i_img = 1:nImgs
    Id = double(Imgs{i_img});
    R = Id(:, :, 1);
    G = Id(:, :, 2);
    B = Id(:, :, 3);
    Img_HSV = double(rgb2hsv(Imgs{i_img}));
    H = Img_HSV(:, :, 1);
    S = Img_HSV(:, :, 2);
    
    % I (HSI)
    I = (R + G + B)/3;
    
    % IMPORTANTE: normalizar primero RGB, para obtener YUV
    R = R/255;
    G = G/255;
    B = B/255;
    
    Y = 0.299*R + 0.587*G + 0.114*B;
    U = 0.492*(B - Y);
    V = 0.877*(R - Y);
    %-------
    
    Img_Lab = double(rgb2lab(Imgs{i_img}));
    L = Img_Lab(:, :, 1);
    a = Img_Lab(:, :, 2);
    b = Img_Lab(:, :, 3);
    
    % NORMALIZAR (RGB ya normalizado)
    H = H/360;
    S = mat2gray(S);
    I = I/255;
    %Y = normalizar(Y);
    U = mat2gray(U);
    V = mat2gray(V);
    
    L = mat2gray(L);
    a = mat2gray(a);
    b = mat2gray(b);


    % OBTENCION DE ValoresColores y CodifValoresColores

    Ietiq = double(ImgsBin{i_img}); % TODO: repasar este codigo

    etiquetas = unique(Ietiq);

    for e = 2:size(etiquetas, 1) % saltamos la etiqueta 0
        Ib = Ietiq == etiquetas(e);
        ValoresColores = [ValoresColores; R(Ib) G(Ib) B(Ib) H(Ib) S(Ib) I(Ib) Y(Ib) U(Ib) V(Ib) L(Ib) a(Ib) b(Ib)];
        CodifValoresColores = [CodifValoresColores; etiquetas(e)*ones(sum(Ib(:)), 1)];
    end
end

%{
R G B H S I Y U V L a b
1 2 3 4 5 6 7 8 9 0 1 2
%}
%% REPRESENTACION

%{
Píxeles Rojo Fresa: valor 255.
Píxeles Verde Fresa: valor 128.
Píxeles Verde Planta: valor 64.
Píxeles Negro Lona: valor 32.
%}

%{
Grafica RGB (utilizando plot3): representar los valores R, G y B 
de los píxeles de color rojo fresa (mostrarlos en rojo),
verde fresa (mostrarlos en verde), 
verde planta (mostrarlos en azul) 
y negro lona (mostrarlos en negro).
%}

figure, hold on;
cols = 1:3; 
% Rojo fresa
PoI = ValoresColores(CodifValoresColores == 255, cols);
plot3(PoI(:, 1), PoI(:, 2), PoI(:, 3), '.r'), hold on;

% Verde fresa
PoI = ValoresColores(CodifValoresColores == 128, cols);
plot3(PoI(:, 1), PoI(:, 2), PoI(:, 3), '.g'), hold on;

% Verde planta
PoI = ValoresColores(CodifValoresColores == 64, cols);
plot3(PoI(:, 1), PoI(:, 2), PoI(:, 3), '.b'), hold on;

% Negro lona
PoI = ValoresColores(CodifValoresColores == 32, cols);
plot3(PoI(:, 1), PoI(:, 2), PoI(:, 3), '.k'), hold on,
legend('Rojo fresa', 'Verde fresa', 'Verde planta', 'Negro lona'),
title('Representación de colores RGB'),
xlabel('R'), ylabel('G'), zlabel('B');

%{
Gráfica HS (utilizando plot): 
representar los valores de H y S de los píxeles de
 color rojo fresa (mostrarlos en rojo),
verde fresa (mostrarlos en verde),
verde planta (mostrarlos en azul)
y negro lona (mostrarlos en negro).
%}

representa2caracs(ValoresColores, CodifValoresColores, 4:5, 'HS', 'H', 'S');

%{
Gráfica UV (utilizando plot):
representar los valores de U y V de los píxeles 
de color rojo fresa (mostrarlos en rojo), 
verde fresa (mostrarlos en verde), 
verde planta (mostrarlos en azul) 
y negro lona (mostrarlos en negro).
%}

representa2caracs(ValoresColores, CodifValoresColores, 8:9, 'UV', 'U', 'V');

representa2caracs(ValoresColores, CodifValoresColores, 11:12, 'ab', 'a', 'b');


%% MODIFICACION DE H

% El problema es que la componente H no agrupa el rojo fresa en un mismo
% cluster

% Columna H -> ValoresColores(:, 4) 
H = ValoresColores(:, 4);

for i = 1:size(H, 1)
    if H(i) <= 0.5
        ValoresColores(i, 4)  = 1 - 2 * H(i);
    else
        ValoresColores(i, 4)  = 2 * (H(i) - 0.5);
    end
end

%{
Gráfica HS (utilizando plot): 
representar los valores de H y S de los píxeles de
 color rojo fresa (mostrarlos en rojo),
verde fresa (mostrarlos en verde),
verde planta (mostrarlos en azul)
y negro lona (mostrarlos en negro).
%}

representa2caracs(ValoresColores, CodifValoresColores, 4:5, 'HS', 'H', 'S');

save("VariablesEtapa1", "ValoresColores", "CodifValoresColores");