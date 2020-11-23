#include <iostream>
// Incluimos la libreria 'cmath' para usar la funcion pow
#include <cmath>

using namespace std;

#define k 8987551787.368176

class Cargas {
	// q1 y q2 son microculombios y r metros
	double q1, q2, r;
	
	public:
		void Leer();
		double Fuerza();
};

void Cargas::Leer() {
	cout << "Introduzca el valor de Q1(microC): ";
	cin >> q1;
	cout << "Introduzca el valor de Q2(microC): ";
	cin >> q2;
	cout << "Introduzca el valor de R(metros): ";
	cin >> r;	
}

double Cargas::Fuerza() {
	// Aplicamos la formula F = k*q1*q2/r^2
	return k * q1*pow(10, -6) * q2*pow(10, -6) / pow(r, 2);
}

int main() {
	Cargas obj;
	obj.Leer();
	cout << "La fuerza electrica entre las cargas es de: " << obj.Fuerza() << " Newton(s)." << endl;
	return 0;
}
