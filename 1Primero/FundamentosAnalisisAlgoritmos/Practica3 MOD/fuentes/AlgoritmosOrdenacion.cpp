/*
 * Clase AlgoritmosOrdenacion que implementa los Algoritmos de Ordenación para ordenar un vector de enteros en orden descendente.
 * Define las implementaciones de los siguientes métodos de Ordenación en vectores: 
 *	- Burbuja
 *	- Inserción
 *	- Selección.
 */

#include "AlgoritmosOrdenacion.h"
#include "TestOrdenacion.h"

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

/**** EXAJUN21****/	
void AlgoritmosOrdenacion::ordenaJunio21(int v[], int size) {
	Junio21(v, 0, size - 1);
}

void AlgoritmosOrdenacion::Junio21(int a[], int pri, int ult) {
	int contAsig = 0;
	while (pri < ult) { // n/2 veces
		swap(a, ult, PosMax(a, pri, ult, contAsig), contAsig);
		ult--;
		swap(a, pri, PosMin(a, pri, ult, contAsig), contAsig);
		pri++;
		contAsig += 9;
	}
	//cout << "OE TOTALES = " << contAsig << endl;
}

int AlgoritmosOrdenacion::PosMax(int a[], int pri, int ult, int &contAsig) {
	/* devuelve la posición del máximo elemento de a[pri..ult] */
	int pmax = pri;
	contAsig += 4;
	for (int i = pri + 1; i <= ult; i++) {
		// Caso mejor:
		// 1,2,3,4,5,6 -> 5
		// 2,3,4,5 -> 3
		// 3,4 -> 1

		// Caso medio:
		// n = 6
		// 1,2,3,6,5,4 -> 3
		// 2,3,4,5 -> 3
		// 3,4 -> 1
		// asig = 7

		// n = 8
		// 1,2,3,4,8,7,6,5 -> 4
		// 2,3,4,5,7,6 -> 4
		// 3,4,5,6 -> 3
		// 4,5 -> 1
		// asig = 12

		// n = 6
		// 5,2,3,4,6,1 -> 1
		// 2,3,4,5 -> 3
		// 3,4 -> 1
		// asig = 7

		// Caso peor:
		// 6,5,4,3,2,1 -> 0
		contAsig += 6;
		if (a[i] > a[pmax]) {
			pmax = i;
			contAsig++;
		}
	}
	return pmax;
}

int AlgoritmosOrdenacion::PosMin(int a[], int pri, int ult, int &contAsig) {
	/* devuelve la posición del mínimo elemento de a[pri..ult] */	
	int pmin = pri;
	contAsig += 4;
	for (int i = pri + 1; i <= ult; i++) {
		// Caso mejor:
		// 1,2,3,4,5 -> 0
		// 2,3,4 -> 0

		// Caso medio:
		// 1,2,3,4,5 -> 0
		// 2,3,4 -> 0

		// n = 8
		// 1,2,3,4,5,7,6 -> 0
		// 2,3,4,5,6 -> 0
		// 3,4,5 -> 0

		// 5,2,3,4,1 -> 2
		// 2,3,4 -> 0

		// Caso peor:
		// 1,5,4,3,2 -> 0
		// 
		contAsig += 6;
		if (a[i] < a[pmin]) {
			pmin = i;
			contAsig++;
		}
	}
	return pmin;
}

void AlgoritmosOrdenacion::swap(int a[], int i, int j, int &contAsig) {
	/* intercambia a[i] con a[j]  */
	int aux = a[i];
	a[i] = a[j];
	a[j] = aux;
	contAsig += 7;
}
