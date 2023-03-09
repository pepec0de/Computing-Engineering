function vEtiq = getEtiquetasVecinos(IEtiq, a, b)

vEtiq = [];
[nFil, nCol] = size(IEtiq);

for i = a-1 : a+1
    % Check bounds
    if i >= 1 && i <= nFil
        for j = b-1 : b+1
            % Check bounds
            if j >= 1 && j <= nCol
                % Check etiqueta is not 0
                
                if ~(i == a && j == b) && IEtiq(i, j) ~= 0
                    vEtiq = [vEtiq IEtiq(i, j)];
                end
            end
        end
    end
end

end

