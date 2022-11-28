#pragma once 
/* 
* Clase Graficas, contiene métodos para guardar las gráficas de los resultados, 
* es decir, crea los ficheros por lo lotes(comandos) para generar los ficheros gráficos que 
corresponda. 
*/ 
#pragma once 
#include <string> 
#include <vector> 
#include <iostream> 
using namespace std;

class Graficas
{
public:
	/*
	 * Configura los parámetros para el fichero de comandos y dibuja la gráfica del caso
	medio de un método de
	   * ordenación y su ajuste a la función correspondiente.
	   * param metodo: metodo de ordenacion.
	   * param metodo: orden de complejidad del metodo de ordenacion.
	   */
	void generarGrafica(string metodo);

	/*
  Examen de Prácticas. EUF_Análisis experimental de algoritmos de búsqueda del k-ésimo menor elemento.
  FAA_1º Grado Ingeniería Informática. Martes 13 de septiembre de 2022
  6/10
	 * Configura los parámetros para el fichero de comandos
	 * y dibuja la gráfica de dos métodos de ordenación.
	 * param metodo1: primer metodo de ordenacion.
	 * param metodo2: segundo metodo de ordenacion.
	 */
	void generarGrafica(string metodo1, string metodo2);
	void generarGrafica(vector<string> nombreAlgoritmo);

};