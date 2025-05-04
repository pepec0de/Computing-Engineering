close all, clear, clc

load("Vars.mat");


%%

save("Vars.mat", "I", "Iroi");

[f, c] = find(Iroi);

fmin = min(f); fmax = max(f); cmin = min(c); cmax = max(c);

Ired = I(fmin:fmax, cmin:cmax);

B = mean(double(Ired(:)));
C = std(double(Ired(:)), 1);

% Otra forma de caluclar el brillo y contraste

h = imhist(Ired);
brillo = 0;
for g = 0:255
    brillo = brillo + h(g+1)*g;
end
brillo = brillo / sum(h);


contraste = 0;
for g = 0:255
    contraste = contraste + h(g+1)*(g - brillo)^2;
end
contraste = sqrt(contraste / sum(h));

ImasBrillo = Ired + 100;

ImenosBrillo = Ired - 100;

BmasBrillo = mean(ImasBrillo(:));

BmenosBrillo = mean(ImenosBrillo(:));

CmasBrillo = std(double(ImasBrillo(:)), 1);
CmenosBrillo = std(double(ImenosBrillo(:)), 1);


%% Manipulacion de brillo
DES = 50;

I1 = Ired + DES;

Id = double(Ired);

pmin = min(Id(:)); pmax = max(Id(:));
qmin = 0; qmax = 255;

m = (qmax - qmin) / (pmax - pmin);
I3 = uint8(qmin + m*(Id - pmin));



cantidad = (pmax - pmin) / 3;
qmin = pmin + cantidad; qmax = pmax - cantidad;

m = (qmax - qmin) / (pmax - pmin);
I4 = uint8(qmin + m*(Id - pmin));

figure, imshow(Ired);
figure, imshow(I1);
figure, imshow(I3);
figure, imshow(I4);


%% Uso de imfilter

w = 9;
hp = (1/(w*w)) * ones(w); % promedio

hl = [-1 -1 -1; -1 8 -1; -1 -1 -1]; % laplaciana

Ip = imfilter(Ired, hp);

Il = imfilter(Ired, hl);

% symmetric : espejo

% replicate : la ultima fila (o las ultimas) se replican