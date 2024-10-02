clear, clc, close all

load("../../Etapa1/VariablesEtapa1.mat");
load("../VariablesEtapa2.mat");

%%
XTrain = ValoresColores;
YTrain = CodifValoresColores == 255;

inputs = XTrain';
outputs = YTrain';
NeuronasCapaEntrada = size(inputs, 1);

NeuronasCapaOculta1=5;
NeuronasCapaOculta2=15;

CapasRed = [NeuronasCapaEntrada NeuronasCapaOculta1 NeuronasCapaOculta2];

net=feedforwardnet(CapasRed); % Estructura de la red. 

NumCapas = length(CapasRed)+1; % para contar la de salida.
net.layers{1:NumCapas}.transferFcn = 'logsig';

view(net) % PARA VER LA ESTRUCTURA DE RED

%% TRAIN

net.trainParam.epochs=500; 
net=train(net, inputs, outputs);

%% GUARDAR
save("RedNeuronal.mat", "net");