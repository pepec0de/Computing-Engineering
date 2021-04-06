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

#include <iostream>
#include <cstdlib>
#include "include/TAlmacen.h"
#include "include/TTienda.h"

#include <locale.h>

using namespace std;

int MenuPrincipal(Cadena NombAlmacen, Cadena NombTienda) {
    int opc = 0;
    do {
        system("cls");
        if (strcmp(NombAlmacen, "") == 0) cout << "----- Men� Principal ----- " << NombTienda << endl;
        else if (strcmp(NombTienda, "") == 0) cout << "----- Men� Principal ----- " << NombAlmacen << endl;
        else if (strcmp(NombAlmacen, "") + strcmp(NombTienda, "") == 0) cout << "----- Men� Principal -----\n";
        else cout << "----- Men� Principal ----- " << NombAlmacen << ", " << NombTienda << endl;
        cout << "1.- Gesti�n del Almacenes.\n";
        cout << "2.- Gesti�n de la Tienda.\n";
        cout << "3.- Reposici�n de Productos en Tienda.\n";
        cout << "0.- Salir.\n";
        if (opc == 0) cout << "\n\tSeleccione una opci�n: "; else cout << "\n\tOpci�n incorrecta. Seleccione otra opci�n: ";
        cin >> opc;
    } while (opc < 0 || opc > 3);
    return opc;
}

int MenuAlmacen(Cadena NombAlmacen) {
    int opc = 0;
    do {
        system("cls");
        cout << "----- Men� Almacenes ----- " << NombAlmacen << endl;
        cout << "1.- Crear un almac�n vac�o.\n";
        cout << "2.- Abrir un fichero de almac�n.\n";
        cout << "3.- Cerrar un almac�n.\n";
        cout << "4.- Listar productos del almac�n.\n";
        cout << "5.- A�adir un producto.\n";
        cout << "6.- Actualizar un producto.\n";
        cout << "7.- Consultar un producto.\n";
        cout << "8.- Eliminar un producto.\n";
        cout << "0.- Salir.\n";
        if (opc == 0) cout << "\n\tSeleccione una opci�n: "; else cout << "\n\tOpci�n incorrecta. Seleccione otra opci�n: ";
        cin >> opc;
    } while(opc < 0 || opc > 8);
    return opc;
}

int MenuTienda(Cadena NombTienda) {
    int opc = 0;
    do {
        system("cls");
        cout << "----- Men� Tienda ----- " << NombTienda << endl;
        cout << "1.- Crear una tienda vac�a.\n";
        cout << "2.- Abrir un fichero tienda.\n";
        cout << "3.- Cerrar la tienda.\n";
        cout << "4.- Actualizar el fichero tienda.\n";
        cout << "5.- Listar productos de la tienda.\n";
        cout << "6.- A�adir un estante.\n";
        cout << "7.- Actualizar un estante.\n";
        cout << "8.- Consultar un estante.\n";
        cout << "9.- Eliminar un estante.\n";
        cout << "0.- Salir.\n";
        if (opc == 0) cout << "\n\tSeleccione una opci�n: "; else cout << "\n\tOpci�n incorrecta. Seleccione otra opci�n: ";
        cin >> opc;
    } while(opc < 0 || opc > 9);
    return opc;
}

void GestionAlmacen(Cadena &NombAlmacen) {
    TAlmacen almacen;
    Cadena dir;
    Cadena nFichero;
    int opc = -1;
    while (opc != 0) {
        opc = MenuAlmacen(NombAlmacen);
        switch (opc) {
            case 1: /// Crear un almac�n vac�o
                cout << "Indique el nombre del almac�n: ";
                cin >> NombAlmacen;
                cout << "Indique la direcci�n del almac�n: ";
                cin >> dir;
                cout << "Indique el nombre del fichero: ";
                cin >> nFichero;
                if (almacen.CrearAlmacen(NombAlmacen, dir, nFichero)) {
                    cout << "El almac�n " << NombAlmacen << " se ha creado con �xito.\n";
                } else {
                    cout << "ERROR! No se ha podido crear el almac�n.\n";
                }
                break;
            case 2: /// Abrir un fichero de almac�n
                break;
            case 3: /// Cerrar un almac�n
                break;
            case 4: /// Listar productos del almac�n
                break;
            case 5: /// A�adir un producto
                break;
            case 6: /// Actualizar un producto
                break;
            case 7: /// Consultar un producto
                break;
            case 8: /// Eliminar un producto
                break;
        }
    }
}

int main() {
    system("title Pr�ctica 1");
    setlocale(LC_CTYPE, "Spanish");

    // Variables auxiliares
    Cadena nAlmacen, nTienda, dir;
    strcpy(nAlmacen, "");
    strcpy(nTienda, "");
    strcpy(dir, "");
    int opc = -1;

    while (opc != 0) {
        opc = MenuPrincipal(nAlmacen, nTienda);
        switch (opc) {
            case 1: /// Gesti�n del Almacenes
                GestionAlmacen(nAlmacen);
                break;
            case 2: /// Gesti�n de la Tienda
                break;
            case 3: /// Reposici�n de Productos en Tienda
                break;
        }
    }
    return 0;
}
