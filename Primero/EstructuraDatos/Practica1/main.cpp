#include <iostream>
#include "include/TAlmacen.h"
#include "include/TTienda.h"

#include <locale.h>

using namespace std;

// FUNCIONES COMPROBADAS:
/*
    TAlmacen:
    + DatosAlmacen
    + CrearAlmacen(Cadena pNomFiche)
    + CrearAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen, Cadena pNomFiche)
    + AbrirAlmacen(Cadena pNomFiche)
    + AnadirProducto(TProducto pProduc)
    + BuscarProducto(Cadena pCodProd)

    TTienda:
    + DatosTienda(Cadena pNombTienda, Cadena pDirTienda)
    + CrearTienda(Cadena pNombTienda, Cadena pDirTienda, Cadena pNomFiche)
    + DUDA CON AbrirTienda(Cadena pNomFiche)
    + GuardarTienda()
    + CerrarTienda()
    + BuscarEstante(int pCodEstante)
    + ObtenerEstante(int pPos)
    + AnadirEstante(TEstante pEstante)
    +

*/
int main() {
    setlocale(LC_CTYPE, "Spanish");
    TTienda miTienda;
    if (miTienda.CrearTienda("Gory Tienda", "C/ Caceres", "miTienda.dat")) {
        cout << "Se ha creado con exito la tienda.\n";
    }
    TEstante estante;
    strcpy(estante.CodProd, "LECHE");
    estante.CodEstante = 123;
    if (miTienda.AnadirEstante(estante)) {
        cout << "Se ha añadido el estante.\n";
    }
    cout << miTienda.ObtenerEstante(0).CodProd << endl;
    return 0;
}

//int main()
//{
//    setlocale(LC_CTYPE, "Spanish");
//    TTienda miTienda;
//
//    if (miTienda.AbrirTienda("miTienda.dat")) {
//        cout << "Se ha abierto la tienda!\n";
//    }
//
//    return 0;
//}
