#include <iostream>
 
using namespace std;

class Movimiento {
	float velO, spcO, a, t;
	
	public:
		void pedirDatos();
		float velocidadFinal();
		float espacioFinal();
};
 
void Movimiento::pedirDatos() {
	cout << "Introduzca la velocidad inicial (m/s): ";
	cin >> velO;
	cout << "Introduzca el espacio inicial (m): ";
	cin >> spcO;
	cout << "Introduzca la aceleracion (m/s^2): ";
	cin >> a;
	cout << "Introduzca el tiempo (seg): ";
	cin >> t;
}

float Movimiento::velocidadFinal() {
	return velO + a*t;
}
 
float Movimiento::espacioFinal() {
	return spcO + velO*t + 0.5*a*t*t;
}
 
int main() {
	Movimiento m;
	m.pedirDatos();
	cout << endl;
	cout << "Aplicamos las formulas:\nVf = Vo + a * t\nS = So + Vo * t * 0.5 * a * t^2\n";
	cout << endl << "Resultados: " << endl;
	cout << "La velocidad final es de: " << m.velocidadFinal() << endl;
	cout << "El espacio final es de : " << m.espacioFinal() << endl;
	return 0;
 }
