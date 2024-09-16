#include <iostream>

using namespace std;

int main() {
	// 1 euro es:
	const float libra = 0.91;
	const float dolar = 1.19;
	
	// Pedimos el valor principal
	float euros;
	cout << "Escriba la cantidad de dinero (EUR): ";
	cin >> euros;
	cout << endl;
	
	// Hacemos la regla de 3
	cout << "Su dinero en libras: " << euros*libra << endl;
	cout << "Su dinero en dolarés: " << euros*dolar << endl;
	
	return 0;
}
