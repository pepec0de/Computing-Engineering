#pragma once 
/* 
* Clase Graficas, contiene m�todos para guardar las gr�ficas de los resultados, 
* es decir, crea los ficheros por lo lotes(comandos) para generar los ficheros gr�ficos que 
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
	 * Configura los par�metros para el fichero de comandos y dibuja la gr�fica del caso
	medio de un m�todo de
	   * ordenaci�n y su ajuste a la funci�n correspondiente.
	   * param metodo: metodo de ordenacion.
	   * param metodo: orden de complejidad del metodo de ordenacion.
	   */
	void generarGrafica(string metodo);

	/*
  Examen de Pr�cticas. EUF_An�lisis experimental de algoritmos de b�squeda del k-�simo menor elemento.
  FAA_1� Grado Ingenier�a Inform�tica. Martes 13 de septiembre de 2022
  6/10
	 * Configura los par�metros para el fichero de comandos
	 * y dibuja la gr�fica de dos m�todos de ordenaci�n.
	 * param metodo1: primer metodo de ordenacion.
	 * param metodo2: segundo metodo de ordenacion.
	 */
	void generarGrafica(string metodo1, string metodo2);
	void generarGrafica(vector<string> nombreAlgoritmo);

};