#include <iostream>
#include <locale.h>
#include "TestOrdenacion.h"

using namespace std;

TestOrdenacion test;

int menuPrincipal() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t\t\t\t\t\t*** FAA. Práctica 2. Curso 20/21 ***\n\n";
		cout << "\t\t\t\tAntonio Pablo García Sanabria y José María González Abad\n";
		cout << "\t\t*** MENU PRINCIPAL ***\n";
		cout << "\t*** ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACIÓN ***\n\n";
		cout << "\t\t1.- Probar los métodos de ordenación.\n";
		cout << "\t\t2.- Obtener el caso medio de un método de ordenación.\n";
		cout << "\t\t3.- Comparar dos métodos.\n";
		cout << "\t\t4.- Comparar todos.\n";
		cout << "\t\t5.- Tiempos teoricos.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opción: "; else cout << "\t\tOpción incorrecta. Elige opción: ";
		cin >> opc;
	} while (opc < 0 || opc > 5);
	cout << endl << endl;
	return opc;
}

void menuCasoMedio() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t*** MÉTODO A ESTUDIAR PARA EL CASO MEDIO ***\n\n";
		cout << "\t\t1.- BURBUJA.\n";
		cout << "\t\t2.- INSERCIÓN.\n";
		cout << "\t\t3.- SELECCIÓN.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opción: "; else cout << "\t\tOpción incorrecta. Elige opción: ";
		cin >> opc;
	} while (opc < 0 || opc > 3);
	cout << endl << endl;

	switch (opc) {
	case 1:
		test.casoMedio(BURBUJA);
		break;
	case 2:
		test.casoMedio(INSERCION);
		break;
	case 3:
		test.casoMedio(SELECCION);
		break;
	}
}

void menuComparar() {
	int met1 = 0;
	do {
		system("cls");
		cout << "\t*** COMPARACIÓN DE DOS MÉTODOS DE ORDENACIÓN ***\n\n";
		cout << "Selecciona los métodos a comparar\n";
		cout << "\t\t1.- BURBUJA.\n";
		cout << "\t\t2.- INSERCIÓN.\n";
		cout << "\t\t3.- SELECCIÓN.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (met1 == 0) cout << "\t\tMétodo 1: "; else cout << "\t\tOpción incorrecta. Elige método 1: ";
		cin >> met1;
	} while (met1 < 0 || met1 > 3);
	if (met1 != 0) {
		int met2 = 0;
		do {
			if (met2 != met1 || met2 == 0) cout << "\t\tMétodo 2: "; else cout << "\t\tOpción incorrecta. Elige método 2: ";
			cin >> met2;
		} while (met2 < 0 || met2 > 3 && met1 == met2);
		if (met2 != 0) {
			met1--;
			met2--;
			test.comparar(met1, met2);
		}
	}
}

void menuTeoricos() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t*** MÉTODO A ESTUDIAR PARA TIEMPOS TEORICOS ***\n\n";
		cout << "\t\t1.- BURBUJA.\n";
		cout << "\t\t2.- INSERCIÓN.\n";
		cout << "\t\t3.- SELECCIÓN.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opción: "; else cout << "\t\tOpción incorrecta. Elige opción: ";
		cin >> opc;
	} while (opc < 0 || opc > 3);
	cout << endl << endl;

	switch (opc) {
	case 1:
		test.casosTeoricos(BURBUJA);
		break;
	case 2:
		test.casosTeoricos(INSERCION);
		break;
	case 3:
		test.casosTeoricos(SELECCION);
		break;
	}
}

/* Programa principal */
int main() {
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	setlocale(LC_CTYPE, "Spanish");

	int opc = 0;
	do {
		opc = menuPrincipal();
		switch (opc) {
		case 1:
			test.comprobarMetodosOrdenacion();
			break;
		case 2:
			menuCasoMedio();
			break;
		case 3:
			menuComparar();
			break;
		case 4:
			test.compararTodos();
			break;
		case 5:
			menuTeoricos();
		}
	} while (opc != 0);
	return 0;
};