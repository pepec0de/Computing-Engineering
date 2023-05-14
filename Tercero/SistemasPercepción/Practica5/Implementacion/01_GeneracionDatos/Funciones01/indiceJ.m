% C�lculo del �ndice J (T�cnica M�trica)
function J = indiceJ(X,Y)

    inputs = X';
    outputs = Y';

    s = size(inputs);
    classes = unique(outputs);
    
    Ni = zeros(1,length(classes));    % N� de datos de cada clase
    Mi = zeros(s(1),length(classes)); % Vector de medias (para cada clase)
    
    N = 0;                            % N� total de datos
    M = zeros(s(1),1);                % Media de todos los datos
    
    % C�lculo de la within-class (Sw)
    
    Sw = zeros(s(1),s(1));
    
    for i=1:length(classes) % Para cada clase ...

        Ni(i) = sum(outputs==classes(i));
        
        icols = find(outputs==classes(i)); % Tomamos los descriptores de dicha clase
        cols = [];
        
        for j=1:length(icols)
            cols = [cols inputs(:,icols(j))];
        end
        
        Mi(:,i) = sum(cols')' / Ni(i);
        
        s = size(cols);
        Si = zeros(s(1),s(1));
        
        for n=1:s(2)
           
            Xn = cols(:,n);
            Vector = Xn - Mi(:,i);
            Si = Si + Vector*Vector';
            
        end
        
        Sw = Sw + Si;
        
    end
    
    % C�lculo de la between-class (Sb)
    
    Sb = zeros(s(1),s(1));
    
    N = length(outputs);
    M = sum(inputs')' / N;
    
    for i=1:length(classes) % Para cada clase ...
        
        Vector = Mi(:,i) - M;
        Sb = Sb + Ni(i)*(Vector*Vector');
        
    end
    
    J = trace(pinv(Sw)*Sb); % producto inverso
    
end