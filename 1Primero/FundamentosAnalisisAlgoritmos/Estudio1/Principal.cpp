#include "Test_Algoritmos_EUF.h"
#include <iostream>
using namespace std;

/* Programa principal */

Test_Algoritmos_EUF test;

int mainOpc() {
	int opc = 0;

	cout << "FAA. EUF. Sept 2022\n\nJose Maria Gonzalez Abad\n\n\n";
	cout << "\t\t*** MENU PRINCIPAL ***\n\n";
	cout << "\t1.- Probar los algoritmos de Busqueda k-esimo menor.\n\n";
	cout << "\t2.- Obtener el caso medio de un algoritmo de busqueda K-esimo menor.\n\n";
	cout << "\t3.- Comparar dos algoritmos.\n\n";
	cout << "\t4.- Comparar todos los algoritmos.\n\n";
	cout << "\t0.- Salir.\n\n";
	cout << "\t-----------\n\tElige opcion: ";
	cin >> opc;

	return opc;
}

int eligeAlgo() {
	int opc = -1;
	cout << "0. Solucion directa\n1.SolucionIterativa\n2.SolucionRecursiva\n\nElige oopcion: ";
	cin >> opc;

	while (opc < 0 || opc > 2) {
		cout << "Elige opcion: ";
		cin >> opc;
	}
	return opc;
}

void opcion2() {
	cout << "Algoritmo a estudiar para caso medio\n";

	test.casoMedio(eligeAlgo());
}

void opcion3() {

}

int main()
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int opc = -1;
	while (opc != 0) {
		opc = mainOpc();

		switch (opc) {
		case 1:
			test.comprobar_Algoritmos_EUF();
			break;

		case 2:
			opcion2();
			break;

		case 3:
			opcion3();
		}
	}
	return 0;
}