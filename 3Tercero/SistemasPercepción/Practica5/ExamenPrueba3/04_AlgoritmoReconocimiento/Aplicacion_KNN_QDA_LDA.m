clear,clc

addpath('..\01_GeneracionDatos\Funciones\');
addpath('..\01_GeneracionDatos\DatosGenerados\');
addpath('Test\')

% Añadir paths de las imagenes de test y funciones a usar.

% Ahora generamos el conjunto de descriptores de todos los objetos de
% interes presentes en la imagne a tratar

I = imread('G3.tif');

Ib = I < 255*graythresh(I);

Ibin = funcion_elimina_regiones_ruidosas(Ib);

[IEtiq , N] = bwlabel(Ibin);

XImagen = funcion_calcula_descriptores_objeto(IEtiq , N);

% Ahora estandarizamos los datos

load('..\01_GeneracionDatos\DatosGenerados\medias_desviaciones_estandarizacion.mat')
load('..\01_GeneracionDatos\DatosGenerados\nombresProblema.mat')

Z = (XImagen-Vec_Medias);

for i=1:size(Z,1)

    for j=1:size(Z,2)-1

        Z(i,j) = Z(i,j) / (Vec_Desviacion(1,j)+eps);

    end
    
end

% Cargamos los .mat creados anteriormente en la fase de entrenamiento

% Para A-C-D y E-F-G LDA

load('..\02_FaseDeEntrenamiento_LDA\DatosGenerados\Datos_espacioCcas_ACD_EFG.mat')
load('..\02_FaseDeEntrenamiento_LDA\DatosGenerados\LDA_ACD_EFG.mat')

coeficientes_d12_LDA = coeficientes_d12;
d1_LDA = d1;
d2_LDA = d2;
d12_LDA = d12;
espacioCcas_LDA = espacioCcas;
X_LDA = XoI;
Y_LDA = YoI;
nombresProblemas_LDA = nombresProblemasOI;

Xct = Z(:,espacioCcas_LDA);
valor_LDA = zeros(N,1);

for i=1:N

     x1 = Xct(i,1); 
     x2 = Xct(i,2);
     x3 = Xct(i,3);

     valor_LDA(i,1) = eval(d12_LDA);

end

% cargar datos clasificador QDA E_F_G

load('..\04_FaseDeEntrenamiento_QDA\DatosGenerados\Datos_espacioCcas_E_F_G.mat')
load('..\04_FaseDeEntrenamiento_QDA\DatosGenerados\QDA_E_F_G.mat')

vMedias = vectorMedias;
mCova = matricesCovarianzas;
proba = probabilidadPriori;
espacioCcas_QDA = espacioCcas;
X_QDA = XoI;
Y_QDA = YoI;
nombresProblemas_QDA = nombresProblemasOI;

Xcc = Z(:,espacioCcas_QDA);
valoresClases =[5 6 7];

YTest = funcion_aplica_QDA(Xcc,vMedias,mCova,proba,valoresClases);

% cargar datos clasificador KNN de las letras A-C-D

load('..\03_FaseDeEntrenamiento_KNN\DatosGenerados\Datos_espacioCcas_A_C_D_knn.mat')

espacioCcas_KNN = espacioCcas;
X_Train = XTrain;
Y_Train = YTrain;
nombresProblemas_KNN = nombresProblemasOI;
K = 5;
Xknn = Z(:,espacioCcas_KNN);

YTestknn = funcion_knn(Xknn,X_Train,Y_Train,K);

% Clasificacion de cada uno de los objetos de la imagen 

for i=1:N

    % decision

    Ibb = IEtiq==i;
    color=[0 255 0];
    
    Io = funcion_visualiza(IEtiq,Ibb,color,true);

    subplot(2,2,1) , imshow(Io);

    % regla de clasificacion:

    if (Z(i,23) == -1)    % B

        reconocimiento = ['la Letra es una : ' 'B'];
        title(reconocimiento);

    elseif valor_LDA(i) >0               % A_C_D

                if YTestknn(i) ==1

                    reconocimiento = ['la Letra es una : ' 'A'];
                    title(reconocimiento);

                elseif YTestknn(i) ==3

                    reconocimiento = ['la Letra es una : ' 'C'];
                    title(reconocimiento);

                else

                    reconocimiento = ['la Letra es una : ' 'D'];
                    title(reconocimiento);

                end 

            else                        % E_F_G

                if YTest(i) == 5

                    reconocimiento = ['la Letra es una : ' 'E'];
                    title(reconocimiento);

                elseif YTest(i) == 6

                    reconocimiento = ['la Letra es una : ' 'F'];
                    title(reconocimiento);

                else

                    reconocimiento = ['la Letra es una : ' 'G'];
                    title(reconocimiento);

                end

    end

    % representacion de las distintas graficas

    subplot(2,2,2);
    hold on , funcion_representa_muestras_clasificacion_binaria_frontera(X_LDA,Y_LDA,nombresProblemas_LDA,coeficientes_d12_LDA);
    hold on , plot3(Xct(i,1),Xct(i,2),Xct(i,3),'ko')

    espacioCcas = espacioCcas_QDA(1:3);

    subplot(2,2,3);
    hold on , funcion_representa_datos(X_QDA, Y_QDA,1:3,nombresProblemas_QDA);
    hold on , plot3(Xcc(i,1),Xcc(i,2),Xcc(i,3),'ko');

    espacioCcas = espacioCcas_KNN(1:3);

    subplot(2,2,4);
    hold on , funcion_representa_datos(X_Train, Y_Train,1:3,nombresProblemas_KNN);
    hold on , plot3(Xknn(i,1),Xknn(i,2),Xknn(i,3),'ko');

    pause;

end

close all;

rmpath('..\01_GeneracionDatos\Funciones\');
rmpath('..\01_GeneracionDatos\DatosGenerados\');
rmpath('Test\')