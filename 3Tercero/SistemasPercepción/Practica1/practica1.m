%% EJERCICIO 1
imfinfo('P1_1.jpg')

%% EJERCICIO 2
Imagen1 = imread('P1_1.jpg');

%% EJERCICIO 3
imtool(Imagen1);
imshow(Imagen1);

%% EJERCICIO 4
whos Imagen1

%% EJERCICIO 5
max(Imagen1)

%% EJERCICIO 6
Imagen2 = 255 - Imagen1;
imshow(Imagen2)
imwrite(Imagen2, 'P1_Ejercicio6.jpg');

%% EJERCICIO 7
Imagen3 = Imagen1(:,:,1);
imtool(Imagen3);

%% EJERCICIO 8
% ajustamos el gamma
Imagen4 = imadjust(Imagen3, [], [], 0.5);
Imagen5 = imadjust(Imagen3, [], [], 1.5);
figure, imhist(Imagen4);
figure, imhist(Imagen5);

% se puede comprobar en los histogramas que la Imagen4 tiene pixeles mas 
% claros que la Imagen5. La Imagen5 a su vez tiene pixeles más oscuros.

%% EJERCICIO 9

% Con el metodo de matlab:
Imagen6a = imabsdiff(Imagen4, Imagen5);
imtool(Imagen6a);

Imagen6b = abs(Imagen4 - Imagen5);
imtool(Imagen6b);

my_compara_matrices(Imagen6a, Imagen6b)

%% EJERCICIO 10

hTool = imhist(Imagen3);
hPropio = my_imhistA(Imagen3);
hPropio1 = my_imhistB(Imagen3);

my_compara_matrices(hTool, hPropio)
my_compara_matrices(hTool, hPropio1)

function vHist = my_imhistA(m)
    vHist = zeros(256, 1);
    
    [nFilas, nColas] = size(m);
    
    for i = 1:nFilas
        for j = 1:nColas
            idc = uint16(m(i, j))+1;
            vHist(idc) = vHist(idc) + 1;
        end
    end
end

function vHist = my_imhistB(m)
    vHist = zeros(256, 1);

    for i = 0:255
        ImBin = (i == m);
        vHist(i+1) = sum(ImBin(:));
    end
end
