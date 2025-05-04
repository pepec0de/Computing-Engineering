function C = funcion_calcula_contraste(I)
%FUNCION_AUMENTA_BRILLO Summary of this function goes here
%   Detailed explanation goes here

B = mean(I(:));
    
[f, c] = size(I, 1:2);

C = 0;
for i = 1:f
    for j = 1:c
        C = C + (double(I(i, j)) - B)^2;
    end
end

C = sqrt(C/(f*c));

end

