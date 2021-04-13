// FUNCIONES COMPROBADAS:
/*
    TAlmacen:
    + DatosAlmacen
    + CrearAlmacen(Cadena pNomFiche)
    + CrearAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen, Cadena pNomFiche)
    + AbrirAlmacen(Cadena pNomFiche)
    + AnadirProducto(TProducto pProduc)
    + BuscarProducto(Cadena pCodProd)
    + ObtenerProducto(int pPos)

    TTienda:
    + DatosTienda(Cadena pNombTienda, Cadena pDirTienda)
    + CrearTienda(Cadena pNombTienda, Cadena pDirTienda, Cadena pNomFiche)
    + AbrirTienda(Cadena pNomFiche)
    + GuardarTienda()
    + CerrarTienda()
    + BuscarEstante(int pCodEstante)
    + ObtenerEstante(int pPos)
    + AnadirEstante(TEstante pEstante)

*/

#include <iostream>
#include <cstdlib>
#include "include/TAlmacen.h"
#include "include/TTienda.h"

#include <locale.h>
#include <conio.h>

using namespace std;

TAlmacen almacen;
TTienda tienda;

/*
TODO (Pepe#1#04/08/21):
Solucionar BUG:
Despues de abrir y listar los productos de la TIENDA, al listar los productos del ALMACEN
salen todos los datos NULOS
*/

// Funcion para mostrar un producto
void MostrarProducto(TProducto prod) {
    cout << prod.CodProd << "\t"
         << prod.NombreProd << "\t\t\t\t\t"
         << prod.Precio << "\t"
         << prod.Cantidad << "\t\t"
         << prod.Caducicidad.Dia << "/"
         << prod.Caducicidad.Mes << "/"
         << prod.Caducicidad.Anyo << endl;
}

void MostrarEstante(TEstante testante) {
    TProducto prod = almacen.ObtenerProducto(almacen.BuscarProducto(testante.CodProd)+1);
    cout << testante.CodEstante << "\t"
         << testante.Posicion << "\t"
         << testante.Capacidad << "\t\t"
         << testante.CodProd << "\t\t"
         << prod.NombreProd << "\t\t\t\t"
         << prod.Precio << "\t"
         << testante.NoProductos << "\t"
         << prod.Precio*testante.NoProductos << endl;
}

// Funcion para pedir una fecha
TFecha pedirFecha() {
    TFecha fecha;
    cout << endl;
    cout << "\tIndique el día: ";
    cin >> fecha.Dia;
    cout << "\tIndique el mes: ";
    cin >> fecha.Mes;
    cout << "\tIndique el año: ";
    cin >> fecha.Anyo;
    return fecha;
}

// Funcion para pedir una cadena, el metodo cin no obtiene los espacios de la misma
void pedirCadena(Cadena cad) {
    string str;
    cin.ignore();
    getline(cin, str);
    strcpy(cad, str.c_str());
}

// Funcion para pedir la confirmacion de una accion al usuario, lo usamos en la opcion de actualizar productos.
bool confirmar(Cadena mensaje) {
    char c;
    do {
        cout << mensaje;
        cin >> c;
        c = tolower(c);
    } while (c != 's' || c != 'n');
    return c == 's';
}

// Funcion para pausar y que no salte directamente a los menus
void pausa() {
    cout << "\nOK. (Presione <Enter> para volver al menú)";
    getch();
}

int MenuPrincipal(Cadena NombAlmacen, Cadena NombTienda) {
    int opc = 0;
    do {
        system("cls");
        if (strcmp(NombAlmacen, "") == 0) cout << "----- Menú Principal ----- " << NombTienda << endl;
        else if (strcmp(NombTienda, "") == 0) cout << "----- Menú Principal ----- " << NombAlmacen << endl;
        else if (strcmp(NombAlmacen, "") + strcmp(NombTienda, "") == 0) cout << "----- Menú Principal -----\n";
        else cout << "----- Menú Principal ----- " << NombAlmacen << ", " << NombTienda << endl;
        cout << "1.- Gestión de Almacenes.\n";
        cout << "2.- Gestión de la Tienda.\n";
        cout << "3.- Reposición de Productos en Tienda.\n";
        cout << "0.- Salir.\n";
        if (opc == 0) cout << "\n\tSeleccione una opción: ";
        else cout << "\n\tOpción incorrecta. Seleccione otra opción: ";
        cin >> opc;
    } while (opc < 0 || opc > 3);
    cout << endl;
    return opc;
}

int MenuAlmacen(Cadena NombAlmacen) {
    int opc = 0;
    do {
        system("cls");
        cout << "----- Menú Almacenes ----- " << NombAlmacen << endl;
        cout << "1.- Crear un almacén vacío.\n";
        cout << "2.- Abrir un fichero de almacén.\n";
        cout << "3.- Cerrar un almacén.\n";
        cout << "4.- Listar productos del almacén.\n";
        cout << "5.- Añadir un producto.\n";
        cout << "6.- Actualizar un producto.\n";
        cout << "7.- Consultar un producto.\n";
        cout << "8.- Eliminar un producto.\n";
        cout << "0.- Salir.\n";
        if (opc == 0) cout << "\n\tSeleccione una opción: ";
        else cout << "\n\tOpción incorrecta. Seleccione otra opción: ";
        cin >> opc;
    } while(opc < 0 || opc > 8);
    cout << endl;
    return opc;
}

int MenuTienda(Cadena NombTienda) {
    int opc = 0;
    do {
        system("cls");
        cout << "----- Menú Tienda ----- " << NombTienda << endl;
        cout << "1.- Crear una tienda vacía.\n";
        cout << "2.- Abrir un fichero tienda.\n";
        cout << "3.- Cerrar la tienda.\n";
        cout << "4.- Actualizar el fichero tienda.\n";
        cout << "5.- Listar productos de la tienda.\n";
        cout << "6.- Añadir un estante.\n";
        cout << "7.- Actualizar un estante.\n";
        cout << "8.- Consultar un estante.\n";
        cout << "9.- Eliminar un estante.\n";
        cout << "0.- Salir.\n";
        if (opc == 0) cout << "\n\tSeleccione una opción: ";
        else cout << "\n\tOpción incorrecta. Seleccione otra opción: ";
        cin >> opc;
    } while(opc < 0 || opc > 9);
    cout << endl;
    return opc;
}

void GestionAlmacen(Cadena nombre) {
    Cadena Direccion;
    Cadena nFichero;
    TProducto prod;
    int opc = 0;
    int pos = 0;
    do {
        opc = MenuAlmacen(nombre);
        switch (opc) {
        case 1: /// Crear un almacén vacío
            if (!almacen.EstaAbierto()) {
                cout << "Indique el nombre del almacén: ";
                pedirCadena(nombre);
                cout << "Indique la dirección del almacén: ";
                pedirCadena(Direccion);
                cout << "Indique el nombre del fichero: ";
                pedirCadena(nFichero);
                if (almacen.CrearAlmacen(nombre, Direccion, nFichero)) {
                    cout << "El almacén " << nombre << " se ha creado con éxito.\n";
                } else {
                    cout << "ERROR! No se ha podido crear el almacén.\n";
                }
            } else {
                cout << "Ya hay un almacén abierto.\n";
            }
            pausa();
            break;
        case 2: /// Abrir un fichero de almacén
            if (!almacen.EstaAbierto()) {
                cout << "Indique el nombre del fichero: ";
                pedirCadena(nFichero);
                if (almacen.AbrirAlmacen(nFichero)) {
                    almacen.DatosAlmacen(nombre, Direccion);
                    cout << "Se ha abierto el almacén \"" << nombre << "\" con éxito.\n";
                } else {
                    cout << "ERROR! No se ha podido abrir el fichero.\n";
                }
            } else {
                cout << "Ya hay un almacén abierto.\n";
            }
            pausa();
            break;
        case 3: /// Cerrar un almacén
            if (almacen.EstaAbierto()) {
                almacen.CerrarAlmacen(); // Nunca da false
                strcpy(nombre, "");
                cout << "Se ha cerrado el almacén.\n";
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 4: /// Listar productos del almacén
            if (almacen.EstaAbierto()) {
                // Cabecera
                cout << "Listado del almacén \"" << nombre << "\" localizado en " << Direccion << endl;
                for (int i = 0; i < 21+(int)strlen(nombre)+16+(int)strlen(Direccion); i++) cout << "*";
                cout << endl;
                cout << "NÚMERO DE PRODUCTOS: " << almacen.NProductos() << endl; // DEBUG
                cout << "CODIGO\tNOMBRE\t\t\t\t\tPRECIO\tCANTIDAD\tFECHA CADUCIDAD\n";
                for (int i = 1; i <= almacen.NProductos(); i++) {
                    MostrarProducto(almacen.ObtenerProducto(i));
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 5: /// Añadir un producto
            if (almacen.EstaAbierto()) {
                cout << "Indique el nombre del producto: ";
                pedirCadena(prod.NombreProd);
                while (strcmp(prod.NombreProd, "") == 0) {
                    cout << "Valor inválido. Indique el nombre del producto: ";
                    pedirCadena(prod.NombreProd);
                }
                // DUDA : comprobacion valor invalido?
                cout << "Indique la descripción del producto: ";
                pedirCadena(prod.Descripcion);
                cout << "Indique el código del producto: ";
                pedirCadena(prod.CodProd);
                while (strcmp(prod.CodProd, "") == 0) {
                    cout << "Valor inválido. Indique el código del producto: ";
                    pedirCadena(prod.CodProd);
                }
                cout << "Indique el precio del producto: ";
                cin >> prod.Precio;
                while (prod.Precio <= 0) {
                    cout << "Valor inválido. Indique el precio del producto: ";
                    cin >> prod.Precio;
                }
                cout << "Indique la cantidad del producto: ";
                cin >> prod.Cantidad;
                while (prod.Cantidad < 0) {
                    cout << "Valor inválido. Indique la cantidad del producto: ";
                    cin >> prod.Cantidad;
                }
                cout << "Indique la fecha de caducidad: ";
                prod.Caducicidad = pedirFecha();
                if (almacen.AnadirProducto(prod)) {
                    cout << "El producto se ha añadido con éxito.\n";
                } else {
                    cout << "ERROR! No se ha podido añadir el producto.\n";
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 6: /// Actualizar un producto
            if (almacen.EstaAbierto()) {
                cout << "Indique el código del producto a actualizar: ";
                pedirCadena(prod.CodProd);
                pos = almacen.BuscarProducto(prod.CodProd);
                if (pos == -1) {
                    cout << "ERROR! No se ha encontrado un producto con el código indicado.\n";
                } else {
                    cout << "Producto encontrado.\n";
                    // Nombre
                    if (confirmar("¿Desea modificar el nombre? (S/n): ")) {
                        cout << "Indique el nombre nuevo: ";
                        pedirCadena(prod.NombreProd);
                        while (strcmp(prod.NombreProd, "") == 0) {
                            cout << "Valor inválido. Indique el nombre nuevo: ";
                            pedirCadena(prod.NombreProd);
                        }
                    }
                    // Descripcion
                    if (confirmar("¿Desea modificar la descripción? (S/n): ")) {
                        cout << "Indique la descripción nueva: ";
                        pedirCadena(prod.Descripcion);
                        // DUDA comprobacion valor invalido?
                    }
                    // Código
                    if (confirmar("¿Desea modificar el código? (S/n): ")) {
                        cout << "Indique el código nuevo: ";
                        pedirCadena(prod.CodProd);
                        while (strcmp(prod.CodProd, "") == 0) {
                            cout << "Valor inválido. Indique el código nuevo: ";
                            pedirCadena(prod.CodProd);
                        }
                    }
                    // Precio
                    if (confirmar("¿Desea modificar el precio? (S/n): ")) {
                        cout << "Indique el precio nuevo: ";
                        cin >> prod.Precio;
                        while (prod.Precio <= 0) {
                            cout << "Valor inválido. Indique el precio nuevo: ";
                            cin >> prod.Precio;
                        }
                    }
                    // Cantidad
                    if (confirmar("¿Desea modificar la cantidad? (S/n): ")) {
                        cout << "Indique la cantidad nueva: ";
                        cin >> prod.Cantidad;
                        while (prod.Cantidad < 0) {
                            cout << "Valor inválido. Indique la cantidad nueva: ";
                            cin >> prod.Cantidad;
                        }
                    }
                    // Caducidad
                    if (confirmar("¿Desea modificar la fecha? (S/n): ")) {
                        cout << "Indique la fecha nueva: ";
                        prod.Caducicidad = pedirFecha();
                    }

                    if (almacen.ActualizarProducto(pos, prod)) {
                        cout << "Se ha actualizado el producto con éxito.\n";
                    } else {
                        cout << "ERROR! No se ha podido actualizar el producto.\n";
                    }
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 7: /// Consultar un producto
            if (almacen.EstaAbierto()) {
                cout << "Indique el código del producto a consultar: ";
                pedirCadena(prod.CodProd);
                pos = almacen.BuscarProducto(prod.CodProd)+1;
                if (pos == 0) { // Valor irreal
                    cout << "ERROR! No se ha encontrado un producto con el código indicado.\n";
                } else {
                    cout << "Producto encontrado.\n";
                    cout << "CODIGO\tNOMBRE\t\t\t\t\tPRECIO\tCANTIDAD\tFECHA CADUCIDAD\n";
                    MostrarProducto(almacen.ObtenerProducto(pos));
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 8: /// Eliminar un producto
            if (almacen.EstaAbierto()) {
                cout << "Indique el código del producto a eliminar: ";
                pedirCadena(prod.CodProd);
                pos = almacen.BuscarProducto(prod.CodProd);
                if (pos == -1) {
                    cout << "ERROR! No se ha encontrado un producto con el código indicado.\n";
                } else {
                    cout << "Producto encontrado.\n";
                    if (almacen.EliminarProducto(pos)) {
                        cout << "Se ha eliminado el producto con éxito.\n";
                    } else {
                        cout << "ERROR! No se ha podido eliminar el producto.\n";
                    }
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        }
    } while (opc != 0);
}

void GestionTienda(Cadena nombre) {
    Cadena Direccion;
    Cadena nFichero;
    TEstante estante;
    TProducto prod;
    int opc = 0;
    int pos = 0;
    do {
        opc = MenuTienda(nombre);
        switch (opc) {
        case 1: /// Crear una tienda vacía
            if (!tienda.EstaAbierta()) {
                cout << "Indique el nombre de la tienda: ";
                pedirCadena(nombre);
                cout << "Indique la dirección de la tienda: ";
                pedirCadena(Direccion);
                cout << "Indique el nombre del fichero: ";
                pedirCadena(nFichero);
                if (tienda.CrearTienda(nombre, Direccion, nFichero)) {
                    cout << "la tienda \"" << nombre << "\" se ha creado con éxito.\n";
                } else {
                    cout << "ERROR! No se ha podido crear la tienda.\n";
                }
            } else {
                cout << "Ya hay una tienda abierta.\n";
            }
            pausa();
            break;
        case 2: /// Abrir un fichero tienda
            cout << "Indique el nombre del fichero: ";
            pedirCadena(nFichero);
            // Si ya habia una tienda abierta, esta se guardara
            if (tienda.AbrirTienda(nFichero)) {
                tienda.DatosTienda(nombre, Direccion);
                cout << "Se ha abierto la tienda \"" << nombre << "\" con éxito.\n";
            } else {
                cout << "ERROR! No se ha podido abrir el fichero.\n";
            }
            pausa();
            break;
        case 3: /// Cerrar la tienda
            if (tienda.EstaAbierta()) {
                if (tienda.CerrarTienda()) {
                    strcpy(nombre, "");
                    cout << "Se ha cerrado la tienda.\n";
                } else {
                    cout << "ERROR! No se ha podido cerrar la tienda. Debe haber sido un error al salvar el fichero.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 4: /// Actualizar el fichero tienda
            if (tienda.EstaAbierta()) {
                if (tienda.GuardarTienda()) {
                    cout << "Se ha actualizado la tienda.\n";
                } else {
                    cout << "ERROR! No se ha podido actualizar la tienda.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 5: /// Listar productos de la tienda
            if (tienda.EstaAbierta()) {
                if (almacen.EstaAbierto()) {
                    // Cabecera
                    cout << "Listado de la tienda \"" << nombre << "\" localizado en " << Direccion << endl;
                    for (int i = 0; i < 22+(int)strlen(nombre)+16+(int)strlen(Direccion); i++) cout << "*";
                    cout << endl;
                    cout << "NÚMERO DE ESTANTES: " << tienda.NoEstantes() << endl;
                    cout << "CODIGO POSICION CAPACIDAD CODIGO PRODUCTO NOMBRE\t\t\tPRECIO\tNoPRODUCTOS\tVALOR TOTAL\n";
                    // i = 1; Si no, no funciona
                    for (int i = 1; i <= tienda.NoEstantes(); i++) {
                        MostrarEstante(tienda.ObtenerEstante(i));
                    }
                } else {
                    cout << "No hay un almacen abierto.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 6: /// Añadir un estante
            if (tienda.EstaAbierta()) {
                if (almacen.EstaAbierto()) {
                    if (almacen.NProductos() > 0) {
                        cout << "Indique el código del estante: ";
                        cin >> estante.CodEstante;
                        cout << "Indique la posición del estante (1- centrado, 2- arriba, 3- abajo): ";
                        cin >> estante.Posicion;
                        while (estante.Posicion < 1 || estante.Posicion > 3) {
                            cout << "Posición inválida. Indique la posición de nuevo: ";
                            cin >> estante.Posicion;
                        }
                        cout << "Indique la capacidad del estante: ";
                        cin >> estante.Capacidad;
                        cout << "Indique el código del producto del estante: ";
                        pedirCadena(estante.CodProd);
                        pos = almacen.BuscarProducto(estante.CodProd)+1;
                        if (pos == 0) {
                            cout << "ERROR! El código del producto indicado no se encuentra en el almacén.\n";
                        } else {
                            prod = almacen.ObtenerProducto(pos);
                            if (prod.Cantidad == 0) {
                                cout << "No quedan más productos de este tipo en el almacén.\n";
                            } else {
                                cout << "Indique el número de productos en el estante: ";
                                cin >> estante.NoProductos;

                                // Comprobamos que el no productos indicado sea menor o igual al que hay en el almacen
                                if (prod.Cantidad >= estante.NoProductos) {
                                    prod.Cantidad -= estante.NoProductos;
                                } else {
                                    // No hay productos suficientes
                                    estante.NoProductos = prod.Cantidad;
                                    prod.Cantidad = 0;
                                }
                                almacen.ActualizarProducto(pos, prod);
                                if (tienda.AnadirEstante(estante)) {
                                    cout << "Se ha añadido el estante con éxito.\n";
                                } else {
                                    cout << "ERROR! No se podido añadir el estante.\n";
                                }
                            }
                        }
                    } else {
                        cout << "El almacen está vacío.\n";
                    }
                } else {
                    cout << "No hay un almacén abierto.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 7: /// Actualizar un estante
            if (tienda.EstaAbierta()) {
                cout << "Indique el código del estante a actualizar: ";
                cin >> estante.CodEstante;
                pos = tienda.BuscarEstante(estante.CodEstante)+1;
                if (pos == 0) {
                    cout << "ERROR! No se ha encontrado un estante con el código indicado.\n";
                } else {
                    cout << "Estante encontrado.\n";
                    cout << "CODIGO POSICION CAPACIDAD CODIGO PRODUCTO NOMBRE\t\t\tPRECIO\tNoPRODUCTOS\tVALOR TOTAL\n";
                    estante = tienda.ObtenerEstante(pos);
                    MostrarEstante(estante);
                    if (confirmar("¿Desea actualizar el número de productos? (S/n): ")) {
                        // Recogemos el producto
                        prod = almacen.ObtenerProducto(almacen.BuscarProducto(estante.CodProd)+1);
                        // Recogemos en opc el dato de numero de productos original
                        opc = estante.NoProductos;
                        cout << "Indique el número de productos nuevo: ";
                        cin >> estante.NoProductos;
                        while (estante.NoProductos < 0) {
                            cout << "Valor inválido. Indique el número de productos nuevo: ";
                            cin >> estante.NoProductos;
                        }
                        /* Si la cantidad es menor a la que había, se ha de devolver la
                        *  diferencia al almacén y si es mayor habrá que reponer esa
                        *  cantidad desde el almacén. En el caso que la cantidad a reponer
                        *  sea superior a la que hay en el almacén se repondrá solo la cantidad
                        *  existente en el almacén. En cada caso se indicará por pantalla que
                        *  cantidad se ha repuesto en el estante o devuelto al almacén.
                        */
                        if (estante.NoProductos <= opc) {
                            prod.Cantidad += (opc - estante.NoProductos);
                        } else {
                            estante.NoProductos = prod.Cantidad;
                            prod.Cantidad = 0;
                        }
                        if (almacen.ActualizarProducto(almacen.BuscarProducto(estante.CodProd), prod)) {
                            if (prod.Cantidad == 0) {
                                cout << "Se ha repuesto solo la cantidad de productos que habia en el almacén.\n";
                            } else {
                                cout << "Se ha devuelto al almacén: " << (opc - estante.NoProductos) << endl;
                            }
                            cout << "Número de productos en el estante: " << estante.NoProductos << endl;
                        }
                        if (tienda.ActualizarEstante(pos, estante)) {
                            cout << "Se ha actualizado el estante con éxito.\n";
                        }
                        opc = -1;
                    }
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 8: /// Consultar un estante
            if (tienda.EstaAbierta()) {
                // DUDA: Mostramos los datos del producto?
                if (almacen.EstaAbierto()) {
                    cout << "Indique el código del estante a consultar: ";
                    cin >> estante.CodEstante;
                    pos = tienda.BuscarEstante(estante.CodEstante)+1;
                    if (pos == 0) {
                        cout << "ERROR! No se ha encontrado un estante con el código indicado.\n";
                    } else {
                        cout << "CODIGO POSICION CAPACIDAD CODIGO PRODUCTO NOMBRE\t\t\tPRECIO\tNoPRODUCTOS\tVALOR TOTAL\n";
                        MostrarEstante(tienda.ObtenerEstante(pos));
                    }
                } else {
                    cout << "No hay un almacén abierto.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 9: /// Eliminar un estante
            if (tienda.EstaAbierta()) {
                if (almacen.EstaAbierto()) {
                    cout << "Indique el código del estante a eliminar: ";
                    cin >> estante.CodEstante;
                    pos = tienda.BuscarEstante(estante.CodEstante)+1;
                    if (pos == 0) {
                        cout << "ERROR! No se ha encontrado un estante con el código indicado.\n";
                    } else {
                        estante = tienda.ObtenerEstante(pos);
                        if (tienda.EliminarEstante(pos)) {
                            // Devolver los productos al almacen
                            pos = almacen.BuscarProducto(estante.CodProd);
                            prod = almacen.ObtenerProducto(pos);
                            prod.Cantidad -= estante.NoProductos;
                            if (almacen.ActualizarProducto(pos, prod)) {
                                cout << "Se ha eliminado el estante con éxito.\n";
                            }
                        } else {
                            cout << "ERROR! No se ha podido eliminar el estante.\n";
                        }
                    }
                } else {
                    cout << "No hay un almacén abierto.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        }
    } while(opc != 0);
}

void ReposicionProductos() {
    if (almacen.EstaAbierto() && tienda.EstaAbierta()) {
        TEstante estante;
        TProducto prod;
        Cadena cEstado;
        int pProd = 0;
        int estado;
        cout << "CÓDIGO CAPACIDAD NOMBRE\t\t\t\tESTADO REPUESTO OCUPACIÓN(%)\n";
        for (int i = 1; i <= tienda.NoEstantes(); i++) {
            estante = tienda.ObtenerEstante(i);
            pProd = almacen.BuscarProducto(estante.CodProd)+1;
            if (pProd != 0) {
                prod = almacen.ObtenerProducto(pProd);
                estado = tienda.ReponerEstante(i, prod);
                switch (estado) {
                case 1:
                    strcpy(cEstado, "COMPLETO");
                    break;
                case 2:
                    strcpy(cEstado, "PARCIAL");
                    break;
                default:
                    strcpy(cEstado, "NO");
                }
                cout << estante.CodEstante << "\t" << estante.Capacidad << "\t" << prod.NombreProd << "\t\t\t\t"
                    << cEstado << "\t" << ((float)estante.NoProductos/estante.Capacidad)*100 << endl;
                // TODO : actualizar producto

            } else {
                cout << "ERROR! No se ha encontrado el producto del estante en el almacén.\n";
            }
        }
    } else {
        cout << "No está el almacén y la tienda abierta.\n";
    }
    pausa();
}

int main() {
    system("title Práctica 1");
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
        case 1: /// Gestión del Almacenes
            GestionAlmacen(nAlmacen);
            break;
        case 2: /// Gestión de la Tienda
            GestionTienda(nTienda);
            break;
        case 3: /// Reposición de Productos en Tienda
            ReposicionProductos();
            break;
        }
    }
    return 0;
}
