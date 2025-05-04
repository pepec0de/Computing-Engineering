function Io = funcion_imfilter(I, H, opcionrelleno)

if opcionrelleno == 'zeros'
    opcionrelleno = 0;
end

[NMASK, MMASK] = size(H);

extraN = floor(NMASK/2); extraM = floor(MMASK/2);


Iamp = padarray(I, [extraN, extraM], opcionrelleno);


p = Roi .* H
end
