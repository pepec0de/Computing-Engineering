from busqueda_greedy import *
from busqueda_local import bl_mejor_vecino
from busqueda_grasp import *
from busqueda_ils import *
from busqueda_vns import *

prueba = load_mats("datasets/prueba.dat")
tai25 = load_mats("datasets/tai25b.dat")
sko90 = load_mats("datasets/sko90b.dat")
tai150 = load_mats("datasets/tai150b.dat")
main_logger = Logger("resultados/practica2.csv")

def main():
    """
    GRASP -> 1 ejecuciones
    ILS -> 1 ejecuciones
    VNS -> 5 ejecuciones
    """
    test_funcion_objetivo()
    #practica2()
    #main_logger = Logger("resultados/practica2.csv", False)
    #graficas(main_logger)
    
def test_funcion_objetivo():
    prueba_optima = [3,4,1,2]
    # idx1 -> [4,15,10,9,13,5,25,19,7,3,17,6,18,20,16,2,22,23,8,11,21,24,14,12,1]
    tai25_optima = [3, 14, 9, 8, 12, 4, 24, 18, 6, 2, 16, 5, 17, 19, 15, 1, 21, 22, 7, 10, 20, 23, 13, 11, 0]
    # idx1 -> [86,27,9,88,76,41,52,38,79,8,48,59,75,84,16,44,40,61,58,25,50,30,49,80,57,66,63,74,43,82,71,69,
    # 15,3,7,19,78,13,17,53,23,36,12,37,28,51,6,14,77,29,34,56,70,65,83,47,54,4,1,2,31,60,10,89,90,26,81,73,
    # 21,64,22,45,55,68,85,35,32,62,87,42,11,18,46,20,33,72,39,67,24,5]
    sko90_optima = [85, 26, 8, 87, 75, 40, 51, 37, 78, 7, 47, 58, 74, 83, 15, 43, 39, 60, 57, 24, 49, 29, 48, 
                 79, 56, 65, 62, 73, 42, 81, 70, 68, 14, 2, 6, 18, 77, 12, 16, 52, 22, 35, 11, 36, 27, 50, 5, 
                 13, 76, 28, 33, 55, 69, 64, 82, 46, 53, 3, 0, 1, 30, 59, 9, 88, 89, 25, 80, 72, 20, 63, 21, 
                 44, 54, 67, 84, 34, 31, 61, 86, 41, 10, 17, 45, 19, 32, 71, 38, 66, 23, 4]
    
    tai150_optima = [83, 62, 114, 18, 57, 64, 50, 104, 102, 5, 29, 26, 82, 122, 98, 24, 146, 30, 124, 47, 46,
                115, 75, 143, 103, 81, 112, 60, 108, 110, 131, 92, 53, 87, 140, 127, 109, 36, 145, 63, 129,
                67, 123, 45, 120, 72, 91, 113, 73, 12, 21, 74, 137, 141, 118, 147, 125, 68, 41, 17, 52, 132,
                130, 15, 88, 86, 136, 38, 119, 23, 20, 70, 1, 8, 90, 3, 96, 89, 148, 25, 59, 65, 121, 99, 55,
                94, 126, 135, 19, 95, 76, 56, 48, 101, 138, 133, 34, 71, 28, 111, 31, 77, 40, 79, 128, 93, 35,
                7, 13, 32, 39, 11, 105, 97, 33, 142, 149, 27, 116, 6, 66, 100, 107, 80, 106, 69, 4, 0, 54, 2,
                117, 51, 9, 84, 78, 14, 22, 61, 42, 43, 37, 134, 44, 58, 144, 139, 49, 85, 16, 10]

    #print(f"prueba optima = {funcion_objetivo(prueba_optima, prueba['distancia'], prueba['flujo'])}")
    print("OPTIMOS")
    print(f"tai25 = {funcion_objetivo(tai25_optima, tai25['distancia'], tai25['flujo'])}")
    #print(f"tai25 = kevin {funcion_objetivo_kevin(tai25_optima, tai25['distancia'], tai25['flujo'])}")
    print(f"sko90 = {funcion_objetivo(sko90_optima, sko90['distancia'], sko90['flujo'])}")
    #print(f"sko90 = kevin {funcion_objetivo_kevin(sko90_optima, sko90['distancia'], sko90['flujo'])}")
    print(f"tai150 = {funcion_objetivo(tai150_optima, tai150['distancia'], tai150['flujo'])}")
    print("##########################")

def test_greedy_grasp():
    print(generar_greedy_prob(tai25['distancia'], tai25['flujo'], int(np.round(0.1*tai25['distancia'].shape[0]))))


def practica2():
    grasp_logger = Logger("resultados/GRASP.csv")
    
    for d in [tai25]: # Datasets QAP
        main_logger.set_dataset(d['nombre'])
        grasp_logger.set_dataset(d['nombre'])

        n = d['distancia'].shape[0]
        matD = d['distancia']
        matF = d['flujo']
        
        grasp_logger.archivo = d['nombre']
        seeds = [42, 420, 4200, 42000, 80987]

        # Greedy
        solucion = busqueda_greedy(matD, matF)
        valor = funcion_objetivo(solucion, matD, matF)
        main_logger.log("Greedy", "N/A", valor, 1, vector_to_str(solucion))

        # Busqueda Local
        #seeds = [42] # PARA TESTEAR
        for seed_bl in seeds:
            solucion, valor, evaluaciones = bl_mejor_vecino(seed_bl, matD, matF, funcion_objetivo, delta)
            main_logger.log("BL Mejor Vecino", seed_bl, valor, evaluaciones, vector_to_str(solucion))
        
        
        # GRASP
        l = int(np.round(0.1 * n))
        solucion, valor, evaluaciones = GRASP(matD, matF, l, seeds, grasp_logger)
        main_logger.log("GRASP", vector_to_str(seeds), valor, evaluaciones, vector_to_str(solucion))
        
        # ILS
        seed = 42
        solucion, valor, evaluaciones = ILS(matD, matF, seed)
        main_logger.log("ILS", seed, valor, vector_to_str(solucion))

        # VNS
        for seed in seeds:
            solucion, valor, evaluaciones = VNS(matD, matF, seed)
            main_logger.log("VNS", seed, valor, evaluaciones, vector_to_str(solucion))

if __name__ == '__main__':
    main()