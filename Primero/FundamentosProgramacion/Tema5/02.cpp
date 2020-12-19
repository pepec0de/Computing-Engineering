#include <iostream>
#include <stdlib.h>

using namespace std;

class Tictactoe {
char Tablero[3][3]; // [FILAS][COLUMNAS]
public:
    Tictactoe();
    void LimpiarTablero();
    void Pintar();
    bool PonerFicha(char ficha, int fila, int columna);
    bool ComprobarFila(char ficha, int fila);
    bool ComprobarColumna(char ficha, int columna);
    bool ComprobarDiagonal(char ficha, int fila, int columna);
    bool TableroCompleto();
};

Tictactoe::Tictactoe() {
    LimpiarTablero();
}

void Tictactoe::LimpiarTablero() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            Tablero[i][j] = ' ';
        }
    }
}

void Tictactoe::Pintar() {
    // Bucle anidado para imprimir la matriz bidimensional
    for (int i = 0; i < 3; i++) {
        cout << "\t";
        for (int j = 0; j < 3; j++) {
            cout << Tablero[i][j];
            // Si no es el ultimo elemento de la fila ponemos | despues
            // y si es ponemos salto
            if (j < 2) cout << "|"; else cout << endl;
        }
        // Si no es el ultimo caracter
        if (i < 2) cout << "\t-+-+-\n";
    }
}

bool Tictactoe::PonerFicha(char ficha, int fila, int columna) {
    // Resultado de la funcion
    bool result;
    // Si las casilla esta libre ponemos la ficha
    if(Tablero[fila][columna] == ' ') {
        Tablero[fila][columna] = ficha;
        result = true;
    } else {
        result = false;
    }
    return result;
}

bool Tictactoe::ComprobarFila(char ficha, int fila) {
    // Resultado de la funcion
    bool result = true;
    // Hacemos un recorrido de busqueda para buscar el 
    // caracter que no coincida
    int j = 0;
    while (j < 3 && result) {
        if (Tablero[fila][j] != ficha) result = false;
        j++;
    }
    return result;
}

bool Tictactoe::ComprobarColumna(char ficha, int columna) {
    // Resultado de la funcion
    bool result = true;
    // Hacemos un recorrido de busqueda para buscar el 
    // caracter que no coincida
    int i = 0;
    while (i < 3 && result) {
        if (Tablero[i][columna] != ficha) result = false;
        i++;
    }
    return result;
}

bool Tictactoe::ComprobarDiagonal(char ficha, int fila, int columna) {
    // Resultado de la funcion
    bool result = false;
    if (Tablero[1][1] == ficha) {
        result = ( Tablero[0][0] == ficha && Tablero[2][2] == ficha ) || 
                ( Tablero[0][2] == ficha && Tablero[2][0] == ficha);
    }
    return result;
}

bool Tictactoe::TableroCompleto() {
    // Hacemos un recorrido de busqueda
    // Buscamos un espacio
    bool encontrado = false;
    int i = 0, j = 0;
    while(i < 3 && !encontrado) {
        while(j < 3 && !encontrado) {
            if (Tablero[i][j] == ' ') encontrado = true;
            j++;
        }
        i++;
    }
    return !encontrado;
}

void PedirPosicion(char ficha, int &fila, int &columna) {
    cout << "Fila: ";
    cin >> fila;
    while (fila < 1 || fila > 3) {
        cout << "Error! Introduzca el dato de nuevo: ";
        cin >> fila;
    }
    cout << "Columna: ";
    cin >> columna;
    while (columna < 1 || columna > 3) {
        cout << "Error! Introduzca el dato de nuevo: ";
        cin >> columna;
    }
    fila--; columna--;
}

int main() {
    Tictactoe juego;
    char ficha = 'X';
    int fila, columna;
    bool terminado = false;
    bool jugada = false;
    cout << "Bienvenido al juego de tres en raya!\n";
    do {
        cout << endl;
        juego.Pintar();
    while (!terminado) {
        cout << "Jugador " << ficha << ". Por favor, Introduzca las coordenadas.\n";
        while(!jugada) {
            PedirPosicion(ficha, fila, columna);
            jugada = juego.PonerFicha(ficha, fila, columna);
            if (!jugada) cout << "Casilla ocupada!\n";
        }
        jugada = false;

        juego.Pintar();

        // Comprobamos si se ha realizado tres en raya
        if (juego.ComprobarDiagonal(ficha, fila, columna) || juego.ComprobarFila(ficha, fila) || juego.ComprobarColumna(ficha, columna)) {
            cout << "Enhorabuena jugador " << ficha << " ha ganado!\n";
            terminado = true;
        } else if (juego.TableroCompleto()) {
            cout << "El juego acaba en empate!\n";
            terminado = true;
        } else {
            // Turnamos 
            if (ficha == 'X') ficha = 'O'; else ficha = 'X';
        }
    }
    juego.LimpiarTablero();
    cout << "Desea jugar otra vez? (S/n): ";
    char opc;
    cin >> opc;
    if (opc == 'S' || opc == 's') terminado = false;
    } while(!terminado);
    cout << "\nGracias por jugar!\n";
    return 0;
} 
