#include <iostream>

using namespace std;

#define M 6
#define N 6

class examen {
	int t[M][N];
	int nfilas;
	int ncolumnas;
	
public:
	void cargar();
	bool esta();
};

void examen::cargar() {
	// Pedimos los valores de la tabla dentro del intervalo
	do {
		cout << "Indique el numero de filas: ";
		cin >> nfilas;
	} while(nfilas < 0 || nfilas > 6);
	do {
		cout << "Indique el numero de columnas: ";
		cin >> ncolumnas;
	} while(ncolumnas < 0 || ncolumnas > 6);
	
	cout << "Introduzca los valores de la tabla.\n";
	for (int i = 0; i < ncolumnas; i++) {
		// Indicamos al usuario en que columna se encuentra
		cout << "Columna " << i+1 << endl;		
		for (int j = 0; j < nfilas; j++) {
			// Indicamos al usuario la fila donde esta
			cout << "Indique el valor en la fila " << j+1 << ": ";
			cin >> t[i][j];
		}
	}
}

bool examen::esta() {
	int valor;
	cout << "Indique el valor que quiere buscar: ";
	cin >> valor;
	int nCol;
	cout << "Indique la columna donde buscar: ";
	cin >> nCol;
	nCol--;
	for (int i = 0; i < nfilas; i++) {
		// Como despues de que se cumpla la condicion no tenemos que hacer
		// nada mas podemos poner el return para para el for en vez de poner break
		if (t[nCol][i] == valor) return true;
	}
	return false;
}

int main() {
	examen e;
	e.cargar();
	if (e.esta()) {
		cout << "Su valor se encuentra en la tabla.\n";
	} else {
		cout << "Su valor no se encuentra en la columna indicada.\n";
	}
	return 0;
}
