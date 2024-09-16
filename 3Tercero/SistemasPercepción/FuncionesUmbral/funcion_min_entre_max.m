function umbral = funcion_min_entre_max(h)

iPico1 = find(h == max(h));

aux = h;
for i = 1:256
    aux(i) = ((i - iPico1).^2)*aux(i);
end

iPico2 = find(aux == max(aux));

aux = h(iPico2:iPico1);

umbral = find(aux == min(aux)) + iPico2 - 1;
end