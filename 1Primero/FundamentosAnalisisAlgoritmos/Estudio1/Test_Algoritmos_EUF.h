#pragma once
#include <string>
#include <iostream>

#include "ConjuntoInt.h"
#include "Algoritmos_EUF.h"
#include "Mtime.h"
#include "Constantes.h"
#include <vector>
#include <fstream>
#include "Graficas.h"

using namespace std;

class Test_Algoritmos_EUF {

	std::vector<string> nombreAlgoritmo;
	int tallaIni = 100, tallaFin = 1000, inc = 100, NUMREPETICIONES = 10;

public:
    
	Test_Algoritmos_EUF();

	~Test_Algoritmos_EUF();
    
	double buscaKesimoMenor(int k, vector<int> &v,int metodo, int &elem);
     /*
      * Comprueba que los algoritmos funcionan correctamente
      */
    void comprobar_Algoritmos_EUF();
        
    /*
	 * Compara dos algoritmos. 
	 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
     * param metodo1: Primer metodo a comparar
     * param metodo2: Segundo metodo a comparar
     */
    void comparar(int metodo1, int metodo2);

	/*
	* Calcula el caso medio de un metodo ,
	* Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
    * param metodo: metodo a estudiar.
    */
	void casoMedio(int metodo);
	/*
	* Compara todos los metodos de busqueda.
	* Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
	*/
	void comparaTodos();
};