%% CIRCULOS VS CUADRADOS

%% Carga de datos

load('../../01_GeneracionDatos/DatosGenerados/datosProblema.mat');
load('../../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat');

%% GENERACION XOI

X = Z;

codifClases = unique(Y);
clasesOI = [8; 4];


XoI = X(filasOI, 1:numDescriptores - 1);

dim = 3;
[espacionCcas, JespacioCcas] = funcion_selecciona_vector_ccvas(XoI, YoI, dim);

XoI = XoI(:, espacionCcas);

nombresProblemaOI.descriptores = nombresProblema(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases;
nombresProblemaOI.simbolos = nombresProblema.simbolos;

funcion_representa_datos(XoI, YoI, 1:dim -1, nombresProblemaOI)

