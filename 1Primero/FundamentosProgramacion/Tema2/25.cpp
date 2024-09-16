#include <iostream>

using namespace std;

// Definimos la constante de gravedad
#define g 9.8

class Energia {
	private:
		// (masa) m en kg; (velocidad) v en km/h; (altura) h en km
		float m, v, h;
	
	public:
		void Leer();
		float ECinetica();
		float EPotencial();
};

void Energia::Leer() {
    cout << "Introduzca el valor de la masa (kg): ";
    cin >> m;
    cout << "Introduzca el valor de la velocidad (km/h): ";
    cin >> v;
    cout << "Introduzca el valor de la altura (km): ";
    cin >> h;

    // Convertimos las unidades al SI
    // Pasamos la velocidad (1 km/h son 5/18 m/s); Hacemos el cast para evitar
    // truncamiento de decimales.
    v *= (float)5/18;
    // Pasamos la altura (1 km son 1000m)
    h *= 1000;
}

float Energia::ECinetica() {
	// Aplicamos la formula Ec = 0.5*m*v^2
	return 0.5 * m * v*v;
}

float Energia::EPotencial() {
    // Aplicamos la formula Ep = m*g*h
	return m * g * h;
}

int main() {
    Energia e;
    cout << "Datos de la masa puntual\n";
    e.Leer();
    float cinetica = e.ECinetica();
    float potencial = e.EPotencial();
    cout << "Energia cinetica = " << cinetica << " Julio(s).\n";
    cout << "Energia potencial = " << potencial << " Julio(s).\n";
    cout << "Energia mecanica = " << cinetica+potencial << " Julio(s).\n";
    return 0;
}
