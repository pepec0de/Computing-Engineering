function Distancia = distancia(A, B)

if size(A) == size(B)
    A = double(A);
    B = double(B);
    Distancia = sqrt (sum((A - B).^2));
end
end

