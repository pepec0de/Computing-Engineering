#include <iostream>
#include <string.h>

using namespace std;

#define Npruebas 5 // La competicion cuenta con 5 pruebas
#define MAXPart 10 // Numero maximo de participantes

typedef char cadena[50];

struct Datos {
    cadena Nombre;
    int dorsal;
};

class Competicion {
    int NumPart;
    Datos Participantes[MAXPart];
    int Puntuaciones[MAXPart][Npruebas];

    public:
        void InscribirP();
        void AnotarPuntos();
        void DatosCompetidor();
        void Perdedor();
};

void Competicion::InscribirP() {
    // Pedimos al usuario la cantidad de participantes
    cout << "Cuanto participantes quiere añadir? ";
    cin >> NumPart;
    // Si no entra en el intervalo [2, MaxPart] lo volvemos a preguntar
    while(NumPart < 2 || NumPart > MAXPart) {
        cout << "Numero incorrecto. Introduzcalo de nuevo: ";
        cin >> NumPart;
    }

    // Ahora pedimos los nombres de cada participante
    for (int i = 0; i < NumPart; i++) {
        cout << "Indique el nombre del participante " << i+1 << ": ";
        cin >> Participantes[i].Nombre;
        // Ponemos un dorsal aleatorio entre el 1 y el 100
        Participantes[i].dorsal = (rand() % 101) + 1;
    }
}

void Competicion::AnotarPuntos() {
    // Vamos a crear un intervalo de puntuacion ya que no se especifica
    int maxP = 10, minP = 0;
    // Iteramos por cada participante solicitando en otro bucle las
    // puntuaciones de cada prueba.
    for (int i = 0; i < NumPart; i++) {
        cout << "Ingrese las puntuaciones del competidor : " << Participantes[i].Nombre << endl;
        for (int j = 0; j < Npruebas; j++) {
            cout << "Puntuacion en la prueba " << j+1 << ": ";
            cin >> Puntuaciones[i][j];
            // Comprobamos que no sale del intervalo
            while(Puntuaciones[i][j] < minP || Puntuaciones[i][j] > maxP) {
                cout << "Valor incorrecto. Introduzcalo de nuevo: ";
                cin >> Puntuaciones[i][j];
            }
        }
    }
}

void Competicion::DatosCompetidor() {
    // Pedimos al usuario el nombre del competidor
    cout << "Introduzca el nombre del competidor a consultar: ";
    cadena competidor;
    cin >> competidor;
    
    // Buscamos al competidor y mostramos sus datos
    bool encontrado = false;
    for (int i = 0; i < NumPart; i++) {
        if(strcmp(Participantes[i].Nombre, competidor) == 0) {
            encontrado = true;
            // Cuando lo hayamos encontrado imprimimos la puntuacion y dorsal
            cout << "Dorsal: " << Participantes[i].dorsal << endl;
            cout << "Puntuaciones obtenidas\n";
            for (int j = 0; j < Npruebas; j++) {
                cout << "Puntuacion de prueba " << j+1 << " : " << Puntuaciones[i][j] << endl;
            }
            break;
        }
    }
    
    if (!encontrado) cout << "El competidor " << competidor << " no ha sido registrado.\n";
}

void Competicion::Perdedor() {
    // Para ver quien ha obtenido menos puntuacion creamos un vector que recoja
    // las puntuaciones totales.
    int pTotal[MAXPart];
    for (int i = 0; i < NumPart; i++) {
        pTotal[i] = 0;
        for (int j = 0; j < Npruebas; j++) {
            pTotal[i] += Puntuaciones[i][j];
        }
    }
    // Una vez recogidas las puntuaciones totales comparamos cual es la minima
    int minima = pTotal[0];
    // Creamos una variable temporal para saber la posicion de la minima
    // puntuacion.
    int minIdx = 0;
    // Hacemos el recorrido completo
    for (int i = 1; i < NumPart; i++) {
        if (pTotal[i] < minima) {
            minima = pTotal[i];
            minIdx = i;
        }
    }
    cout << "El competidor con la puntuacion minima es " << Participantes[minIdx].Nombre 
        << " con dorsal " << Participantes[minIdx].dorsal << endl;
    // Imprimimos sus puntuaciones
    for (int j = 0; j < Npruebas; j++) {
        cout << "Puntuacion de prueba " << j+1 << " : " << Puntuaciones[minIdx][j] << endl;
    }
}

int main() {
    Competicion obj;
    obj.InscribirP();
    cout << "\n\n";
    obj.AnotarPuntos();
    cout << "\n\n";
    cadena respuesta;
    do {
        obj.DatosCompetidor();
        cout << "\nDesea seguir consultando datos? (si/no): ";
        cin >> respuesta;
    } while(strcmp(respuesta, "si") == 0);
    cout << "\n\n";
    obj.Perdedor();
    return 0;
}
