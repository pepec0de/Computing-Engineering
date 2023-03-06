function areas = calcula_areas(IEtiq, N)
%CALCULA_AREAS Summary of this function goes here
%   Detailed explanation goes here

[~, SZ] = size(N);
areas = [];

for i = 1:SZ
    MatBin = N(i) == IEtiq;
    areas = [areas double(sum(MatBin(:)))];
end
