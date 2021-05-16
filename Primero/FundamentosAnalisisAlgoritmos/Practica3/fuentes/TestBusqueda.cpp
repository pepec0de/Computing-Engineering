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
	ConjuntoInt* v = new ConjuntoInt(talla);
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++) {
		v->GeneraVector(talla);
		cout << "\n\nVector para el metodo " << nombreAlgoritmo[metodo] << ":\n\n";
		v->escribe();
		int key;
		cout << "Introduce la clave: ";
		cin >> key;
		int pos;
		double tiempo = buscaEnArrayDeInt(v->getDatos(), talla, key, metodo, pos);
		cout << "\n\nLa posicion del key en el vector es : " << key << " y ha tardado: " << tiempo << " ms\n\n";
		v->vaciar();
		system("pause");
		system("cls");
	}
}

void TestBusqueda::casoMedio(int metodo) {

}