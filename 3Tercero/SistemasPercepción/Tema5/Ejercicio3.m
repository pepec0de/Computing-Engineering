clc, clear;

%%

x1 = sym('x1', 'real');
x2 = sym('x2', 'real');
X = [x1; x2];
% Clases equiprobables -> balanceadas

mu1 = [0; 3];
mu2 = [5; 2];
mu3 = [1; 0];

MCov = [1/2, 0; 0, 1/4];

% Funcion decision: dK(x) = dist_Mahalanobis^2(x, muK) = -(x -
% muK)'*inv(MCov)*(x - muK)

d1 = expand( -(X - mu1)'*inv(MCov)*(X - mu1));
d2 = expand( -(X - mu2)'*inv(MCov)*(X - mu2));
d3 = expand( -(X - mu3)'*inv(MCov)*(X - mu3));

d12 = d1 - d2;
d13 = d1 - d3;
d23 = d2 - d3;