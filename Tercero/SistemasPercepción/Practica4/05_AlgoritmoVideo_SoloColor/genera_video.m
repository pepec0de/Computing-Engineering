clc, clear;

addpath("..\01_GeneracionMaterial\MaterialGenerado\");

% Cargamos y guardamos los parametros del clasificador
load('..\04_AjusteClasificador_ImgCalib\VariablesGeneradas\parametros_clasificador.mat');
save("VariablesRequeridas\parametros_clasificador.mat", 'umbral_conectividad', 'datos_multiples_esferas', 'RADIO');

%% VIDEO SOLO COLOR
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
color = [255 0 0];

BLOCK_SIZE = 3;

for i = 1:nFrames

    I = readFrame(videoIn);

    Ib = calcula_deteccion_multiples_esferas_imagen(I, datos_multiples_esferas, RADIO);

    IEtiq = bwareaopen(Ib, umbral_conectividad);

    etiquetas = unique(IEtiq);
    if length(etiquetas) > 1
        props = regionprops(IEtiq, 'Area', 'Centroid');

        %areas = cat(1, props.Area);
        %[~, idxs] = sort(areas, 'descend');

        centroides = cat(1, props.Centroid);
        for j = 1:size(centroides, 1)
            x = round(centroides(j, 1));
            y = round(centroides(j, 2));
    
            % Checkeamos el bounding box y pintamos la caja
            if x >= BLOCK_SIZE && x <= nFilas - BLOCK_SIZE &&...
                    y >= BLOCK_SIZE && y <= nCols - BLOCK_SIZE
                for c = 1:3
                    I( x - BLOCK_SIZE : x + BLOCK_SIZE, y - BLOCK_SIZE : y + BLOCK_SIZE, c) = color(c);
                end
            end
        end
    end
    writeVideo(videoOut, I);

    imshow(I);
end

