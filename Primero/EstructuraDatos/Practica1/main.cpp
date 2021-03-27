#include <iostream>
#include <TTipos.h>
#include <TAlmacen.h>
#include <TTienda.h>

using namespace std;

// FUNCIONES COMPROBADAS:
/*
    TAlmacen:
    + DatosAlmacen
    + CrearAlmacen(Cadena pNomFiche)
    + CrearAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen, Cadena pNomFiche)
    + AbrirAlmacen(Cadena pNomFiche)
    +
    + AnadirProducto(TProducto pProduc)
*/
int main1() {
    fstream fichero;
    fichero.open("miAlmacen.dat", ios::binary | ios::in);
    fichero.seekg(0, ios::end);
    cout << fichero.tellp() << endl;
    cout << fichero.tellg() << endl;
    if (fichero.eof()) {
        cout << "eof is true.\n";
    } else cout << "eof is false.\n";
    fichero.close();
    return 0;
}

int main()
{
    TAlmacen miAlmacen;
    miAlmacen.CrearAlmacen("Almacenes Gory", "C/ Paseo Maritimo", "miAlmacen.dat");
    if (miAlmacen.AbrirAlmacen("miAlmacen.dat")) {
        cout << "Se ha abierto\n";
    } else {
        cout << "Ya estaba abierto\n";
    }
    if (miAlmacen.EstaAbierto()) {
        cout << "El almacen esta abierto!\n";
    } else {
        cout << "El almacen esta cerrado!\n";
    }
    cout << "Productos: " << miAlmacen.NProductos() << endl;

    TProducto prod1;

    strcpy(prod1.CodProd, "941924");

    if (miAlmacen.AnadirProducto(prod1)) {
        cout << "el producto se ha añadido con exito\n";
    }
    Cadena prod;
    strcpy(prod, "941924");
    cout << "El producto se encuentra en la posicion: " << miAlmacen.BuscarProducto(prod) << endl;

    return 0;
}
