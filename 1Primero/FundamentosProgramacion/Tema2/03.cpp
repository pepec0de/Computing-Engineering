#include<iostream>

using namespace std;

int main () {
	int primero, segundo, tercero;
	cout << primero  << ”\n” << segundo << ”\n” << tercero;
	return 0;
	
	/*
	 * Como las variables primero segundo y tercero no tienen
	 * un valor asignado, el compilador asigna automáticamente un valor nulo.
	 * En este caso será 0. Por tanto el cout imprimira:
	 0
	 0
	 0
	 */
}
