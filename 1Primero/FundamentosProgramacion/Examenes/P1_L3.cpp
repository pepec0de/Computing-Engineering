#include <iostream>

using namespace std;

#define N 6

class Primitiva {
    int Ganadora[N];
    int Jugada[N];
    int Complementario;

public:
    void CargarGanadora();
    void CargarJugada();
    void CargarComplementario();
    int CalcularAciertos();
    void MostrarGanadora();
    void MostrarJugada();
    bool EstaComplementario();
};

void Primitiva::CargarGanadora() {
    cout << "Introduzca la combinacion ganadora:\n";
    // Un bucle para almacenar cada valor en la combinacion
    for (int i = 0; i < N; i++) {
        cout << "Introduzca cifra " << i+1 << ": ";
        cin >> Ganadora[i];
        // Comprobamos que no salga del intervalo
        while(Ganadora[i] < 1 || Ganadora[i] > 49) {
            cout << "Numero incorrecto. Introduzcalo de nuevo: ";
            cin >> Ganadora[i];
        }
    }
}

void Primitiva::CargarJugada() {
    cout << "Introduzca la combinacion jugada:\n";
    // Un bucle para almacenar cada valor en la combinacion
    for (int i = 0; i < N; i++) {
        cout << "Introduzca cifra " << i+1 << ": ";
        cin >> Jugada[i];
        // Comprobamos que no salga del intervalo
        while(Jugada[i] < 1 || Jugada[i] > 49) {
            cout << "Numero incorrecto. Introduzcalo de nuevo: ";
            cin >> Jugada[i];
        }
    }
}

void Primitiva::CargarComplementario() {
    cout << "Introduzca el valor complementario: ";  
    cin >> Complementario;
    while(Complementario < 1 || Complementario > 49) {
        cout << "Numero incorrecto. Introduzcalo de nuevo: ";
        cin >> Complementario;
    }
}

int Primitiva::CalcularAciertos() {
    // Los aciertos los calculamos comparando las veces que coinciden los
    // valores de la jugada y ganadora
    int aciertos = 0;
    for (int i = 0; i < N; i++) {
        if (Ganadora[i] == Jugada[i]) aciertos++;
    }
    return aciertos;
}

void Primitiva::MostrarGanadora() {
    cout << "La combinacion ganadora es: ";
    for (int i = 0; i < N; i++) {
        cout << Ganadora[i];
    }
    cout << endl;
}

void Primitiva::MostrarJugada() {
    cout << "La combinacion jugada es: ";
    for (int i = 0; i < N; i++) {
        cout << Jugada[i];
    }
    cout << endl;
    
}

bool Primitiva::EstaComplementario() {
    // Comparamos el valor del complementario para saber si fue indicado
    if (Complementario == 0) return false;
    return true;
}

int main() {
    // Llamamos a la clase
    Primitiva obj;
    // Creamos un booleano para ver si el usuario ha cargado los datos
    bool gCargada = false;
    bool jCargada = false;
    // Una variable para guardar la accion del usuario
    int accion;
    do {
        do {
            cout << "COMPROBAR COMBINACION\n";
            cout << "1. Cargar combinacion ganadora y complementario.\n";
            cout << "2. Cargar combinacion jugada.\n";
            cout << "3. Comprobar aciertos.\n";
            cout << "4. Salir.\n";
            cout << "\nSeleccione una accion: ";
            cin >> accion;
            if (accion < 1 || accion > 4) cout << "Opción Incorrecta. Vuelva a intentarlo.\n";
        } while(accion < 1 || accion > 4);

        switch(accion) {
            case 1:
                // Se cargan la ganadora
                gCargada = true;
                obj.CargarGanadora();
                obj.CargarComplementario();
                break;

            case 2:
                jCargada = true;
                obj.CargarJugada();
                break;

            case 3:
                if (gCargada && jCargada) {
                    obj.MostrarGanadora();
                    obj.MostrarJugada();
                    cout << "Su numero de aciertos obtenidos es: " << obj.CalcularAciertos() << endl;
                    if(obj.EstaComplementario()) {
                        cout << "Esta el complementario.\n";
                    } else {
                        cout << "No esta el complementario.\n";
                    }
                } else {
                    cout << "Debe cargar los datos.\n";
                }
                break;
        }
        cout << endl;
    } while(accion != 4);
    return 0;
}
