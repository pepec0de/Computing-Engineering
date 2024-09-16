#include <iostream>

using namespace std;

int main() {
	int longitud_en_metros = 15000;
	float Precio_total_en_euros;
	
	// Con este valor se indica que la division será entre un entero y punto flotante 
	// por lo que no se realizara truncamiento de los decimales y se imprimira el valor con decimales
	Precio_total_en_euros = 5000*(longitud_en_metros/5280.0);
	cout << Precio_total_en_euros << endl;
	
	// En este otro valor se declara que la division sea entre dos valores enteros por lo que
	// no se asignaran numeros decimales en el valor de la variable final
	Precio_total_en_euros = 5000*(longitud_en_metros/5280);
	cout << Precio_total_en_euros << endl;
	
	return 0;
}
