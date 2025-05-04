#include <iostream>
#include <locale.h>
#include "TestOrdenacion.h"

using namespace std;

TestOrdenacion test;

int menuPrincipal() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t\t\t\t\t\t*** FAA. Pr�ctica 2. Curso 20/21 ***\n\n";
		cout << "\t\t\t\tAntonio Pablo Garc�a Sanabria y Jos� Mar�a Gonz�lez Abad\n";
		cout << "\t\t*** MENU PRINCIPAL ***\n";
		cout << "\t*** ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACI�N ***\n\n";
		cout << "\t\t1.- Probar los m�todos de ordenaci�n.\n";
		cout << "\t\t2.- Obtener el caso medio de un m�todo de ordenaci�n.\n";
		cout << "\t\t3.- Comparar dos m�todos.\n";
		cout << "\t\t4.- Comparar todos.\n";
		cout << "\t\t5.- Tiempos teoricos.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
		cin >> opc;
	} while (opc < 0 || opc > 5);
	cout << endl << endl;
	return opc;
}

void menuCasoMedio() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t*** M�TODO A ESTUDIAR PARA EL CASO MEDIO ***\n\n";
		cout << "\t\t1.- BURBUJA.\n";
		cout << "\t\t2.- INSERCI�N.\n";
		cout << "\t\t3.- SELECCI�N.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
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
		cout << "\t*** COMPARACI�N DE DOS M�TODOS DE ORDENACI�N ***\n\n";
		cout << "Selecciona los m�todos a comparar\n";
		cout << "\t\t1.- BURBUJA.\n";
		cout << "\t\t2.- INSERCI�N.\n";
		cout << "\t\t3.- SELECCI�N.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (met1 == 0) cout << "\t\tM�todo 1: "; else cout << "\t\tOpci�n incorrecta. Elige m�todo 1: ";
		cin >> met1;
	} while (met1 < 0 || met1 > 3);
	if (met1 != 0) {
		int met2 = 0;
		do {
			if (met2 != met1 || met2 == 0) cout << "\t\tM�todo 2: "; else cout << "\t\tOpci�n incorrecta. Elige m�todo 2: ";
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
		cout << "\t*** M�TODO A ESTUDIAR PARA TIEMPOS TEORICOS ***\n\n";
		cout << "\t\t1.- BURBUJA.\n";
		cout << "\t\t2.- INSERCI�N.\n";
		cout << "\t\t3.- SELECCI�N.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
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