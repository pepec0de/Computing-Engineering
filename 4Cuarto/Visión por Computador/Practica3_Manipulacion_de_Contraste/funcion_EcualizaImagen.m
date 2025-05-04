function Ieq = funcion_EcualizaImagen(I)
%FUNCION_ECUALIZAIMAGEN Summary of this function goes here
%   Detailed explanation goes here

h = imhist(I);
H = funcion_HistAcum(h);

[N, M] = size(I);

NM = N*M;

Ieq = uint8(zeros([N, M]));


for i = 1:N
    for j = 1:M
        Ieq(i, j) = max(0, round((255/NM)*H(I(i, j)+1)));
    end
end

end

