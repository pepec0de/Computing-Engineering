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
     * ios::app -> añadir contenido al final del archivo
     */
}

//Destructor que cerrará el almacén en caso de que el usuario no lo haya hecho.
TAlmacen::~TAlmacen() {
    CerrarAlmacen();
}

// Devuelve los atributos nombre y dirección por parámetro.
void TAlmacen::DatosAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen) {
    strcpy(pNombAlmacen, Nombre);
    strcpy(pDirAlmacen, Direccion);
}

// Crea un fichero binario vacío con el nombre pasado por parámetro. Crea la cabecera del fichero
// y lo deja abierto para su utilización. Devuelve true si se ha creado.
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
// lo deja abierto. No se puede abrir un fichero si previamente el almacén está abierto. Devuelve true
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
// almacén.
bool TAlmacen::CerrarAlmacen() {
    if (EstaAbierto()) FicheProductos.close();
    strcpy(Nombre, "");
    strcpy(Direccion, "");
    NProduc = -1;
    return true;
}

// Devuelve true si el fichero está abierto.
bool TAlmacen::EstaAbierto() {
    // Si esta abierto : NProduc >= 0
    return NProduc != -1;
}

// Devuelve el número de productos.
int TAlmacen::NProductos() {
    return NProduc;
}

// Dado un código de producto, devuelve la posición dentro del fichero donde se encuentra. Si no
// lo encuentra devuelve -1. POSICION DE PRODUCTO -> NO DEL BYTE DEL FICHERO
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

// Dado la posición devuelve el producto del fichero situado en dicha posición. Debe verificar
// previamente que la posición sea correcta. Si la posición no es correcta devolverá un producto cuyo
// código tendrá el valor “NULO”.
TProducto TAlmacen::ObtenerProducto(int pPos) {
    TProducto resultado;
    if (EstaAbierto()) {
        FicheProductos.seekg(pPos, ios::beg);
        FicheProductos.read((char*) &resultado, sizeof(TProducto));
        if (FicheProductos.fail()) {
            // La posicion no es correcta, ya que el tipo de variable es distinto
            strcpy(resultado.CodProd, "NULO");
        }
    }
    return resultado;
}

// Dado un producto, lo busca en el fichero y si no lo encuentra lo añade al final del fichero.
// Devuelve true si se ha añadido el producto.
bool TAlmacen::AnadirProducto(TProducto pProduc) {
    bool resultado = false;
    if (EstaAbierto() && BuscarProducto(pProduc.CodProd) == -1) {
        FicheProductos.seekp(0, ios::end);
        FicheProductos.write((char*) &pProduc, sizeof(TProducto));
        NProduc++;
        FicheProductos.seekp(0, ios::beg);
        FicheProductos.write((char*) &NProduc, sizeof(int));
        resultado = true;
    }
    return resultado;
}

// Dada la posición de un producto en el fichero lo actualiza con la información del producto pasado
// por parámetro. Devuelve true si se ha actualizado el producto. Se debe verificar previamente que la
// posición sea correcta.
bool TAlmacen::ActualizarProducto(int pPos, TProducto pProduc) {
    bool resultado = false;
    if (EstaAbierto()) {
        TProducto aux;
        FicheProductos.seekg(pPos, ios::beg);
        FicheProductos.read((char*) &aux, sizeof(TProducto));
        if (FicheProductos.good()) {
            FicheProductos.seekp(pPos, ios::beg);
            FicheProductos.write((char*) &pProduc, sizeof(TProducto));
            resultado = true;
        }
    }
    return resultado;
}

// Dado la posición de un producto en el fichero lo borra. Devuelve true si se ha podido borrar. Se
// debe verificar que la posición sea correcta.
bool TAlmacen::EliminarProducto(int pPos) {
    bool resultado = false;
    if (EstaAbierto()) {
        // Comprobamos que la posicion sea correcta
        TProducto prodEliminar;
        FicheProductos.seekg(sizeof(int)+ 2*sizeof(Cadena) + pPos*sizeof(TProducto), ios::beg);
        FicheProductos.read((char*) &prodEliminar, sizeof(TProducto));
        if (!FicheProductos.fail()) {
            TProducto prodActual;
            // tellg() = Estamos detras del producto a eliminar
            /// TODO: COMPROBAR
            if (pPos < NProduc-1) { // Si el producto esta en la ultima posicion no ejecutamos el bucle ya que se queda como basura
                for (int i = pPos; i < NProduc; i++) {
                    // Sustituimos el producto que se quiere eliminar por el que esta justo despues de este
                    FicheProductos.seekg(sizeof(int )+ 2*sizeof(Cadena) + (pPos + 1)*sizeof(TProducto), ios::beg);
                    FicheProductos.read((char*) &prodActual, sizeof(TProducto)); // leemos el siguiente producto
                    FicheProductos.seekp(sizeof(int)+ 2*sizeof(Cadena) + pPos*sizeof(TProducto), ios::beg); // escritura
                    FicheProductos.write((char*) &prodActual, sizeof(TProducto));
                }
            }
            // Actualizamos el valor de productos
            NProduc--;
            FicheProductos.seekp(0, ios::beg);
            FicheProductos.write((char*) &NProduc, sizeof(int));
            resultado = true;
        }
    }
    return resultado;
}
