function areas = calcula_areas(IEtiq, N)
%CALCULA_AREAS Summary of this function goes here
%   Detailed explanation goes here

areas = zeros(N, 1);
for i = 1:N
    MatBin = IEtiq == i;
    areas(i) = double(sum(MatBin(:)));
end
