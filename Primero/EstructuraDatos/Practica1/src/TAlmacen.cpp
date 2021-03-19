#include "TAlmacen.h"

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
     */
    FicheProductos.open("Almacen.dat", ios::binary | ios::out | ios::in);
    if (FicheProductos.fail()) {
        // El archivo no existe, lo creamos
        FicheProductos.close();
        FicheProductos.clear();
        FicheProductos.open("Almacen.dat", ios::binary | ios::out);
        FicheProductos.close();
        FicheProductos.clear();
        FicheProductos.open("Almacen.dat", ios::binary | ios::out | ios::in);
    }
    FicheProductos.close();
}

TAlmacen::~TAlmacen() {
    //dtor
}

// Devuelve los atributos nombre y dirección por parámetro.
void TAlmacen::DatosAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen) {
    strcpy(pNombAlmacen, Nombre);
    strcpy(pDirAlmacen, Direccion);
}

// Crea un fichero binario vacío con el nombre pasado por parámetro. Crea la cabecera del fichero
// y lo deja abierto para su utilización. Devuelve true si se ha creado.
bool TAlmacen::CrearAlmacen(Cadena pNomFiche) {
    FicheProductos.open(pNomFiche, ios::binary | ios::out | ios::trunc);
    if (!FicheProductos.fail()) {
        FicheProductos.seekp(0);
        FicheProductos.write((char*) &NProduc, sizeof(int));
        FicheProductos.write((char*) &Nombre, sizeof(Cadena));
        FicheProductos.write((char*) &Direccion, sizeof(Cadena));
        return true;
    }
    return false;
}

// Igual que el método anterior, pero actualizando los atributos nombre y dirección con los valores
// pasados por parámetro. Devuelve true si ha podido crear el fichero.
bool TAlmacen::CrearAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen, Cadena pNomFiche) {
    strcpy(Nombre, pNombAlmacen);
    strcpy(Direccion, pDirAlmacen);

    return CrearAlmacen(pNomFiche);
}

// Abre un fichero y actualiza los atributos de la clase con los datos de cabecera del fichero y lo
// lo deja abierto. No se puede abrir un fichero si previamente el almacén está abierto. Devuelve true
// si ha podido abrir el fichero.
// 0 -> Numero Productos
// 1 -> Nombre
// 2 -> Direccion
bool TAlmacen::AbrirAlmacen(Cadena pNomFiche) {
    if (EstaAbierto()) return false;

    FicheProductos.open(pNomFiche, ios::binary | ios::out | ios::in);
    if (!FicheProductos.fail()) {
        FicheProductos.seekg(0);
        FicheProductos.read((char*) &NProduc, sizeof(int));
        FicheProductos.read((char*) &Nombre, sizeof(Cadena));
        FicheProductos.read((char*) &Direccion, sizeof(Cadena));
        return true;
    }

    return false;
}

// Cierra el fichero e inicializa los atributos a valores iniciales. Devuelve true si se ha cerrado el
// almacén.
bool TAlmacen::CerrarAlmacen() {
    NProduc = -1;
    FicheProductos.close();

    return !FicheProductos.is_open();
}

// Devuelve true si el fichero está abierto.
bool TAlmacen::EstaAbierto() {
    return NProduc != -1;
}

// Devuelve el número de productos.
int TAlmacen::NProductos() {
    return NProduc;
}

// Dado un código de producto, devuelve la posición dentro del fichero donde se encuentra. Si no
// lo encuentra devuelve -1.
int TAlmacen::BuscarProducto(Cadena pCodProd) {
    Cadena codActual;
    FicheProductos.seekg(0);
    do {
        if (FicheProductos.eof()) {
            // Con return automaticamente sale del metodo
            return -1;
        }
        FicheProductos.read((char*) &codActual, sizeof(Cadena));
    } while(strcmp(codActual, pCodProd) != 0);

    return FicheProductos.tellg();
}

// Dado la posición devuelve el producto del fichero situado en dicha posición. Debe verificar
// previamente que la posición sea correcta. Si la posición no es correcta devolverá un producto cuyo
// código tendrá el valor “NULO”.
TProducto TAlmacen::ObtenerProducto(int pPos) {
    TProducto resultado;
    FicheProductos.seekg(pPos);
    FicheProductos.read((char*) &resultado, sizeof(TProducto));
    if (!FicheProductos.good()) {
        // La posicion no es correcta, ya que el tipo de variable es distinto
        strcpy(resultado.CodProd, "NULO");
    }
    return resultado;
}

// Dado un producto, lo busca en el fichero y si no lo encuentra lo añade al final del fichero.
// Devuelve true si se ha añadido el producto.
bool TAlmacen::AnadirProducto(TProducto pProduc) {
    if (BuscarProducto(pProduc.CodProd) == -1) {
        FicheProductos.seekp(0, ios::end);
        FicheProductos.write((char*) &pProduc, sizeof(TProducto));
        return true;
    }
    return false;
}

// Dada la posición de un producto en el fichero lo actualiza con la información del producto pasado
// por parámetro. Devuelve true si se ha actualizado el producto. Se debe verificar previamente que la
// posición sea correcta.
bool TAlmacen::ActualizarProducto(int pPos, TProducto pProduc) {
    TProducto aux;
    FicheProductos.seekg(pPos);
    FicheProductos.read((char*) &aux, sizeof(TProducto));
    if (FicheProductos.good()) {
        FicheProductos.seekp(pPos);
        FicheProductos.write((char*) &pProduc, sizeof(TProducto));
        return true;
    }
    return false;
}

// Dado la posición de un producto en el fichero lo borra. Devuelve true si se ha podido borrar. Se
// debe verificar que la posición sea correcta.
bool TAlmacen::EliminarProducto(int pPos) {
    TProducto prodEliminar;
    FicheProductos.seekg(pPos);
    FicheProductos.read((char*) &prodEliminar, sizeof(TProducto));
    if (FicheProductos.good()) {
        TProducto prodActual; // auxiliar
        int tamano = 0;
        FicheProductos.seekg(0, ios::end);
        tamano = FicheProductos.tellg();
        for (int i = 0; i < NProduc; i++) {
            // Comprobamos que el producto no este en la ultima posicion
            if (pPos+sizeof(TProducto) < tamano) {
                // Sustituimos el producto que se quiere eliminar por el que esta justo despues de este
                FicheProductos.seekg(pPos+sizeof(TProducto)*(i+1)); // lectura
                FicheProductos.read((char*) &prodActual, sizeof(TProducto));
                FicheProductos.seekp(pPos+sizeof(TProducto)*i); // escritura
                FicheProductos.write((char*) &prodActual, sizeof(TProducto));
                if (FicheProductos.eof()) break;
            }
        }
        NProduc--;
        FicheProductos.seekp(0);
        FicheProductos.write((char*) &NProduc, sizeof(int));
        return true;
    }
    return false;
}
