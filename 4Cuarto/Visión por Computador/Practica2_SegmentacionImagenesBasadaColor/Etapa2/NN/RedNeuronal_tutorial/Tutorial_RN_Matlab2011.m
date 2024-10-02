clear
clc
close all


% SE ASUME QUE SE TRABAJA CON UN CONJUNTO DE DATOS ENTRADA-SALIDA YA CREADOS (HABR�A QUE CARGARLOS): 
% inputs-outputs. 

% inputs: en cada columna los 
% descriptores de cada muestra de entrenamiento.
% dimensiones de inputs: tantas filas como descriptores tenga y tantas
% columnas como muestras de entrenamiento haya.

% outputs = codificaci�n binaria de las clases asociada a cada columna de inputs.
% dimensiones outputs: tantas filas como digitos en la codificaci�n tenga y
% tantas columnas como muestras de entrenamiento haya

% la nueva versi�n de matlab admite cualquier valor para representar las
% clases.

% CREACI�N DE ESTRUCTURA DE RED

[NeuronasCapaEntrada temp] = size(inputs);

NeuronasCapaOculta1=15;
NeuronasCapaOculta2=15;
% Etc, etc, etc

CapasRed = [NeuronasCapaEntrada NeuronasCapaOculta1 NeuronasCapaOculta2];

% Con esta configuraci�n se crea una RN de cuatro capas:

net=feedforwardnet(CapasRed); % Estructura de la red. 

view(net) % PARA VER LA ESTRUCTURA DE RED

% Para el ejemplo anterior:

% una capa de entrada de NueronasCapaEntrada neuronas
% dos capas ocultas, cada una con 15 neuronas
% una capa de salida: su n�mero de neuronas se adapta a la dimension de
% ouputs cuando se entrene.

% Por defecto, las funciones de activaci�n de las neuronas de las capas:
% 'tansig' para las capas de entrada y ocultas. 
% 'purelin' para las de salida.

% Si se quieren cambiar las funciones de activacion:
% net.layers{NumeroDeCapa}.transferFcn = 'nombreMatlabFunci�nActivaci�n'

% Funciones de activaci�n
% LOGSIG: sigmoidea: salidas acotadas entre 0 y 1
% TANSIG: tangente hiperbolica, salidas actotadas entre -1 y 1
% PURELIN: salida lineal

% Ejemplo: si se quiere cambiar todas las capas a logsig.

% NumCapas = length(CapasRed)+1; % para contar la de salida.
%  net.layers{1:NumCapas}.transferFcn = 'logsig'; 

% Si solo se quiere cambiar la de salida:
% net.layers{NumCapas}.transferFcn = 'logsig'


% ENTRENAMIENTO CON TRAIN
net.trainParam.epochs=500; % numero maximo de epocas - normalmente para antes
% cuando el gradiente descendiente es m�nimo o cuando el error en la muestra de validaci�n que el
% se coge por defecto no disminuye en 6 �pocas.

net=train(net,inputs,outputs);


save net net % PARA SALVAR LA RED ENTRENADA

% SIMULACI�N

% Para simular: sim(net,vectorinoputs) o net(vectorinputs)

% Ejemplo para comprobar los resultados sobre todas las muestras disponibles 
% de dos clases con codificaciones 0 y 1's.

Matriz_Test=inputs(:,outputs==1);
rango=size(Matriz_Test);
m=rango(2);
res=[];

for i=1:m
    salida=(sim(net,Matriz_Test(:,i)));
    res = [res salida];
   
end

res'