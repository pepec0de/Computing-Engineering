function umbral = funcion_isodata(h)

% Calculamos umbral inicial
umbral = 0;
for i = 1:256
    umbral = umbral + h(i)*(i-1);
end

umbral = round(umbral / sum(h));

diferencia = false;
while diferencia
    G1 = h(1:umbral);
    G2 = h(umbral+1:256);

    % Calculamos M1
    M1 = 0;
    for i = 1:umbral
        M1 = M1 + G1(i)*(i-1);
    end
    M1 = round(M1/sum(G1));

    % Calculamos M2
    M2 = 0;
    for i = 1:(256 - umbral+1 +1)
        M2 = M2 + G2(i)*(i-1);
    end
    M2 = round(M2/sum(G2));

    T = 0.5*(M1 + M2);
    diferencia = umbral == T;
    umbral = T;
end

end

