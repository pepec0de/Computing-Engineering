from funciones import *
from busqueda_local import bl_primer_mejor

"""
Se usa el Modelo estrategia (mu + lambda)

Se hacen mu soluciones y se generan lambda hijos

-> Revisar Tema 2 1.6
"""

def ILS(matD, matF, seed, logger : Logger = None):
    # Init semilla
    np.random.seed(seed)

    n = matD.shape[0]
    s = int(np.round(n/4))
    iters = 0

    solucion_actual = generar_solucion_inicial(n)
    valor_actual = funcion_objetivo(solucion_actual, matD, matF)

    mejor_solucion = solucion_actual
    mejor_valor = valor_actual

    """
    PARA HACER ILS POBLACIONAL:
    HACER UN VECTOR DE SOLUCIONES : [s1, s2, s3 .. sK]
    Optimizar cada solucion con busqueda local y luego seleccionar y obtener el vector final:

    vector final = [mejor(s1, s1_opt), .. mejor(sK, sK_opt)]
    """
    
    num_no_mejora = 0
    while num_no_mejora < 10:
        iters += 1
        
        solucion_optimizada, valor_optimizado = bl_primer_mejor(solucion_actual, matD, matF, funcion_objetivo)

        if valor_optimizado < mejor_valor:
            mejor_solucion = solucion_optimizada
            mejor_valor = valor_optimizado
            num_no_mejora = 0
        else:
            num_no_mejora += 1
        
        # Hacer mutacion sobre mejor_solucion
        solucion_actual = operador_sublista_aleatoria(mejor_solucion, s)
    
    return mejor_solucion, mejor_valor, iters