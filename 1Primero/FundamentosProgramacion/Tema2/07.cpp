#include <iostream>
#include <cmath>

using namespace std;

int main() {
	float aX, aY, bX, bY;
	
	cout << "Coordenadas del punto A" << endl;
	cout << "X: ";
	cin >> aX;
	cout << "Y: ";
	cin >> aY;
	cout << endl;
	
	cout << "Coordenadas del punto B" << endl;
	cout << "X: ";
	cin >> bX;
	cout << "Y: ";
	cin >> bY;
	
	cout << endl;
	
	cout << "La distancia entre los puntos es: " << sqrt( pow(aX - bX, 2) + pow(aY - bY, 2) ) << endl;
	return 0;
}
