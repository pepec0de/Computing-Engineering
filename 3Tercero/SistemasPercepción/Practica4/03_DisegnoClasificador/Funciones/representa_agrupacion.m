function representa_agrupacion(X, idx, k)

colores = ['.y'; '.r'; '.b'; '.m'];

for i = 1:k
    R = X(idx == i, 1);
    G = X(idx == i, 2);
    B = X(idx == i, 3);
    plot3(R, G, B, colores(i)), hold on;
end
end

