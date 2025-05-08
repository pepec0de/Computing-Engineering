from funciones import *
from busqueda_local import bl_primer_mejor

def VNS(matD, matF, seed):
    # Init semilla
    np.random.seed(seed)
    
    n = matD.shape[0]
    k_max = 5
    bl_max = 10
    k = 1
    bl = 0

    mejor_solucion = np.random.permutation(n)
    mejor_valor = funcion_objetivo(mejor_solucion, matD, matF)
    evaluaciones = 0

    # Paso 6
    while bl < bl_max:
        # Paso 2
        if k > k_max:
            k = 1
        # Paso 3
        s = int(np.round(n / (9 - k)))
        vecino = operador_sublista_aleatoria(mejor_solucion, s)
        # Paso 4
        vecino_opt, valor_vecino, evals = bl_primer_mejor(vecino, matD, matF, delta, funcion_objetivo=funcion_objetivo)
        evaluaciones += evals
        bl += 1
        # Paso 5
        if valor_vecino < mejor_valor:
            mejor_solucion = vecino_opt
            mejor_valor = valor_vecino
            k = 1
        else:
            k += 1

    return mejor_solucion, mejor_valor, evaluaciones