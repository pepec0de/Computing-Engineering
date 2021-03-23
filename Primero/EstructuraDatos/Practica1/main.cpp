#include <iostream>
#include <TTipos.h>
#include <TAlmacen.h>
#include <TTienda.h>

using namespace std;

// FUNCIONES COMPROBADAS:
/*
    TAlmacen:
    + CrearAlmacen(Cadena pNomFiche)
    + CrearAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen, Cadena pNomFiche)
*/

int main()
{
    TAlmacen miAlmacen;
    if (miAlmacen.AbrirAlmacen("miAlmacen.dat")) {
        cout << "Se ha abierto\n";
    }
    if (miAlmacen.EstaAbierto()) {
        cout << "El almacen esta abierto!\n";
    } else {
        cout << "El almacen esta cerrado!\n";
    }
    cout << "Productos: " << miAlmacen.NProductos() << endl;
    return 0;
}
