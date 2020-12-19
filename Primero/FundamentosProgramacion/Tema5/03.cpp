#include <iostream>

using namespace std;

typedef char Cadena[50]; // Tipo de datos Cadena

#define MAX_CUENTAS 100 // Numero de cuentas

class Cuenta { // Contiene los datos de una cuenta bancaria
    float Saldo; // Saldo de la cuenta
    int NoCuenta; // Numero de la cuenta
    bool Bloqueada; // true si esta bloqueada

public:
    Cuenta();
    Cuenta(int pNo, float pSal);
    bool ActualizarSaldo(int pSal);
    void ActualizarBloqueo(bool pBloq);
    float DameSaldo();
    int DameNoCuenta();
    bool EstaBloqueada();
};

Cuenta::Cuenta() {

}

Cuenta::Cuenta(int pNo, float pSal) {

}

bool Cuenta::ActualizarSaldo(int pSal) {

}

void Cuenta::ActualizarBloqueo(bool pBloq) {

}

float Cuenta::DameSaldo() {

}

int Cuenta::DameNoCuenta() {

}

bool Cuenta::EstaBloqueada() {

}

int main() {
    return 0;
}
