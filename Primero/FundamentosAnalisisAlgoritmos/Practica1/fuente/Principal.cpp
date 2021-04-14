#include "TestAlgoritmo.h"
#include <iostream>
#include <stdlib.h>
#include "Constantes.h"
#include <locale.h>

using namespace std;

TestAlgoritmo test;

// Desarrollo del menu para los casos del menú teórico
void menuCosteCasoTeorico() {
	int opc;
	system("cls");
	cout << "\n\t*** Caso a estudiar durante la búsqueda secuencial ***\n\n";
	cout << "\t0: Caso peor\n";
	cout << "\t1: Caso medio\n";
	cout << "\t2: Caso mejor\n";
	cout << "\t- - - - - - - - - -\n\n";
	cout << "\tElija una opción: ";
	cin >> opc;

	switch (opc)
	{
	case 0:
		test.costeCasoTeorico(0);
		break;
	case 1:
		test.costeCasoTeorico(1);
		break;
	case 2:
		test.costeCasoTeorico(2);
		break;
	default:
		cout << "\n Error en su elección, inténtelo de nuevo.\n\n";
		break;
	}
}

void menuTeorico() {
	int opc;
	system("cls");
	do {
		cout << "\n\t*** MENÚ TEÓRICO DEL ALGORITMO DE BÚSQUEDA SECUENCIAL\n\n";
		cout << "\t1.- Comprobar el algoritmo de búsqueda secuencial.\n";
		cout << "\t2.- Obtener los casos del método de búsqueda secuencial\n";
		cout << "\t3.- Comparar los casos\n";
		cout << "\t0.- Volver al menú principal\n";
		cout << "\t- - - - - - - - - -\n\n";
		cout << "\tElija opción: ";
		cin >> opc;

		switch (opc) {
		case 1:
			test.comprobarAlgoritmo();
			break;
		case 2:
			menuCosteCasoTeorico();
			break;
		case 3:
			test.compararTeorico(0, 1, 2);
			break;
		case 0:
			break;
		default:
			cout << "\n Error en su elección, inténtelo de nuevo.\n\n";
			break;
		}
	} while (opc != 0);
}

void menuCosteCasoEmpirico() {

	int opc;
	system("cls");
	cout << "\n\t*** Caso a estudiar durante la búsqueda secuencial ***\n\n";
	cout << "\t0: Caso peor\n";
	cout << "\t1: Caso medio\n";
	cout << "\t2: Caso mejor\n";
	cout << "\t- - - - - - - - - -\n\n";
	cout << "\tElija una opción: ";
	cin >> opc;

	switch (opc)
	{
	case 0:
		test.costeCasoEmpirico(0);
		break;
	case 1:
		test.costeCasoEmpirico(1);
		break;
	case 2:
		test.costeCasoEmpirico(2);
		break;
	default:
		cout << "\n Error en su elección, inténtelo de nuevo.\n\n";
		break;
	}
}

void menuEmpirico() {
	int opc;
	system("cls");
	do {
		cout << "\n\t*** MENÚ EMPÍRICO DEL ALGORITMO DE BÚSQUEDA SECUENCIAL\n\n";
		cout << "\t1.- Comprobar el algoritmo de búsqueda secuencial\n";
		cout << "\t2.- Obtener los casos del método de búsqueda secuencial\n";
		cout << "\t3.- Comparar los casos\n";
		cout << "\t0.- Volver al menú principal\n";
		cout << "\t- - - - - - - - - -\n\n";
		cout << "\tElija opción: ";
		cin >> opc;

		switch (opc) {
		case 1:
			test.comprobarAlgoritmo();
			break;
		case 2:
			menuCosteCasoEmpirico();
			break;
		case 3:
			test.compararEmpirico(0, 1, 2);
			break;
		case 0:
			break;
		default:
			cout << "\n Error en su elección, inténtelo de nuevo." << endl << endl;
			break;
		}
	} while (opc != 0);
}

/* Programa principal */
int main()
{
	// Para poner el idioma en español
	setlocale(LC_CTYPE, "Spanish");

	/* ESCRIBIR PARA COMPLETAR LA PRÁCTICA*/
	int opc;

	do {
		system("cls");
		cout << "\n\t*** FAA. Práctica 1. Curso 20/21 ***\n";
		cout << "\t\t\t\t\tAlumnos: Antonio Pablo García Sanabria y José María González Abad\n\n";
		cout << "\t*** ESTUDIO DE LA COMPLEJIDAD DEL ALGORITMO DE BÚSQUEDA SECUENCIAL\n\n";
		cout << "\t\t\t1.- ESTUDIO TEÓRICO\n";
		cout << "\t\t\t2.- ESTUDIO EMPÍRICO\n";
		cout << "\t\t\t0.- Salir\n";
		cout << "\t\t\t- - - - - - - - - -\n\n";
		cout << "\t\t\tElija opción: ";
		cin >> opc;

		switch (opc) {
		case 1:
			menuTeorico();
			break;
		case 2:
			menuEmpirico();
			break;
		case 0:
			break;
		default:
			cout << "\n Error en su elección, inténtelo de nuevo." << endl << endl;
			break;
		}
	} while (opc != 0);
	return 0;
}