#include <iostream>

using namespace std;

#define M 10

class matrices {
    int tabla[M];
    public:
        void cargar();
        // Pondrá en cada elemento de la tabla el valor de su índice.
        bool encontrar();
        // Pedirá un número entero por teclado y devolverá true si ese
        // número está en la tabla, en caso contrario devolverá false.
};

void matrices::cargar() {
    for (int i = 0; i < M; i++) {
        tabla[i] = i;
    }
}

bool matrices::encontrar() {
    cout << "Indique el numero a encontrar: ";
    int n;
    cin >> n;
    for (int i = 0; i < M; i++) {
        if (tabla[i] == n) return true;
    }
    return false;
}

int main() {
    matrices obj;
    obj.cargar();
    if (obj.encontrar()) 
        cout << "Numero encontrado!\n"; 
    else cout << "Numero no encontrado.\n";
}
