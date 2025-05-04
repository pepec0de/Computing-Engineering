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
    Saldo = 0;
    NoCuenta = 0;
    Bloqueada = false;
}

Cuenta::Cuenta(int pNo, float pSal) {
    Bloqueada = false;
    NoCuenta = pNo;
    Saldo = pSal;
}

bool Cuenta::ActualizarSaldo(int pSal) {
    if (!Bloqueada) Saldo = pSal;
    return !Bloqueada;
}

void Cuenta::ActualizarBloqueo(bool pBloq) {
    Bloqueada = pBloq;
}

float Cuenta::DameSaldo() {
    return Saldo;
}

int Cuenta::DameNoCuenta() {
    return NoCuenta;
}

bool Cuenta::EstaBloqueada() {
    return Bloqueada;
}

// FUNCIONES GENERICAS
int BuscarCuenta(Cuenta Ctas[MAX_CUENTAS], int NCuentas, int NoCuenta) {
    int i = 0;
    while(i < NCuentas && Ctas[i].DameNoCuenta() != NoCuenta) {
        i++;
    }
    if (i == NCuentas) i = -1;
    return i;
}

int MenuCuentas() {
    int opc;
    cout << "\n\n";
    cout << "\t\tMenu Gestion de Cuentas\n";
    cout << "\t1 Añadir una cuenta a un cliente\n";
    cout << "\t2 Mostrar las cuentas del cliente\n";
    cout << "\t3 Borrar una cuenta del cliente\n";
    cout << "\t4 Modificar Saldo de una cuenta\n";
    cout << "\t5 Modificar Estado de una cuenta\n";
    cout << "\t6 Salir\n";
    cout << "Elige Opcion: ";
    cin >> opc;
    cout << "\n";
    return opc;
}

int main() {
    Cuenta DatosCuentas[MAX_CUENTAS];
    int nCuentas = 0, opc, noCuenta, buscarCuenta;
    float saldo;
    char bloqueada;
    do {
        opc = MenuCuentas();
        switch(opc) {
            case 1:
                if (nCuentas != MAX_CUENTAS) {
                    cout << "Indique el numero de cuenta deseado: ";
                    cin >> noCuenta;
                    // Comprobamos si la cuenta no existe
                    if (BuscarCuenta(DatosCuentas, nCuentas+1, noCuenta) == -1) {
                        cout << "Indique el saldo de su cuenta: ";
                        cin >> saldo;
                        while(saldo < 0) {
                            cout << "No puede ser negativo. Indiquelo de nuevo: ";
                            cin >> saldo;
                        }
                        DatosCuentas[nCuentas] = Cuenta(noCuenta, saldo);
                        nCuentas++;
                    } else {
                        cout << "La cuenta ya existe.\n";
                    }
                } else {
                    cout << "El banco esta lleno.\n";
                }
                break;

            case 2:
                if (nCuentas != 0) {
                    cout << "NO. CUENTA\tSALDO\tBLOQUEADA\n";
                    for (int i = 0; i < nCuentas; i++) {
                        cout << DatosCuentas[i].DameNoCuenta() << "\t"
                             << DatosCuentas[i].DameSaldo() << " euros\t";
                        if (DatosCuentas[i].EstaBloqueada()) cout << "SI\n";
                        else cout << "NO\n";
                    }
                } else {
                    cout << "No hay cuentas para mostrar.\n";
                }
                break;

            case 3:
                if (nCuentas != 0) {
                    cout << "Indique el numero de cuenta a eliminar: ";
                    cin >> noCuenta;
                    buscarCuenta = BuscarCuenta(DatosCuentas, nCuentas, noCuenta);
                    if (buscarCuenta != -1) {
                        for (int i = buscarCuenta; i < nCuentas; i++) {
                            DatosCuentas[i] = DatosCuentas[i+1];
                        }
                        nCuentas--;
                    } else {
                        cout << "La cuenta no existe.\n";
                    } 
                } else {
                    cout << "No hay cuentas para eliminar.\n";
                }
                break;

            case 4:
                if (nCuentas != 0) {
                    cout << "Indique el numero de cuenta a actualizar: ";
                    cin >> noCuenta;
                    buscarCuenta = BuscarCuenta(DatosCuentas, nCuentas, noCuenta);
                    if (buscarCuenta != -1) {
                        cout << "Indique la cantidad de saldo: ";
                        cin >> saldo;
                        while (saldo < 0) {
                            cout << "No puede ser negativo. Indiquelo de nuevo: ";
                            cin >> saldo;
                        }
                        if (DatosCuentas[buscarCuenta].ActualizarSaldo(saldo)) {
                            cout << "El saldo se actualizo correctamente.\n";
                        } else {
                            cout << "Error! La cuenta " << noCuenta << " esta bloqueada.\n";
                        }
                    } else {
                        cout << "La cuenta no existe.\n";
                    }
                } else {
                    cout << "No hay cuentas para actualizar.\n";
                }
                break;

            case 5:
                if (nCuentas != 0) {
                    cout << "Indique el numero de cuenta a actualizar: ";
                    cin >> noCuenta;
                    buscarCuenta = BuscarCuenta(DatosCuentas, nCuentas, noCuenta);
                    if (buscarCuenta != -1) {
                        cout << "Indique si la cuenta esta bloqueada (S/n): ";
                        cin >> bloqueada;
                        bloqueada = toupper(bloqueada);
                        cout << "bloqueada : " << bloqueada << endl;
                        while (bloqueada != 'S' && bloqueada != 'N') {
                            cout << "Opcion incorrecta. Debe ser (S/n): ";
                            cin >> bloqueada;
                            bloqueada = toupper(bloqueada);
                        }
                        DatosCuentas[buscarCuenta].ActualizarBloqueo(bloqueada == 'S');
                        cout << "La cuenta " << noCuenta << " se ha actualizado correctamente.\n";
                    } else {
                        cout << "La cuenta no existe.\n";
                    }
                } else {
                    cout << "No hay cuentas para actualizar.\n";
                }
                break;

            case 6:
                break;

            default:
                cout << "Opción incorrecta.\n";
        }
    } while(opc != 6);
    return 0;
}
