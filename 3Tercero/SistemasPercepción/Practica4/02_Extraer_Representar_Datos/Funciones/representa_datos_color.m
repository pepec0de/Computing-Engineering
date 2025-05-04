function representa_datos_color(X, Y, titulo)
%REPRESENTA_DATOS_COLOR
figure(), hold on;

% Representamos los puntos del color de seguimiento
PoI = X(Y == 1, :);
plot3(PoI(:, 2), PoI(:, 3), PoI(:, 4), '.y'), hold on;

% Representamos los puntos del color de fondo
PoI = X(Y == 0, :);
plot3(PoI(:, 2), PoI(:, 3), PoI(:, 4), '.k'), hold on, 
legend('Color seguimiento', 'Color fondo'), title(titulo),
xlabel('R'), ylabel('G'), zlabel('B');
end

