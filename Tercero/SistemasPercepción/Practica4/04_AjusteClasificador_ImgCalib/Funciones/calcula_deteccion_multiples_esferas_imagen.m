function Ib = calcula_deteccion_multiples_esferas_imagen(I, multiples_esferas, tipoRadio)
%CALCULA_DETECCION_MULTIPLES_ESFERAS_IMAGEN Summary of this function goes here
%   Detailed explanation goes here

% Nos añadimos la funcion distancia
addpath("..\03_DisegnoClasificador\Funciones\");
[nFilas, nCol, ~] = size(I);
Ib = false(nFilas, nCol);

% Iteramos por todos los pixeles de la imagen
for i = 1:nFilas
    for j = 1:nCol
        color = reshape(I(i, j, :), [1 3]);
        % Iteramos por cada agrupacion
        for a = 1:size(multiples_esferas, 1)
            dist = distancia(color, multiples_esferas(a, 1:3));
            if dist <= multiples_esferas(a, tipoRadio)
                % Pertenece a alguna agrupacion por lo que cortamos el
                % bucle
                Ib(i, j) = true;
                break
            end
        end
    end
end


rmpath("..\03_DisegnoClasificador\Funciones\");
end

