function centroides = calcula_centroides(IEtiq, N)

[~, SZ] = size(N);
centroides = zeros(SZ, 2);

for i = 1:SZ
    [row, col] = find(IEtiq == N(i));
    centroides(i, 1) = round(mean(col));
    centroides(i, 2) = round(mean(row));
end

end