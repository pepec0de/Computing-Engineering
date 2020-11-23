#include <iostream>
// Incluimos la biblioteca 'cstdlib' para ejecutar instrucciones de sistema
#include <cstdlib>

using namespace std;

int main() {
	// Como se deben recoger unos cuantos valores he decidido 
	// crear una matriz para no repetir codigo.
	float notas[5];
	// Tomamos el tamaño de la matriz:
	int tNotas = sizeof(notas)/sizeof(notas[0]);
	float sumatorio;
    
    	system("clear"); // o 'cls' para windows
	for (unsigned int i = 0; i < tNotas; i++) {
		cout << "Introduzca valor de nota " << i+1 << ": ";
		cin >> notas[i];
		sumatorio += notas[i];
        	system("clear"); // o 'cls' para windows
	}
	
	cout << "Su media es: " << sumatorio/tNotas << endl;
	return 0;
}
