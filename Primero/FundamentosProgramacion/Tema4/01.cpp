#include <iostream>

using namespace std;

#define M 10

class uno {
    int tabla[M];
    public:
        // Rellena la tabla con valores enteros leídos desde teclado
        void cargar();

        // Devuelve el valor máximo almacenado en la tabla
        int maximo();

        // Devuelve el valor mínimo almacenado en la tabla
        int minimo();
};

void uno::cargar() {
    for (int i = 0; i < M; i++) {
        cout << "Introduzca el numero de la posicion " << i << ": ";
        cin >> tabla[i];
    }
}

int uno::maximo() {
    int n = tabla[0];
    for (int i = 1; i < M; i++) {
        if (tabla[i] > n) n = tabla[i];
    }
    return n;
}

int uno::minimo() {
    int n = tabla[0];
    for (int i = 1; i < M; i++) {
        if (tabla[i] < n) n = tabla[i];
    }
    return n;
}

int main() {
    uno obj;
    obj.cargar();
    cout << "El numero maximo es: " << obj.maximo() << "\nEl numero minimo es: " << obj.minimo() << endl;
    return 0;
}
