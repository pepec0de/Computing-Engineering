#include <iostream>
#include "TestOrdenacion.h"
#include "TestBusqueda.h"

using namespace std;

void opcionesOrdenacion() {
	cout << "\t\t1.- BURBUJA.\n";
	cout << "\t\t2.- INSERCI�N.\n";
	cout << "\t\t3.- SELECCI�N.\n";
	/**** EXAJUN21****/
	cout << "\t\t4.- JUNIO21.\n";
	/*****************/
	cout << "\t\t0.- Salir.\n";
	cout << "\t\t----------\n";
}

void menuOrdenacionCasoMedio() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t*** M�TODO A ESTUDIAR PARA EL CASO MEDIO ***\n\n";
		opcionesOrdenacion();
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
		cin >> opc;
	} while (opc < 0 || opc > 4);
	cout << endl << endl;
	TestOrdenacion testOrdenacion;
	testOrdenacion.casoMedio(opc - 1);
}

void menuOrdenacionComparar() {
	int met1 = 0;
	do {
		system("cls");
		cout << "\t*** COMPARACI�N DE DOS M�TODOS DE ORDENACI�N ***\n\n";
		cout << "Selecciona los m�todos a comparar\n";
		opcionesOrdenacion();
		if (met1 == 0) cout << "\t\tM�todo 1: "; else cout << "\t\tOpci�n incorrecta. Elige m�todo 1: ";
		cin >> met1;
	} while (met1 < 0 || met1 > 4);
	if (met1 != 0) {
		int met2 = 0;
		do {
			if (met2 != met1 || met2 == 0) cout << "\t\tM�todo 2: "; else cout << "\t\tOpci�n incorrecta. Elige m�todo 2: ";
			cin >> met2;
		} while (met2 < 0 || met2 > 4 && met1 == met2);
		if (met2 != 0) {
			TestOrdenacion testOrdenacion;
			testOrdenacion.comparar(met1-1, met2-1);
		}
	}
}

void menuOrdenacionTeoricos() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t*** M�TODO A ESTUDIAR PARA TIEMPOS TEORICOS ***\n\n";
		opcionesOrdenacion();
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
		cin >> opc;
	} while (opc < 0 || opc > 4);
	cout << endl << endl;

	if (opc != 0) {
		TestOrdenacion testOrdenacion;
		testOrdenacion.casosTeoricos(opc-1);
	}
}

void menuOrdenacion() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t\t*** MEN� ORDENACI�N ***\n";
		cout << "\t\t1.- Probar los m�todos de ordenaci�n.\n";
		cout << "\t\t2.- Obtener el caso medio de un m�todo de ordenaci�n.\n";
		cout << "\t\t3.- Comparar dos m�todos.\n";
		cout << "\t\t4.- Comparar todos.\n";
		cout << "\t\t5.- Tiempos teoricos.\n";
		cout << "\t\t0.- Volver al men� principal.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
		cin >> opc;
	} while (opc < 0 || opc > 5);
	cout << endl << endl;

	TestOrdenacion testOrdenacion;
	switch (opc) {
	case 1:
		testOrdenacion.comprobarMetodosOrdenacion();
		break;
	case 2:
		menuOrdenacionCasoMedio();
		break;
	case 3:
		menuOrdenacionComparar();
		break;
	case 4:
		testOrdenacion.comparar();
		break;
	case 5:
		menuOrdenacionTeoricos();
		break;
	}
}

void opcionesBusqueda() {
	cout << "\t\t1.- SECUENCIAL ITERATIVA.\n";
	cout << "\t\t2.- BINARIA ITERATIVA.\n";
	cout << "\t\t3.- INTERPOLACI�N ITERATIVA.\n";
	cout << "\t\t0.- Salir.\n";
	cout << "\t\t----------\n";
}

void menuBusquedaCasoMedio() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t*** M�TODO A ESTUDIAR PARA EL CASO MEDIO ***\n\n";
		opcionesBusqueda();
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
		cin >> opc;
	} while (opc < 0 || opc > 3);
	cout << endl << endl;

	if (opc != 0) {
		TestBusqueda testBusqueda;
		testBusqueda.casoMedio(opc - 1);
	}
}

void menuBusquedaComparar() {
	int met1 = 0;
	do {
		system("cls");
		cout << "\t*** COMPARACI�N DE DOS M�TODOS DE B�SQUEDA ***\n\n";
		cout << "Selecciona los m�todos a comparar\n";
		opcionesBusqueda();
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
			TestBusqueda testBusqueda;
			testBusqueda.comparar(met1-1, met2-1);
		}
	}
}

void menuBusquedaTeoricos() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t*** M�TODO A ESTUDIAR PARA TIEMPOS TEORICOS ***\n\n";
		cout << "\t\t1.- SECUENCIAL ITERATIVO.\n";
		cout << "\t\t2.- BINARIO ITERATIVO.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
		cin >> opc;
	} while (opc < 0 || opc > 2);
	cout << endl << endl;

	if (opc != 0) {
		TestBusqueda testBusqueda;
		testBusqueda.casosTeoricos(opc - 1);
	}
}

void menuBusqueda() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t\t*** MEN� B�SQUEDA ***\n";
		cout << "\t\t1.- Probar los m�todos de b�squeda.\n";
		cout << "\t\t2.- Obtener el caso medio de un m�todo de b�squeda.\n";
		cout << "\t\t3.- Comparar dos m�todos.\n";
		cout << "\t\t4.- Comparar todos.\n";
		cout << "\t\t5.- Casos teoricos.\n";
		cout << "\t\t0.- Volver al men� principal.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
		cin >> opc;
	} while (opc < 0 || opc > 5);
	cout << endl << endl;
	
	TestBusqueda testBusqueda;
	switch (opc) {
	case 1:
		testBusqueda.comprobarMetodosBusqueda();
		break;
	case 2:
		menuBusquedaCasoMedio();
		break;
	case 3:
		menuBusquedaComparar();
		break;
	case 4:
		testBusqueda.comparar();
		break;
	case 5:
		menuBusquedaTeoricos();
		break;
	}
}

int menuPrincipal() {
	int opc = 0;
	do {
		system("cls");
		cout << "\t\t\t\t\t\t*** FAA. Prueba Modificaci�n Pr�ctica 3. Curso 20/21 ***\n\n";
		cout << "\t\t\t\tJos� Mar�a Gonz�lez Abad\n";
		cout << "\t\t*** MEN� PRINCIPAL ***\n";
		cout << "\t*** ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACI�N Y DE B�SQUEDA ***\n\n";
		cout << "\t\t1.- Men� algoritmos de ordenaci�n.\n";
		cout << "\t\t2.- Men� algoritmos de b�squeda.\n";
		cout << "\t\t0.- Salir.\n";
		cout << "\t\t----------\n";
		if (opc == 0) cout << "\t\tElige opci�n: "; else cout << "\t\tOpci�n incorrecta. Elige opci�n: ";
		cin >> opc;
	} while (opc < 0 || opc > 2);
	cout << endl << endl;
	return opc;
}

int main() {
	setlocale(LC_CTYPE, "Spanish");
	int opc = 0;
	do {
		opc = menuPrincipal();
		switch (opc) {
		case 1:
			menuOrdenacion();
			break;
		case 2:
			menuBusqueda();
			break;
		}
	} while (opc != 0);
	return 0;
}

int main1() {
	int a[] = {1, 2, 3, 4, 5, 6, 7, 8};
	int b[] = {6,5,4,3,2,1};
	int b1[] = {1,2,3,4,5,6};
	int c1[] = { 1, 2, 3,5, 6, 4 };
	int c2[] = { 1, 2, 3, 4, 8, 7, 6, 5};
	int c3[] = { 1, 2, 3, 4, 5, 10, 9, 8, 7, 6};
	int c4[] = { 1, 2, 4, 3 };
	AlgoritmosOrdenacion ord;
	ord.ordenaJunio21(b, 6);
	return 0;
}