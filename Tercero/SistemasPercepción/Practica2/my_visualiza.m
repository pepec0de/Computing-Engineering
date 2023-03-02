%MY_VISUALIZA Summary of this function goes here
% Ii: imagen de entrada, que puede ser en color o en escala de grises.
% Ib: matriz binaria del mismo número de filas y columnas que la imagen de
%     entrada, puede ser tipo logical o double.
% Color: vector con 3 valores de 0 a 255, con la codificación RGB de un
%   determinado color.
% flagRepresenta: variable opcional que, cuando se pasa como un true
%   lógico, indica a la función que debe generar una ventana tipo figure con la
%   representación de la imagen de salida.
% Io: imagen en color de salida que representa la información de Ib (%1s 
%   binarios) en el color especificado en Color, sobre la imagen de entrada Ii.

function Io = my_visualiza(Ii, Ib, Color, flagRepresenta)
    [nFilas nColumnas nComponentes] = size(Ii);

    if nComponentes > 1
        Io = Ii;
    else
        nComponentes = 3;
        for i = 1:3
            Io(:,:,i) = Ii;
        end
    end


    for i = 1:nFilas
        for j = 1:nColumnas
            if Ib(i, j) == 1
                for k = 1:nComponentes
                    Io(i, j, k) = Color(k);
                end
            end
        end
    end

    if flagRepresenta
        imtool(Io);
    end
end