#include <iostream>
#include <string.h>

using namespace std;

typedef char Cadena[50]; // Tipo de datos Cadena
#define MAX_CUENTAS 10 // Numero de cuentas
#define MAX_CLIENTES 100 // Numero de clientes

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

class Cliente {
    Cadena Nombre, Direccion;
    Cuenta Cuentas[MAX_CUENTAS];
    int NoCuentas;
    
public:
    Cliente();
    void ActualizarCliente(Cadena pNomb, Cadena pDir);
    void DameNombre(Cadena pNom);
    void DameDireccion(Cadena pDir);
    int BuscarCuenta(int pNoCuenta);
    bool CrearCuenta(Cuenta pCu);
    bool ActualizarCuenta(Cuenta pCu);
    bool BorrarCuenta(int pNoCuenta);
    int DameNoCuentas();
    Cuenta DameCuenta(int pos);
    void Mostrar(char Campo);
};

Cliente::Cliente() {
    strcpy(Nombre, "");
    strcpy(Direccion, "");
    NoCuentas = 0;
}

void Cliente::ActualizarCliente(Cadena pNomb, Cadena pDir) {
    strcpy(Nombre, pNomb);
    strcpy(Direccion, pDir);
    NoCuentas = 0;
}

void Cliente::DameNombre(Cadena pNom) {
    strcpy(pNom, Nombre);
}

void Cliente::DameDireccion(Cadena pDir) {
    strcpy(pDir, Direccion);
}

int Cliente::BuscarCuenta(int pNoCuenta) {
    int i = 0;
    while(i < NoCuentas && Cuentas[i].DameNoCuenta() != pNoCuenta) {
        i++;
    }
    if (i == NoCuentas) i = -1;
    return i;
}

bool Cliente::CrearCuenta(Cuenta pCu) {
    bool resultado = false;
    // Queda espacio para crear cuentas y la cuenta no existe
    if (NoCuentas < MAX_CUENTAS && BuscarCuenta(pCu.DameNoCuenta()) == -1) {
        Cuentas[NoCuentas] = pCu;
        NoCuentas++;
        resultado = true;
    }
    return resultado;
}

bool Cliente::ActualizarCuenta(Cuenta pCu) {
    bool resultado = false;
    int pos = BuscarCuenta(pCu.DameNoCuenta());

    if (pos != -1) {
        Cuentas[pos] = pCu;
        resultado = true;
    }
    return resultado;
}

bool Cliente::BorrarCuenta(int pNoCuenta) {
    bool resultado = false;
    int pos = BuscarCuenta(pNoCuenta);
    if (pos != -1) {
        // Llevamos los elementos siguientes hacia atras sustituyendo la cuenta
        // a eliminar.
        for (int i = pos; i < NoCuentas; i++) {
            Cuentas[pos] = Cuentas[pos+1];
        }
        NoCuentas--;
        resultado = true;
    }
    return resultado;
}

int Cliente::DameNoCuentas() {
    return NoCuentas;
}

Cuenta Cliente::DameCuenta(int pos) {
    return Cuentas[pos];
}

void Cliente::Mostrar(char Campo) {
    Campo = toupper(Campo);
    if (Campo == 'S' || Campo == 'T') {
        // Nombre y direccion del cliente
        cout << "Nombre: " << Nombre << "\nDireccion: " << Direccion << endl; 
    }

    if (Campo == 'C' || Campo == 'T') {
        // Cuentas del cliente
        cout << "NO. CUENTA\tSALDO\tBLOQUEADA\n";
        if (NoCuentas != 0) {
            for (int i = 0; i < NoCuentas; i++) {
                cout << Cuentas[i].DameNoCuenta() << "\t" << Cuentas[i].DameSaldo() << " euros\t";
                if (Cuentas[i].EstaBloqueada()) cout << "SI\n"; else cout << "NO\n";
            }
        } else {
            cout << "No hay cuentas.\n";
        }
    }
}

// FUNCIONES GENERICAS
int BuscarCliente(Cliente Ctes[MAX_CLIENTES], int NCtes, Cadena Nombre) {
    int i = 0;
    bool encontrado = false;
    Cadena nom;
    while (i < NCtes && !encontrado) {
        Ctes[i].DameNombre(nom);
        if (strcmp(Nombre, nom) == 0) {
            encontrado = true;
        } else i++;
    }

    if (!encontrado) i = -1;
    return i;
}

int Menu() {
    int opc;
    cout << "\n\n";
    cout << "\t\tMenu Principal\n";
    cout << "\t1 Añadir un cliente\n";
    cout << "\t2 Actualizar Direccion del Cliente\n";
    cout << "\t3 Mostrar un cliente\n";
    cout << "\t4 Mostrar todos los clientes\n";
    cout << "\t5 Submenu Gestion de Cuentas\n";
    cout << "\t6 Salir\n";
    cout << "\nElige una opcion: ";
    cin >> opc;
    cout << endl;
    return opc;
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
    cout << "\nElige una opcion: ";
    cin >> opc;
    cout << endl;
    return opc;
}

int main() {
    int opc;

    // VARIABLES PARA CLIENTE
    Cliente Datos[MAX_CLIENTES];
    // Numero total de clientes y numero seleccionado del cliente
    int nClientes, selCliente;
    Cadena nombre, direccion;
    
    // VARIABLES PARA CUENTA
    Cuenta cuenta;
    int noCuenta, buscarCuenta;
    float saldo;
    char bloqueada;
    
    do {
        opc = Menu(); 
        switch(opc) {
            case 1: // Creacion del cliente
                cout << "Creacion de un nuevo cliente.\n";
                cout << "Indique el nombre del cliente: ";
                cin >> nombre;
                cout << "Indique la direccion: ";
                cin >> direccion;
                // Actualizamos el banco
                Datos[nClientes].ActualizarCliente(nombre, direccion);
                nClientes++;
                break;

            case 2: // Actualizar direccion del cliente
                if (nClientes == 0) {
                    cout << "El banco esta vacio.\n";
                } else {
                    cout << "Indique el nombre del cliente: ";
                    cin >> nombre;
                    selCliente = BuscarCliente(Datos, nClientes, nombre);
                    if (selCliente != -1) {
                        cout << "Indique la nueva direccion del cliente: ";
                        cin >> direccion;
                        // El numero de cuentas se inicializa a 0
                        Datos[selCliente].ActualizarCliente(nombre, direccion);
                    } else {
                        cout << "No se ha encontrado el cliente.\n";
                    }
                }
                break;

            case 3: // Mostrar un cliente
                if (nClientes == 0) {
                    cout << "El banco esta vacio.\n";
                } else {
                    cout << "Indique el nombre del cliente: ";
                    cin >> nombre;
                    selCliente = BuscarCliente(Datos, nClientes, nombre);
                    if (selCliente != -1) {
                        Datos[selCliente].Mostrar('S');
                    } else {
                        cout << "No se ha encontrado el cliente.\n";
                    }
                }
                break;

            case 4: // Mostrar todos los clientes
                
                break;

            case 5: // Gestion de cuentas de un cliente
                if (nClientes == 0) {
                    cout << "El banco esta vacío.\n";
                } else {
                    cout << "Indique el nombre del cliente: ";
                    cin >> nombre;
                    selCliente = BuscarCliente(Datos, nClientes, nombre);
                    if (selCliente != -1) {
                        // SUBMENU CUENTA
                        do {
                            opc = MenuCuentas();
                            switch(opc) {
                                case 1: // Añadir una cuenta
                                    cout << "Indique el numero de cuenta a crear: ";
                                    cin >> noCuenta;
                                    cout << "Indique la cantidad de saldo a cargar: ";
                                    cin >> saldo;
                                    while(saldo < 0) {
                                        cout << "ERROR! No puede ser un saldo negativo. Indiquelo de nuevo: ";
                                        cin >> saldo;
                                    }
                                    cuenta = Cuenta(noCuenta, saldo);
                                    if (Datos[selCliente].CrearCuenta(cuenta)) {
                                        cout << "La cuenta se ha creado con exito.\n";
                                    } else {
                                        cout << "ERROR! Ha habido un problema al intentar crear la cuenta.\n";
                                        if (Datos[selCliente].DameNoCuentas() == MAX_CUENTAS) {
                                            cout << "Numero maximo de cuentas ya esta alcanzado.\n";
                                        } else {
                                            cout << "La cuenta ya existe.\n";
                                        }
                                    }
                                    break;

                                case 2: // Mostrar todas las cuentas del cliente
                                    Datos[selCliente].Mostrar('C');
                                    break;

                                case 3: // Borrar una cuenta del cliente
                                    cout << "Indique el numero de cuenta a eliminar: ";
                                    cin >> noCuenta;
                                    if (Datos[selCliente].BorrarCuenta(noCuenta)) {
                                        cout << "La cuenta se ha borrado con exito.\n";
                                    } else {
                                        cout << "No se ha encontrado la cuenta indicada.\n";
                                    }
                                    break;

                                case 4: // Modificar saldo de una cuenta
                                    cout << "Indique el numero de cuenta a actualizar: ";
                                    cin >> noCuenta;
                                    // Comprobamos antes si existe la cuenta
                                    // para no hacer perder tiempo al usuario
                                    if (Datos[selCliente].BuscarCuenta(noCuenta) != -1) {
                                        cout << "Indique la cantidad de saldo: ";
                                        cin >> saldo;
                                        while (saldo < 0) {
                                            cout << "ERROR! No puede ser un saldo negativo. Indiquelo de nuevo: ";
                                            cin >> saldo;
                                        }
                                        cuenta = Cuenta(noCuenta, saldo);
                                        if (Datos[selCliente].ActualizarCuenta(cuenta)) {
                                            cout << "La cuenta se ha actualizado con exito.\n";
                                        }
                                    } else {
                                        cout << "No se ha encontrado la cuenta indicada.\n";
                                    }
                                    break;

                                case 5: // Modificar el estado de la cuenta
                                    cout << "Indique el numero de cuenta a actualizar: ";
                                    cin >> noCuenta;
                                    buscarCuenta = Datos[selCliente].BuscarCuenta(noCuenta);
                                    if (buscarCuenta != -1) {
                                            cout << "Indique si desea bloquear la cuenta (S/n): ";
                                            cin >> bloqueada;
                                            bloqueada = toupper(bloqueada);
                                            while (bloqueada != 'S' && bloqueada != 'N') {
                                                cout << "Opcion incorrecta. Debe ser (S/n): ";
                                                cin >> bloqueada;
                                                bloqueada = toupper(bloqueada);
                                            }
                                            Datos[selCliente].DameCuenta(buscarCuenta).ActualizarBloqueo(bloqueada == 'S');
                                            cout << "La cuenta se ha actualizado correctamente.\n";
                                    } else {
                                        cout << "No se ha encontrado la cuenta indicada.\n";
                                    }
                                    break;

                                case 6:
                                    break;

                                default:
                                    cout << "Opción incorrecta.\n";
                            }
                        } while(opc != 6);
                    } else {
                        cout << "No se ha encontrado el cliente.\n";
                    }
                }
                break;

            case 6:
                break;

            default:
                cout << "Opcion incorrecta.\n";
        }
    } while(opc != 6);
    return 0;
}
