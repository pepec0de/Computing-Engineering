#include <iostream>

using namespace std;

#define TAMA 25

int aleatorio(int a, int b) {
    return (rand() % b) + a;
}

class Analisis {
    private:
        int Datos[1000];
        int NDatos;
        // La cantidad de veces que ha aparecido un numero
        // El indice correspondiente es el numero y las veces su valor
        int Valores[TAMA];
    public:
        void PedirDatos();
        void AnalizarDatos();
        bool EstanTodos();
        int ValorRepetido();
        int ValorMasRepetido();
        void MostrarDatos();
        void MostrarAnalisis();

};

void Analisis::PedirDatos() {
    char modo;
    do {
        cout << "\nDesea introducir valores manual o automaticamente? (m/a): ";
        cin >> modo;
    } while(!((modo == 'm' || modo == 'a')));
    switch (modo) {
        case 'm':
            // Introducimos los valores en el vector dados por el usuario
            for (int i = 0; i < 1000; i++) {
                do {
                    cout << "\nIntroduzca el dato de la posicion " << i+1 << ": ";
                    cin >> Datos[i];
                    // Si es menor que -1 o mayor que 24 seguimos pidiendo la misma posicion
                } while(Datos[i] < -1 || Datos[i] > 24);
                if (Datos[i] == -1) {
                        NDatos = i+1;
                        break;
                    }
                }
            break;
        case 'a':
            // Generamos un valor aleatorio entre el 1 y 1000
            NDatos = aleatorio(1, 1000);
            for (int i = 0; i < NDatos; i++) {
                Datos[i] = aleatorio(0, 25);
            }
            cout << "Datos generados automaticamente (" << NDatos << ").\n";
            break;
    }
}

void Analisis::AnalizarDatos() {
    if (NDatos > 0) {
        // Contamos las veces que se repite cada numero en el intervalo 0, 24
        for (int i = 0; i < TAMA; i++) {
            int cont = 0;
            for (int j = 0; j < NDatos; j++) {
                if (i == Datos[j]) cont ++;
            }
            // Pasamos el valor de las veces que se han repetido en el indice (numero)
            Valores[i] = cont;
        }
    } else {
        cout << "No se ha cargado el vector Datos.\n";
    }
}

bool Analisis::EstanTodos() {
    for (int i = 0; i < TAMA; i++) {
        if (Valores[i] == 0) return false;
    }
    return true;
}

int Analisis::ValorRepetido() {
    int n;
    do {
        cout << "\nIntroduzca el numero para ver las repeticiones: ";
        cin >> n;
    } while(n < 0 || n > 24);
    AnalizarDatos();
    return Valores[n];
}

int Analisis::ValorMasRepetido() {
    AnalizarDatos();
    int masrepe;
    for (int i = 1; i < TAMA; i++) {
        if (Valores[i] > Valores[masrepe]) masrepe = i;
    }
    return masrepe;
}

void Analisis::MostrarDatos() {
    if (NDatos > 0) {
        for (int i = 0; i < NDatos; i++) {
            cout << "Posicion(" << i+1 << ") = " << Datos[i] << endl;
        }
    } else {
        cout << "No se ha cargado el vector Datos.\n";
    }
}

void Analisis::MostrarAnalisis() {
    if (NDatos > 0) {
        AnalizarDatos();
        for (int i = 0; i < TAMA; i++) {
            cout << "El valor " << i << " se ha repetido " << Valores[i] << " veces.\n";
        }
    } else {
        cout << "No se ha cargado el vector Datos.\n";
    }
}

int main() {
    Analisis a;
    int opcion;
    do {
        do {
            cout << "\n\n\t\t1) Cargar los vectores (PedirDatos).\n";
            cout << "\t\t2) Analizar los datos.\n";
            cout << "\t\t3) Comprobar si estan todos los numeros.\n";
            cout << "\t\t4) Dar las veces que un valor dado esta repetido.\n";
            cout << "\t\t5) Dar el valor mas repetido.\n";
            cout << "\t\t6) Mostrar los datos dados.\n";
            cout << "\t\t7) Mostrar el analisis de los datos.\n";
            cout << "\t\t8) Salir.\n";

            cout << "\n\n\t Seleccione un metodo: ";
            cin >> opcion;
        } while(opcion < 1 || opcion > 8);

        switch(opcion) {
            case 1:
                a.PedirDatos();
                break;
            case 2:
                a.AnalizarDatos();
                break;
            case 3:
                if(a.EstanTodos()) cout << "Estan todos los numeros\n"; else cout << "Faltan numeros\n";
                break;
            case 4:
                cout << a.ValorRepetido() << " veces.\n";
                break;
            case 5:
                cout << "El valor mas repetido es: " << a.ValorMasRepetido() << endl;
                break;
            case 6:
                a.MostrarDatos();
                break;
            case 7:
                a.MostrarAnalisis();
                break;
        }
    } while(opcion != 8);
    return 0;
}
