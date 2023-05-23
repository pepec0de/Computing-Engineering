function X = funcion_calcula_extent_objetos(IEtiq, N)
    
    X = zeros(N, 1);

    for e = 1:N
        Ib = IEtiq == e;
        
        % Calculamos la extension del objeto
        IbCentrada = Funcion_Centra_Objeto(Ib);

        angulos = 0:5:355;
        nAngulos = length(angulos);
        extensiones = zeros(length(nAngulos), 1);
        for i = 1:nAngulos
            IbRotate = imrotate(IbCentrada, angulos(i));

            [filas, cols] = find(IbRotate);

            % Calculo de la bounding box
            fmax = max(filas)+0.5; 
            cmax = max(cols)+0.5;

            fmin = min(filas)-0.5;
            cmin = min(cols)-0.5;

            nPixeles = length(filas);
            nPixelesBoundingBox = (fmax-fmin)*(cmax-cmin);

            extensiones(i) = nPixeles/nPixelesBoundingBox;
        end
        
        extension = max(extensiones);

        % Guardamos la extension
        X(e) = extension;
    end

end