#include <iostream>

using namespace std;

int main() {
	float gCelsius;
	cout << "Escriba los grados Celsius: ";
	cin >> gCelsius;
	cout << endl;
	
	// Grados Fahrenheit = 9/5 * celsius +32
	cout << gCelsius << " grados Celsius equivalen a " << (9/5)*gCelsius +32 << " grados Fahrenheit" << endl;
	return 0;
}
