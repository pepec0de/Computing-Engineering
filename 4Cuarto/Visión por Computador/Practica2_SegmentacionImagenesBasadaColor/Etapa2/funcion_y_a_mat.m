function mat = funcion_y_a_mat(Y, N, M)
%FUNCION_Y_A_MAT Transforma una Y (1xN) a mat (NxM)

mat = zeros(N, M);
i = 1;
for j = 1:M
    mat(:, j) = Y(i : (i + N - 1));
    i = i + N;
end

