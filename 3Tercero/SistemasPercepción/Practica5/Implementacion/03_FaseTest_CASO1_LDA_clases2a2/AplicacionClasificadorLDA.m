clc, clear;

load('..\conjunto_datos_test.mat');
addpath('..\02_Funciones\');
addpath('..\01_GeneracionDatos\Funciones\');

load('../01_GeneracionDatos/DatosGenerados/datosProblema.mat');
load('../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat');

%% SELECCION DE DESCRIPTORES

dim = 3;

[XCircCuad, YCircCuad, espacioCcasCircCuad, JespacioCcasCircCuad,...
    nombresProblemaCircCuad] = ...
    funcion_seleccion_descriptores(Z, Y, datosProblema, [1, 2], dim);

[XCircTrian, YCircTrian, espacioCcasCircTrian, JespacioCcasCircTrian,...
    nombresProblemaCircTrian] = ...
    funcion_seleccion_descriptores(Z, Y, datosProblema, [1, 3], dim);

[XCuadTrian, YCuadTrian, espacioCcasCuadTrian, JespacioCcasCuadTrian,...
    nombresProblemaCuadTrian] = ...
    funcion_seleccion_descriptores(Z, Y, datosProblema, [2, 3], dim);

%% CALCULO DE FUNCIONES

[d12CircCuad, coeficientesCircCuad] =...
    funcion_calcula_LDA_2clases_3dim(XCircCuad, YCircCuad);

[d12CircTrian, coeficientesCircTrian] =...
    funcion_calcula_LDA_2clases_3dim(XCircTrian, YCircTrian);

[d12CuadTrian, coeficientesCuadTrian] =...
    funcion_calcula_LDA_2clases_3dim(XCuadTrian, YCuadTrian);

%% EVALUAMOS CLASIFICADORES 

numMuestras = size(XTest, 1);
for i = 1:numMuestras
    Xi = XTest(i, :);
    
    % Circulo vs Cuadrado
    XiOI = Xi(espacioCcasCircCuad);
    x1 = XiOI(1); x2 = XiOI(2); x3 = XiOI(3);
    valor_d12CircCuad = eval(d12CircCuad);
    
    % Circulo vs Triangulo
    XiOI = Xi(espacioCcasCircTrian);
    x1 = XiOI(1); x2 = XiOI(2); x3 = XiOI(3);
    valor_d12CircTrian = eval(d12CircTrian);

    % Cuadrado vs Triangulo
    XiOI = Xi(espacioCcasCuadTrian);
    x1 = XiOI(1); x2 = XiOI(2); x3 = XiOI(3);
    valor_d12CuadTrian = eval(d12CuadTrian);
    
    % Regla de decision
    if valor_d12CircCuad > 0 && valor_d12CircTrian > 0
        reconocimento = 'Reconocimiento objeto Circulo';
        color = [255 0 0];
    elseif valor_d12CircCuad < 0 && valor_d12CuadTrian > 0
        reconocimento = 'Reconocimiento objeto Cuadrado';
        color = [255 0 0];
    elseif valor_d12CuadTrian < 0 && valor_d12CircTrian < 0
        reconocimento = 'Reconocimiento objeto Triangulo';
        color = [255 0 0];
    end

    figure, subplot(2, 2, 1), 
        funcion_visualiza(I, IEtiq == i, color), title(reconocimento);

    subplot(2, 2, 2), title('Frontera Circulo-Cuadrado'), 
        funcion_representa_frontera(XCircCuad, YCircCuad, ...
        nombresProblemaCircCuad, coeficientesCircCuad);
        plot3(XiOI(1), XiOI(2), XiOI(3), 'ok');
        
    subplot(2, 2, 3), title('Frontera Circulo-Triangulo'),
        funcion_representa_frontera(XCircTrian, YCircTrian, ...
        nombresProblemaCircTrian, coeficientesCircTrian);
        plot3(XiOI(1), XiOI(2), XiOI(3), 'ok');
    
    subplot(2, 2, 4), title('Frontera Cuadrado-Triangulo'),
        funcion_representa_frontera(XCuadTrian, YCuadTrian, ...
        nombresProblemaCuadTrian, coeficientesCuadTrian);
        plot3(XiOI(1), XiOI(2), XiOI(3), 'ok');
end