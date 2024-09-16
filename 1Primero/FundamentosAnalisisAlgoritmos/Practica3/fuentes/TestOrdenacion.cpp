/* 
 * La clase TestOrdenacion contiene los metodos para:
 * 1. Comprobar que los métodos de ordenacion de la clase Ordenacion funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de ordenación,
 *    guardando los datos y permitiendo imprimir la gráfica correspondiente 
 * 3. Comparar el coste temporal de dos de los métodos de ordenación 
 *    burbuja, inserción, y selección, guardando los datos y permitiendo imprimir la gráfica correspondiente.
 */
#include "AlgoritmosOrdenacion.h"
#include "TestOrdenacion.h"
#include "ConjuntoInt.h"
#include "Mtime.h"
#include "Constantes.h"
#include <string>
#include <iostream>
using namespace std;


TestOrdenacion::TestOrdenacion()
{
	nombreAlgoritmo.push_back("Burbuja");
	nombreAlgoritmo.push_back("Insercion");
	nombreAlgoritmo.push_back("Seleccion");
} 
TestOrdenacion::~TestOrdenacion(){}

/*
 * Ordena un array de enteros según el método indicado
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 * param metodo: Metodo de ordenacion a utilizar
 * return Tiempo empleado en la ordenación (en milisegundos)
 */
double TestOrdenacion::ordenarArrayDeInt(int v[],int size,int metodo) 
{
	AlgoritmosOrdenacion estrategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	/* Invoca al método de ordenación elegido */
	switch (metodo){
		case BURBUJA: estrategia.ordenaBurbuja(v, size);
			break;
		case INSERCION: estrategia.ordenaInsercion(v, size);
			break;
		case SELECCION: estrategia.ordenaSeleccion(v, size);
			break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;
}

/*
 * Comprueba los metodos de ordenacion
 */
void TestOrdenacion::comprobarMetodosOrdenacion()
{
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla; 
	system("cls"); 
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++)
	{
		ConjuntoInt *v= new ConjuntoInt(talla); 
		v->GeneraVector(talla);
		cout <<endl<<endl<< "vector inicial para el metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		double secs=ordenarArrayDeInt(v->getDatos(),talla,metodo);
		cout<<endl<<endl<<"Array ordenado con metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		cout<<endl;
		v->vaciar(); 
		system("pause");
		system("cls");
	} 
	system("cls");
}
    
/*
 * Calcula el caso medio de un metodo de ordenacion.
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: metodo de ordenacion a estudiar.
 */
void TestOrdenacion::casoMedio(int metodo)
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream f(nombreAlgoritmo[metodo] + ".dat");
	cout << endl << "Tiempo promedio de ordenacion por " << nombreAlgoritmo[metodo] << endl;
	cout << "Tiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" << endl << endl;
	double tiempo = 0;

	ConjuntoInt* conjunto;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {

		for (int i = 0; i < NUMREPETICIONES; i++) {
			conjunto = new ConjuntoInt(talla);
			conjunto->GeneraVector(talla);
			tiempo += ordenarArrayDeInt(conjunto->getDatos(), talla, metodo);
			delete conjunto;
		}
		tiempo = tiempo / NUMREPETICIONES;
		f << talla << "\t" << tiempo << endl;
		cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempo << endl;
	}
	f.close();
	cout << endl << "Datos guardados en el fichero " << nombreAlgoritmo[metodo] << ".dat " << endl;

	/* Generar grafica */
	char opc;
	cout << endl << "Generar gráfica de resultados? (s/n): ";
	cin >> opc;
	switch (opc) {
	case 's':
	case 'S': {
		/* Ejecutar el fichero por lotes (comandos)*/
		Graficas g;
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], CUADRADO);
	}break;
	default:
		cout << "\nGráfica no generada.\n";
	}
	cout << endl;
	system("cls");
}

/*
 * Compara dos metodos de ordenacion. 
 * Genera el fichero de datos y permite las opcion de crear la gráfica correspondiente.
 * param metodo1: Primer metodo de ordenacion a comparar
 * param metodo2: Segundo metodo de ordenacion a comparar
 */
void TestOrdenacion::comparar(int metodo1, int metodo2) {
	 //** ESCRIBIR PARA COMPLETAR LA PRACTICA **//	
	ofstream f1(nombreAlgoritmo[metodo1] + ".dat");
	ofstream f2(nombreAlgoritmo[metodo2] + ".dat");
	cout << endl << "Comparación de los algoritmos de ordenación: " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << endl;
	cout << "Tiempos de ejecucion promedio" << endl << endl;
	cout << "\t\t\t" << nombreAlgoritmo[metodo1] << "\t\t\t" << nombreAlgoritmo[metodo2] << endl;
	cout << "\tTalla\t\tTiempo (ms)\t\tTiempo (ms)"<< endl << endl;
	
	double tiempo1 = 0, tiempo2 = 0;
	ConjuntoInt* conjunto;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {
		for (int i = 0; i < NUMREPETICIONES; i++) {
			conjunto = new ConjuntoInt(talla);
			conjunto->GeneraVector(talla);
			tiempo1 += ordenarArrayDeInt(conjunto->getDatos(), talla, metodo1);
			// volvemos a generar el vector
			conjunto->GeneraVector(talla);
			tiempo2 += ordenarArrayDeInt(conjunto->getDatos(), talla, metodo2);
			delete conjunto;
		}
		tiempo1 = tiempo1 / NUMREPETICIONES;
		tiempo2 = tiempo2 / NUMREPETICIONES;
		f1 << talla << "\t" << tiempo1 << endl;
		f2 << talla << "\t" << tiempo2 << endl;
		cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempo1 << "\t\t" << setw(10) << setprecision(2) << (double)tiempo2 << endl;
	}
	f1.close();
	f2.close();
	cout << endl << "Datos guardados en los ficheros: " << nombreAlgoritmo[metodo1] << ".dat y " << nombreAlgoritmo[metodo2] << ".dat\n";

	/* Generar grafica */
	char opc;
	cout << endl << "Generar grafica de resultados? (s/n): ";
	cin >> opc;
	switch (opc) {
	case 's':
	case 'S': {
		/* Ejecutar el fichero por lotes (comandos)*/
		Graficas g;
		g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
	}break;
	default:
		cout << "\nGrafica no generada.\n";
	}
	cout << endl;
	system("cls");
}


void TestOrdenacion::comparar() {
	// Concatenamos los nombres de los algoritmos
	string nombre = "";
	for (string str : nombreAlgoritmo) {
		nombre += str;
	}
	ofstream f(nombre + ".dat");
	cout << endl << "Comparación de los algoritmos de ordenación: " << nombre << endl;
	cout << "Tiempos de ejecucion promedio" << endl << endl;
	cout << "\t\t\t" << nombre << endl;
	cout << "\tTalla\t\tTiempo (ms)\t\tTiempo (ms)" << endl << endl;

	vector<double> tiempos;
	for (string str : nombreAlgoritmo) {
		tiempos.push_back(0);
	}
	int pos;
	ConjuntoInt* conjunto;
	// Iteracion por cada talla
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {
		for (int i = 0; i < nombreAlgoritmo.size(); i++) {
			tiempos[i] = 0.0;
		}

		for (int i = 0; i < NUMREPETICIONES; i++) {
			conjunto = new ConjuntoInt(talla);
			// Acumulamos los tiempos en el vector tiempos
			for (int j = 0; j < nombreAlgoritmo.size(); j++) {
				conjunto->GeneraVector(talla);
				tiempos[j] += ordenarArrayDeInt(conjunto->getDatos(), talla, j);
				conjunto->vaciar();
			}
			delete conjunto;
		}
		f << talla << "\t";
		cout << "\t" << talla << "\t";
		for (int i = 0; i < tiempos.size(); i++) {
			tiempos[i] /= NUMREPETICIONES;
			f << tiempos[i] << "\t";
			cout << "\t" << setw(10) << setprecision(2) << (double)tiempos[i];
		}
		f << endl;
		cout << endl;
	}
	f.close();
	cout << endl << "Datos guardados en los ficheros: " << nombre << ".dat\n";

	// Generar grafica
	char opc;
	cout << endl << "Generar grafica de resultados? (s/n): ";
	cin >> opc;
	switch (opc) {
	case 's':
	case 'S': {
		// Ejecutar el fichero por lotes (comandos)
		Graficas g;
		g.generarGraficaCMP(nombreAlgoritmo);
	}break;
	default:
		cout << "\nGrafica no generada.\n";
	}
	cout << endl;
	system("cls");
}

void TestOrdenacion::casosTeoricos(int metodo) {
	ofstream f(nombreAlgoritmo[metodo] + "Teorico.dat");
	cout << endl << "Tiempo en operacion elementales segun diferentes casos del algoritmo: " << nombreAlgoritmo[metodo] << endl;
	cout << "Operaciones elementales (OE)" << endl << endl;
	cout << "\tTalla\tTiempo Caso Peor\tTiempo Caso Medio\tTiempo caso Mejor" << endl << endl;

	double tiempoPeor = 0; int tiempoMedio = 0; int tiempoMejor = 0;
	switch (metodo) {
	case BURBUJA:
		for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {
			// Caso mejor T(n) = 1 + 4*(n-1) +3*(n^2-n)
			tiempoMejor = 1 + 4*(talla - 1) + 3*(talla * talla - talla);

			// Caso medio T(n) = 1 + 4*(n-1) +3*(n^2-n) + 7*((n^2-n)/4)
			tiempoMedio = 1 + 4 * (talla - 1) + 3 * (talla * talla - talla) + 7*((talla*talla - talla)/4);

			// Caso peor T(n) = 1 + 4*(n-1) +3*(n^2-n) + 7*((n^2-n)/2)
			tiempoPeor = 1 + 4*(talla - 1) + 3*(talla * talla - talla) + 7*((talla * talla - talla)/2);

			// Mostrar datos
			cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempoPeor << " \t" << setw(10) << setprecision(2) << (double)tiempoMedio << " \t" << setw(10) << setprecision(2) << (double)tiempoMejor << " \t\t" << endl;
			// escribimos en el fichero
			f << talla << "\t" << tiempoPeor << "\t" << tiempoMedio << "\t" << tiempoMejor << endl;
		}
		break;
	case INSERCION:
		for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {
			// Caso mejor T(n) = 1 + 12*(n-1)
			tiempoMejor = 1 + 12 * (talla - 1);

			// Caso medio T(n) = 1 + 12*(n-1) + 5*((n^2-n)/4)
			tiempoMedio = 1 + 12 * (talla - 1) + 5*((talla*talla - talla)/4);

			// Caso peor T(n) = 1 + 12*(n-1) + 5*((n^2-n)/2)
			tiempoPeor = 1 + 12 * (talla - 1) + 5 * ((talla * talla - talla) / 2);

			// Mostrar datos
			cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempoPeor << " \t" << setw(10) << setprecision(2) << (double)tiempoMedio << " \t" << setw(10) << setprecision(2) << (double)tiempoMejor << " \t\t" << endl;
			// escribimos en el fichero
			f << talla << "\t" << tiempoPeor << "\t" << tiempoMedio << "\t" << tiempoMejor << endl;
		}
		break;
	case SELECCION:
		for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {
			// Caso mejor T(n) = 1 + 12*(n-1) + 3*(n^2 -n)
			tiempoMejor = 1 + 12*(talla - 1) + 3 *(talla * talla - talla);

			// Caso medio T(n) = 1 + 12*(n-1) + 3*(n^2 -n) + ((n^2-n)/4)
			tiempoMedio = 1 + 12 * (talla - 1) + 3 * (talla * talla - talla) + ((talla * talla - talla)/4);

			// Caso peor T(n) = 1 + 12*(n-1) + 3*(n^2 -n) + ((n^2-n)/2)
			tiempoPeor = 1 + 12 * (talla - 1) + 3 * (talla * talla - talla) + ((talla * talla - talla)/2);

			// Mostrar datos
			cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempoPeor << " \t" << setw(10) << setprecision(2) << (double)tiempoMedio << " \t" << setw(10) << setprecision(2) << (double)tiempoMejor << " \t\t" << endl;
			// escribimos en el fichero
			f << talla << "\t" << tiempoPeor << "\t" << tiempoMedio << "\t" << tiempoMejor << endl;
		}
		break;
	}
	f.close();
	cout << endl << "Datos guardados en el fichero " << nombreAlgoritmo[metodo] << "Teorico.dat " << endl;

	/* Generar grafica */
	char opc;
	cout << endl << "Generar grafica de resultados? (s/n): ";
	cin >> opc;
	switch (opc) {
	case 's':
	case 'S': {
		/* Ejecutar el fichero por lotes (comandos)*/
		Graficas g;
		g.generarGraficaTeorico(nombreAlgoritmo[metodo]);
	}break;
	default:
		cout << "\nGrafica no generada.\n";
	}
	system("cls");
}