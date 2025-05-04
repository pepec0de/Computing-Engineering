#include <iostream>
#include <cstring>

using namespace std;

typedef char Cadena[50];
struct Vehiculo {
    Cadena Matricula;
    float hora;
    int Kilometros;
    bool Cadenas;
}

class PuertoMontana {
    Vehiculo Trafico[10000];
    int NVehiculos;
    Cadena Nombre;
    bool Abierto;
public:
    bool ActualizarPuerto(float pTemperaturas[3]);
    void Dame_Nombre(Cadena pNombre);
    int VehiculosEnPeligro(int KmInicial, int KmFinal);
};

bool PuertoMontana::ActualizarPuerto(float pTemperaturas[3]) {
    Abierto = false;
    float sum = 0;
    for(int i = 0; i < 3; i++) {
        sum += pTemperaturas[i];
    }
    if ((sum/3.0) > 0) Abierto = true;
    return Abierto;
}

void PuertoMontana::DameNombre(Cadena pNombre) {
    strcpy(pNombre, Nombre);
}

int PuertoMontana::VehiculosEnPeligro(int KmInicial, int KmFinal) {
    int nVehiculos;
    if (Abierto) {
        nVehiculos = -1;
    } else {
        for (int i = 0; i < NVehiculos; i++) {
            if (!Trafico[i].Cadenas && Trafico[i].Kilometros > KmInicial && Trafico[i].Kilometros < KmFinal) {
                nVehiculos++;
            }
        }
    }
    return nVehiculos;
}
int main() {
    
}
