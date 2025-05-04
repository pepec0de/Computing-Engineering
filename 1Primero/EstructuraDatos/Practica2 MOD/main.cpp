#include <iostream>
#include <cstdlib>
#include "include/TAlmacen.h"
#include "include/TTienda.h"

// Librerias extras para mejorar la experiencia del usuario
#include <locale.h>
#include <conio.h>

using namespace std;

// Funcion para mostrar un producto
void MostrarProducto(TProducto prod) {
	printf("%s\t %-30s\t %5.2f  %8d %15d/%02d/%d\t %s\n", prod.CodProd, prod.NombreProd, prod.Precio,
		   prod.Cantidad, prod.Caducicidad.Dia, prod.Caducicidad.Mes, prod.Caducicidad.Anyo, prod.Descripcion);
}

void MostrarEstante(TEstante testante, TProducto prod) {
	printf("%d %8d %8d\t  %s\t  %-30s\t %5.2f %8d %15.2f\n", testante.CodEstante, testante.Posicion,
		   testante.Capacidad, testante.CodProd, prod.NombreProd, prod.Precio, testante.NoProductos, prod.Precio * testante.NoProductos);
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
	} while(c != 's' && c != 'n');
	return c == 's';
}

// Funcion para pausar y que no salte directamente a los menus
void pausa() {
	cout << "\nOK. (Presione <Enter> para volver al menú)";
	getch();
}

int MenuPedidos() {
	int opc = 0;
	do {
		system("cls");
		cout << "----- Gestión de Pedidos ----- " << endl;
		cout << "1.- Cargar pedidos de fichero.\n";
		cout << "2.- Añadir pedido.\n";
		cout << "3.- Atender pedidos.\n";
		cout << "4.- Listar pedidos completos de todos los productos.\n";
		cout << "5.- Listar pedidos de un producto.\n";
		cout << "6.- Listar todas las cantidades pendientes.\n";
		cout << "7.- Listar cantidades pendientes de un producto.\n";
		cout << "8.- Guardar pedidos a fichero.\n";
		cout << "9.- Bloquear pedidos.\n";
		cout << "0.- Salir.\n";
		if(opc == 0) cout << "\n\tSeleccione una opción: ";
		else cout << "\n\tOpción incorrecta. Seleccione otra opción: ";
		cin >> opc;
	} while(opc < 0 || opc > 9);
	cout << endl;
	return opc;
}

void GestionPedidos(TAlmacen &almacen) {
	int opc = 0;
	Cadena nFichero;
	TPedido pedido;
	int pos;
	do {
		opc = MenuPedidos();
		switch(opc) {
		case 1: /// Cargar pedidos de fichero
			if(almacen.EstaAbierto()) {
				cout << "Indique el nombre del fichero: ";
				pedirCadena(nFichero);
				if(almacen.CargarColaPedidos(nFichero)) {
					cout << "Se ha cargado el fichero de pedidos.\n";
				} else {
					cout << "ERROR! No se ha podido cargar el fichero de pedidos.\n";
				}
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 2: /// Añadir pedido
			if(almacen.EstaAbierto()) {
				if(almacen.NProductos() > 0) {
					cout << "Indique el nombre de la tienda: ";
					pedirCadena(pedido.Nomtienda);
                    while(strcmp(pedido.Nomtienda, "") == 0) {
						cout << "Valor inválido. Indique otro nombre de tienda: ";
						pedirCadena(pedido.Nomtienda);
					}

					cout << "Indique el código del producto: ";
					pedirCadena(pedido.CodProd);
					while(strcmp(pedido.CodProd, "") == 0) {
						cout << "Valor inválido. Indique otro código de producto: ";
						pedirCadena(pedido.CodProd);
					}

					cout << "Indique la cantidad pedida: ";
					cin >> pedido.CantidadPed;
                    while (pedido.CantidadPed <= 0) {
                        cout << "Valor inválido. Indique la cantidad pedida: ";
                        cin >> pedido.CantidadPed;
                    }

					pos = almacen.BuscarProducto(pedido.CodProd);
					if (pos != -1) {
                        almacen.AnadirPedido(pedido);
					} else {
                        cout << "No se ha encontrado el código de producto " << pedido.CodProd << " en el almacén.\n";
					}
				} else {
					cout << "No hay productos en el almacén.\n";
				}
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 3: /// Atender pedidos
			if(almacen.EstaAbierto()) {
				cout << "Indique el código del producto a comprar: ";
				pedirCadena(pedido.CodProd);
				while(almacen.BuscarProducto(pedido.CodProd) == -1) {
					cout << "No se ha encontrado el código indicado en el almacén. Indique otro código de producto a comprar: ";
					pedirCadena(pedido.CodProd);
				}

				cout << "Indique la cantidad a comprar: ";
				cin >> pedido.CantidadPed;
				while(pedido.CantidadPed < 0) {
					cout << "Valor inválido. Indique la cantidad a comprar: ";
					cin >> pedido.CantidadPed;
				}

				if(almacen.AtenderPedidos(pedido.CodProd, pedido.CantidadPed)) {
					cout << "Se han atendido los pedidos con éxito.\n";
				} else {
					cout << "No se han podido atender los pedidos.\n";
				}
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 4: /// Listar pedidos completos de todos los productos
			if(almacen.EstaAbierto()) {
				almacen.ListarPedidosCompleto("");
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 5: /// Listar pedidos de un producto
			if(almacen.EstaAbierto()) {
				cout << "Indique el código del producto a listar: ";
				pedirCadena(pedido.CodProd);
				while(strcmp(pedido.CodProd, "") == 0) {
					cout << "Indique el código del producto a listar: ";
					pedirCadena(pedido.CodProd);
				}
				almacen.ListarPedidosCompleto(pedido.CodProd);
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 6: /// Listar todas las cantidades pendientes
			if(almacen.EstaAbierto()) {
				almacen.ListarCantidadesPendientes("");
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 7: /// Listar cantidades pendientes de un producto
			if(almacen.EstaAbierto()) {
				cout << "Indique el código del producto a listar: ";
				pedirCadena(pedido.CodProd);
				while(strcmp(pedido.CodProd, "") == 0) {
					cout << "Valor inválido. Indique el código del producto a listar: ";
					pedirCadena(pedido.CodProd);
				}
				almacen.ListarCantidadesPendientes(pedido.CodProd);
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 8: /// Guardar pedidos a fichero
			if(almacen.EstaAbierto()) {
				cout << "Indique el nombre del fichero de los pedidos: ";
				pedirCadena(nFichero);
				while(strcmp(nFichero, "") == 0) {
					cout << "Valor inválido. Indique el nombre del fichero de los pedidos: ";
					pedirCadena(nFichero);
				}
				if(almacen.SalvarColaPedidos(nFichero)) {
					cout << "Se han guardado los pedidos en el fichero: " << nFichero << ".\n";
				} else {
					cout << "No se ha podido guardar el fichero.\n";
				}
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
        case 9: /// Bloquear pedidos
            if (almacen.EstaAbierto()) {
                cout << "Indique el nombre de la tienda a la que bloquear los pedidos: ";
				pedirCadena(pedido.Nomtienda);
				while(strcmp(pedido.Nomtienda, "") == 0) {
					cout << "Valor inválido. Indique el nombre de la tienda a la que bloquear los pedidos: ";
					pedirCadena(pedido.Nomtienda);
				}

                pos = almacen.BloquearPedidos(pedido.Nomtienda);
                if (pos > 0) {
                    cout << "Número de pedidos que han sido bloqueados: " << pos << endl;
                } else {
                    cout << "No se ha encontrado ningún pedido con la tienda indicada.\n";
                }
            } else {
                cout << "No hay almacenes abiertos.\n";
            }
            pausa();
            break;
		}
	} while(opc != 0);
}

int MenuEnvios() {
	int opc = 0;
	do {
		system("cls");
		cout << "----- Gestión de Envíos ----- " << endl;
		cout << "1.- Cargar envíos de fichero.\n";
		cout << "2.- Insertar un nuevo envío.\n";
		cout << "3.- Reparto de envíos a tienda.\n";
		cout << "4.- Listar todos los envíos.\n";
		cout << "5.- Listar los envíos a una tienda.\n";
		cout << "6.- Guardar envíos a fichero.\n";
		cout << "0.- Salir.\n";
		if(opc == 0) cout << "\n\tSeleccione una opción: ";
		else cout << "\n\tOpción incorrecta. Seleccione otra opción: ";
		cin >> opc;
	} while(opc < 0 || opc > 6);
	cout << endl;
	return opc;
}

void GestionEnvios(TAlmacen &almacen) {
	int opc = 0;
	Cadena nFichero;
	TPedido pedido;
	do {
		opc = MenuEnvios();
		switch(opc) {
		case 1: /// Cargar envíos de fichero
			if(almacen.EstaAbierto()) {
				cout << "Indique el nombre del fichero: ";
				pedirCadena(nFichero);
				if(almacen.CargarListaEnvios(nFichero)) {
					cout << "Se ha cargado el fichero de envíos.\n";
				} else {
					cout << "ERROR! No se ha podido cargar el fichero de pedidos.\n";
				}
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 2: /// Insertar un nuevo envío
			if(almacen.EstaAbierto()) {
				if(almacen.NProductos() > 0) {
					cout << "Indique el nombre de la tienda: ";
					pedirCadena(pedido.Nomtienda);

					cout << "Indique el código del producto: ";
					pedirCadena(pedido.CodProd);
					while(almacen.BuscarProducto(pedido.CodProd) == -1) {
						cout << "No se ha encontrado el código indicado en el almacén. Indique otro código de producto: ";
						pedirCadena(pedido.CodProd);
					}

					cout << "Indique la cantidad pedida: ";
					cin >> pedido.CantidadPed;

					almacen.InsertarEnvios(pedido);
				} else {
					cout << "No hay productos en el almacén.\n";
				}
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 3: /// Reparto de envíos a tienda
			if(almacen.EstaAbierto()) {
				cout << "Indique el nombre de la tienda a la que repartir: ";
				pedirCadena(pedido.Nomtienda);
				while(strcmp(pedido.Nomtienda, "") == 0) {
					cout << "Valor inválido. Indique el nombre de la tienda a la que repartir: ";
					pedirCadena(pedido.Nomtienda);
				}
				if(almacen.SalidaCamionTienda(pedido.Nomtienda)) {
					cout << "Se han repartido los envíos.\n";
				} else {
					cout << "No se han repartido envíos.\n";
				}
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 4: /// Listar todos los envíos
			if(almacen.EstaAbierto()) {
				almacen.ListarListaEnvios("");
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 5: /// Listar los envíos a una tienda
			if(almacen.EstaAbierto()) {
				cout << "Indique el nombre de la tienda a listar: ";
				pedirCadena(pedido.Nomtienda);
				while(strcmp(pedido.Nomtienda, "") == 0) {
					cout << "Valor inválido. Indique el nombre de la tienda a listar: ";
					pedirCadena(pedido.Nomtienda);
				}
				almacen.ListarListaEnvios(pedido.Nomtienda);
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 6: /// Guardar envíos a fichero
			cout << "Indique el nombre del fichero de envios: ";
			pedirCadena(nFichero);
			while(strcmp(nFichero, "") == 0) {
				cout << "Valor inválido. Indique el nombre del fichero de envios: ";
				pedirCadena(nFichero);
			}
			if(almacen.SalvarListaEnvios(nFichero)) {
				cout << "Se han guardado los envios con éxito.\n";
			} else {
				cout << "No se han podido guardar los envios.\n";
			}
			pausa();
			break;
		}
	} while(opc != 0);
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
		cout << "9.- Gestión de pedidos.\n";
		cout << "10.- Gestión de envíos.\n";
		cout << "0.- Salir.\n";
		if(opc == 0) cout << "\n\tSeleccione una opción: ";
		else cout << "\n\tOpción incorrecta. Seleccione otra opción: ";
		cin >> opc;
	} while(opc < 0 || opc > 10);
	cout << endl;
	return opc;
}

void GestionAlmacen(Cadena nombre, TAlmacen &almacen) {
	Cadena nFichero, direccion;
	TProducto prod;
	int opc = 0;
	int pos = 0;
	almacen.DatosAlmacen(nombre, direccion);
	do {
		opc = MenuAlmacen(nombre);
		switch(opc) {
		case 1: /// Crear un almacén vacío
			if(!almacen.EstaAbierto()) {
				cout << "Indique el nombre del almacén: ";
				pedirCadena(nombre);
				cout << "Indique la dirección del almacén: ";
				pedirCadena(direccion);
				cout << "Indique el nombre del fichero: ";
				pedirCadena(nFichero);
				if(almacen.CrearAlmacen(nombre, direccion, nFichero)) {
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
			if(!almacen.EstaAbierto()) {
				cout << "Indique el nombre del fichero: ";
				pedirCadena(nFichero);
				if(almacen.AbrirAlmacen(nFichero)) {
					almacen.DatosAlmacen(nombre, direccion);
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
			if(almacen.EstaAbierto()) {
				almacen.CerrarAlmacen(); // Nunca da false
				strcpy(nombre, "");
				cout << "Se ha cerrado el almacén.\n";
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 4: /// Listar productos del almacén
			if(almacen.EstaAbierto()) {
				if(almacen.NProductos() > 0) {
					// Cabecera
					cout << "Listado del almacén \"" << nombre << "\" localizado en " << direccion << endl;
					for(int i = 0; i < 21 + (int)strlen(nombre) + 16 + (int)strlen(direccion); i++) cout << "*";
					cout << endl;
					printf("%s\t %-30s\t %s  %s\t %s\t %s\n", "CODIGO", "NOMBRE", "PRECIO", "CANTIDAD", "FECHA CADUCIDAD", "DESCRIPCION");
					//cout << "CODIGO\tNOMBRE\t\t\t\t\tPRECIO\tCANTIDAD\tFECHA CADUCIDAD\t\tDESCRIPCION\n";
					for(int i = 0; i < almacen.NProductos(); i++) {
						MostrarProducto(almacen.ObtenerProducto(i));
					}
				} else {
					cout << "No hay productos en el almacén.\n";
				}
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 5: /// Añadir un producto
			if(almacen.EstaAbierto()) {
				// NOMBRE
				cout << "Indique el nombre del producto: ";
				pedirCadena(prod.NombreProd);
				while(strcmp(prod.NombreProd, "") == 0) {
					cout << "Valor inválido. Indique el nombre del producto: ";
					pedirCadena(prod.NombreProd);
				}
				// DESCRIPCION
				cout << "Indique la descripción del producto: ";
				pedirCadena(prod.Descripcion);
				while(strcmp(prod.Descripcion, "") == 0) {
					cout << "Indique la descripción del producto: ";
					pedirCadena(prod.Descripcion);
				}
				// CODIGO
				cout << "Indique el código del producto: ";
				pedirCadena(prod.CodProd);
				while(strcmp(prod.CodProd, "") == 0) {
					cout << "Valor inválido. Indique el código del producto: ";
					pedirCadena(prod.CodProd);
				}
				// PRECIO
				cout << "Indique el precio del producto: ";
				cin >> prod.Precio;
				while(prod.Precio <= 0) {
					cout << "Valor inválido. Indique el precio del producto: ";
					cin >> prod.Precio;
				}
				// CANTIDAD
				cout << "Indique la cantidad del producto: ";
				cin >> prod.Cantidad;
				while(prod.Cantidad < 0) {
					cout << "Valor inválido. Indique la cantidad del producto: ";
					cin >> prod.Cantidad;
				}
				// FECHA
				cout << "Indique la fecha de caducidad: ";
				prod.Caducicidad = pedirFecha();

				if(almacen.AnadirProducto(prod)) {
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
			if(almacen.EstaAbierto()) {
				cout << "Indique el código del producto a actualizar: ";
				pedirCadena(prod.CodProd);
				pos = almacen.BuscarProducto(prod.CodProd);
				if(pos == -1) {
					cout << "ERROR! No se ha encontrado un producto con el código indicado.\n";
				} else {
					prod = almacen.ObtenerProducto(pos);
					cout << "Producto encontrado.\n";
					cout << "CODIGO\tNOMBRE\t\t\t\t\tPRECIO\tCANTIDAD\tFECHA CADUCIDAD\t\tDESCRIPCION\n";
					MostrarProducto(prod);
					// Nombre
					if(confirmar("¿Desea modificar el nombre? (S/n): ")) {
						cout << "Indique el nombre nuevo: ";
						pedirCadena(prod.NombreProd);
						while(strcmp(prod.NombreProd, "") == 0) {
							cout << "Valor inválido. Indique el nombre nuevo: ";
							pedirCadena(prod.NombreProd);
						}
					}
					// Descripcion
					if(confirmar("¿Desea modificar la descripción? (S/n): ")) {
						cout << "Indique la descripción nueva: ";
						pedirCadena(prod.Descripcion);
					}
					// Código
					if(confirmar("¿Desea modificar el código? AVISO: Si modifica el codigo puede haber problemas con la tienda (S/n): ")) {
						cout << "Indique el código nuevo: ";
						pedirCadena(prod.CodProd);
						while(strcmp(prod.CodProd, "") == 0) {
							cout << "Valor inválido. Indique el código nuevo: ";
							pedirCadena(prod.CodProd);
						}
					}
					// Precio
					if(confirmar("¿Desea modificar el precio? (S/n): ")) {
						cout << "Indique el precio nuevo: ";
						cin >> prod.Precio;
						while(prod.Precio <= 0) {
							cout << "Valor inválido. Indique el precio nuevo: ";
							cin >> prod.Precio;
						}
					}
					// Cantidad
					if(confirmar("¿Desea modificar la cantidad? (S/n): ")) {
						cout << "Indique la cantidad nueva: ";
						cin >> prod.Cantidad;
						while(prod.Cantidad < 0) {
							cout << "Valor inválido. Indique la cantidad nueva: ";
							cin >> prod.Cantidad;
						}
					}
					// Caducidad
					if(confirmar("¿Desea modificar la fecha? (S/n): ")) {
						cout << "Indique la fecha nueva: ";
						prod.Caducicidad = pedirFecha();
					}

					if(almacen.ActualizarProducto(pos, prod)) {
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
			if(almacen.EstaAbierto()) {
				cout << "Indique el código del producto a consultar: ";
				pedirCadena(prod.CodProd);
				pos = almacen.BuscarProducto(prod.CodProd);
				if(pos == -1) {  // Valor irreal
					cout << "ERROR! No se ha encontrado un producto con el código indicado.\n";
				} else {
					cout << "Producto encontrado.\n";
                    printf("%s\t %-30s\t %s  %s\t %s\t %s\n", "CODIGO", "NOMBRE", "PRECIO", "CANTIDAD", "FECHA CADUCIDAD", "DESCRIPCION");
					MostrarProducto(almacen.ObtenerProducto(pos));
					cout << endl;
				}
			} else {
				cout << "No hay almacenes abiertos.\n";
			}
			pausa();
			break;
		case 8: /// Eliminar un producto
			if(almacen.EstaAbierto()) {
				cout << "Indique el código del producto a eliminar: ";
				pedirCadena(prod.CodProd);
				pos = almacen.BuscarProducto(prod.CodProd);
				if(pos == -1) {
					cout << "ERROR! No se ha encontrado un producto con el código indicado.\n";
				} else {
					cout << "Producto encontrado.\n";
					if(almacen.EliminarProducto(pos)) {
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
		case 9: /// Gestión de pedidos
			GestionPedidos(almacen);
			break;
		case 10: /// Gestión de envíos
			GestionEnvios(almacen);
			break;
		}
	} while(opc != 0);
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
		if(opc == 0) cout << "\n\tSeleccione una opción: ";
		else cout << "\n\tOpción incorrecta. Seleccione otra opción: ";
		cin >> opc;
	} while(opc < 0 || opc > 9);
	cout << endl;
	return opc;
}

void GestionTienda(Cadena nombre, TAlmacen &almacen, TTienda &tienda) {
	Cadena nFichero, direccion;
	int opc = 0;
	// Variables aux
	TEstante estante;
	TProducto prod;
	int pos = 0;
	int nprod = 0;
	tienda.DatosTienda(nombre, direccion);
	do {
		opc = MenuTienda(nombre);
		switch(opc) {
		case 1: /// Crear una tienda vacía
			if(!tienda.EstaAbierta()) {
				cout << "Indique el nombre de la tienda: ";
				pedirCadena(nombre);
				cout << "Indique la dirección de la tienda: ";
				pedirCadena(direccion);
				cout << "Indique el nombre del fichero: ";
				pedirCadena(nFichero);
				if(tienda.CrearTienda(nombre, direccion, nFichero)) {
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
			// Si ya habia una tienda abierta, esta se guardara (implementacion TTienda)
			if(tienda.AbrirTienda(nFichero)) {
				tienda.DatosTienda(nombre, direccion);
				cout << "Se ha abierto la tienda \"" << nombre << "\" con éxito.\n";
			} else {
				cout << "ERROR! No se ha podido abrir el fichero.\n";
			}
			pausa();
			break;
		case 3: /// Cerrar la tienda
			if(tienda.EstaAbierta()) {
				if(tienda.CerrarTienda()) {
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
			if(tienda.EstaAbierta()) {
				if(tienda.GuardarTienda()) {
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
			if(tienda.EstaAbierta()) {
				if(almacen.EstaAbierto()) {
					if(tienda.NoEstantes() > 0) {
						// Cabecera
						cout << "Listado de la tienda \"" << nombre << "\" localizado en " << direccion << endl;
						for(int i = 0; i < 22 + (int)strlen(nombre) + 16 + (int)strlen(direccion); i++) cout << "*";
						cout << endl;
						//cout << "NÚMERO DE ESTANTES: " << tienda.NoEstantes() << endl;
						cout << "CODIGO POSICION CAPACIDAD CODIGO PRODUCTO NOMBRE\t\t\t\tPRECIO\tNoPRODUCTOS\tVALOR TOTAL\n";
						for(int i = 0; i < tienda.NoEstantes(); i++) {
							estante = tienda.ObtenerEstante(i);
							pos = almacen.BuscarProducto(estante.CodProd);
							if(pos != -1) {
								MostrarEstante(estante, almacen.ObtenerProducto(pos));
							} else {
								cout << "ERROR! No se ha encontrado el producto con el código: " << estante.CodProd << endl;
							}
						}
					} else {
						cout << "No hay estantes en la tienda.\n";
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
			if(tienda.EstaAbierta()) {
				if(almacen.EstaAbierto()) {
					if(almacen.NProductos() > 0) {
						cout << "Indique el código del estante: ";
						cin >> estante.CodEstante;
						while(tienda.BuscarEstante(estante.CodEstante) != -1) {
							cout << "Ya existe un estante con ese código. Indique el código del estante: ";
							cin >> estante.CodEstante;
						}
						cout << "Indique la posición del estante (1- centrado, 2- arriba, 3- abajo): ";
						cin >> estante.Posicion;
						while(estante.Posicion < 1 || estante.Posicion > 3) {
							cout << "Posición inválida. Indique la posición de nuevo: ";
							cin >> estante.Posicion;
						}
						cout << "Indique la capacidad del estante: ";
						cin >> estante.Capacidad;
						while(estante.Capacidad < 1) {
							cout << "Posición inválida. Indique la capacidad del estante: ";
							cin >> estante.Capacidad;
						}
						cout << "Indique el código del producto del estante: ";
						pedirCadena(estante.CodProd);
						while(strcmp(estante.CodProd, "") == 0) {
							cout << "Valor inválido. Indique el código del producto del estante: ";
							pedirCadena(estante.CodProd);
						}
						pos = almacen.BuscarProducto(estante.CodProd);
						if(pos == -1) {
							cout << "ERROR! El código del producto indicado no se encuentra en el almacén.\n";
						} else {
							prod = almacen.ObtenerProducto(pos);
							if(prod.Cantidad == 0) {
								cout << "No quedan más productos de este tipo en el almacén.\n";
							} else {
								cout << "Indique el número de productos en el estante: ";
								cin >> estante.NoProductos;

								// Comprobamos que el no productos indicado sea menor o igual al que hay en el almacen
								if(prod.Cantidad >= estante.NoProductos) {
									prod.Cantidad -= estante.NoProductos;
								} else {
									// No hay productos suficientes
									estante.NoProductos = prod.Cantidad;
									prod.Cantidad = 0;
								}
								if(tienda.AnadirEstante(estante) && almacen.ActualizarProducto(pos, prod)) {
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
			if(tienda.EstaAbierta()) {
				if(almacen.EstaAbierto()) {
					cout << "Indique el código del estante a actualizar: ";
					cin >> estante.CodEstante;
					pos = tienda.BuscarEstante(estante.CodEstante);
					if(pos == -1) {
						cout << "ERROR! No se ha encontrado un estante con el código indicado.\n";
					} else {
						cout << "Estante encontrado.\n";
						cout << "CODIGO POSICION CAPACIDAD CODIGO PRODUCTO NOMBRE\t\tPRECIO\tNoPRODUCTOS\tVALOR TOTAL\n";
						estante = tienda.ObtenerEstante(pos);
						pos = almacen.BuscarProducto(estante.CodProd);
						if(pos == -1) {
							cout << "ERROR! No se ha encontrado el producto con el código: " << estante.CodProd << endl;
						} else {
							prod = almacen.ObtenerProducto(pos);
							MostrarEstante(estante, prod);
							if(confirmar("¿Desea actualizar el número de productos? (S/n): ")) {
								// Recogemos el producto
								nprod = estante.NoProductos;
								cout << "Indique el número de productos nuevo: ";
								cin >> estante.NoProductos;
								while(estante.NoProductos < 0) {
									cout << "Valor inválido. Indique el número de productos nuevo: ";
									cin >> estante.NoProductos;
								}
								/*
								 *  Si la cantidad es menor a la que había, se ha de devolver la
								 *  diferencia al almacén y si es mayor habrá que reponer esa
								 *  cantidad desde el almacén. En el caso que la cantidad a reponer
								 *  sea superior a la que hay en el almacén se repondrá solo la cantidad
								 *  existente en el almacén. En cada caso se indicará por pantalla que
								 *  cantidad se ha repuesto en el estante o devuelto al almacén.
								 */
								if(estante.NoProductos <= nprod) {
									prod.Cantidad += (nprod - estante.NoProductos);
								} else {
									estante.NoProductos = prod.Cantidad;
									prod.Cantidad = 0;
								}
								if(almacen.ActualizarProducto(almacen.BuscarProducto(estante.CodProd), prod)) {
									if(prod.Cantidad == 0) {
										cout << "Se ha repuesto solo la cantidad de productos que habia en el almacén.\n";
									} else {
										cout << "Se ha devuelto al almacén: " << (nprod - estante.NoProductos) << endl;
									}
									cout << "Número de productos en el estante: " << estante.NoProductos << endl;
								}
								if(tienda.ActualizarEstante(pos, estante)) {
									cout << "Se ha actualizado el estante con éxito.\n";
								}
							}
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
		case 8: /// Consultar un estante
			if(tienda.EstaAbierta()) {
				if(almacen.EstaAbierto()) {
					cout << "Indique el código del estante a consultar: ";
					cin >> estante.CodEstante;

					pos = tienda.BuscarEstante(estante.CodEstante);
					if(pos == -1) {
						cout << "ERROR! No se ha encontrado un estante con el código indicado.\n";
					} else {
						estante = tienda.ObtenerEstante(pos);
						pos = almacen.BuscarProducto(estante.CodProd);
						if(pos == -1) {
							cout << "ERROR! No se ha encontrado el producto con el código: " << estante.CodProd << endl;
						} else {
							cout << "CODIGO POSICION CAPACIDAD CODIGO PRODUCTO NOMBRE\t\t\tPRECIO\tNoPRODUCTOS\tVALOR TOTAL\n";
							MostrarEstante(estante, almacen.ObtenerProducto(pos));
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
		case 9: /// Eliminar un estante
			if(tienda.EstaAbierta()) {
				if(almacen.EstaAbierto()) {
					cout << "Indique el código del estante a eliminar: ";
					cin >> estante.CodEstante;
					pos = tienda.BuscarEstante(estante.CodEstante);
					if(pos == -1) {
						cout << "ERROR! No se ha encontrado un estante con el código indicado.\n";
					} else {
						estante = tienda.ObtenerEstante(pos);
						if(tienda.EliminarEstante(pos)) {
							// Devolver los productos al almacen
							pos = almacen.BuscarProducto(estante.CodProd);
							prod = almacen.ObtenerProducto(pos);
							prod.Cantidad += estante.NoProductos;
							if(almacen.ActualizarProducto(pos, prod)) {
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

void ReposicionProductos(TAlmacen &almacen, TTienda &tienda) {
	if(almacen.EstaAbierto() && tienda.EstaAbierta()) {
		TEstante estante;
		TProducto prod;
		Cadena cEstado;
		int pProd = -1;
		int estado;
		cout << "CÓDIGO CAPACIDAD NOMBRE\t\t\t\tESTADO REPUESTO OCUPACIÓN(%)\n";
		for(int i = 0; i < tienda.NoEstantes(); i++) {
			estante = tienda.ObtenerEstante(i);
			pProd = almacen.BuscarProducto(estante.CodProd);
			if(pProd != -1) {
				// Como el producto existe lo recogemos
				prod = almacen.ObtenerProducto(pProd);
				estado = tienda.ReponerEstante(i, prod);
				// Actualizamos los atributos del estante
				estante = tienda.ObtenerEstante(i);
				switch(estado) {
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
				if(almacen.ActualizarProducto(pProd, prod)) {
					cout << estante.CodEstante << "\t" << estante.Capacidad << "\t" << prod.NombreProd << "\t\t\t\t"
						 << cEstado << "\t" << ((float)estante.NoProductos / estante.Capacidad) * 100 << " %\n";
				} else {
					cout << "ERROR! No se ha podido actualizar el producto del estante en el almacén.\n";
				}
			} else {
				cout << "ERROR! No se ha encontrado el producto con el código: " << estante.CodProd << endl;
			}
		}
	} else {
		cout << "No está el almacén y la tienda abierta.\n";
	}
	pausa();
}

int MenuPrincipal(Cadena NombAlmacen, Cadena NombTienda) {
	int opc = 0;
	do {
		system("cls");
		if(strcmp(NombAlmacen, "") == 0) cout << "----- Menú Principal ----- " << NombTienda << endl;
		else if(strcmp(NombTienda, "") == 0) cout << "----- Menú Principal ----- " << NombAlmacen << endl;
		else if(strcmp(NombAlmacen, "") + strcmp(NombTienda, "") == 0) cout << "----- Menú Principal -----\n";
		else cout << "----- Menú Principal ----- " << NombAlmacen << ", " << NombTienda << endl;
		cout << "1.- Gestión de Almacenes.\n";
		cout << "2.- Gestión de la Tienda.\n";
		cout << "3.- Reposición de Productos en Tienda.\n";
		cout << "0.- Salir.\n";
		if(opc == 0) cout << "\n\tSeleccione una opción: ";
		else cout << "\n\tOpción incorrecta. Seleccione otra opción: ";
		cin >> opc;
	} while(opc < 0 || opc > 3);
	cout << endl;
	return opc;
}

int main() {
	system("title Práctica 2");
	setlocale(LC_CTYPE, "Spanish");

	TAlmacen almacen;
	TTienda tienda;

	// Variables auxiliares
	Cadena nAlmacen, nTienda;
	strcpy(nAlmacen, "");
	strcpy(nTienda, "");

	int opc = -1;
	while(opc != 0) {
		opc = MenuPrincipal(nAlmacen, nTienda);
		switch(opc) {
		case 1: /// Gestión del Almacenes
			GestionAlmacen(nAlmacen, almacen);
			break;
		case 2: /// Gestión de la Tienda
			GestionTienda(nTienda, almacen, tienda);
			break;
		case 3: /// Reposición de Productos en Tienda
			ReposicionProductos(almacen, tienda);
			break;
		}
	}
	return 0;
}
