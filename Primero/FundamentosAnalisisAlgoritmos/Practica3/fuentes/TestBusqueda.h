/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los m�todos de b�squeda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un m�todo de b�squeda, permitiendo guardar los 
 *    datos e imprimir la gr�fica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos m�todos de b�squeda
 *    permitiendo guardar los datos e imprimir la gr�fica correspondiente.
 * 4. Comparar todos los algoritmos de b�squeda implementados.
 */
#ifndef _TEST_BUSQUEDA
#define _TEST_BUSQUEDA

//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
#include <iostream>
#include <vector>
#include "AlgoritmosBusqueda.h"
#include "Mtime.h"
#include "Constantes.h"
#include "ConjuntoInt.h"

class TestBusqueda
{
private:
	vector<string> nombreAlgoritmo;

public:
    
	TestBusqueda();
	~TestBusqueda();
	double buscaEnArrayDeInt(int v[], int size, int key, int metodo, int& pos);
	void comprobarMetodosBusqueda();
	void casoMedio(int metodo);
};

#endif