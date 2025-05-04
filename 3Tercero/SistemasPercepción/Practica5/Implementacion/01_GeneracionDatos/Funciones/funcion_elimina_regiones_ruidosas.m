function IbinFilt = funcion_elimina_regiones_ruidosas(Ibin)
% COMPONENTE RUIDOSA:
% COMPONENTES DE MENOS DEL 0.1% DEL NÚMERO TOTAL DE PÍXELES DE LA IMAGEN
% O NÚMERO DE PÍXELES MENOR AL AREA DEL OBJETO MAYOR /5
% SE DEBE CUMPLIR CUALQUIERA DE LAS DOS CONDICIONES

IbinFilt = false(size(Ibin));

[IEtiq, N] = bwlabel(Ibin);

% Calculamos primero el vector de areas
areas = zeros(1, N);
for i = 1:N
    Iobj = IEtiq == i;
    areas(i) = sum(Iobj(:));
end

[F, C] = size(Ibin);
pixelesCond1 = round(F*C*(0.1/100));
pixelesCond2 = round(max(areas)/5);

for i = 1:N
    if not(areas(i) < pixelesCond1 || areas(i) < pixelesCond2)
        Iobj = IEtiq == i;
        IbinFilt = or(IbinFilt, Iobj);
    end
end

end

