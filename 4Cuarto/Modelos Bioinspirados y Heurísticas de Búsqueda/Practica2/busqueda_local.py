import numpy as np

"""
Algoritmo de la busqueda local del mejor vecino
"""
def bl_mejor_vecino(matD, matF, funcion_objetivo, seed):
    # Init semilla
    np.random.seed(seed)

    n_nodes = matD.shape[0]
    mejor_solucion = np.random.permutation(n_nodes)
    mejor_valor = funcion_objetivo(mejor_solucion, matD, matF)

    done = False

    while not done:
        mejor_vecino = mejor_solucion
        valor_mejor_vecino = funcion_objetivo(mejor_vecino, matD, matF)

        for i in range(n_nodes - 1):
            for j in range(i + 1, n_nodes):
                vecino = operador_opt2(mejor_solucion, i, j)
                valor_vecino = funcion_objetivo(vecino, matD, matF)

                if valor_vecino < valor_mejor_vecino:
                    valor_mejor_vecino = valor_vecino
                    mejor_vecino = vecino
        
        if valor_mejor_vecino < mejor_valor:
            mejor_solucion = mejor_vecino
            mejor_valor = valor_mejor_vecino
        else:
            done = True
        
    return mejor_solucion, valor_mejor_vecino

def bl_primer_mejor2222222(solucion_actual, matD, matF, funcion_objetivo):
    """
    Se hace la busqueda local del primer mejor
    No hay componentes aleatorias
    """
    n_nodes = matD.shape[0]
    mejor_solucion = solucion_actual.copy()
    mejor_valor = funcion_objetivo(mejor_solucion, matD, matF)

    mejor_vecino = None
    valor_vecino = 0

    seguir = True
    iters = 0
    while seguir:
        iters += 1
        print(f"BL_Primer_mejor {iters}")

        primer_encontrado = False
        for i in range(n_nodes - 1):
            for j in range(i + 1, n_nodes):
                vecino = operador_opt2(mejor_solucion, i, j)
                valor_vecino = funcion_objetivo(vecino, matD, matF)

                if valor_vecino < mejor_valor:
                    mejor_vecino = vecino
                    primer_encontrado = True
                    break
            if primer_encontrado:
                break

        if valor_vecino < mejor_valor:
            mejor_solucion = mejor_vecino
            mejor_valor = valor_vecino
        else:
            seguir = False

    return mejor_solucion, mejor_valor

def operador_opt2(solucion, i, j): 
    vecino = solucion.copy()
    vecino[i], vecino[j] = vecino[j], vecino[i]
    return vecino

############################################################

def bl_primer_mejor111111(solucion_actual, matD, matF, funcion_objetivo):
    """
    Se hace la busqueda local del primer mejor
    No hay componentes aleatorias
    """
    n_nodes = matD.shape[0]
    mejor_solucion = solucion_actual.copy()
    mejor_valor = funcion_objetivo(mejor_solucion, matD, matF)

    mejor_vecino = []
    valor_mejor_vecino = 0

    seguir = True
    while seguir:

        for i in range(n_nodes - 1): # list(np.random.permutation(n_nodes - 1))
            for j in range(i + 1, n_nodes):
                vecino = operador_opt2(mejor_solucion, i, j)
                valor_vecino = funcion_objetivo(vecino, matD, matF)

                if valor_vecino < mejor_valor:
                    mejor_solucion = vecino
                    mejor_valor = valor_vecino
                    

        if valor_vecino < mejor_valor:
            mejor_solucion = mejor_vecino
            mejor_valor = valor_vecino
        else:
            seguir = False

    return mejor_solucion, mejor_valor

def bl_primer_mejor(solucion_actual, matD, matF, funcion_objetivo):
    """
    Se hace la busqueda local del primer mejor
    No hay componentes aleatorias
    """
    n_nodes = matD.shape[0]
    
    mejor_solucion = solucion_actual
    mejor_valor = funcion_objetivo(mejor_solucion, matD, matF)
    vecino = mejor_solucion.copy()

    while True:

        encontrado = False
        for i in range(n_nodes - 1):
            for j in range(i + 1, n_nodes):
                #vecino = operador_opt2(mejor_solucion, i, j) ->
                vecino[i], vecino[j] = vecino[j], vecino[i]
                valor_vecino = funcion_objetivo(vecino, matD, matF)

                if valor_vecino < mejor_valor:
                    mejor_solucion = vecino
                    mejor_valor = valor_vecino
                    encontrado = True
                    break
                #else: # No hace falta por el break de arriba
                vecino[i], vecino[j] = vecino[j], vecino[i]

            if encontrado:
                break

        if not encontrado:
            break
            
    return mejor_solucion, mejor_valor
