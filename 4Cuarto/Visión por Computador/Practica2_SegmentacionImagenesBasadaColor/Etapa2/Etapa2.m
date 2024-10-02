clc, clear
close all

load("../Etapa1/VariablesEtapa1.mat");
addpath("../FuncionesMatlabMaterialAyuda");
%%%% PASO 2.1: SEGMENTACIÓN DEL COLOR ROJO FRESA

%{
R G B H S I Y U V L a b
1 2 3 4 5 6 7 8 9 0 1 2
%}

%% 2.1.1 Seleccion de conjuntos de descriptores
n_dimensiones = size(ValoresColores, 2);
CodifRojoFresa = CodifValoresColores == 255;

% 2.1.1.1

% ??? [B W] = scattermat(ValoresColores, CodifRojoFresa);


%% 2.1.1.2 Seleccionar los conjuntos de 3, 4, 5 y 6 descriptores que proporcionan mayor separabilidad
mejor_J = 0;
mejor_vct_caracs = [];
for num_d = 3:6 % num_d : numero de descriptores
    combinaciones = nchoosek(1:n_dimensiones, num_d);
    for c = 1:size(combinaciones, 1)
        J = indiceJ(ValoresColores(:, combinaciones(c, :)), CodifRojoFresa);
        if mejor_J < J
            mejor_J = J;
            mejor_vct_caracs = combinaciones(c, :);
        end
    end
end

mejor_J_3caracs = 0;
mejor_vct_3caracs = [];
combinaciones = nchoosek(1:n_dimensiones, 3);
for c = 1:size(combinaciones, 1)
    J = indiceJ(ValoresColores(:, combinaciones(c, :)), CodifRojoFresa);
    if mejor_J_3caracs < J
        mejor_J_3caracs = J;
        mejor_vct_3caracs = combinaciones(c, :);
    end
end
% sol: RSL


%% Representacion

figure, hold on;
% Rojo fresa
PoI = ValoresColores(CodifRojoFresa == 1, mejor_vct_3caracs);
plot3(PoI(:, 1), PoI(:, 2), PoI(:, 3), '.r'), hold on;

% Otros
PoI = ValoresColores(CodifRojoFresa == 0, mejor_vct_3caracs);
plot3(PoI(:, 1), PoI(:, 2), PoI(:, 3), '.k'), hold on,
legend('Rojo fresa', 'Otro'),
title("Representacion con caractericistas RSL"),
xlabel('R'), ylabel('S'), zlabel('L');

%% Guardar

save("VariablesEtapa2.mat", "mejor_J", "mejor_vct_caracs", "mejor_J_3caracs", "mejor_vct_3caracs");
