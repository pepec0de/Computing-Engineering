import numpy as np

def busqueda_greedy(matD, matF, funcion_objetivo):
    """
    Construye una solución inicial para el QAP usando una heurística greedy probabilística.

    :param flujo: Matriz de flujo entre unidades (numpy array n x n).
    :param distancia: Matriz de distancia entre localizaciones (numpy array n x n).
    :return: solucion.
    """
    n = matD.shape[0]
    idx_localizaciones_no_asignadas = list(range(n))
    idx_unidades_no_asignadas = list(range(n))
    
    # Calcular potenciales
    promedio_distancias = np.sum(matD, axis=1)  # Distancia total por localización
    promedio_flujos = np.sum(matF, axis=1) # Flujo total por unidad

    solucion = [0 for _ in range(n)]

    idx_mejores_distancias = sorted(idx_localizaciones_no_asignadas, key=lambda i: promedio_distancias[i])
    idx_mejores_flujos = sorted(idx_unidades_no_asignadas, key=lambda i: -promedio_flujos[i])

    while idx_localizaciones_no_asignadas:
        # Selección de los mejores candidatos
        idx_localizacion_mejor = idx_mejores_distancias.pop(0)
        idx_unidad_mejor = idx_mejores_flujos.pop(0)

        #solucion[idx_localizacion_mejor] = idx_unidad_mejor
        # Segun ChatGPT es asi
        solucion[idx_unidad_mejor] = idx_localizacion_mejor

        # Se eliminan de las listas
        idx_localizaciones_no_asignadas.remove(idx_localizacion_mejor)
        idx_unidades_no_asignadas.remove(idx_unidad_mejor)
    
    return solucion, funcion_objetivo(solucion, matD, matF)
    