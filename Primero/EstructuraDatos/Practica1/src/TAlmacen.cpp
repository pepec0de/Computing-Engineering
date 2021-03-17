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
    fstream nuevoFiche;
    nuevoFiche.open(pNomFiche, ios::binary | ios::out | ios::in);
    if (!nuevoFiche.fail()) {
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
    FicheProductos.clear();

    return !FicheProductos.is_open();
}

bool TAlmacen::EstaAbierto() {
    return NProduc != -1;
}

int TAlmacen::NProductos() {
    return NProduc;
}

int TAlmacen::BuscarProducto(Cadena pCodProd) {

    return 0;
}
