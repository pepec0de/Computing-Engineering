clc, clear;

addpath('DatosGenerados\');
addpath('Funciones02');

load('conjunto_datos.mat');
load('datosProblema.mat');

%% REPRESENTACION ESPACIO DE CARACTERISTICAS
% Cada representación en una ventana tipo figure.
% Para ello, programar la siguiente función:
% funcion_representa_datos(X,Y, espacioCcas, nombresProblema)
% donde espacioCcas es un vector de dos o tres elementos que indica los 
% descriptores que definen el espacio de características (representación
% bidimensional o tridimensional.

espacioCcas = [4 5];
funcion_representa_datos(X, Y, espacioCcas, datosProblema);


%% REPRESENTACION DE HISTOGRAMA Y DIAGRAMA DE CAJAS
% Para cada descriptor, abrir dos ventanas tipo figure
% una para representar histogramas y otra para diagramas de caja
% En cada una de ellas se representan los datos del descriptor para las
% distintas clases del problema en gráficas independientes
% - Histogramas: tantas filas de gráficas como clases
% - Diagramas de caja: tantas columnas de gráficas como clases

% Dado un conjunto de datos X-Y, y definidas las variables representativas
% del problema: numClases, codifClases, nombreClases, numDescriptores,
% nombreDescriptores

numClases = length(datosProblema.clases);
numDescriptores = length(datosProblema.descriptores);
[numMuestras, ~] = size(X);
for j=1:numDescriptores
    % Valores máximo y mínimos para representar en la misma escala
    vMin = min(X(:,j));
    vMax = max(X(:,j));
    
    hFigure = figure; hold on
    bpFigure = figure; hold on
    
    for i=1:numClases
        Xij = X(Y == datosProblema.codificacion(i),j); % datos clase i del descriptor j
        
        figure(hFigure)
        subplot(numClases,1,i), imhist(Xij),
        xlabel(datosProblema.descriptores{j})
        ylabel('Histograma')
        axis([ vMin vMax 0 numMuestras/4]) % inf escala automática eje y
        title(datosProblema.clases{i})
        
        figure(bpFigure)
        subplot(1,numClases,i), boxplot(Xij)
        xlabel('Diagrama de Caja')
        ylabel(datosProblema.descriptores{j})
        axis([ 0 2 vMin vMax ])
        title(datosProblema.clases{i})
    end
end

