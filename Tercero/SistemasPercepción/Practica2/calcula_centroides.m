function centroides = calcula_centroides(IEtiq, N)

[nFilas, nCol] = size(IEtiq);
[~, SZ] = size(N);
centroides = [ zeros(SZ, 2) ];
areas = calcula_areas(IEtiq, N);

for i = 1 : SZ
    MatBin = N(i) == IEtiq;
    xprod = 0; 
    yprod = 0;
    for x = 1:nFilas
        for y = 1:nCol
            xprod = xprod + x*MatBin(x, y);
            yprod = yprod + y*MatBin(x, y);
        end
    end
    centroides(i, 1) = xprod/areas(i);
    centroides(i, 2) = yprod/areas(i);
end
end