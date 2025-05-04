clc, clear;

addpath("..\01_GeneracionMaterial\MaterialGenerado\");

% Cargamos y guardamos los parametros del clasificador
load('..\04_AjusteClasificador_ImgCalib\VariablesGeneradas\parametros_clasificador.mat');
save("VariablesRequeridas\parametros_clasificador.mat", 'umbral_conectividad', 'datos_multiples_esferas', 'RADIO');

%% VIDEO COLOR MOV
videoIn = VideoReader("video.avi");

get(videoIn);

nFrames = videoIn.NumFrames;
FPS = videoIn.FrameRate;
nFilas = videoIn.Height;
nCols = videoIn.Width;

videoOut = VideoWriter('output.avi', 'Uncompressed AVI');
videoOut.FrameRate = FPS;

open(videoOut);
videoIn.CurrentTime = 0;
% Verde
color = [0 255 0];

BLOCK_SIZE = 3;

Iant = readFrame(videoIn);
for i = 2:nFrames
    Isig = readFrame(videoIn);

    IbAnt = calcula_deteccion_multiples_esferas_imagen(Iant, datos_multiples_esferas, RADIO);
    IbSig = calcula_deteccion_multiples_esferas_imagen(Isig, datos_multiples_esferas, RADIO);

    IbFinal = imabsdiff(IbAnt, IbSig);
    
    props = regionprops(IbFinal, 'Area', 'Centroid');
    areas = cat(1, props.Area);
    [~, idxs] = sort(areas, 'descend');
    centroides = cat(1, props.Centroid);

    x = round(centroides(idxs(1), 1));
    y = round(centroides(idxs(1), 2));

    IFinal = imabsdiff(Iant, Isig);
    IResultado = funcion_visualiza(IFinal, IbFinal, color);
    
    writeVideo(videoOut, IResultado);

    imshow(IResultado);

    Iant = Isig;
end

close(videoOut);
