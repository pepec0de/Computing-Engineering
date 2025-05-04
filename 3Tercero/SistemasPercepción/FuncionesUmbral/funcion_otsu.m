function umbral = funcion_otsu(h)

nPix = sum(h);
valor_medio = sum((1:256) * h) / nPix;
Vari = zeros(256,1);

for i = 2:255
    % Agrupacion 1
    numPix1 = sum(h(1:i));
    media1 = sum((1:i) * h(1:i));
    
    u1 = (media1/numPix1);
    w1 = (numPix1/nPix);
    
    % Agrupacion 2
    numPix2 = sum(h(i+1:256));
    media2 = sum((i+1:256) * h(i+1:256));
    
    u2 = (media2/numPix2);
    w2 = (numPix2/nPix);

    Vari(i) = w1*(u1 - valor_medio).^2 + w2 * (u2 - valor_medio).^2;
end
[valor, umbral] = max(Vari);
umbral = umbral-1;

end

