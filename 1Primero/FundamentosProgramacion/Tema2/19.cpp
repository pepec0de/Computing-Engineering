#include <iostream>

using namespace std;

class rectangulo {
	int largo;
	int ancho;
	
	public:
		// Funcion que pone 2 de ancho y 4 de largo
		void iniciar();
		// Funcion que pide el ancho y el largo
		void cargar();
		// Funcion que da el valor del area
		int area();
		// Funcion que intercambie el valor de las variables largo y ancho
		void cambiar();
};

// Programamos las funciones de la clase
void rectangulo::iniciar() {
	ancho = 2;
	largo = 4;
}

void rectangulo::cargar() {
	cout << "Introduzca el valor del ancho: ";
	cin >> ancho;
	cout << "Introduzca el valor del largo: ";
	cin >> largo;
}

int rectangulo::area() {
	return ancho*largo;
}

void rectangulo::cambiar() {
	// Declaramos una variable temporal para guardar el valor del ancho
	int temp = ancho;
	ancho = largo;
	largo = temp;
}

int main() {
	rectangulo rect;
	rect.iniciar();
	cout << "El area del rectangulo es: " << rect.area() << endl;
	rect.cambiar();
	cout << "El area del rectangulo es: " << rect.area() << endl;
	rect.cargar();
	cout << "El area del rectangulo es: " << rect.area() << endl;
	return 0;
}
