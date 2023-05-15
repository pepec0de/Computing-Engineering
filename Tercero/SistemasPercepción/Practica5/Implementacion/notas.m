addpath('Funciones')
addpath('Entrenamiento')

nombreClases = {'Circulo', 'Cuadrado', 'Triangulo'};

numClases = length(nombreClases);

codifClases = [1, 2, 3];
numImagenesPorClase = 2;

X = [];
Y = [];

for i = 1:numClases

    for j = 1:numImagenesPorClase
        nombreImagen = [nombreClases{i} num2str(j, '%02d') 'jpg'];
        I = imread(nombreImagen);
        Ib = I < 255*graythresh(I);

        % Filtramos Ib
        Ib = funcion_elimina_regiones_ruidosas(Ib);

        [IEtiq, N] = bwlabel(Ib);
    
        %XImg = funcion_calcula_descriptores_extent_hu_imagen(IEtiq, N);
        %XImg = funcion_calcula_descriptores_extent_invRot_hu_imagen(IEtiq, N);
        YImg = codifClases(i)*ones(N, 1);
    
        X = [X; XImg];
        Y = [Y; YImg];
    end
end

nombresProblema.clases = nombreClases;
nombreProblema.descriptores = {'Extent', 'Hu1', 'Hu2', 'Hu3', 'Hu4', 'Hu5', 'Hu6', 'Hu7', 'Hu8'};
nombresProblema.simbolos = {'*b', '+r'};

espacioCcas = [4, 6, 7];
funcion_representa_datos(X, Y, espacioCcas, nombresProblema);

IbCentro = Funcion_Centra_Objeto(Ib);
ang = 180;
Ib_a = imrotate(IbCentro, ang);
figure, imshow(Ib_a);


%% PROCESO DE RECONOCIMIENTO DE FIGURAS GEOMETRICAS

% Binarizar y etiquetar Imagen
% Sacar descriptores en XImg
% Normaliar XImg -> Zimg con la media y desv. tipica del entrenamiento
% Comparar descriptores normalizados del primer objeto con la funcion de
% decisión


%% ESTRUCTURA DE DIRECTORIOS

% 01_GeneracionDatos
% \DatosGenerados\
% \Funciones\
% \Paso1_GeneracionConjuntoDeDatos.m
% \Paso2_RepresentacionDatos.m
% \Paso3_EstandarizacionDatos.m
% 02_FaseEntrenamiento
%
% 03_FaseTest
%
% Imagenes

%% Obtener los mejores descriptores


% Sacar todos los valoresJ de todas las columnas (descriptores)
% Estudiar la separabilidad viendo las combinaciones de 3 columnas
% Funcion: nchoosek(n, k) para obtener todas las combinaciones del vector n 
% de k elementos

% Obtener el índiceJ que de la mayor separabilidad

%% Paso4

codifClases = unique(Y);

posClaseOI = [1 3];
valoresCodifClasesOI = codifClases(posClasesOI);

posClasesNoOI = find(not(ismember(codifClases, valoresCodifClasesOI)));

valoresCodifClasesNoOI = codifClases(posClaseNoOI);

% Seleccionamos descriptores desde 1 a 22 para no coger el descriptor de
% Euler
XoI = X(:, 1:22);
YoI = Y;

XoI(Y == valoresCodifClasesNoOI, :) = [];
YoI(Y == valoresCodifClasesNoOI, :) = [];

dim = 3;
[espacioCcas, JespacioCcas] = funcion_seleccion_vector_espacioCcas(XoI, YoI);

XoI = XoI(:, espacioCcas);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases(posClasesOI);
nombresProblemaOI.simbolos = nombresProblema.simbolos(posClasesOI);

funcion_representa_datos(XoI, YoI, 1:3, nombresProblemaOI)

%{
    Hierarchy del proyecto:

    Implementacion
        |
        |---> 01_GeneracionDatos
        |       |
        |       |--> DatosGenerados
        |       |       |
        |       |       |--> conjunto_datos.mat
        |       |       |--> conjunto_datos_estandarizados.mat
        |       |       |--> datos_estandarizacion.mat
        |       |       +--> nombres_problema.mat
        |       | 
        |       |--> Funciones
        |       |--> Paso1_GeneracionConjuntoDatosInformacion
        |       |--> Paso2_RepresentacionDatos
        |       +--> Paso3_EstandarizacionDatos
        |
        |
        |
        |---> 02_FaseEntrenamiento
        |---> 03_FaseTest
        +---> Imagenes
%}

clases = [];
filasOI = false(numDatos, 1);
for i = 1:length(clases)
    filasOI = or(filasOI, Y == clases(i));
end
filasOI_C1 = filasOI;