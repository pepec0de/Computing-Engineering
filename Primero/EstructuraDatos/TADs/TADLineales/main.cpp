#include <iostream>
#include "TADLista.h"
#include <fstream>
#include "TADCola.h"
#include "TADPila.h"
using namespace std;

bool InvertirSecuencia(char nombre[100], int a, int b) {
    fstream f;
    f.open(nombre, ios::binary | ios::in | ios::out);
    f.seekg(0, ios::end);
    int tamano = f.tellg();
    TADPila secuencia; TADCola numeros;
    bool esSecuencia = false;
    f.seekg(0, ios::beg);
    int valor;
    int cont = 0;
    while (f.tellg() < tamano) {
        f.read((char*) &valor, sizeof(int));
        if (!esSecuencia) {
            if (valor == a && cont == 0) {
                esSecuencia = true;
                secuencia.apilar(valor);
            } else numeros.encolar(valor);
        } else {
            secuencia.apilar(valor);
            if (valor == b) {
                cont = 1;
                esSecuencia = false;
                for (int i = 0; i < secuencia.longitud(); i++) {
                    numeros.encolar(secuencia.cima());
                    secuencia.desapilar();
                }
            }
        }
    }
    // debug
    bool error = true;
    if (esSecuencia) {
        error = false;
    } else {
        f.close();
        f.open("final.dat", ios::out |ios::binary);
        cout << "LONGITUD = " << numeros.longitud() << endl;
        for (int i = 0; i < numeros.longitud(); i++) {
            valor = numeros.primero();
            f.write((char*) &valor, sizeof(int));
            numeros.desencolar();
        }
    }
    f.close();
    return error;
}

int main1() {
    ofstream fo;
    fo.open("numeros.dat", ios::binary);
    for (int i = 0; i < 10; i++) {
        fo.write((char*) &i, sizeof(int));
    }
    fo.close();



        ifstream f;
        f.open("numeros.dat", ios::binary);
        int valor;
        f.read((char*) &valor, sizeof(int));
        while(!f.eof()) {
            cout << valor;
            f.read((char*) &valor, sizeof(int));
        }

        cout << endl << endl;
        if (InvertirSecuencia("numeros.dat", 2, 6)) {
            cout << "funcsiona\n";
        } else cout << "no funcsiona\n\n\n";
        f.close();
        f.open("final.dat", ios::binary);
        f.read((char*) &valor, sizeof(int));

        while(!f.eof()) {
            cout << valor;
            f.read((char*) &valor, sizeof(int));
        }
        f.close();
    return 0;
}

int main() {
    TADCola cola;
    cola.encolar(1);
    cola.encolar(2);
    cout << cola << endl;
    return 0;
}
