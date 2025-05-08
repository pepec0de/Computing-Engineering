from funciones import *
from busqueda_local import bl_primer_mejor
from logger import Logger

"""
Se usa el Modelo estrategia (mu + lambda)

Se hacen mu soluciones y se generan lambda hijos

-> Revisar Tema 2 1.6
"""

################################################### BUSQUEDA ILS

def ILS(matD, matF, seed, logger : Logger = None):
    # Init semilla
    np.random.seed(seed)

    n = matD.shape[0]
    s = int(np.round(n/4))
    evaluaciones = 0

    solucion_actual = np.random.permutation(n)
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
        solucion_optimizada, valor_optimizado, evals = bl_primer_mejor(solucion_actual, matD, matF, delta, valor_fo=funcion_objetivo(solucion_actual, matD, matF))
        evaluaciones += evals

        if valor_optimizado < mejor_valor:
            mejor_solucion = solucion_optimizada
            mejor_valor = valor_optimizado
            num_no_mejora = 0
        else:
            num_no_mejora += 1
        
        # Hacer mutacion sobre mejor_solucion
        solucion_actual = operador_sublista_aleatoria(mejor_solucion, s)
    
    return mejor_solucion, mejor_valor, evaluaciones