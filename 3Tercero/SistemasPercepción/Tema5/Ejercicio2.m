%% Ejercicio 2 diapositiva 102
clc, clear;
clase1 = [2, 3, 3, 4; 1, 2, 3, 2];
clase2 = [6, 5, 7; 1, 2, 3];

clase1 = clase1';
clase2 = clase2';


n1 = length(clase1);
n2 = length(clase2);
n = n1 + n2;

mu1 = mean(clase1)';
mu2 = mean(clase2)';

cov1 = (2 - mu1(1))*(1 - mu1(2)) + (3 - 3)*(2 - 2) + (3 - 3)*(3 - 2) +...
    (4 - 3)*(2 - 2);

var111 = (2 - mu1(1)).^2 + (3 - 3).^2 + (3 - 3).^2 + (4 - 3).^2;
var122 = (1 - mu1(2)).^2 + (2 - 2).^2 + (3 - 2).^2 + (2 - 2).^2;

covClase1 = [var111, cov1; cov1, var122];

covClase1*(1 / (n1 - 1)), cov(clase1)

cov2 = (6 - mu2(1))*(1 - mu2(2)) + (5 - 6)*(2 - 2) + (7 - 6)*(3 - 2);
var211 = (6 - mu2(1)).^2 + (5 - 6).^2 + (7 - 6).^2;
var222 = (1 - mu2(2)).^2 + (2 - 2).^2 + (3 - 2).^2;

covClase2 = [var211, cov2; cov2, var222];

covClase2*(1 / (n2 - 1)), cov(clase2)

MCov = (1/(n - 2))*(covClase1 + covClase2);

% Funcion de decisión: dK(x) = DistMahalanobis^2(x, muK) = -(x -
% muK)'*inv(MCov)*(x - muK);

x1 = sym('x1', 'real');
x2 = sym('x2', 'real');
X = [x1; x2];
d1 = expand(-(X - mu1)'*inv(MCov)*(X - mu1));
d2 = expand(-(X - mu2)'*inv(MCov)*(X - mu2));

x1 = 4; x2 = 2; % clase 1
x1 = 6; x2 = 1; % clase 2

ed1 = eval(d1)
ed2 = eval(d2)

