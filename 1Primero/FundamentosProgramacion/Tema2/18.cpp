#include <iostream>

using namespace std;

class empleado {
	int horasNormales, horasExtras;
	
	public:
		// Funcion para pedir por teclado las horas normales y las horas extras trabajadas
		void cargar();
		// Funcion para obtener el sueldo que cobrará después de la retención
		float nomina();
};

// Vamos a programar las funciones de la clase empleado
void empleado::cargar() {
	// Pedimos al usuario las horas
	cout << "Indique el numero de horas normales: ";
	cin >> empleado::horasNormales;
	cout << "Indique el numero de horas extra: ";
	cin >> empleado::horasExtras;
}

float empleado::nomina() {
	// Calculamos la nomina total del trabajador a partir de las horas trabajadas
	// Y por ultimo aplicamos retencion
	
	// Horas normales -> 5eur Horas extras -> 8eur
	float dineroTotal = empleado::horasNormales*5 + empleado::horasExtras*8;
	
	// La retencion es del 15% del total
	float retencion = dineroTotal*0.15;
	
	return dineroTotal-retencion;
}

int main() {
	empleado cEmpleado;
	cEmpleado.cargar();
	cout << "La nomina del empleado es de: " << cEmpleado.nomina() " EUR." << endl;
	return 0;
}
