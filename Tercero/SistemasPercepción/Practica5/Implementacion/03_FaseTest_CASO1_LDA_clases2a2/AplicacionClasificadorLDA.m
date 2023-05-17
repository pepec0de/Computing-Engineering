

%% EVALUAMOS CLASIFICADORES 

for i = 1:numMuestras
    Xi = XTest(i, :);

    % Cuadrado-Triangulo
    XiOI = Xi(i, espacioCcasCuadTrian);
    x1 = XiOI(1); x2 = XiOI(2); x3 = XiOI(3);
    valor_d12_CuadTrian = eval(d12CuadTrian);
    
    % Regla de decision
    if valor_d12_CircCuad > 0 && valor_d12CircTrian > 0
        claseOI = nombresCircCuad.clases{1};
        reconocimento = ['Reconocimiento Objeto ' claseOI];
        color = [255 0 0];
    elseif valor_d12_CircCuad < 0 && valor_d12_CuadTrian > 0
        claseOI = nombresCircCuad.clases{2};
        reconocimento = ['Reconocimiento Objeto ' claseOI];
        color = [255 0 0];
    elseif valor_d12_CuadTrian < 0 && valor_d12_CircTrian < 0
        claseOI = nombresCircCuad.clases{3};
        reconocimento = ['Reconocimiento Objeto ' claseOI];
        color = [255 0 0];
    end
end