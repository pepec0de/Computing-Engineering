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
	do {
		cout << "Indique el numero de filas: ";
		cin >> nfilas;
	} while(nfilas < 0 || nfilas > 6);
	do {
		cout << "Indique el numero de columnas: ";
		cin >> ncolumnas;
	} while(ncolumnas < 0 || ncolumnas > 6);
	cout << "Introduzca los valores de la tabla";
	for (int i = 0; i < ncolumnas; i++) {
		cout << "Columna " << i << endl;		
		for (int j = 0; j < ncolumnas; j++) {
			cout << "Valor en fila " << j << ": ";
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
	for (int i = 0; i < nfilas; i++) {
		if (t[nCol][i] == valor) return true;
	}
	return false;
}

int main() {

}
