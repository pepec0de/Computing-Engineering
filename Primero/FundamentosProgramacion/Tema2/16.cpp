#include <iostream>

using namespace std;

int main() {
	int nSeg;
	cout << "Introduzca la cantidad de segundos que desee convertir: ";
	cin >> nSeg;
	cout << endl;

	cout << nSeg/3600 << " hora(s), "
	<< (nSeg % 3600) /60 << " minuto(s) y "
	<< (nSeg % 3600) %60 << " segundo(s)." << endl;
	return 0;
}
