#include <iostream>
// Incluimos la libreria 'cmath' para usar la funcion pow
#include <cmath>

using namespace std;

// Permitividad eléctrica
#define Eo 0.00000000000885
// Numero pi:
#define PI 3.14159265358979

// Aplicamos la formula de la constante de Coulomb usando la permitividad electrica (Eo)
double k = 1 / (4*PI*Eo);

class CampoElec {
	private:
		// q en microCulombios y r en metros
		double q, r;
	
	public:
		void Leer();
		double Intensidad();
};

void CampoElec::Leer() {
	cout << "Introduzca valor de Q(microCulombios): ";
	cin >> q;
	cout << "Introduzca valor de R(metros): ";
	cin >> r;
}

double CampoElec::Intensidad() {
	// Aplicamos la formula de campo eléctrico: E = k*q/r^2 (N/C)
	return k * q*pow(10, -6) / pow(r, 2);
}

int main() {
	CampoElec obj;
	obj.Leer();
	cout << "La intensidad que hace el campo electrico en esa distancia es de: " << obj.Intensidad() << "Newton(s) por Columbio(s).\n";
	return 0;
}
