function datos_esfera = calcula_datos_esfera(centro, XAgrupacion, XFondo)
%CALCULA_DATOS_ESFERA datos_esfera = [radioConRuido, radioSinRuido, 
% radioCompromiso] 
%   Funcion que calcula los radios de la esfera

colorLejano = XAgrupacion(1, :);
radioConRuido = distancia(colorLejano, centro);
for i = 2:size(XAgrupacion, 1)
    dist = distancia(XAgrupacion(i, :), centro);
    if radioConRuido < dist
        %colorLejano = XAgrupacion(i, :);
        radioConRuido = dist;
    end
end

colorFondoCercano = XFondo(1, :);
radioSinRuido = distancia(colorFondoCercano, centro);
for i = 2:size(XFondo, 1)
    dist = distancia(XFondo(i, :), centro);
    if radioSinRuido > dist
        %colorFondoCercano = XFondo(i, :);
        radioSinRuido = dist;
    end
end

radioCompromiso = (radioConRuido + radioSinRuido)/2;

datos_esfera = [radioConRuido, radioSinRuido, radioCompromiso];
end

