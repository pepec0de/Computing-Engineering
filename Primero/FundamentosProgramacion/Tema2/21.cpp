#include <iostream>

using namespace std;

class Fracciones {
	// a/b y c/d
	float a, b, c, d;
	public:
		void pedir();
		void sumar();
		void restar();
		void multiplicar();
		void dividir();
};

void Fracciones::pedir() {
	cout << "Introduzca valor de A: ";
	cin >> a;
	cout << "Introduzca valor de B: ";
	cin >> b;
	cout << "Introduzca valor de C: ";
	cin >> c;
	cout << "Introduzca valor de D: ";
	cin >> d;
}

void Fracciones::sumar() {
	cout << a << "/" << b << " + " << c << "/" << d << " = "
	<< a*d + b*c << "/" << b*d << endl;
}

void Fracciones::restar() {
	cout << a << "/" << b << " - " << c << "/" << d << " = "
	<< a*d - b*c << "/" << b*d << endl;
}

void Fracciones::multiplicar() {
	cout << a << "/" << b << " * " << c << "/" << d << " = "
	<< a*c << "/" << b*d << endl;
}

void Fracciones::dividir() {
	cout << a << "/" << b << " / " << c << "/" << d << " = "
	<< a*d << "/" << b*c << endl;
}

int main() {
	Fracciones f;
	// Pedimos al usuario los datos
	f.pedir();
	
	f.sumar();
	f.restar();
	f.multiplicar();
	f.dividir();
	return 0;
}
