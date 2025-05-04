clc, clear;

clase1 = [1, 3; 2, 1; 2, 2; 2, 3; 2, 4; 3, 2; 3, 3; 4, 3; 5, 2; 1, 2];
clase2 = [4, 5; 5, 5; 5, 6; 4, 7; 6, 5; 6, 6; 6, 7; 7, 6; 4, 6; 8, 7];

mu1 = mean(clase1)';
mu2 = mean(clase2)';

x1 = sym('x1', 'real');
x2 = sym('x2', 'real');
X = [x1; x2];

x1 = 8;
x2 = 7;

d1 = expand( -(X - mu1)'*(X - mu1));
ed1 = eval(d1);

d2 = expand( -(X - mu2)'*(X - mu2));
ed2 = eval(d2);

ed1 > ed2