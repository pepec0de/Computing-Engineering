clc, clear;
addpath('../../02_FaseEntrenamiento/Funciones/');
%addpath('../../01_GeneracionDatos/Funciones/');

%% CARGAMOS LOS  DATOS DE INTERES

load('DatosGenerados\espacio_ccas_circ_cuad.mat');

%% CLASIFICADOR QDA


[vectorMedias, matricesCovarianzas, probabilidadPriori] = funcion_ajusta_QDA(XoI, YoI);

%%
[YQDA, d] = funcion_aplica_QDA(XoI, vectorMedias, matricesCovarianzas,...
    probabilidadPriori, datosProblemaOI.codificacion);


%% DEBUGEO
X = XoI;
valoresClases = datosProblemaOI.codificacion;

[n, p] = size(X);
YQDA = zeros(n, 1);
d = zeros(n, 1);
nClases = length(valoresClases);

% Declaracion de p variables simbolicas
Xsym = sym('Xsym', [p 1]);

% Obtenemos las funciones de decision de cada clase
dClases = [];
for i = 1:nClases
    Mi = vectorMedias(i, :)';
    MCovi = matricesCovarianzas(:, :, i);
    Probai = probabilidadPriori(i);
    dClases = [dClases; expand(-0.5*(Xsym - Mi)' * inv(MCovi) * (Xsym - Mi) -0.5*log(det(MCovi))...
    + log(Probai) )];
end