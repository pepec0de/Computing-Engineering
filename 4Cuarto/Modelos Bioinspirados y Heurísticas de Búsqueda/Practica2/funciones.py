import numpy as np
from logger import Logger

def load_mats(path, verbose=False):
    """
    Leer archivos .dat
        1ra matriz -> distancias
        2da matriz -> flujos
    """
    try:
        with open(path, "r") as file:
            lines = file.readlines()
            try:
                mat_size = int(lines[0].strip())
                if (verbose): print(f"mat_size={mat_size}")
                
                n_mats = int((len(lines)-1) / mat_size)
                if (verbose): print(f"nmats={n_mats}")
                mats = np.zeros((n_mats, mat_size, mat_size), dtype=np.int32)
                for idx_mat in range(n_mats):
                    for i in range(1, mat_size+1):
                        elements = list(filter(None, lines[idx_mat*mat_size + i].strip().split(' ')))
                        if elements:
                            if len(elements) != mat_size:
                                print(f"Matriz irregular [{len(elements)} != {mat_size}] en {path} (linea {idx_mat*mat_size + i})\nDetail:\n{elements}")
                                return dict()
                            for j in range(len(elements)):
                                if elements[j]:
                                    mats[idx_mat, i-1, j] = np.int32(elements[j])
                return {"nombre" : path, "distancia" : mats[0, :, :], "flujo" : mats[1, :, :]}
            except ValueError:
                print("ValueError")
            except:
                print(f"Error al parsear {path}")
    except:
        print(f"Archivo {path} no existe")

    return dict()

def save(path, solucion, valor, iteraciones):
    try:
        file = open(path, "w")

        file.write(f"{valor} {iteraciones}\n")
        for el in solucion:
            file.write(f"{el} ")
        file.write("\n")
    except:
        print(f"Unable to write file {path}")

# Funcion objetivo de kevin
def funcion_objetivo(solucion, matD, matF):
    permutado = matF[solucion, :][:, solucion]
    return np.sum(np.multiply(matD, permutado))

def funcion_objetivo_original_QAP(solucion, matD, matF):
    result = 0
    n = matD.shape[0]

    for i in range(n):
        for j in range(n):
                if i != j:
                    result += matD[i, j] * matF[solucion[i] , solucion[j] ]
    return result

"""
def funcion_objetivo(solucion, matD, matF):
    return funcion_objetivo_original_QAP(solucion, matD, matF) #- delta(solucion, matD, matF)
"""

def delta(solucion, matD, matF):
    result = 0
    n = matD.shape[0]
    r = s = np.random.randint(0, n)
    while r == s:
        s = np.random.randint(0, n)
    
    for k in range(n):
        if k != r and k != s:
            result += matF[r][k] * \
(matD[solucion[s] ][solucion[k] ] - matD[solucion[s] ][solucion[k] ]) + \
matF[s][k] * \
(matD[solucion[s] ][solucion[k] ] - matD[solucion[r] ][solucion[k] ]) + \
matF[k][r] * \
(matD[solucion[k] ][solucion[r] ] - matD[solucion[k] ][solucion[s] ]) + \
matF[k][s] * \
(matD[solucion[k] ][solucion[s] ] - matD[solucion[k] ][solucion[r] ]) + \
matF[r][s] * \
(matD[solucion[r] ][solucion[s] ] - matD[solucion[s] ][solucion[r] ]) + \
matF[s][r] * \
(matD[solucion[s] ][solucion[r] ] - matD[solucion[r] ][solucion[s] ])
    
    return result

def generar_solucion_inicial(n : int) -> np.ndarray:
    return np.random.permutation(n, dtype=np.uint8)

def operador_sublista_aleatoria(solucion, s : int):
    """
    Operador de generacion de vecino -> Sublista Aleatoria de Tamaño Fijo: 
    este proceso consiste en generar aleatoriamente dos posiciones
     que determinen una sublista de tamaño s, analizar las asignaciones
    efectuadas entre ambas y reasignarlas aleatoriamente. Se considerarán
    sublistas cíclicas y, por tanto, la posición inicial de la sublista
    no siempre será inferior a la posición final.
    """
    n = len(solucion)
    inicio = np.random.randint(n)

    # Crear los índices cíclicos de la sublista
    indices = [(inicio + i) % n for i in range(s)]

    # Extraer y mezclar la sublista
    sublista = [solucion[i] for i in indices]
    shuffled = sublista.copy()
    while shuffled == sublista:
        np.random.shuffle(shuffled)
    
    # Reinsertar la sublista mezclada
    nueva_solucion = solucion.copy()
    for idx, val in zip(indices, shuffled):
        nueva_solucion[idx] = val

    return nueva_solucion

def vector_to_str(vct : list) -> str:
    s = " "
    for e in vct:
        s = s + str(e) + " "

    return s