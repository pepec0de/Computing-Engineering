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
    cout << "sizeof(TProducto) = " << sizeof(TProducto) << endl;
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

    TFecha fecha;
    fecha.Anyo = 2002;
    fecha.Dia = 11;
    fecha.Mes = 2;

    prod1.Caducicidad = fecha;
    prod1.Cantidad = 10;
    strcpy(prod1.CodProd, "941924");
    strcpy(prod1.NombreProd, "Leche");
    prod1.Precio = 14.34;
    strcpy(prod1.Descripcion, "Leche asturiana");

    if (miAlmacen.AnadirProducto(prod1)) {
        cout << "el producto se ha añadido con exito\n";
    }
    Cadena prod;
    strcpy(prod, "941924");
    cout << "El producto se encuentra en la posicion: " << miAlmacen.BuscarProducto(prod) << endl;

    return 0;
}
