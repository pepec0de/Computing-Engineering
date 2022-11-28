#include "Test_Algoritmos_EUF.h"

Test_Algoritmos_EUF::Test_Algoritmos_EUF() {
	nombreAlgoritmo.push_back("BusquedaMenorDirecta");
	nombreAlgoritmo.push_back("BusquedaMenorIterativa");
	nombreAlgoritmo.push_back("BusquedaMenorRecursiva");
}

Test_Algoritmos_EUF::~Test_Algoritmos_EUF() {}

double Test_Algoritmos_EUF::buscaKesimoMenor(int k, vector<int>& v, int metodo, int& elem) {
	Algoritmos_EUF algos;
	Mtime t;
	LARGE_INTEGER t_ini, t_fin;
	QueryPerformanceCounter(&t_ini);

	switch (metodo) {
	case busquedaK_menor_Dir:
		elem = algos.busquedaK_menor_Dir(v, k);
		break;

	case busquedaK_menor_It:
		elem = algos.busquedaK_menor_It(v, k);
		break;

	case busquedaK_menor_RC:
		elem = algos.busquedaK_menor_RC(v, k);
		break;
	}

	QueryPerformanceCounter(&t_fin);
	return t.performancecounter_diff(&t_fin, &t_ini) * 1000;
}

/*
 * Comprueba que los algoritmos funcionan correctamente
 */
void Test_Algoritmos_EUF::comprobar_Algoritmos_EUF() {
	int talla;
	cout << endl << endl << "Introduce la talla: ";
	cin >> talla;
	ConjuntoInt v;
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++)
	{
		vector<int> A;
		v.GeneraVector(A, talla);
		cout << endl << endl << "vector inicial para el metodo " << nombreAlgoritmo[metodo] << ":" << endl << endl;
		v.escribe(A);
		int k = 0;
		while (k < 1 || k > talla) {
			cout << "\tIntroduce el k-menor a buscar: ";
			cin >> k;
		}
		int elem;
		buscaKesimoMenor(k, A, metodo, elem);
		cout << endl << endl << "\t\tElemento " << k << " menor buscado con el algoritmo " << nombreAlgoritmo[metodo] << ": " << elem << endl << endl;
		system("pause");
		system("cls");
	}
}


/*
* Calcula el caso medio de un metodo ,
* Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
* param metodo: metodo a estudiar.
*/
void Test_Algoritmos_EUF::casoMedio(int metodo) {
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream f(nombreAlgoritmo[metodo] + ".dat");
	cout << endl << "Tiempo promedio de " << nombreAlgoritmo[metodo] << endl;
	cout << "Tiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" << endl << endl;
	double tiempo = 0;

	ConjuntoInt c;
	for (int talla = tallaIni; talla <= tallaFin; talla += inc) {
		for (int i = 0; i < 10; i++) {
			vector<int> v;
			c.GeneraVector(v, talla);
			int e, k = (rand() % talla);
			tiempo += buscaKesimoMenor(k, v, metodo, e);
		}
		tiempo = tiempo / 10;
		f << talla << "\t" << tiempo << endl;
		cout << "\t" << talla << "\t\t" << (double)tiempo << endl;
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
		g.generarGrafica(nombreAlgoritmo[metodo]);
	}break;
	default:
		cout << "\nGráfica no generada.\n";
	}
	cout << endl;
	system("cls");
}

/*
 * Compara dos algoritmos.
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo1: Primer metodo a comparar
 * param metodo2: Segundo metodo a comparar
 */
void Test_Algoritmos_EUF::comparar(int metodo1, int metodo2) {
	ofstream f1(nombreAlgoritmo[metodo1] + ".dat");
	ofstream f2(nombreAlgoritmo[metodo2] + ".dat");
	cout << endl << "Tiempo promedio de " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << endl;
	cout << "Tiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" << endl << endl;
	double t1 = 0, t2 = 0;

	ConjuntoInt c;
	int e;
	for (int talla = tallaIni; talla <= tallaFin; talla += inc) {
		for (int i = 0; i < 10; i++) {
			vector<int> v1, v2;
			c.GeneraVector(v1, talla);
			t1 += buscaKesimoMenor((rand() % talla), v1, metodo1, e);
			c.GeneraVector(v2, talla);
			t2 += buscaKesimoMenor((rand() % talla), v2, metodo2, e);
		}
		t1 /= 10;
		t2 /= 10;
		f1 << talla << "\t" << t1 << endl;
		f2 << talla << "\t" << t2 << endl;
		cout << "\t" << talla << "\t\t" << (double)t1 << "\t\t" << (double)t2 << endl;
	}
	f1.close();
	f2.close();

	cout << endl << "Datos guardados en el fichero " << nombreAlgoritmo[metodo1] << ".dat " << endl;
	cout << endl << "Datos guardados en el fichero " << nombreAlgoritmo[metodo2] << ".dat " << endl;

	/* Generar grafica */
	char opc;
	cout << endl << "Generar gráfica de resultados? (s/n): ";
	cin >> opc;
	switch (opc) {
	case 's':
	case 'S': {
		/* Ejecutar el fichero por lotes (comandos)*/
		Graficas g;
		g.generarGrafica(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
	}break;
	default:
		cout << "\nGráfica no generada.\n";
	}
	cout << endl;
	system("cls");
}

/*
* Compara todos los metodos de busqueda.
* Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
*/
void Test_Algoritmos_EUF::comparaTodos() {
	string nombre = "";
	for (string str : nombreAlgoritmo) {
		nombre += str;
	}
	ofstream f(nombre + ".dat");
	cout << endl << "Comparación de los algoritmos de búsqueda: " << nombre << endl;
	cout << "Tiempos de ejecucion promedio" << endl << endl;
	cout << "\t\t\t" << nombre << endl;
	cout << "\tTalla\t\tTiempo (ms)\t\tTiempo (ms)" << endl << endl;

	vector<double> tiempos;
	int e;
	for (string str : nombreAlgoritmo) {
		tiempos.push_back(0);
	}
	int pos;
	ConjuntoInt* conjunto;
	// Iteracion por cada talla
	for (int talla = tallaIni; talla <= tallaFin; talla += inc) {
		for (int i = 0; i < nombreAlgoritmo.size(); i++) {
			tiempos[i] = 0.0;
		}
		ConjuntoInt c;
		for (int i = 0; i < NUMREPETICIONES; i++) {
			// Acumulamos los tiempos en el vector tiempos
			for (int j = 0; j < nombreAlgoritmo.size(); j++) {
				vector<int> v;
				c.GeneraVector(v, talla);
				int k = (rand() % talla);
				tiempos[j] += buscaKesimoMenor(k, v, j, e);
			}
		}
		f << talla << "\t";
		cout << "\t" << talla << "\t";
		for (int i = 0; i < tiempos.size(); i++) {
			tiempos[i] /= NUMREPETICIONES;
			f << tiempos[i] << "\t";
			cout << "\t" << (double)tiempos[i];
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
		g.generarGrafica(nombreAlgoritmo);
	}break;
	default:
		cout << "\nGrafica no generada.\n";
	}
	cout << endl;
	system("cls");
}