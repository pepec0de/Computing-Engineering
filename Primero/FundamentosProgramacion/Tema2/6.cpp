#include <iostream>

using namespace std;

int main() {
	float nTeoria, nPractica;
	cout << "Nota de teoria: ";
	cin >> nTeoria;
	
	// Comprobamos que este valorada entre 0 y 10
	if (nTeoria < 0 || nTeoria > 10) {
		cout << "Nota de teoria inválida!" << endl;
		main();
	}
	
	cout << "Nota de practica: ";
	cin >> nPractica;
	
	// Comprobamos que este valorada entre 0 y 10
	if (nPractica < 0 || nPractica > 10) {
		cout << "Nota de teoria inválida!" << endl;
		main();
	}
	
	cout << endl;
	
	cout << "Su nota final es: " << ( nTeoria*0.7 + nPractica*0.3 ) << endl;
	return 0;
}
