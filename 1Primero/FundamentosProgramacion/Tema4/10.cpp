#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

struct Casilla {
    // T tesoro, B bomba, A arena
    char tipo;
    // 1, 2, 3 de menos a mas profundo
    int profundidad;
};

class Tesoro {
    Casilla Tablero[5][5];
    int Puntos;

public:
    void Iniciar();
    bool Jugar();
    void MostrarTablero();
};

void Tesoro::Iniciar() {
    srand(time(0));

}

int main() {
    Tesoro juego;
    juego.Iniciar();
    return 0;
}
