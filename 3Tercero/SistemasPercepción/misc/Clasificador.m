%% Modelado de puntos esferico

% Pasamos a vector de columnas
% P = centroideNubePuntosColor';
% NP = XColor';
% vectorDistancias = calcula_Distancia_punto_nubepuntos(P, NP);
% r = max(VectorDistancias)

% datosEsfera = calcula_esfera(XColor, XFondo)
% Formato datosEsfera (1x6): (1:3) -> posicion centroide, (4:6) r1, r2, r3
% r1 = max(vectorDistancias)

% representa_esfera(centro, radio) - representa la esfera como un plot 3
%{

%}

% Ib = calcula_deteccion_esfera_imagen(Ic, datosEsfera) 
% MatrizDeDistancias = sqrt( (R - Rc).^2 + (B - Bc).^2 + (B - Bc).^2 )
% Ib = MD < r