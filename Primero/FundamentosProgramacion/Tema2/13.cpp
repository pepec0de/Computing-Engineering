#include <iostream>
// Incluimos la biblioteca 'cmath' para usar las funciones sqrt y pow
#include <cmath>

using namespace std;

int main() {
	int a, b, c;
	// Resolvemos ax^2 +bx +c
	cout << "Introduzca valor de A: ";
	cin >> a;
	cout << "Introduzca valor de B: ";
	cin >> b;
	cout << "Introduzca valor de C: ";
	cin >> c;
	
	cout << endl;
	
	// Comprobamos que no nos salga una raiz imaginaria
	if ( pow(b, 2) -4*a*c < 0 ) {
		cout << "ERROR (" << b << "^2 -4*" << a << "*" << c << " = " << pow(b, 2) -4*a*c << 
			"): No se puede resolver la raiz cuadrada de un entero negativo!" << endl;
		return 0;
	}
	
	// Aplicamos las formulas
	float resultado1 = (-b + sqrt(pow(b, 2) -4*a*c)) / (2*a);
	float resultado2 = (-b - sqrt(pow(b, 2) -4*a*c)) / (2*a);
	
	cout << "X1 = " << resultado1 << endl;
	cout << "X2 = " << resultado2 << endl;
	return 0;
}
