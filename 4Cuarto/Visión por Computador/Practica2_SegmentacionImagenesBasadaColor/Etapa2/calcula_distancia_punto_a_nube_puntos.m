function Vector_Distancia =  calcula_distancia_punto_a_nube_puntos(P, NP)

    PAmpliada= repmat(P ,1,size(NP,1));

    Vector_Distancia = sqrt(sum((PAmpliada-NP').^2));

end