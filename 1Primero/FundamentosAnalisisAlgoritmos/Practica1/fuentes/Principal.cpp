#include "TestAlgoritmo.h"
#include <iostream>
#include <stdlib.h>
#include "Constantes.h"

using namespace std;

TestAlgoritmo test;

// Desarrollo del menu para los casos del menú teórico
void menuCosteCasoTeorico() {
	int opc;
	system("cls");
	cout << "\n\t*** Caso a estudiar durante la busqueda secuencial ***\n\n";
	cout << "\t0: Caso peor\n";
	cout << "\t1: Caso medio\n";
	cout << "\t2: Caso mejor\n";
	cout << "\t- - - - - - - - - -\n\n";
	cout << "\tElija una opcion: ";
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
		cout << "\n Error en su eleccion, inténtelo de nuevo.\n";
		system("pause");
		break;
	}
}

void menuTeorico() {
	int opc;
	system("cls");
	do {
		cout << "\n\t*** MENU TEORICO DEL ALGORITMO DE BUSQUEDA SECUENCIAL\n\n";
		cout << "\t1.- Comprobar el algoritmo de busqueda secuencial.\n";
		cout << "\t2.- Obtener los casos del metodo de busqueda secuencial\n";
		cout << "\t3.- Comparar los casos\n";
		cout << "\t0.- Volver al menu principal\n";
		cout << "\t- - - - - - - - - -\n\n";
		cout << "\tElija opcion: ";
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
			cout << "\n Error en su eleccion, inténtelo de nuevo.\n";
			system("pause");
			break;
		}
	} while (opc != 0);
}

void menuCosteCasoEmpirico() {

	int opc;
	system("cls");
	cout << "\n\t*** Caso a estudiar durante la busqueda secuencial ***\n\n";
	cout << "\t0: Caso peor\n";
	cout << "\t1: Caso medio\n";
	cout << "\t2: Caso mejor\n";
	cout << "\t- - - - - - - - - -\n\n";
	cout << "\tElija una opcion: ";
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
		cout << "\n Error en su eleccion, inténtelo de nuevo.\n";
		system("pause");
		break;
	}
}

void menuEmpirico() {
	int opc;
	system("cls");
	do {
		cout << "\n\t*** MENU EMPIRICO DEL ALGORITMO DE BUSQUEDA SECUENCIAL\n\n";
		cout << "\t1.- Comprobar el algoritmo de busqueda secuencial\n";
		cout << "\t2.- Obtener los casos del metodo de busqueda secuencial\n";
		cout << "\t3.- Comparar los casos\n";
		cout << "\t0.- Volver al menu principal\n";
		cout << "\t- - - - - - - - - -\n\n";
		cout << "\tElija opcion: ";
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
			cout << "\n Error en su eleccion, inténtelo de nuevo.\n";
			system("pause");
			break;
		}
	} while (opc != 0);
}

/* Programa principal */
int main()
{
	/* ESCRIBIR PARA COMPLETAR LA PRÁCTICA*/
	int opc;

	do {
		system("cls");
		cout << "\n\t*** FAA. Practica 1. Curso 20/21 ***\n";
		cout << "\t\t\t\t\tAlumnos: Antonio Pablo Garcia Sanabria y Jose Maria Gonzalez Abad\n\n";
		cout << "\t*** ESTUDIO DE LA COMPLEJIDAD DEL ALGORITMO DE BUSQUEDA SECUENCIAL\n\n";
		cout << "\t\t\t1.- ESTUDIO TEORICO\n";
		cout << "\t\t\t2.- ESTUDIO EMPIRICO\n";
		cout << "\t\t\t0.- Salir\n";
		cout << "\t\t\t- - - - - - - - - -\n\n";
		cout << "\t\t\tElija opcion: ";
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
			cout << "\n Error en su eleccion, inténtelo de nuevo.\n";
			system("pause");
			break;
		}
	} while (opc != 0);
	return 0;
}