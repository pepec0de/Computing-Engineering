#include <iostream>
// Incluimos la biblioteca 'cmath' para usar la funcion pow
#include <cmath>

using namespace std;

class Complejo {
	// z1 = (a, b); z2 = (c, d);
	float a, b, c, d;
	
	public:
		void pedir();
		void sumar();
		void restar();
		void multiplicar();
		void dividir();
};

void Complejo::pedir() {
	cout << "Introduzca valor de A: ";
	cin >> a;
	cout << "Introduzca valor de B: ";
	cin >> b;
	cout << "Introduzca valor de C: ";
	cin >> c;
	cout << "Introduzca valor de D: ";
	cin >> d;
	
	cout << "\nz1 = (" << a << ", " << b << ");\nz2 = (" << c << ", " << d << ");\n\n";
}

void Complejo::sumar() {
	cout << "z1 + z2 = (" << a << ", " << b << ") + (" << c << ", " << d << ") = (" << a+b << ", " << c+d << ")\n";
}
void Complejo::restar() {
	cout << "z1 - z2 = (" << a << ", " << b << ") - (" << c << ", " << d << ") = (" << a-b << ", " << c-d << ")\n";
}
void Complejo::multiplicar() {
	cout << "z1 * z2 = (" << a << ", " << b << ") * (" << c << ", " << d << ") = (" << a*c - b*d << ", " << a*d + b*c << ")\n";
}
void Complejo::dividir() {
	cout << "z1 / z2 = (" << a << ", " << b << ") / (" << c << ", " << d << ") = (" << (a*c + b*d) / (pow(c, 2) + pow(d, 2)) << ", " << (b*c - a*d) / (pow(c, 2) + pow(d, 2)) << ")\n";
}

int main() {
	Complejo c;
	c.pedir();
	c.sumar();
	c.restar();
	c.multiplicar();
	c.dividir();
	return 0;
}
