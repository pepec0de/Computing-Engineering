/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los m�todos de b�squeda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un m�todo de b�squeda,
 *    permitiendo guardar los datos e imprimir la gr�fica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos m�todos de b�squeda
 *    permitiendo guardar los datos e imprimir la gr�fica correspondiente.
 * 4. Comparar todos los algoritmos de b�squeda implementados.
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
	/* Invoca el m�todo de b�squeda elegido */
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