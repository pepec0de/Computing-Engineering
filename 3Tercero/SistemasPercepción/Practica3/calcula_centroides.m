function centroides = calcula_centroides(IEtiq, N)

centroides = zeros(N, 2);

for i = 1:N
    [row, col] = find(IEtiq == i);
    centroides(i, :) = [round(mean(col)), round(mean(row))];
end

end