function representa_esfera(radio, centro)
%REPRESENTA_ESFERA Summary of this function goes here
%   Detailed explanation goes here
radio = round(radio);

centro = double(centro);
% Matrices de puntos de una esfera centrada en el origen de radio unidad
[R, G, B] = sphere(radio);

x = radio*R(:) + centro(1);
y = radio*G(:) + centro(2); 
z = radio*B(:) + centro(3);

plot3(x, y, z, '.b'), hold on, plot3(centro(1), centro(2), centro(3), '*r');

end

