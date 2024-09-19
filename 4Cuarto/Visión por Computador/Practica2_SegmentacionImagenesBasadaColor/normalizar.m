function normA = normalizar(A)
%normalizar(A) customized min-max normalization
%   normA = A - min(A(:))
%   normA = normA ./ max(normA(:))

normA = A - min(A(:));
normA = normA ./ max(normA(:));

end