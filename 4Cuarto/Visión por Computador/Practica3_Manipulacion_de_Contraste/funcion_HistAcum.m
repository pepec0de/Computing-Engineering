function H = funcion_HistAcum(h)
%FUNCION_HISTACUM Summary of this function goes here
%   Detailed explanation goes here

H = zeros(size(h));

for i = 1:256
    H(i) = sum(h(1:i));
end

end

