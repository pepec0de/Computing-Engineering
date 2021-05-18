/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos métodos de búsqueda
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente.
 * 4. Comparar todos los algoritmos de búsqueda implementados.
 */
#include "TestBusqueda.h"
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
TestBusqueda::TestBusqueda() {
	nombreAlgoritmo.push_back("Secuencial");
	nombreAlgoritmo.push_back("BinariaIterativa");
	nombreAlgoritmo.push_back("InterpolacionIterativa");
} 
TestBusqueda::~TestBusqueda(){}

double TestBusqueda::buscaEnArrayDeInt(int v[], int size, int key, int metodo, int& pos) {
	AlgoritmosBusqueda estrategia;
	Mtime time;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	/* Invoca el método de búsqueda elegido */
	switch (metodo) {
	case SECUENCIALIT:
		pos = estrategia.busquedaSecuencialIt(v, size, key);
		break;
	case BINARIAIT:
		pos = estrategia.busquedaBinariaIt(v, size, key);
		break;
	case INTERIT:
		pos = estrategia.busquedaInterpolacionIt(v, size, key);
		break;
	}
	QueryPerformanceCounter(&t_final);
	return time.performancecounter_diff(&t_final, &t_inicial) * 1000;
}

void TestBusqueda::comprobarMetodosBusqueda() {
	int talla;
	cout << "\n\nIntroduce la talla: ";
	cin >> talla;
	system("cls");
	ConjuntoInt *v = new ConjuntoInt(talla);
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++) {
		v->GeneraVector(talla);
		cout << "\n\nVector para el metodo " << nombreAlgoritmo[metodo] << ":\n\n";
		v->escribe();
		int key;
		cout << "Introduce la clave: ";
		cin >> key;
		int pos;
		double tiempo = buscaEnArrayDeInt(v->getDatos(), talla, key, metodo, pos);
		if (pos != -1) {
			cout << "\n\nLa posicion del key en el vector es : " << pos << " y ha tardado: " << tiempo << " ms.\n\n";
		} else {
			cout << "\n\nNo se ha encontrado la key en el vector. Se ha tardado: " << tiempo << " ms.\n\n";
		}
		v->vaciar();
		system("pause");
		system("cls");
	}
}

void TestBusqueda::casoMedio(int metodo) {
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream f(nombreAlgoritmo[metodo] + ".dat");
	cout << endl << "Tiempo promedio de búsqueda por " << nombreAlgoritmo[metodo] << endl;
	cout << "Tiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" << endl << endl;
	double tiempo = 0;
	int pos;
	ConjuntoInt* conjunto;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {

		for (int i = 0; i < NUMREPETICIONES; i++) {
			conjunto = new ConjuntoInt(talla);
			conjunto->GeneraVector(talla);
			tiempo += buscaEnArrayDeInt(conjunto->getDatos(), talla, conjunto->generaKey(), metodo, pos);
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
		int orden;
		switch (metodo) {
		case SECUENCIALIT:
			orden = N;
			break;
		case BINARIAIT:
			orden = logN;
			break;
		case INTERIT:
			orden = loglogN;
			break;
		}
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], orden);
	}break;
	default:
		cout << "\nGráfica no generada.\n";
	}
	cout << endl;
	system("cls");
}

void TestBusqueda::comparar(int metodo1, int metodo2) {
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//	
	ofstream f1(nombreAlgoritmo[metodo1] + ".dat");
	ofstream f2(nombreAlgoritmo[metodo2] + ".dat");
	cout << endl << "Comparación de los algoritmos de búsqueda: " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << endl;
	cout << "Tiempos de ejecucion promedio" << endl << endl;
	cout << "\t\t\t" << nombreAlgoritmo[metodo1] << "\t\t\t" << nombreAlgoritmo[metodo2] << endl;
	cout << "\tTalla\t\tTiempo (ms)\t\tTiempo (ms)" << endl << endl;

	double tiempo1 = 0, tiempo2 = 0;
	int pos;
	ConjuntoInt* conjunto;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {
		for (int i = 0; i < NUMREPETICIONES; i++) {
			conjunto = new ConjuntoInt(talla);
			conjunto->GeneraVector(talla);
			tiempo1 += buscaEnArrayDeInt(conjunto->getDatos(), talla, conjunto->generaKey(), metodo1, pos);
			// volvemos a generar el vector
			conjunto->GeneraVector(talla);
			tiempo2 += buscaEnArrayDeInt(conjunto->getDatos(), talla, conjunto->generaKey(), metodo2, pos);
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

void TestBusqueda::compararTodos() {

}