from funciones import *
from busqueda_local import bl_primer_mejor

def VNS(matD, matF, seed):
    # Init semilla
    np.random.seed(seed)

    iters = 0
    
    n = matD.shape[0]
    k_max = 5
    bl_max = 10
    k = 1
    bl = 0

    mejor_solucion = generar_solucion_inicial(n)
    mejor_valor = funcion_objetivo(mejor_solucion, matD, matF)

    # Paso 6
    while bl < bl_max:
        iters += 1
        # Paso 2
        if k > k_max:
            k = 1
        # Paso 3
        """
        k = 1: Se aplica un tamaño de s = n/8.
        k = 2: Se aplica un tamaño de s = n/7.
        k = 3: Se aplica un tamaño de s = n/6.
        k = 4: Se aplica un tamaño de s = n/5.
        k = 5: Se aplica un tamaño de s = n/4.
        """
        s = int(np.round(n / (9 - k)))
        vecino = operador_sublista_aleatoria(mejor_solucion, s)
        # Paso 4
        vecino_opt, valor_vecino = bl_primer_mejor(vecino, matD, matF, funcion_objetivo)
        bl += 1
        # Paso 5
        if valor_vecino < mejor_valor:
            mejor_solucion = vecino_opt
            mejor_valor = valor_vecino
            k = 1
        else:
            k += 1

    return mejor_solucion, mejor_valor, iters