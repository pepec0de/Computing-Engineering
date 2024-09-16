/*
 * Clase AlgoritmosOrdenacion que implementa los Algoritmos de Ordenación para ordenar un vector de enteros en orden descendente.
 * Define las implementaciones de los siguientes métodos de Ordenación en vectores: 
 *	- Burbuja
 *	- Inserción
 *	- Selección.
 */

#include "AlgoritmosOrdenacion.h"

AlgoritmosOrdenacion :: AlgoritmosOrdenacion() {}
AlgoritmosOrdenacion :: ~AlgoritmosOrdenacion(){}

/*
 * método ordenaBurbuja, implementa el método de ordenación Burbuja.
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 */

void AlgoritmosOrdenacion :: ordenaBurbuja(int v[], int size) {
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	for (int i = size - 2; i >= 0; i--) {
		for (int j = 0; j <= i; j++) {
			// Como ordenamos de menor a mayor debemos comprobar que el siguiente es menor al actual
			if (v[j] > v[j + 1]) {
				// intercambio
				int aux = v[j];
				v[j] = v[j + 1];
				v[j + 1] = aux;
			}
		}
	}
}


/*
 * método ordenaInsercion, implementa el método de ordenación por Inserción-
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 */

void AlgoritmosOrdenacion :: ordenaInsercion(int v[], int size)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **// 
	int x;
	for (int i = 1; i < size; i++) {
		// Valor actual (se empieza por el segundo)
		x = v[i];
		int j = i - 1; // Indice anterior
		// Comprobamos que el actual es menor al anterior
		while (j > -1 && x < v[j]) {
			// Si es menor le damos al valor actual el valor del anterior
			v[j + 1] = v[j];
			j--;
		}
		v[j + 1] = x;
	}
}

/*
 * método ordenaSeleccion, implementa el método de ordenación por Selección.
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 */
void AlgoritmosOrdenacion :: ordenaSeleccion(int v[], int size)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int posminimo;
	for (int i = 0; i <= size - 2; i++) {
		posminimo = i;
		for (int j = i + 1; j < size; j++) {
			if (v[j] < v[posminimo]) {
				posminimo = j;
			}
		}
		int aux = v[posminimo];
		v[posminimo] = v[i];
		v[i] = aux;
	}
}