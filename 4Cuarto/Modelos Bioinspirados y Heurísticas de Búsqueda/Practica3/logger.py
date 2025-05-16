import csv
import os
import pandas as pd

class Logger:
    
    def __init__(self, ruta_csv, overwrite=True):
        self.ruta_csv = ruta_csv
        self.dataset = None

        # Crear archivo con cabecera
        if overwrite or not os.path.exists(self.ruta_csv):
            with open(self.ruta_csv, mode='w', newline='') as f:
                writer = csv.writer(f)
                writer.writerow(["Dataset", "Algoritmo", "Semilla", "Costo", "Evaluaciones", "Solucion"])
        else:
            self.data = pd.read_csv(self.ruta_csv)

    def set_dataset(self, dataset):
        self.dataset = dataset

    def log(self, algoritmo, semilla, costo, evaluaciones, solucion):
        if not self.dataset:
            raise Exception("Dataset not set!")
        fila = [
            self.dataset,
            algoritmo,
            semilla,
            costo,
            evaluaciones,
            solucion
        ]

        with open(self.ruta_csv, mode='a', newline='') as f:
            writer = csv.writer(f)
            writer.writerow(fila)
