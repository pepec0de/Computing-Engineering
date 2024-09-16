/*
 * Clase Algoritmos_EUF que define los Algoritmos de búsqueda del K-ésimo menor elemento en un vector de enteros.
 * Define las implementaciones de los siguientes Algoritmos en vectores: 
 *	- busquedaK_esimo_menor_Dir (solución directa).
 *	- busquedaK_esimo_menor_It (solución iterativa).
 *	- busquedaK_esimo_menor_Rc (solución recursiva).
 */
#pragma once
#include <vector>
#include <iostream>

using namespace std;
/*
 * declaración de la clase Algoritmos_EUF
 */
class Algoritmos_EUF
{  

public:
	Algoritmos_EUF();
	~Algoritmos_EUF();

	/*
	 * Función busquedaK_esimo_menor_Dir, implementa el método de búsqueda del K-ésimo menor elemento
	 * param v: el vector de enteros donde buscar
	 * param k: K-ésimo menor elemento a buscar
	 * return K-ésimo menor elemento en el vector
	 */
int busquedaK_menor_Dir(vector<int> &v, int k);
	/*
	 * Función busquedaK_esimo_menor_It, implementa el método de búsqueda del K-ésimo menor elemento
	 * param v: el vector de enteros donde buscar
	 * param k: K-ésimo menor elemento a buscar
	 * return K-ésimo menor elemento en el vector
	 */
int busquedaK_menor_It(vector<int> &v, int k);
int SelectIt(vector<int> &A, int p, int r, int k);
	/*
	 * Función busquedaK_esimo_menor_Rc, implementa el método de búsqueda del K-ésimo menor elemento
	 * param v: el vector de enteros donde buscar
	 * param k: K-ésimo menor elemento a buscar
	 * return K-ésimo menor elemento en el vector
	 */
int busquedaK_menor_RC(vector<int> &v, int k);
int Select(vector<int> &A, int p, int r, int k);

	/*
	 * OrdenaQuickSort implementa el método de ordenación QuickSort
	 * param v: el vector de enteros a ordenar
	*/

void OrdenaQuickSort(vector<int> &v);
	void QuickSort(vector<int> &v, int p, int r);
	int Partition(vector<int> &v, int p, int r);
	void swap(vector<int>& v, int a, int b);
};