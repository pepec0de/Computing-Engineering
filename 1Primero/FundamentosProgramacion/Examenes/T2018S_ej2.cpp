#include <iostream>
#include <cstring>

struct VIVIENDA {
    float precio;
    char dni[10];
    char nombre[30];
};

class PLANTA {
    VIVIENDA viviendas[10];
    int nc;
public:
    int num_viviendas();
    VIVIENDA devolver_vivienda(int n);
    void agregar(VIVIENDA c);
};

class EDIFICIO {
    PLANTA plantas[20];
    int np;
public:
    int num_plantas();
    PLANTA devolver_planta(int n);
    void cargar();
};

int main() {
    EDIFICIO x;
    x.cargar();
    float max = x.devolver_planta(0).devolver_vivienda(0).precio;
    int nPlanta = 0, nVivienda = 0;
    for (int i = 0; i < x.num_plantas(); i++) {
        for (int j = 1; j < x.devolver_planta(i).num_viviendas(); j++) {
            if (max < x.devolver_planta(i).devolver_vivienda(j).precio) {
                max = x.devolver_planta(i).devolver_vivienda(j).precio;
                nPlanta = i;
                nVivienda = j;
            }
        }
    }

    cout << "DNI : " << x.devolver_planta(nPlanta).devolver_vivienda(nVivienda).dni << endl
        << "NOMBRE : " << x.devolver_planta(nPlanta).devolver_vivienda(nVivienda).nombre << endl
        << "PRECIO : " << x.devolver_planta(nPlanta).devolver_vivienda(nVivienda).precio << endl;
    return 0;
}
