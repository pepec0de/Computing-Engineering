#include "../include/TAlmacen.h"

/// Para las dudas buscar "DUDA"

using namespace std;

TAlmacen::TAlmacen() {
	//ctor
	strcpy(Nombre, "");
	strcpy(Direccion, "");
	NProduc = -1;
	// Inicializamos el fstream
	/* Flags:
	 * ios::binary -> modo archivo binario
	 * ios::out -> modo escritura
	 * ios::in -> modo lectura
	 * ios::trunc -> crear el archivo de nuevo
	 * ios::app -> a�adir contenido al final del archivo
	 */
}

//Destructor que cerrar� el almac�n en caso de que el usuario no lo haya hecho.
TAlmacen::~TAlmacen() {
	CerrarAlmacen();
}

// Devuelve los atributos nombre y direcci�n por par�metro.
void TAlmacen::DatosAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen) {
	strcpy(pNombAlmacen, Nombre);
	strcpy(pDirAlmacen, Direccion);
}

// Crea un fichero binario vac�o con el nombre pasado por par�metro. Crea la cabecera del fichero
// y lo deja abierto para su utilizaci�n. Devuelve true si se ha creado.
bool TAlmacen::CrearAlmacen(Cadena pNomFiche) {
	bool resultado = false;
	FicheProductos.open(pNomFiche, ios::binary | ios::out | ios::in | ios::trunc);
	if(!FicheProductos.fail()) {
		FicheProductos.seekp(0, ios::beg);
		NProduc = 0; // 0 productos para dejarlo abierto
		FicheProductos.write((char*) &NProduc, sizeof(int));
		FicheProductos.write((char*) Nombre, sizeof(Cadena));
		FicheProductos.write((char*) Direccion, sizeof(Cadena));
		resultado = true;
	}
	return resultado;
}

// Igual que el método anterior, pero actualizando los atributos nombre y dirección con los valores
// pasados por parámetro. Devuelve true si ha podido crear el fichero.
bool TAlmacen::CrearAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen, Cadena pNomFiche) {
	bool resultado = false;
	// Asignamos los valores antes para que la funcion CrearAlmacen ingrese en el archivo los datos
	strcpy(Nombre, pNombAlmacen);
	strcpy(Direccion, pDirAlmacen);
	if(CrearAlmacen(pNomFiche)) {
		resultado = true;
	} else {
		// Si falla reseteamos los atributos
		strcpy(Nombre, "");
		strcpy(Direccion, "");
		NProduc = -1;
	}
	return resultado;
}

// Abre un fichero y actualiza los atributos de la clase con los datos de cabecera del fichero y lo
// lo deja abierto. No se puede abrir un fichero si previamente el almac�n est� abierto. Devuelve true
// si ha podido abrir el fichero.
// 0 -> Numero Productos
// 1 -> Nombre
// 2 -> Direccion
bool TAlmacen::AbrirAlmacen(Cadena pNomFiche) {
	bool resultado = false;
	if(!EstaAbierto()) {
		FicheProductos.open(pNomFiche, ios::binary | ios::out | ios::in);
		if(!FicheProductos.fail()) {
			FicheProductos.seekg(0, ios::beg);
			// Si el almacen esta recien creado NProduc se quedara con el valor 0
			FicheProductos.read((char*) &NProduc, sizeof(int));
			FicheProductos.read((char*) Nombre, sizeof(Cadena));
			FicheProductos.read((char*) Direccion, sizeof(Cadena));
			resultado = true;
		}
	}
	return resultado;
}

// Cierra el fichero e inicializa los atributos a valores iniciales. Devuelve true si se ha cerrado el
// almac�n.
bool TAlmacen::CerrarAlmacen() {
	FicheProductos.close();
	FicheProductos.clear();
	strcpy(Nombre, "");
	strcpy(Direccion, "");
	NProduc = -1;
	return true;
}

// Devuelve true si el fichero est� abierto.
bool TAlmacen::EstaAbierto() {
	// Si esta abierto : NProduc >= 0
	return NProduc != -1;
}

// Devuelve el n�mero de productos.
int TAlmacen::NProductos() {
	return NProduc;
}

// Dado un c�digo de producto, devuelve la posici�n dentro del fichero donde se encuentra. Si no
// lo encuentra devuelve -1.
int TAlmacen::BuscarProducto(Cadena pCodProd) {
	int resultado = -1;
	if(EstaAbierto()) {
		// Tenemos que posicionarnos detras de la cabecera
		FicheProductos.seekg(sizeof(int) +2 * sizeof(Cadena), ios::beg);
		TProducto prodActual;
		int contProd = 0;
		bool encontrado = false;
		while(contProd < NProduc && !encontrado)  {
			FicheProductos.read((char*) &prodActual, sizeof(TProducto));
			if(strcmp(pCodProd, prodActual.CodProd) == 0) {
				encontrado = true;
			} else contProd++;
		}
		if(encontrado) {
			resultado = contProd;
		}
	}
	return resultado;
}

// Dado la posición devuelve el producto del fichero situado en dicha posici�n. Debe verificar
// previamente que la posición sea correcta. Si la posici�n no es correcta devolver� un producto cuyo
// c�digo tendr� el valor "NULO".
TProducto TAlmacen::ObtenerProducto(int pPos) {
	TProducto resultado;
	if(EstaAbierto()) {
		FicheProductos.seekg(sizeof(int) + sizeof(Cadena) * 2 + pPos * sizeof(TProducto), ios::beg);
		FicheProductos.read((char*) &resultado, sizeof(TProducto));
		if(FicheProductos.fail()) {
			// La posicion no es correcta, ya que el tipo de variable es distinto
			strcpy(resultado.CodProd, "NULO");
		}
	}
	return resultado;
}

// Dado un producto, lo busca en el fichero y si no lo encuentra lo a�ade al final del fichero.
// Devuelve true si se ha añadido el producto.
bool TAlmacen::AnadirProducto(TProducto pProduc) {
	bool resultado = false;
	if(EstaAbierto() && BuscarProducto(pProduc.CodProd) == -1) {
		FicheProductos.seekp(sizeof(int) + 2 * sizeof(Cadena) + NProduc * sizeof(TProducto), ios::beg);
		FicheProductos.write((char*) &pProduc, sizeof(TProducto));
		NProduc++;
		FicheProductos.seekp(0, ios::beg);
		FicheProductos.write((char*) &NProduc, sizeof(int));
		resultado = true;
	}
	return resultado;
}

// Dada la posici�n de un producto en el fichero lo actualiza con la informaci�n del producto pasado
// por par�metro. Devuelve true si se ha actualizado el producto. Se debe verificar previamente que la
// posici�n sea correcta.
bool TAlmacen::ActualizarProducto(int pPos, TProducto pProduc) {
	bool resultado = false;
	if(EstaAbierto()) {
		TProducto aux;
		FicheProductos.seekg(sizeof(int) + 2 * sizeof(Cadena) + pPos * sizeof(TProducto), ios::beg);
		FicheProductos.read((char*) &aux, sizeof(TProducto));
		// Si la posicion es incorrecta estos valores seran valores basuara
		if(aux.Precio > 0 && aux.Cantidad >= 0) {
			FicheProductos.seekp(sizeof(int) + 2 * sizeof(Cadena) + pPos * sizeof(TProducto), ios::beg);
			FicheProductos.write((char*) &pProduc, sizeof(TProducto));
			resultado = true;
		}
	}
	return resultado;
}

// Dado la posici�n de un producto en el fichero lo borra. Devuelve true si se ha podido borrar. Se
// debe verificar que la posici�n sea correcta.
bool TAlmacen::EliminarProducto(int pPos) {
	bool resultado = false;
	if(EstaAbierto()) {
		// Comprobamos que la posicion sea correcta
		TProducto prodEliminar;
		FicheProductos.seekg(sizeof(int) + 2 * sizeof(Cadena) + pPos * sizeof(TProducto), ios::beg);
		FicheProductos.read((char*) &prodEliminar, sizeof(TProducto));
		// Si la posicion es incorrecta esos valores seran basura
		if(prodEliminar.Cantidad > 0 && prodEliminar.Precio > 0) {
			if(!FicheProductos.fail()) {
				TProducto prodActual;
				NProduc--;
				if(pPos < NProduc) {  // Si el producto esta en la ultima posicion no ejecutamos el bucle ya que se queda como basura
					for(int i = pPos; i < NProduc; i++) {
						// Sustituimos el producto que se quiere eliminar por el que esta justo despues de este
						FicheProductos.seekg(sizeof(int) + 2 * sizeof(Cadena) + (i + 1)*sizeof(TProducto), ios::beg);
						FicheProductos.read((char*) &prodActual, sizeof(TProducto)); // leemos el siguiente producto
						FicheProductos.seekp(sizeof(int) + 2 * sizeof(Cadena) + i * sizeof(TProducto), ios::beg); // escritura
						FicheProductos.write((char*) &prodActual, sizeof(TProducto));
					}
				}
				// Actualizamos el valor de productos
				FicheProductos.seekp(0, ios::beg);
				FicheProductos.write((char*) &NProduc, sizeof(int));
				resultado = true;
			}
		}
	}
	return resultado;
}

/// ADD PRACTICA 2
//Método que carga la lista de envíos a partir del nombre del fichero que se le pasa por parámetro.
//El fichero tiene una sucesión de elementos de tipo TPedido
bool TAlmacen::CargarListaEnvios(Cadena Nomf) {
	bool result = false;
	ifstream ficheEnvios;
	ficheEnvios.open(Nomf, ios::binary);

	if(!ficheEnvios.fail()) {
		// Comprobamos si la lista ya tenia datos
		while(!Envios.esvacia()) Envios.eliminarDch();

		TPedido pedido;
		ficheEnvios.read((char*) &pedido, sizeof(TPedido));
		while(!ficheEnvios.eof()) {
			Envios.anadirDch(pedido);
			ficheEnvios.read((char*) &pedido, sizeof(TPedido));
		}
		result = true;
	}
	ficheEnvios.close();
	return result;
}

//Método que carga la cola de pedidos a partir del nombre del fichero que se le pasa por parámetro.
//El fichero tiene una sucesión de elementos de tipo TPedido
bool TAlmacen::CargarColaPedidos(Cadena Nomf) {
	bool result = false;
	ifstream fichePedidos;
	fichePedidos.open(Nomf, ios::binary);

	if(!fichePedidos.fail()) {
		// Comprobamos si la lista ya tenia datos
		while(!Pedidos.esvacia()) Pedidos.desencolar();

		TPedido pedido;
		fichePedidos.read((char*) &pedido, sizeof(TPedido));
		while(!fichePedidos.eof()) {
			Pedidos.encolar(pedido);
			fichePedidos.read((char*) &pedido, sizeof(TPedido));
		}
		result = true;
	}
	fichePedidos.close();
	return result;
}

//Añadirá un nuevo pedido a la cola de pedidos
void TAlmacen::AnadirPedido(TPedido p) {
	Pedidos.encolar(p);
}

//Método que atiende los pedidos del producto en cuestión pendientes de suministrar con la cantidad
//comprada por el almacén, los incorpora a la lista de Envíos, eliminando de la cola de pedidos los
//pedidos atendidos.
//Si algún pedido es atendido parcialmente por que se acabe el producto, la cola se modificará
//modificando y dejando pendiente la cantidad correspondiente, introduciendo en la lista de Envios
//la cantidad que se puede suministrar.
//Si el producto comprado excede de la cantidad pendiente de servir en los pedidos, la cantidad
//sobrante, entra en el Almacén.
// TODO: COMPROBAR SI FUNCIONA
bool TAlmacen::AtenderPedidos(Cadena CodProd, int cantidadcomprada) {
	bool exito = false;
    // Comprobar que el producto se encuentre en el almacén
    int pos = BuscarProducto(CodProd);
    if (pos != -1) {
        // Añadimos la cantidad comprada al almacén
        TProducto prod = ObtenerProducto(pos);
        prod.Cantidad += cantidadcomprada;

        // Buscamos el pedido en la cola de Pedidos y vemos si lo podemos atender
        TPedido pedido, envio;
        for (int i = 0; i < Pedidos.longitud(); i++) {
            bool encolarPedido = true;
            pedido = Pedidos.primero();
            if (strcmp(pedido.CodProd, CodProd) == 0) {
                envio = pedido;
                if(pedido.CantidadPed > prod.Cantidad) {
                    // Añadimos a envios
                    envio.CantidadPed = prod.Cantidad;
                    if (prod.Cantidad != 0) InsertarEnvios(envio);

                    // Modificamos pedidos
                    pedido.CantidadPed -= prod.Cantidad;
                    prod.Cantidad = 0;

                    // El pedido en la cola se cambiara al final del bucle for
                    encolarPedido = true;
                } else if(pedido.CantidadPed <= prod.Cantidad) {
                    // Restamos la cantidad en el almacén
                    prod.Cantidad -= pedido.CantidadPed;

                    envio.CantidadPed = pedido.CantidadPed;
                    InsertarEnvios(envio);
                    // Borramos el pedido de Pedidos
                    encolarPedido = false;
                }
            }
            Pedidos.desencolar();
            if (encolarPedido) Pedidos.encolar(pedido);
        }
        exito = ActualizarProducto(pos, prod);
    } else {
        cout << "ERROR! No se ha encontrado en el almacén el producto con el codigo: " << CodProd << ".\n";
    }
	return exito;
}

//Muestra el contenido completo, con todos los datos de los productos leídos del almacén, de la cola
//si CodProd es '' o muestra los pedidos del Codprod pasado con todos sus datos del almacén.
//pasado por parámetro
void TAlmacen::ListarPedidosCompleto(Cadena CodProd) {
	if(Pedidos.longitud() > 0) {
		TPedido pedido;
		TProducto prod;
		int pos;
		if(strcmp(CodProd, "") == 0) {
			// Cabecera
			cout << "NOMBRE TIENDA\tCODIGO PRODUCTO CANTIDAD PEDIDA\tNOMBRE\t\tPRECIO CANTIDAD FECHA CADUCIDAD\tDESCRIPCION\n";
			for (int i = 0; i < Pedidos.longitud(); i++) {
				pedido = Pedidos.primero();
				pos = BuscarProducto(pedido.CodProd);
				if(pos != -1) {
					prod = ObtenerProducto(pos);
					printf("%s\t\t%-15s %d\t%s\t%5.2f  %2d %8d/%02d/%d\t%s\n", pedido.Nomtienda, pedido.CodProd, pedido.CantidadPed,
						prod.NombreProd, prod.Precio, prod.Cantidad, prod.Caducicidad.Dia, prod.Caducicidad.Mes, prod.Caducicidad.Anyo, prod.Descripcion);
				} else {
				    printf("%s\t\t%-15s %d\t%s\t%5.2f  %2d %8d/%02d/%d\t      %s\n", pedido.Nomtienda, pedido.CodProd, pedido.CantidadPed, "??????????????", 0.0, 0, 0, 0, 0, "??????????????");
					//cout << "ERROR! No se ha encontrado en el almacén el producto con el codigo: " << pedido.CodProd << ".\n";
				}
				Pedidos.desencolar();
				Pedidos.encolar(pedido);
			}
		} else {
			// Muestro el codigo producto dado
			bool encontrado = false;
			for (int i = 0; i < Pedidos.longitud(); i++) {
                pedido = Pedidos.primero();
				if(strcmp(pedido.CodProd, CodProd) == 0) {
                    pos = BuscarProducto(CodProd);
					if(pos != -1) {
                        // Cabecera
                        if (!encontrado) cout << "NOMBRE TIENDA\t CANTIDAD PEDIDA\tNOMBRE\t\tPRECIO CANTIDAD FECHA CADUCIDAD\tDESCRIPCION\n";
                        encontrado = true;
						prod = ObtenerProducto(pos);
						printf("%s\t\t %d\t%s\t%5.2f  %2d %8d/%02d/%d\t%s\n", pedido.Nomtienda, pedido.CantidadPed, prod.NombreProd,
                            prod.Precio, prod.Cantidad, prod.Caducicidad.Dia, prod.Caducicidad.Mes, prod.Caducicidad.Anyo, prod.Descripcion);
					} else {
						printf("%s\t\t %d\t%s\t%5.2f  %2d %8d/%02d/%d\t      %s\n", pedido.Nomtienda, pedido.CantidadPed, "??????????????", 0.0, 0, 0, 0, 0, "??????????????");
					}
				}
				Pedidos.desencolar();
				Pedidos.encolar(pedido);
			}
			if(!encontrado) cout << "\nERROR! No se ha encontrado el código de producto dado en los pedidos.\n";
		}
	} else {
		cout << "No hay pedidos.\n";
	}
}

//Muestra la cantidad pendiente de cada tipo de producto si CodProd es '' o del producto concreto
//que se pase por parámetro

// TODO: COMPROBAR SI FUNCIONA
/*
    Por cada pedido de la cola ¿cuantas tiendas han pedido? PONER LA SUMA TOTAL
*/
void TAlmacen::ListarCantidadesPendientes(Cadena CodProd) {
	if (Pedidos.longitud() > 0) {
	    Cola copiaPedidos;
	    // Copiamos la cola
	    for (int i = 0; i < Pedidos.longitud(); i++) {
            TPedido p = Pedidos.primero();
            copiaPedidos.encolar(p);
            Pedidos.desencolar();
            Pedidos.encolar(p);
	    }

	    TPedido pedido, pedidoCurr;
	    int total;
        if(strcmp(CodProd, "") == 0) {
            cout << "CODIGO PRODUCTO CANTIDAD PENDIENTE\n";
            for (int i = 0; i < Pedidos.longitud(); i++) {
                pedido = Pedidos.primero();
                total = 0;
                for (int j = 0; j < copiaPedidos.longitud(); j++) {
                    pedidoCurr = copiaPedidos.primero();
                    if (strcmp(pedido.Nomtienda, pedidoCurr.Nomtienda) == 0) {
                        total += pedidoCurr.CantidadPed;
                    }
                    copiaPedidos.desencolar();
                    copiaPedidos.encolar(pedidoCurr);
                }
                printf("%s\t %d\n", pedido.CodProd, total);
                Pedidos.desencolar();
                Pedidos.encolar(pedido);
            }
        } else {
            bool encontrado = false;
            int i = 0;
            while(i < Pedidos.longitud() && !encontrado) {
                pedido = Pedidos.primero();
                if (strcmp(CodProd, pedido.CodProd) == 0) {
                    if (!encontrado) cout << "CANTIDAD PENDIENTE\n";
                    encontrado = true;
                    total = 0;
                    for (int j = 0; j < copiaPedidos.longitud(); j++) {
                        pedidoCurr = copiaPedidos.primero();
                        if (strcmp(pedido.Nomtienda, pedidoCurr.Nomtienda) == 0) {
                            total += pedidoCurr.CantidadPed;
                        }
                        copiaPedidos.desencolar();
                        copiaPedidos.encolar(pedidoCurr);
                    }
                    printf("%s\t %d\n", pedido.CodProd, total);
                    Pedidos.desencolar();
                    Pedidos.encolar(pedido);
                }
                i++;
            }
            if(!encontrado) cout << "No se ha encontrado ningún producto con ese código con cantidades pendientes.\n";
        }
	} else {
        cout << "No hay pedidos.\n";
	}
}

//Se encarga de meter en la lista de envíos, de forma ordenada, por nombre del fichero de tienda, el
//pedido a enviar
bool TAlmacen::InsertarEnvios(TPedido p) {
	// TODO : COMPROBAR SI FUNCIONA
	bool encontrado = false;
    int i = 1;
    while (i <= Envios.longitud() && !encontrado) {
		if(strcmp(Envios.observar(i).Nomtienda, p.Nomtienda) == 1) {
			// p.Nomtienda es alfabeticamente mayor que primero().Nomtienda
			Envios.insertar(i, p);
			encontrado = true;
		}
        i++;
	}
	return encontrado;
}

//Se encarga de sacar de la lista los envíos que tienen por destino la tienda que se le pasa por
//parámetro mostrando por pantalla los envíos que van en el camión
bool TAlmacen::SalidaCamionTienda(Cadena NomTienda) {
	bool result = false;
	ListarListaEnvios(NomTienda);
	for(int i = 1; i <= Envios.longitud(); i++) {
		if(strcmp(Envios.observar(i).Nomtienda, NomTienda) == 0) {
            result = true;
            Envios.eliminar(i);
		}
	}
	return result;
}

// Si recibe Nomtienda a '' muestra por pantalla todo el contenido de la lista de envíos.
// Si se le pasa el nombre de una tienda muestra por pantalla los envíos a dicha tienda
void TAlmacen::ListarListaEnvios(Cadena Nomtienda) {
	if(Envios.longitud() > 0) {
		TProducto prod;
		TPedido pedido;
		int pos;
		if(strcmp(Nomtienda, "") == 0) {
			cout << "NOMBRE TIENDA\tCODIGO PRODUCTO CANTIDAD PEDIDA\tNOMBRE\t\tPRECIO CANTIDAD FECHA CADUCIDAD\tDESCRIPCION\n";
			for(int i = 1; i <= Envios.longitud(); i++) {
				pedido = Envios.observar(i);
				pos = BuscarProducto(pedido.CodProd);
				if(pos != -1) {
					prod = ObtenerProducto(pos);
					printf("%s\t\t%-15s %d\t%s\t%5.2f  %2d %8d/%02d/%d\t%s\n", pedido.Nomtienda, pedido.CodProd, pedido.CantidadPed,
						   prod.NombreProd, prod.Precio, prod.Cantidad, prod.Caducicidad.Dia, prod.Caducicidad.Mes, prod.Caducicidad.Anyo, prod.Descripcion);
				} else {
					printf("%s\t\t%-15s %d\t%s\t%5.2f  %2d %8d/%02d/%d\t      %s\n", pedido.Nomtienda, pedido.CodProd, pedido.CantidadPed, "??????????????", 0.0, 0, 0, 0, 0, "??????????????");
				}
			}
		} else {
			bool encontrado = false;
			for(int i = 1; i <= Envios.longitud(); i++) {
				pedido = Envios.observar(i);
				if(strcmp(pedido.Nomtienda, Nomtienda) == 0) {
					pos = BuscarProducto(pedido.CodProd);
					if(pos != -1) {
                        // Cabecera
                        if (!encontrado) cout << "CODIGO PRODUCTO CANTIDAD PEDIDA\tNOMBRE\t\tPRECIO CANTIDAD FECHA CADUCIDAD\tDESCRIPCION\n";
                        encontrado = true;
						prod = ObtenerProducto(pos);
						printf("%-15s %d\t%s\t%5.2f  %2d %8d/%02d/%d\t%s\n", pedido.CodProd, pedido.CantidadPed, prod.NombreProd,
                            prod.Precio, prod.Cantidad, prod.Caducicidad.Dia, prod.Caducicidad.Mes, prod.Caducicidad.Anyo, prod.Descripcion);
					} else {
						printf("%-15s %d\t%s\t%5.2f  %2d %8d/%02d/%d\t      %s\n", pedido.CodProd, pedido.CantidadPed, "??????????????", 0.0, 0, 0, 0, 0, "??????????????");
					}
				}
			}
			if(!encontrado) cout << "No se ha encontrado ningún envios a la tienda dada.\n";
		}
	}
}

//Método que vuelca en el fichero nomf la cola de pedidos
bool TAlmacen::SalvarColaPedidos(Cadena Nomf) {
	bool result = false;
	ofstream fichero;
	fichero.open(Nomf, ios::binary);
	if(!fichero.fail()) {
		TPedido pedido;
		for (int i = 0; i < Pedidos.longitud(); i++) {
			pedido = Pedidos.primero();
			fichero.write((char*) &pedido, sizeof(TPedido));
			Pedidos.desencolar();
			Pedidos.encolar(pedido);
		}
		result = true;
	}
	fichero.close();
	return result;
}

//Método que vuelca en el fichero nomf la lista de envíos
bool TAlmacen::SalvarListaEnvios(Cadena Nomf) {
	bool result = false;
	ofstream fichero;
	fichero.open(Nomf, ios::binary);
	if(!fichero.fail()) {
		TPedido pedido;
		for(int i = 1; i <= Envios.longitud(); i++) {
			pedido = Envios.observar(i);
			fichero.write((char*) &pedido, sizeof(TPedido));
		}
		result = true;
	}
	fichero.close();
	return result;
}
