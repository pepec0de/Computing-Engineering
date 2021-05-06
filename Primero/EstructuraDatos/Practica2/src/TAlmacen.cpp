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
    if (!FicheProductos.fail()) {
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
    if (CrearAlmacen(pNomFiche)) {
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
    if (!EstaAbierto()) {
        FicheProductos.open(pNomFiche, ios::binary | ios::out | ios::in);
        if (!FicheProductos.fail()) {
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
    if (EstaAbierto()) {
        // Tenemos que posicionarnos detras de la cabecera
        FicheProductos.seekg(sizeof(int)+2*sizeof(Cadena), ios::beg);
        TProducto prodActual;
        int contProd = 0;
        bool encontrado = false;
        while(contProd < NProduc && !encontrado)  {
            FicheProductos.read((char*) &prodActual, sizeof(TProducto));
            if (strcmp(pCodProd, prodActual.CodProd) == 0) {
                encontrado = true;
            } else contProd++;
        }
        if (encontrado) {
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
    if (EstaAbierto()) {
        FicheProductos.seekg(sizeof(int)+sizeof(Cadena)*2 + pPos*sizeof(TProducto), ios::beg);
        FicheProductos.read((char*) &resultado, sizeof(TProducto));
        if (FicheProductos.fail()) {
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
    if (EstaAbierto() && BuscarProducto(pProduc.CodProd) == -1) {
        FicheProductos.seekp(sizeof(int) + 2*sizeof(Cadena) + NProduc*sizeof(TProducto), ios::beg);
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
    if (EstaAbierto()) {
        TProducto aux;
        FicheProductos.seekg(sizeof(int) + 2*sizeof(Cadena) + pPos*sizeof(TProducto), ios::beg);
        FicheProductos.read((char*) &aux, sizeof(TProducto));
        // Si la posicion es incorrecta estos valores seran valores basuara
        if (aux.Precio > 0 && aux.Cantidad >= 0) {
            FicheProductos.seekp(sizeof(int) + 2*sizeof(Cadena) + pPos*sizeof(TProducto), ios::beg);
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
    if (EstaAbierto()) {
        // Comprobamos que la posicion sea correcta
        TProducto prodEliminar;
        FicheProductos.seekg(sizeof(int) + 2*sizeof(Cadena) + pPos*sizeof(TProducto), ios::beg);
        FicheProductos.read((char*) &prodEliminar, sizeof(TProducto));
        // Si la posicion es incorrecta esos valores seran basura
        if (prodEliminar.Cantidad > 0 && prodEliminar.Precio > 0) {
            if (!FicheProductos.fail()) {
                TProducto prodActual;
                NProduc--;
                if (pPos < NProduc) { // Si el producto esta en la ultima posicion no ejecutamos el bucle ya que se queda como basura
                    for (int i = pPos; i < NProduc; i++) {
                        // Sustituimos el producto que se quiere eliminar por el que esta justo despues de este
                        FicheProductos.seekg(sizeof(int)+ 2*sizeof(Cadena) + (i + 1)*sizeof(TProducto), ios::beg);
                        FicheProductos.read((char*) &prodActual, sizeof(TProducto)); // leemos el siguiente producto
                        FicheProductos.seekp(sizeof(int)+ 2*sizeof(Cadena) + i*sizeof(TProducto), ios::beg); // escritura
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

    if (!ficheEnvios.fail()) {
        // Comprobamos si la lista ya tenia datos
        while (!Envios.esvacia()) Envios.eliminarDch();

        TPedido pedido;
        ficheEnvios.read((char*) &pedido, sizeof(TPedido));
        while (!ficheEnvios.eof()) {
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

    if (!fichePedidos.fail()) {
        // Comprobamos si la lista ya tenia datos
        while(!Pedidos.esvacia()) Pedidos.desencolar();

        TPedido pedido;
        fichePedidos.read((char*) &pedido, sizeof(TPedido));
        while (!fichePedidos.eof()) {
            Pedidos.encolar(pedido);
            fichePedidos.read((char*) &pedido, sizeof(TPedido));
        }
        result = true;
    }
    fichePedidos.close();
    return result;
}

//Añadirá un nuevo pedido a la cola de pedidos
void TAlmacen::AnadirPedido (TPedido p) {
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
bool TAlmacen::AtenderPedidos(Cadena CodProd, int cantidadcomprada) {
    bool result = false;
    TPedido pedido;

    return result;
}

//Muestra el contenido completo, con todos los datos de los productos leídos del almacén, de la cola
//si CodProd es '' o muestra los pedidos del Codprod pasado con todos sus datos del almacén.
//pasado por parámetro
void TAlmacen::ListarPedidosCompleto(Cadena CodProd) {
    Cola copiaPedidos = Pedidos;
    if (strcmp(CodProd, "")) {
        // Cabecera
        cout << "NOMBRE TIENDA\tCODIGO PRODUCTO\tCANTIDAD\tNOMBRE\t\t\t\t\tPRECIO\tCANTIDAD\tFECHA CADUCIDAD\t\tDESCRIPCION\n";
        TPedido pedido;
        int pos = -1;
        TProducto prod;
        while(!copiaPedidos.esvacia()) {
            pos = BuscarProducto(copiaPedidos.primero().CodProd);
            if (pos != -1) {
                prod = ObtenerProducto(pos);
                pedido = copiaPedidos.primero();
                cout << pedido.Nomtienda << "\t"
                    << pedido.CodProd << "\t"
                    << pedido.CantidadPed << "\t"
                    << prod.NombreProd << "\t" << prod.Precio << "\t"
                    << prod.Cantidad << "\t" << prod.Caducicidad.Dia << "/" << prod.Caducicidad.Mes << "/" << prod.Caducicidad.Anyo
                    << "\t" << prod.Descripcion << endl;
            } else {
                cout << "ERROR! No se ha encontrado un producto con ese código.\n";
            }
            copiaPedidos.desencolar();
        }
    } else {
        // Muestro el codigo producto dado
        while(!copiaPedidos.esvacia() && !strcmp(copiaPedidos.primero().CodProd, CodProd))
            copiaPedidos.desencolar();
        if (!copiaPedidos.esvacia()) {
            int pos = BuscarProducto(copiaPedidos.primero().CodProd);
            if (pos != -1) {
                TProducto prod = ObtenerProducto(pos);
                cout << "NOMBRE TIENDA\tCODIGO PRODUCTO\tCANTIDAD\tNOMBRE\t\t\t\t\tPRECIO\tCANTIDAD\tFECHA CADUCIDAD\t\tDESCRIPCION\n"
                    << copiaPedidos.primero().Nomtienda << "\t"
                    << copiaPedidos.primero().CodProd << "\t"
                    << copiaPedidos.primero().CantidadPed << "\t"
                    << prod.NombreProd << "\t" << prod.Precio << "\t"
                    << prod.Cantidad << "\t" << prod.Caducicidad.Dia << "/" << prod.Caducicidad.Mes << "/" << prod.Caducicidad.Anyo
                    << "\t" << prod.Descripcion << endl;
            } else {
                cout << "ERROR! No se ha encontrado un producto con ese código.\n";
            }
        }
    }
}

//Muestra la cantidad pendiente de cada tipo de producto si CodProd es '' o del producto concreto
//que se pase por parámetro
void TAlmacen::ListarCantidadesPendientes(Cadena CodProd) {
    if(strcmp(CodProd, "")) {

    } else {

    }
}

//Se encarga de meter en la lista de envíos, de forma ordenada, por nombre del fichero de tienda, el
//pedido a enviar
bool TAlmacen::InsertarEnvios(TPedido p) {
    bool result = true;
    Envios.anadirDch(p);
    return result;
}

//Se encarga de sacar de la lista los envíos que tienen por destino la tienda que se le pasa por
//parámetro mostrando por pantalla los envíos que van en el camión
bool TAlmacen::SalidaCamionTienda(Cadena NomTienda) {
    bool result = false;

    return result;
}

// Si recibe Nomtienda a '' muestra por pantalla todo el contenido de la lista de envíos.
// Si se le pasa el nombre de una tienda muestra por pantalla los envíos a dicha tienda
void TAlmacen::ListarListaEnvios(Cadena Nomtienda) {
    TProducto prod;
    int pos = -1;
    if (strcmp(Nomtienda, "")) {
        cout << "NOMBRE TIENDA CODIGO PRODUCTO CANTIDAD PEDIDA NOMBRE PRODUCTO\t\t\tPRECIO CANTIDAD FECHA CADUCIDAD DESCRIPCION\n";
        for (int i = 1; i <= Envios.longitud(); i++) {
            pos = BuscarProducto(Envios.observar(i).CodProd);
            if (pos != -1) {
                prod = ObtenerProducto(pos);
                cout << Envios.observar(i).CodProd << "\t" << Envios.observar(i).CantidadPed << "\t"
                 << prod.NombreProd << "\t" << prod.Precio << "\t" << prod.Cantidad << "\t"
                 << prod.Caducicidad.Dia << "/" << prod.Caducicidad.Mes << "/" << prod.Caducicidad.Anyo << "\t" << prod.Descripcion << endl;
            } else {
                cout << "ERROR! No se ha encontrado un producto con el código del pedido.\n";
            }
        }
    } else {
        int i = 1;
        cout << "Nombre de la tienda a mostrar: " << Nomtienda << endl;
        cout << "CODIGO PRODUCTO CANTIDAD PEDIDA NOMBRE PRODUCTO\t\t\tPRECIO CANTIDAD FECHA CADUCIDAD DESCRIPCION\n";
        while (i <= Envios.longitud() && strcmp(Envios.observar(i).Nomtienda, Nomtienda)) {
            pos = BuscarProducto(Envios.observar(i).CodProd);
            if (pos != -1) {
                prod = ObtenerProducto(pos);
                cout << Envios.observar(i).CodProd << "\t" << Envios.observar(i).CantidadPed << "\t"
                 << prod.NombreProd << "\t" << prod.Precio << "\t" << prod.Cantidad << "\t"
                 << prod.Caducicidad.Dia << "/" << prod.Caducicidad.Mes << "/" << prod.Caducicidad.Anyo << "\t" << prod.Descripcion << endl;
            } else {
                cout << "ERROR! No se ha encontrado un producto con el código del pedido.\n";
            }
        }
    }
}

//Método que vuelca en el fichero nomf la cola de pedidos
bool TAlmacen::SalvarColaPedidos(Cadena Nomf) {
    bool result = false;
    ofstream fichePedidos;
    fichePedidos.open(Nomf, ios::binary);
    if (!fichePedidos.fail()) {
        TPedido pedido;
        while (!Pedidos.esvacia()) {
            pedido = Pedidos.primero();
            fichePedidos.write((char*) &pedido, sizeof(TPedido));
        }
        result = true;
    }
    fichePedidos.close();
    return result;
}

//Método que vuelca en el fichero nomf la lista de envíos
bool TAlmacen::SalvarListaEnvios(Cadena Nomf) {
    bool result = false;

    return result;
}
