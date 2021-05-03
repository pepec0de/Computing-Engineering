#include <iostream>
#include <cstdlib>
#include "include/TAlmacen.h"
#include "include/TTienda.h"

// Librerias extras para mejorar la experiencia del usuario
#include <locale.h>
#include <conio.h>

using namespace std;

TAlmacen almacen;
TTienda tienda;
// declaramos variables globales para las direcciones para evitar problemas a la hora de salir de menus y
// volver a listar datos
Cadena DirAlmacen, DirTienda;

// Funcion para mostrar un producto
void MostrarProducto(TProducto prod) {
    cout << prod.CodProd << "\t"
         << prod.NombreProd << "\t\t\t\t"
         << prod.Precio << "\t"
         << prod.Cantidad << "\t\t"
         << prod.Caducicidad.Dia << "/"
         << prod.Caducicidad.Mes << "/"
         << prod.Caducicidad.Anyo << "\t\t"
         << prod.Descripcion << endl;
}

void MostrarEstante(TEstante testante) {
    int pos = almacen.BuscarProducto(testante.CodProd);
    if (pos != -1) {
        TProducto prod = almacen.ObtenerProducto(pos);
        cout << testante.CodEstante << "\t"
             << testante.Posicion << "\t"
             << testante.Capacidad << "\t\t"
             << testante.CodProd << "\t\t"
             << prod.NombreProd << "\t\t\t\t"
             << prod.Precio << "\t"
             << testante.NoProductos << "\t"
             << prod.Precio*testante.NoProductos << endl;
    } else {
        cout << "ERROR! No se ha encontrado el producto con el c�digo: " << testante.CodProd << endl;
    }
}

// Funcion para pedir una fecha
TFecha pedirFecha() {
    TFecha fecha;
    cout << endl;
    cout << "\tIndique el d�a: ";
    cin >> fecha.Dia;
    cout << "\tIndique el mes: ";
    cin >> fecha.Mes;
    cout << "\tIndique el a�o: ";
    cin >> fecha.Anyo;
    return fecha;
}

// Funcion para pedir una cadena, el metodo cin >> no obtiene los espacios de la misma
void pedirCadena(Cadena cad) {
    fflush(stdin);
    cin.getline(cad, 90, '\n');
}

// Funcion para pedir la confirmacion de una accion al usuario, lo usamos en la opcion de actualizar productos.
bool confirmar(Cadena mensaje) {
    char c;
    do {
        cout << mensaje;
        cin >> c;
        c = tolower(c);
    } while (c != 's' && c != 'n');
    return c == 's';
}

// Funcion para pausar y que no salte directamente a los menus
void pausa() {
    cout << "\nOK. (Presione <Enter> para volver al men�)";
    getch();
}

int MenuPrincipal(Cadena NombAlmacen, Cadena NombTienda) {
    int opc = 0;
    do {
        system("cls");
        if (strcmp(NombAlmacen, "") == 0) cout << "----- Men� Principal ----- " << NombTienda << endl;
        else if (strcmp(NombTienda, "") == 0) cout << "----- Men� Principal ----- " << NombAlmacen << endl;
        else if (strcmp(NombAlmacen, "") + strcmp(NombTienda, "") == 0) cout << "----- Men� Principal -----\n";
        else cout << "----- Men� Principal ----- " << NombAlmacen << ", " << NombTienda << endl;
        cout << "1.- Gesti�n de Almacenes.\n";
        cout << "2.- Gesti�n de la Tienda.\n";
        cout << "3.- Reposici�n de Productos en Tienda.\n";
        cout << "0.- Salir.\n";
        if (opc == 0) cout << "\n\tSeleccione una opci�n: ";
        else cout << "\n\tOpci�n incorrecta. Seleccione otra opci�n: ";
        cin >> opc;
    } while (opc < 0 || opc > 3);
    cout << endl;
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
        cout << "9.- Rebajas de productos.\n";
        cout << "0.- Salir.\n";
        if (opc == 0) cout << "\n\tSeleccione una opci�n: ";
        else cout << "\n\tOpci�n incorrecta. Seleccione otra opci�n: ";
        cin >> opc;
    } while(opc < 0 || opc > 9);
    cout << endl;
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
        if (opc == 0) cout << "\n\tSeleccione una opci�n: ";
        else cout << "\n\tOpci�n incorrecta. Seleccione otra opci�n: ";
        cin >> opc;
    } while(opc < 0 || opc > 9);
    cout << endl;
    return opc;
}

void GestionAlmacen(Cadena nombre) {
    Cadena nFichero;
    TProducto prod;
    int opc = 0;
    int pos = 0;

    /// MODIFICACION
    float descuento, preciomin, precioBAK;
    do {
        opc = MenuAlmacen(nombre);
        switch (opc) {
        case 1: /// Crear un almac�n vac�o
            if (!almacen.EstaAbierto()) {
                cout << "Indique el nombre del almac�n: ";
                pedirCadena(nombre);
                cout << "Indique la direcci�n del almac�n: ";
                pedirCadena(DirAlmacen);
                cout << "Indique el nombre del fichero: ";
                pedirCadena(nFichero);
                if (almacen.CrearAlmacen(nombre, DirAlmacen, nFichero)) {
                    cout << "El almac�n " << nombre << " se ha creado con �xito.\n";
                } else {
                    cout << "ERROR! No se ha podido crear el almac�n.\n";
                }
            } else {
                cout << "Ya hay un almac�n abierto.\n";
            }
            pausa();
            break;
        case 2: /// Abrir un fichero de almac�n
            if (!almacen.EstaAbierto()) {
                cout << "Indique el nombre del fichero: ";
                pedirCadena(nFichero);
                if (almacen.AbrirAlmacen(nFichero)) {
                    almacen.DatosAlmacen(nombre, DirAlmacen);
                    cout << "Se ha abierto el almac�n \"" << nombre << "\" con �xito.\n";
                } else {
                    cout << "ERROR! No se ha podido abrir el fichero.\n";
                }
            } else {
                cout << "Ya hay un almac�n abierto.\n";
            }
            pausa();
            break;
        case 3: /// Cerrar un almac�n
            if (almacen.EstaAbierto()) {
                almacen.CerrarAlmacen(); // Nunca da false
                strcpy(nombre, "");
                cout << "Se ha cerrado el almac�n.\n";
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 4: /// Listar productos del almac�n
            if (almacen.EstaAbierto()) {
                if (almacen.NProductos() > 0) {
                    // Cabecera
                    cout << "Listado del almac�n \"" << nombre << "\" localizado en " << DirAlmacen << endl;
                    for (int i = 0; i < 21+(int)strlen(nombre)+16+(int)strlen(DirAlmacen); i++) cout << "*";
                    cout << endl;
                    //cout << "N�MERO DE PRODUCTOS: " << almacen.NProductos() << endl;
                    cout << "CODIGO\tNOMBRE\t\t\t\t\tPRECIO\tCANTIDAD\tFECHA CADUCIDAD\t\tDESCRIPCION\n";
                    for (int i = 0; i < almacen.NProductos(); i++) {
                        MostrarProducto(almacen.ObtenerProducto(i));
                    }
                } else {
                    cout << "No hay productos en el almac�n.\n";
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 5: /// A�adir un producto
            if (almacen.EstaAbierto()) {
                // NOMBRE
                cout << "Indique el nombre del producto: ";
                pedirCadena(prod.NombreProd);
                while (strcmp(prod.NombreProd, "") == 0) {
                    cout << "Valor inv�lido. Indique el nombre del producto: ";
                    pedirCadena(prod.NombreProd);
                }
                // DESCRIPCION
                cout << "Indique la descripci�n del producto: ";
                pedirCadena(prod.Descripcion);
                while (strcmp(prod.Descripcion, "") == 0) {
                    cout << "Indique la descripci�n del producto: ";
                    pedirCadena(prod.Descripcion);
                }
                // CODIGO
                cout << "Indique el c�digo del producto: ";
                pedirCadena(prod.CodProd);
                while (strcmp(prod.CodProd, "") == 0) {
                    cout << "Valor inv�lido. Indique el c�digo del producto: ";
                    pedirCadena(prod.CodProd);
                }
                // PRECIO
                cout << "Indique el precio del producto: ";
                cin >> prod.Precio;
                while (prod.Precio <= 0) {
                    cout << "Valor inv�lido. Indique el precio del producto: ";
                    cin >> prod.Precio;
                }
                // CANTIDAD
                cout << "Indique la cantidad del producto: ";
                cin >> prod.Cantidad;
                while (prod.Cantidad < 0) {
                    cout << "Valor inv�lido. Indique la cantidad del producto: ";
                    cin >> prod.Cantidad;
                }
                // FECHA
                cout << "Indique la fecha de caducidad: ";
                prod.Caducicidad = pedirFecha();

                if (almacen.AnadirProducto(prod)) {
                    cout << "El producto se ha a�adido con �xito.\n";
                } else {
                    cout << "ERROR! No se ha podido a�adir el producto.\n";
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 6: /// Actualizar un producto
            if (almacen.EstaAbierto()) {
                cout << "Indique el c�digo del producto a actualizar: ";
                pedirCadena(prod.CodProd);
                pos = almacen.BuscarProducto(prod.CodProd);
                if (pos == -1) {
                    cout << "ERROR! No se ha encontrado un producto con el c�digo indicado.\n";
                } else {
                    prod = almacen.ObtenerProducto(pos);
                    cout << "Producto encontrado.\n";
                    cout << "CODIGO\tNOMBRE\t\t\t\t\tPRECIO\tCANTIDAD\tFECHA CADUCIDAD\t\tDESCRIPCION\n";
                    MostrarProducto(prod);
                    // Nombre
                    if (confirmar("�Desea modificar el nombre? (S/n): ")) {
                        cout << "Indique el nombre nuevo: ";
                        pedirCadena(prod.NombreProd);
                        while (strcmp(prod.NombreProd, "") == 0) {
                            cout << "Valor inv�lido. Indique el nombre nuevo: ";
                            pedirCadena(prod.NombreProd);
                        }
                    }
                    // Descripcion
                    if (confirmar("�Desea modificar la descripci�n? (S/n): ")) {
                        cout << "Indique la descripci�n nueva: ";
                        pedirCadena(prod.Descripcion);
                    }
                    // C�digo
                    if (confirmar("�Desea modificar el c�digo? AVISO: Si modifica el codigo puede haber problemas con la tienda (S/n): ")) {
                        cout << "Indique el c�digo nuevo: ";
                        pedirCadena(prod.CodProd);
                        while (strcmp(prod.CodProd, "") == 0) {
                            cout << "Valor inv�lido. Indique el c�digo nuevo: ";
                            pedirCadena(prod.CodProd);
                        }
                    }
                    // Precio
                    if (confirmar("�Desea modificar el precio? (S/n): ")) {
                        cout << "Indique el precio nuevo: ";
                        cin >> prod.Precio;
                        while (prod.Precio <= 0) {
                            cout << "Valor inv�lido. Indique el precio nuevo: ";
                            cin >> prod.Precio;
                        }
                    }
                    // Cantidad
                    if (confirmar("�Desea modificar la cantidad? (S/n): ")) {
                        cout << "Indique la cantidad nueva: ";
                        cin >> prod.Cantidad;
                        while (prod.Cantidad < 0) {
                            cout << "Valor inv�lido. Indique la cantidad nueva: ";
                            cin >> prod.Cantidad;
                        }
                    }
                    // Caducidad
                    if (confirmar("�Desea modificar la fecha? (S/n): ")) {
                        cout << "Indique la fecha nueva: ";
                        prod.Caducicidad = pedirFecha();
                    }

                    if (almacen.ActualizarProducto(pos, prod)) {
                        cout << "Se ha actualizado el producto con �xito.\n";
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
                cout << "Indique el c�digo del producto a consultar: ";
                pedirCadena(prod.CodProd);
                pos = almacen.BuscarProducto(prod.CodProd);
                if (pos == -1) { // Valor irreal
                    cout << "ERROR! No se ha encontrado un producto con el c�digo indicado.\n";
                } else {
                    cout << "Producto encontrado.\n";
                    cout << "CODIGO\tNOMBRE\t\t\t\t\tPRECIO\tCANTIDAD\tFECHA CADUCIDAD\tDESCRIPCION\n";
                    MostrarProducto(almacen.ObtenerProducto(pos));
                    cout << endl;
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 8: /// Eliminar un producto
            if (almacen.EstaAbierto()) {
                cout << "Indique el c�digo del producto a eliminar: ";
                pedirCadena(prod.CodProd);
                pos = almacen.BuscarProducto(prod.CodProd);
                if (pos == -1) {
                    cout << "ERROR! No se ha encontrado un producto con el c�digo indicado.\n";
                } else {
                    cout << "Producto encontrado.\n";
                    if (almacen.EliminarProducto(pos)) {
                        cout << "Se ha eliminado el producto con �xito.\n";
                    } else {
                        cout << "ERROR! No se ha podido eliminar el producto.\n";
                    }
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
        case 9: /// Rebajas de productos
            if (almacen.EstaAbierto()) {
                if (almacen.NProductos() > 0) {
                    cout << "Indique que porcentaje de descuento desea hacer (0-100%): ";
                    cin >> descuento;
                    while (descuento <= 0 || descuento >= 100) {
                        cout << "Valor inv�lido. Indique que porcentaje de descuento desea hacer (0-100%): ";
                        cin >> descuento;
                    }
                    cout << "Indique el precio m�nimo que debe tener cada producto para aplicar el descuento: ";
                    cin >> preciomin;
                    while (preciomin <= 0) {
                        cout << "Valor inv�lido. Indique el precio m�nimo que debe tener cada producto para aplicar el descuento: ";
                        cin >> preciomin;
                    }
                    // Pasamos el descuento a decimal
                    descuento = (float)descuento/100;

                    // Mostramos la cabecera
                    cout << "CODIGO\tNOMBRE\t\t\t\t\tPRECIO SIN DESCUENTO\tPRECIO CON DESCUENTO\n";
                    for (int i = 0; i < almacen.NProductos(); i++) {
                        prod = almacen.ObtenerProducto(i);
                        if (prod.Precio >= preciomin) {
                            precioBAK = prod.Precio;
                            // Aplicamos el descuento
                            prod.Precio = prod.Precio - (prod.Precio*descuento);
                            if (almacen.ActualizarProducto(i, prod)) {
                                cout << prod.CodProd << "\t" << prod.NombreProd << "\t" << precioBAK << "\t" << prod.Precio << endl;
                            } else {
                                cout << "ERROR! No se ha podido aplicar el descuento.\n";
                            }
                        }
                    }
                } else {
                    cout << "No hay productos en el almac�n.\n";
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
    Cadena nFichero;
    int opc = 0;
    // Variables aux
    TEstante estante;
    TProducto prod;
    int pos = 0;
    int nprod = 0;
    do {
        opc = MenuTienda(nombre);
        switch (opc) {
        case 1: /// Crear una tienda vac�a
            if (!tienda.EstaAbierta()) {
                cout << "Indique el nombre de la tienda: ";
                pedirCadena(nombre);
                cout << "Indique la direcci�n de la tienda: ";
                pedirCadena(DirTienda);
                cout << "Indique el nombre del fichero: ";
                pedirCadena(nFichero);
                if (tienda.CrearTienda(nombre, DirTienda, nFichero)) {
                    cout << "la tienda \"" << nombre << "\" se ha creado con �xito.\n";
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
            // Si ya habia una tienda abierta, esta se guardara (implementacion TTienda)
            if (tienda.AbrirTienda(nFichero)) {
                tienda.DatosTienda(nombre, DirTienda);
                cout << "Se ha abierto la tienda \"" << nombre << "\" con �xito.\n";
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
                    if (tienda.NoEstantes() > 0) {
                        // Cabecera
                        cout << "Listado de la tienda \"" << nombre << "\" localizado en " << DirTienda << endl;
                        for (int i = 0; i < 22+(int)strlen(nombre)+16+(int)strlen(DirTienda); i++) cout << "*";
                        cout << endl;
                        //cout << "N�MERO DE ESTANTES: " << tienda.NoEstantes() << endl;
                        cout << "CODIGO POSICION CAPACIDAD CODIGO PRODUCTO NOMBRE\t\t\tPRECIO\tNoPRODUCTOS\tVALOR TOTAL\n";
                        for (int i = 0; i < tienda.NoEstantes(); i++) {
                            MostrarEstante(tienda.ObtenerEstante(i));
                        }
                    } else {
                        cout << "No hay estantes en el almac�n.\n";
                    }
                } else {
                    cout << "No hay un almacen abierto.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 6: /// A�adir un estante
            if (tienda.EstaAbierta()) {
                if (almacen.EstaAbierto()) {
                    if (almacen.NProductos() > 0) {
                        cout << "Indique el c�digo del estante: ";
                        cin >> estante.CodEstante;
                        while (tienda.BuscarEstante(estante.CodEstante) != -1) {
                            cout << "Ya existe un estante con ese c�digo. Indique el c�digo del estante: ";
                            cin >> estante.CodEstante;
                        }
                        cout << "Indique la posici�n del estante (1- centrado, 2- arriba, 3- abajo): ";
                        cin >> estante.Posicion;
                        while (estante.Posicion < 1 || estante.Posicion > 3) {
                            cout << "Posici�n inv�lida. Indique la posici�n de nuevo: ";
                            cin >> estante.Posicion;
                        }
                        cout << "Indique la capacidad del estante: ";
                        cin >> estante.Capacidad;
                        while (estante.Capacidad < 1) {
                            cout << "Posici�n inv�lida. Indique la capacidad del estante: ";
                            cin >> estante.Capacidad;
                        }
                        cout << "Indique el c�digo del producto del estante: ";
                        pedirCadena(estante.CodProd);
                        while (strcmp(estante.CodProd, "") == 0) {
                            cout << "Valor inv�lido. Indique el c�digo del producto del estante: ";
                            pedirCadena(estante.CodProd);
                        }
                        pos = almacen.BuscarProducto(estante.CodProd);
                        if (pos == -1) {
                            cout << "ERROR! El c�digo del producto indicado no se encuentra en el almac�n.\n";
                        } else {
                            prod = almacen.ObtenerProducto(pos);
                            if (prod.Cantidad == 0) {
                                cout << "No quedan m�s productos de este tipo en el almac�n.\n";
                            } else {
                                cout << "Indique el n�mero de productos en el estante: ";
                                cin >> estante.NoProductos;

                                // Comprobamos que el no productos indicado sea menor o igual al que hay en el almacen
                                if (prod.Cantidad >= estante.NoProductos) {
                                    prod.Cantidad -= estante.NoProductos;
                                } else {
                                    // No hay productos suficientes
                                    estante.NoProductos = prod.Cantidad;
                                    prod.Cantidad = 0;
                                }
                                if (tienda.AnadirEstante(estante) && almacen.ActualizarProducto(pos, prod)) {
                                    cout << "Se ha a�adido el estante con �xito.\n";
                                } else {
                                    cout << "ERROR! No se podido a�adir el estante.\n";
                                }
                            }
                        }
                    } else {
                        cout << "El almacen est� vac�o.\n";
                    }
                } else {
                    cout << "No hay un almac�n abierto.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 7: /// Actualizar un estante
            if (tienda.EstaAbierta()) {
                if (almacen.EstaAbierto()) {
                    cout << "Indique el c�digo del estante a actualizar: ";
                    cin >> estante.CodEstante;
                    pos = tienda.BuscarEstante(estante.CodEstante);
                    if (pos == -1) {
                        cout << "ERROR! No se ha encontrado un estante con el c�digo indicado.\n";
                    } else {
                        cout << "Estante encontrado.\n";
                        cout << "CODIGO POSICION CAPACIDAD CODIGO PRODUCTO NOMBRE\t\t\tPRECIO\tNoPRODUCTOS\tVALOR TOTAL\n";
                        estante = tienda.ObtenerEstante(pos);
                        MostrarEstante(estante);
                        if (confirmar("�Desea actualizar el n�mero de productos? (S/n): ")) {
                            // Recogemos el producto
                            prod = almacen.ObtenerProducto(almacen.BuscarProducto(estante.CodProd));
                            nprod = estante.NoProductos;
                            cout << "Indique el n�mero de productos nuevo: ";
                            cin >> estante.NoProductos;
                            while (estante.NoProductos < 0) {
                                cout << "Valor inv�lido. Indique el n�mero de productos nuevo: ";
                                cin >> estante.NoProductos;
                            }
                           /*
                            *  Si la cantidad es menor a la que hab�a, se ha de devolver la
                            *  diferencia al almac�n y si es mayor habr� que reponer esa
                            *  cantidad desde el almac�n. En el caso que la cantidad a reponer
                            *  sea superior a la que hay en el almac�n se repondr� solo la cantidad
                            *  existente en el almac�n. En cada caso se indicar� por pantalla que
                            *  cantidad se ha repuesto en el estante o devuelto al almac�n.
                            */
                            if (estante.NoProductos <= nprod) {
                                prod.Cantidad += (nprod - estante.NoProductos);
                            } else {
                                estante.NoProductos = prod.Cantidad;
                                prod.Cantidad = 0;
                            }
                            if (almacen.ActualizarProducto(almacen.BuscarProducto(estante.CodProd), prod)) {
                                if (prod.Cantidad == 0) {
                                    cout << "Se ha repuesto solo la cantidad de productos que habia en el almac�n.\n";
                                } else {
                                    cout << "Se ha devuelto al almac�n: " << (nprod - estante.NoProductos) << endl;
                                }
                                cout << "N�mero de productos en el estante: " << estante.NoProductos << endl;
                            }
                            if (tienda.ActualizarEstante(pos, estante)) {
                                cout << "Se ha actualizado el estante con �xito.\n";
                            }
                        }
                    }
                } else {
                    cout << "No hay un almac�n abierto.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 8: /// Consultar un estante
            if (tienda.EstaAbierta()) {
                if (almacen.EstaAbierto()) {
                    cout << "�Como desea consultar por c�digo del estante o c�digo del producto?\n";
                    cout << "1.- C�digo del estante.\n2.- C�digo del producto.\n";
                    cout << "\n\tSeleccione una opci�n: ";
                    cin >> opc;
                    while (opc < 1 || opc > 2) {
                        cout << "\tOpci�n incorrecta. Seleccione una opci�n: ";
                        cin >> opc;
                    }

                    if (opc == 1) {
                        cout << "Indique el c�digo del estante a consultar: ";
                        cin >> estante.CodEstante;
                    } else {
                        cout << "Indique el c�digo del producto a consultar: ";
                        pedirCadena(estante.CodProd);
                        estante.CodEstante = tienda.BuscarProducto(estante.CodProd);
                    }
                    if (estante.CodEstante != -1) {
                        pos = tienda.BuscarEstante(estante.CodEstante);
                        if (pos == -1) {
                            cout << "ERROR! No se ha encontrado un estante con el c�digo indicado.\n";
                        } else {
                            cout << "CODIGO POSICION CAPACIDAD CODIGO PRODUCTO NOMBRE\t\t\tPRECIO\tNoPRODUCTOS\tVALOR TOTAL\n";
                            MostrarEstante(tienda.ObtenerEstante(pos));
                        }
                    } else {
                        cout << "ERROR! No se ha encontrado un estante con el c�digo de producto indicado.\n";
                    }
                } else {
                    cout << "No hay un almac�n abierto.\n";
                }
            } else {
                cout << "No hay tiendas abiertas.\n";
            }
            pausa();
            break;
        case 9: /// Eliminar un estante
            if (tienda.EstaAbierta()) {
                if (almacen.EstaAbierto()) {
                    cout << "Indique el c�digo del estante a eliminar: ";
                    cin >> estante.CodEstante;
                    pos = tienda.BuscarEstante(estante.CodEstante);
                    if (pos == -1) {
                        cout << "ERROR! No se ha encontrado un estante con el c�digo indicado.\n";
                    } else {
                        estante = tienda.ObtenerEstante(pos);
                        if (tienda.EliminarEstante(pos)) {
                            // Devolver los productos al almacen
                            pos = almacen.BuscarProducto(estante.CodProd);
                            prod = almacen.ObtenerProducto(pos);
                            prod.Cantidad -= estante.NoProductos;
                            if (almacen.ActualizarProducto(pos, prod)) {
                                cout << "Se ha eliminado el estante con �xito.\n";
                            }
                        } else {
                            cout << "ERROR! No se ha podido eliminar el estante.\n";
                        }
                    }
                } else {
                    cout << "No hay un almac�n abierto.\n";
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
        int pProd = -1;
        int estado;
        cout << "C�DIGO CAPACIDAD NOMBRE\t\t\t\tESTADO REPUESTO OCUPACI�N(%)\n";
        for (int i = 0; i < tienda.NoEstantes(); i++) {
            estante = tienda.ObtenerEstante(i);
            pProd = almacen.BuscarProducto(estante.CodProd);
            if (pProd != -1) {
                // Como el producto existe lo recogemos
                prod = almacen.ObtenerProducto(pProd);
                estado = tienda.ReponerEstante(i, prod);
                // Actualizamos los atributos del estante
                estante = tienda.ObtenerEstante(i);
                switch (estado) {
                case 0:
                    strcpy(cEstado, "NO");
                    break;
                case 1:
                    strcpy(cEstado, "COMPLETO");
                    break;
                case 2:
                    strcpy(cEstado, "PARCIAL");
                    break;
                }
                if (almacen.ActualizarProducto(pProd, prod)) {
                    cout << estante.CodEstante << "\t" << estante.Capacidad << "\t" << prod.NombreProd << "\t\t\t\t"
                        << cEstado << "\t" << ((float)estante.NoProductos/estante.Capacidad)*100 << " %\n";
                } else {
                    cout << "ERROR! No se ha podido actualizar el producto del estante en el almac�n.\n";
                }
            } else {
                cout << "ERROR! No se ha encontrado el producto del estante en el almac�n.\n";
            }
        }
    } else {
        cout << "No est� el almac�n y la tienda abierta.\n";
    }
    pausa();
}

int main() {
    system("title Pr�ctica 1");
    setlocale(LC_CTYPE, "Spanish");

    // Variables auxiliares
    Cadena nAlmacen, nTienda;

    // inicializamos a nulo
    strcpy(nAlmacen, "");
    strcpy(nTienda, "");

    int opc = -1;
    while (opc != 0) {
        opc = MenuPrincipal(nAlmacen, nTienda);
        switch (opc) {
        case 1: /// Gesti�n del Almacenes
            GestionAlmacen(nAlmacen);
            break;
        case 2: /// Gesti�n de la Tienda
            GestionTienda(nTienda);
            break;
        case 3: /// Reposici�n de Productos en Tienda
            ReposicionProductos();
            break;
        }
    }
    return 0;
}
