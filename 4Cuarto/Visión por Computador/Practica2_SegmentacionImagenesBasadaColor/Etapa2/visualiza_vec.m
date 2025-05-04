function Io = visualiza_vec(Ii, Ib, Color, flagRepresenta)
% MY_VISUALIZA
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

    [nFilas, nColumnas, nComponentes] = size(Ii);
    
    Io = uint8([nFilas, nColumnas, 3]);
    
    bin = size(Color, 1) == 1;

    if nComponentes > 1
        Io = uint8(Ii);
    else
        for i = 1:3
            Io(:,:,i) = uint8(Ii);
        end
    end


    for i = 1:nFilas
        for j = 1:nColumnas
            if bin && Ib(i, j) == 1
                Io(i, j, :) = uint8(Color);
            elseif ~bin & Ib(i, j) ~= 0
                Io(i, j, :) = uint8(Color(Ib(i, j), :));
            end
            
        end
    end

    
    if flagRepresenta
        imshow(Io);
    end
end