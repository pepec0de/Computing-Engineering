function representa2caracs(ValoresColores, CodifValoresColores, cols, titulo, xl, yl)

figure, hold on;
% Rojo fresa
PoI = ValoresColores(CodifValoresColores == 255, cols);
plot(PoI(:, 1), PoI(:, 2), '.r'), hold on;

% Verde fresa
PoI = ValoresColores(CodifValoresColores == 128, cols);
plot(PoI(:, 1), PoI(:, 2), '.g'), hold on;

% Verde planta
PoI = ValoresColores(CodifValoresColores == 64, cols);
plot(PoI(:, 1), PoI(:, 2), '.b'), hold on;

% Negro lona
PoI = ValoresColores(CodifValoresColores == 32, cols);
plot(PoI(:, 1), PoI(:, 2), '.k'), hold on,
legend('Rojo fresa', 'Verde fresa', 'Verde planta', 'Negro lona'),
title(titulo),
xlabel(xl), ylabel(yl);

end