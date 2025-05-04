% Funci�n que compara dos matrices de la misma dimension
% Salida: true o false, dependiendo si las matrices son iguales o distintas

% Metodolog�a: 
% - Calcular una matriz diferencias (resta de las dos matrices de entrada)
% - Calcular el valor m�ximo y m�nimo de esta matriz diferencias
% - Si el valor m�ximo es igual al valor m�nimo y cualquiera de ellos tiene
% el valor 0, entonces las matrices de entrada son iguales

% Observaci�n: si las matrices no tienen la misma dimensi�n la funci�n
% �nicamente debe advertir por el command window este hecho (para ello,
% puedes utilizar la funci�n instrucci�n: disp('cadena de texto a mostrar').

function varLogica = my_compara_matrices(m1,m2)
    
    % comprobamos dimensiones
    
    [numFilas_m1 , numColumas_m1] = size(m1);
    [numFilas_m2 , numColumas_m2] = size(m2);
    
    if (numFilas_m1 ~= numFilas_m2) || (numColumas_m1 ~= numColumas_m2)
        varLogica = false;
        
    else % s�lo entra si las matrices  son iguales
        m1 = double(m1); % para asegurar que hacemos la diferencia en el  formato adecuado
        m2 = double(m2);

        dif = m1-m2;

        vMin = min(dif(:));
        vMax = max(dif(:));

        if vMin==vMax && vMin==0
            varLogica = true;
        else
            varLogica = false; 
        end
    end
end