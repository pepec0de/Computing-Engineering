#include "TTienda.h"

// Metodo privado
void TTienda::OrdenarProductos() {

}

TTienda::TTienda()
{
    //ctor

}

TTienda::~TTienda()
{
    //dtor
}

//Devuelve los atributos nombre y dirección por parámetro.
void TTienda::DatosTienda(Cadena pNombTienda, Cadena pDirTienda) {

}

//Crea un fichero binario vacío con el nombre pasado por parámetro e inicializa los atributos nombre y
//dirección mediante los parámetros y a continuación lo cerrará. Devolverá true si ha podido crear el
//fichero.
bool TTienda::CrearTienda(Cadena pNombTienda, Cadena pDirTienda, Cadena pNomFiche) {
    return false;
}

//Abre un fichero y lo carga a memoria. Si ya había un fichero previamente cargado, guardará los datos
//de la tienda y procederá a cargar el nuevo fichero. Si el fichero es el mismo que el que está en
//memoria, eliminará los datos y procederá a cargar nuevamente los datos del fichero. Devolverá true
//si se ha podido cargar el fichero.
bool TTienda::AbrirTienda(Cadena pNomFiche) {
    return false;
}

//Vuelca los datos de la memoria al fichero. Devolverá true si se han guardado los datos.
bool TTienda::GuardarTienda() {
    return false;
}

// Guarda los datos de la memoria en el fichero y borra todos los atributos del objeto. Devuelve true
// si se ha podido guardar los datos.
bool TTienda::CerrarTienda() {
    return false;
}

// Devuelve true si la tienda está abierta.
bool TTienda::EstaAbierta() {
    return false;
}

// Devuelve el número de estantes de la tienda.
int TTienda::NoEstantes() {
    return 0;
}

// Dado un código de estante, devuelve la posición dentro del vector donde se encuentra. Si no lo
// encuentra devuelve -1.
int TTienda::BuscarEstante(int pCodEstante) {
    return 0;
}

// Dado la posición el estante que está en la posición indicada por parámetro.
TEstante TTienda::ObtenerEstante(int pPos) {
    TEstante resultado;
    return resultado;
}

// Añade un estante nuevo al vector siempre que el estante no esté previamente almacenado en memoria.
// El vector de estantes debe siempre estar ordenado. Devolverá true si se ha añadido el estante.
bool TTienda::AnadirEstante(TEstante pEstante) {
    return false;
}

// Dado la posición de un estante lo borra desplazando el resto de estantes una posición hacia abajo.
// Se debe verificar previamente que la posición sea correcta. Devuelve true si se ha eliminado el
// estante.
bool TTienda::EliminarEstante(int pPos) {
    return false;
}

// Dada la posición de un estante, lo actualiza con los datos pasados por parámetros. Se debe verificar
// previamente que la posición sea correcta Devuelve true si se actualiza el estante.
bool TTienda::ActualizarEstante(int pPos, TEstante pEstante) {
    return false;
}

// Dada la posición de un estante y un producto del almacén, actualizará el número de productos del
// estante a su máxima capacidad si hay suficientes unidades en el producto, en caso contrario se
// añadirán al estante la totalidad de unidades que estén en el producto del almacén. El mismo número
// de unidades añadidas en el estante deben reducirse del producto. Se debe verificar previamente que
// la posición sea correcta. El método devuelve:
//  0 si la posición es incorrecta.
//  1 si se ha repuesto unidades hasta llegar a la capacidad máxima del estante.
//  2 si no se ha completado el estante al completo.
int TTienda::ReponerEstante(int pPos, TProducto &pProduc) {
    return 0;
}


