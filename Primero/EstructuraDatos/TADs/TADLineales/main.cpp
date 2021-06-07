#include <iostream>
#include "TADLista.h"
#include "TADCola.h"
#include "TADPila.h"

using namespace std;

int main() {
    lista l;
    for (int i = 0; i < 11; i++) l.anadirDch(i);
    l.insertar(5, 69);
    for (int i = 1; i < 13; i++) cout << l.observar(i) << endl;
    return 0;
}
