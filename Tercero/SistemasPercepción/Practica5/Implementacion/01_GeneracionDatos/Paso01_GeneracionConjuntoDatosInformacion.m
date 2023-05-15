clc, clear;
addpath('Funciones/')
addpath('../../Entrenamiento/')

%% Descripcion del problema
nClases = 3;
nImagenesClase = 2;
codifClases = [8, 4, 3];
nombresClases = {'Circulo', 'Cuadrado', 'Triangulo'};
simbolosClases = {'*r', '*g', '*b'};

nombresDescriptores = {'COMPACTICIDAD', 'EXCENTRICIDAD', 'SOLIDEZ_CHULL', 'EXTENSION_BB', 'EXTENSION_BB_INV'};

nDescript = length(nombresDescriptores);
sHu = 'HU';
nHu = 7;
for i = 1:nHu
    nombresDescriptores{nDescript+i} = [sHu num2str(i, "%d")];
end

nDescript = length(nombresDescriptores);
sDF = 'F';
nDF = 10;
for i = 1:nDF
    nombresDescriptores{nDescript+i} = [sDF num2str(i, "%02d")];
end

nombresDescriptores{length(nombresDescriptores)+1} = 'EULER';

datosProblema.clases = nombresClases;
datosProblema.codificacion = codifClases;
datosProblema.simbolos = simbolosClases;
datosProblema.descriptores = nombresDescriptores;

%% Inicializamos los conjuntos X e Y
X = [];
Y = [];

for clase = 1:nClases

for img = 1:nImagenesClase
    % 1.1- BINARIZAR CON METODOLOGÍA DE SELECCIÓN AUTOMÁTICA DE UMBRAL
    % Genera: Ibin. Hay que tener programadas las funciones de elección de
    % umbral vistas en el tema anterior: 
    % funcion_min_entre_max, funcion_isodata, funcion_otsu.
    
    nombreImagen = [datosProblema.clases{clase} num2str(img, "%02d") '.jpg'];
    %nombreImagen = 'Triangulo01.jpg';
    I = imread(nombreImagen);
    umbral = graythresh(I)*255; 
    % umbral = funcion_otsu(imhist(I));
    
    % Los objetos a seleccionar son más oscu    ros que el fondo
    Ibin = I <= umbral;
    
    % Más claros que el fondo
    %Ibin = I >= umbral;
    
    % 1.2.- ELIMINAR POSIBLES COMPONENTES CONECTADAS RUIDOSAS:
    % Para ello, se debe programar la siguiente funcion:
    % IbinFilt = funcion_elimina_regiones_ruidosas(Ibin);
    
    IbinFilt = funcion_elimina_regiones_ruidosas(Ibin);
    %figure, imshow(IbinFilt);
    
    
    % 1.3.- ETIQUETAR.
    % Genera matriz etiquetada Ietiq y número N de agrupaciones conexas
    
    [IEtiq, N] = bwlabel(IbinFilt);
    
    % 1.4.- CALCULAR TODOS LOS DESCRIPORES DE CADA AGRUPACIÓN CONEXA
    % Genera Ximagen - matriz de N filas y 23 columnas (los 23 descriptores
    % generados en el orden indicado en la práctica)
    % XImagen = funcion_calcula_descriptores_imagen(Ietiq,N);
    
    XImagen = funcion_calcula_descriptores_imagen(IEtiq, N);
    
    % 1.5.- GENERAR Yimagen
    % Genera Yimagen - matriz de N filas y 1 columna con la codificación
    % empleada para la clase a la que pertenecen los objetos de la imagen
    YImagen = codifClases(clase)*ones(N,1);

    X = [X; XImagen];
    Y = [Y; YImagen];
end
end

%% Guardamos los datos

save('DatosGenerados\conjunto_datos.mat', 'X', 'Y');
save('DatosGenerados\datosProblema.mat', 'datosProblema');