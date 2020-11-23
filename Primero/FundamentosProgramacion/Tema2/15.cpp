#include <iostream>

using namespace std;

int main() {
	/*
	 * Nos piden solucionar:
	 *
	 * ax +by = c;
	 * dx +ey = f;
	 * 
	 * Aplicamos las formulas:
	 *  
	 * x = (c*e - b*f) / (a*e - b*d)
	 * y = (a*f - c*d) / (a*e - b*d)
	 */
	
	// Coeficientes
	float a, b, c, d, e, f;
	// Terminos
	float x, y;
	
	cout << "Introduzca valor de A: ";
	cin >> a;
	cout << "Introduzca valor de B: ";
	cin >> b;
	cout << "Introduzca valor de C: ";
	cin >> c;
	cout << "Introduzca valor de D: ";
	cin >> d;
	cout << "Introduzca valor de E: ";
	cin >> e;
	cout << "Introduzca valor de F: ";
	cin >> f;
	
	cout << endl;
	
	cout << "El sistema es: " << endl <<
	a << "x +(" << b << "y) = " << c << ";" << endl <<
	d << "x +(" << e << "y) = " << f << ";" << endl;
	
	cout << endl;
	
	cout << "x = " << (c*e - b*f) / (a*e - b*d) << endl;
	cout << "y = " << (a*f - c*d) / (a*e - b*d) << endl;
	return 0;
}
