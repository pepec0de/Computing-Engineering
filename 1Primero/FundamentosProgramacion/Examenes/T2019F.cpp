#include <iostream>

using namespace std;

/// EJERCICIO 1
class Vector { float x, y; public:    Vector(); void NuevosXY(float cx, float cy); void ComponentesXY (float &cx, float &cy); void Sumar(float cx, float cy);    Vector Sumar (Vector v); int Cuadrante(); }; Vector::Vector() {    x=y=0;  } void Vector::NuevosXY(float cx, float cy) {    x=cx;    y=cy; } void Vector::ComponentesXY(float &cx, float &cy){      cx=x;    cy=y; }

void Vector::Sumar(float cx, float cy){    x+=cx;    y+=cy;  } Vector Vector::Sumar (Vector v){    v.Sumar(x,y);    return v;  } int Vector::Cuadrante() {    int c;    if (x>0 && y>0)        c=1;    else if (x<0 && y>0)        c=2;    else if (x<0 && y<0)        c=3;    else        c=4;    return c; }
int main1() {
    Vector Conjunto[3], VSuma;
    float x,y;
    int i;

    for (i = 0; i < 3; i++) {
        cout << "Indique: ";
        cin >> x >> y;
        Conjunto[i].NuevosXY(x, y);
    }
    cout << "Valor de i : " << i;
    i--;
    while( i >= 0) {
        VSuma = VSuma.Sumar(Conjunto[i]);
        i--;
    }
    VSuma.ComponentesXY(x, y);

    cout << "El vector resultado es (" << x << ", " << y << ")" << endl;
    cout << "Dicho vector está en el cuadrante: " << VSuma.Cuadrante() << endl;
    return 0;
}

/// EJERCICIO 2
int main2() {
    // PETICION 1
    equipo ultimo;
    for (int i = 0; i < Nligas; i++) {
        cout << "Mostrando datos de liga " << i+1 << endl;
        ultimo = ligas[i].equipos[numequipos-1];
        cout << "Equipo en el ultimo lugar: " << ultimo.nombre << endl;
        cout << "Porteros: ";
        // Bucle para iterar por cada jugador
        for (int j = 0; j < ultimo.numjugadores; j++) {
            if (ultimo.jugadores[j].demarcacion == 1) {
                cout << ultimo.jugadores[j].nombre << ", ";
            }
        }
        cout << endl << endl;
    }

    // PETICION 2
    equipo primero;
    float media = 0;
    for (int i = 0; i < Nligas; i++) {
        cout << "Mostrando datos de la liga " << i+1 << endl;
        primero = ligas[i].equipos[0];
        cout << "Equipo en primer lugar: " << primero.nombre << endl;
        // Bucle para iterar por cada jugador
        for (int j = 0; j < primero.numjugadores; j++) {
            media += primero.jugadores[j].nomina;
        }
        cout << "Media del equipo: " << media/primero.numjugadores << endl;
        cout << endl;
    }
    return 0;
}

/// EJERCICIO 3
int BuscarTorre(Torre Red[1000], int NTorres, Cadena Carretera, int Kilometro) {
    int i = 0;
    // Iteramos por cada torre
    Cadena currCarretera;
    // Inicializamos el valor de currCarretera
    Red[i].getCarretera(currCarretera);
    while(i < NTorres && strcmp(currCarretera, Carretera) != 0 && Red[i].getKilometro() != Kilometro) {
        i++;
    }
    if (i == NTorres) i = -1;
    return i;
}

int main3() {
    Torre RedEspana[1000];
    int NTorres;

    int numeroMovil;
    cout << "Introduzca el numero de movil: ";
    cin >> numeroMovil;

    int pos;
    int posNum;
    bool enc = false;
    for (int km = 0; km < 1109; km += 50) {
        pos = BuscarTorre(RedEspana, NTorres, "AP-7", km);
        if (pos != -1) {
            posNum = BuscarMovil(numeroMovil);
            if (posNum != -1) {cout << "Km: " << km << ", Nivel de bateria: " << RedEspana[pos].getMovil(posMovil).bateria << endl; enc = true;}
        }
    }
    if (!enc) cout << "No se ha encontrado el numero " << numeroMovil << endl;
    return 0;
}
