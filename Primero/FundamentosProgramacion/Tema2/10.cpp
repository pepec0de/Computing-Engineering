#include <iostream>
#include <cmath>

#define PI 3.1415926

using namespace std;

int main() {
	float radio;
	cout << "Inserte el valor del radio: ";
	cin >> radio;
	cout << endl;
	cout << "Longitud de la circunferencia: " << 2*PI*radio << " u" << endl;
	cout << "Superficie del circulo: " << PI*pow(radio, 2) << " u" << endl;
	cout << "Volumen de la esfera: " << 4*PI*pow(radio, 3)/3 << " u" << endl;
	return 0;
}
