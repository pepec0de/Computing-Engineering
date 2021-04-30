#include <iostream>
#include <locale.h>
#include "TestOrdenacion.h"

using namespace std;

TestOrdenacion test;

int MenuPrincipal() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t\t\t\t\t\t*** FAA. Práctica 2. Curso 20/21 ***\n\n";
		cout << "\t\t*** MENU PRINCIPAL ***\n";
		cout << "\t*** ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACIÓN ***\n\n";
		cout << "\t\t1.- Probar los métodos de ordenación.\n";
		cout << "\t\t2.- Obtener el caso medio de un método de ordenación.\n";
		cout << "\t\t3.- Comparar dos métodos.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opción: "; else cout << "\t\tOpción incorrecta. Elige opción: ";
		cin >> opc;
	} while (opc < 0 || opc > 3);
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
/* Programa principal */
int main() {
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	setlocale(LC_CTYPE, "Spanish");

	int opc = 0;
	do {
		opc = MenuPrincipal();
		switch (opc) {
		case 1:
			test.comprobarMetodosOrdenacion();
			break;
		case 2:
			
			break;
		case 3:
			break;
		}
	} while (opc != 0);
	return 0;
};