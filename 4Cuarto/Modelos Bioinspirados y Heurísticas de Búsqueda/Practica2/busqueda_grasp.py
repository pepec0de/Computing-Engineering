from funciones import *
from busqueda_local import bl_primer_mejor

################ Construccion de soluciones greedy probabilisticas ###########################################
def generar_solucion_greedy(matD, matF, l : int, seed : float):
    """
    Construye una solución inicial para el QAP usando una heurística greedy probabilística.

    :param flujo: Matriz de flujo entre unidades (numpy array n x n).
    :param distancia: Matriz de distancia entre localizaciones (numpy array n x n).
    :param l: Tamaño de las listas RCL (listas de candidatos restringidas).
    :return: Diccionario representando la asignación {unidad: localización}.
    """
    # Init semilla
    np.random.seed(seed)

    n = matD.shape[0]
    idx_localizaciones_no_asignadas = np.arange(0, n, dtype=np.uint8)
    idx_unidades_no_asignadas = np.arange(0, n, dtype=np.uint8)
    
    promedio_distancias = np.sum(matD, axis=1)  # Distancia total por localización
    promedio_flujos = np.sum(matF, axis=1) # Flujo total por unidad

    solucion = np.zeros(n, dtype=np.uint8)

    while idx_localizaciones_no_asignadas.size != 0:
        idx_mejores_distancias = sorted(idx_localizaciones_no_asignadas, key=lambda i: promedio_distancias[i])
        l_mejores_distancias = idx_mejores_distancias[:min(l, len(idx_mejores_distancias))]

        idx_mejores_flujos = sorted(idx_unidades_no_asignadas, key=lambda i: -promedio_flujos[i])
        l_mejores_flujos = idx_mejores_flujos[:min(l, len(idx_mejores_flujos))]

        # Selección aleatoria de entre los mejores candidatos
        idx_localizacion_aleatoria = np.random.choice(l_mejores_distancias)
        idx_unidad_aleatoria = np.random.choice(l_mejores_flujos)

        # Segun ChatGPT es asi
        solucion[idx_unidad_aleatoria] = idx_localizacion_aleatoria

        # Se eliminan de las listas
        #idx_localizaciones_no_asignadas.remove(idx_localizacion_aleatoria)
        #idx_unidades_no_asignadas.remove(idx_unidad_aleatoria)

        idx_localizaciones_no_asignadas = np.delete(idx_localizaciones_no_asignadas, np.where(idx_localizaciones_no_asignadas == idx_localizacion_aleatoria))
        idx_unidades_no_asignadas = np.delete(idx_unidades_no_asignadas, np.where(idx_unidades_no_asignadas == idx_unidad_aleatoria))
    
    return solucion, funcion_objetivo(solucion, matD, matF)


############################### Algoritmo GRASP ###############################################
def GRASP(matD, matF, l, semillas_bl, logger : Logger = None):
    mejor_solucion = None
    mejor_valor = np.inf
    evaluaciones = 0

    for seed_bl in semillas_bl: # 5 semillas!!
        solucion_greedy, valor_greedy = generar_solucion_greedy(matD, matF, l, seed_bl)
        if (logger != None): logger.log("Greedy Prob", seed_bl, valor_greedy, 1, vector_to_str(solucion_greedy))

        solucion_optimizada, valor_optimizada, eval = bl_primer_mejor(solucion_greedy, matD, matF, valor_greedy, funcion_objetivo)
        if (logger != None): logger.log("BL Primer Mejor", "N/A", valor_optimizada, eval, vector_to_str(solucion_optimizada))
        evaluaciones += eval

        if valor_optimizada < mejor_valor:
            mejor_solucion = solucion_optimizada
            mejor_valor = valor_optimizada

    return mejor_solucion, mejor_valor, evaluaciones