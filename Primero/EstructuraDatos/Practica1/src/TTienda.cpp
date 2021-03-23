#include "TTienda.h"

// Metodo privado
// Método privado para ordenar los estantes del vector. La ordenación se realizará por el código de
// producto en orden ascendente. En caso de tener el mismo código se ordenará por la posición del
// producto en el estante en orden ascendente.
void TTienda::OrdenarProductos() {

}

// Constructor que debe inicializar los atributos de la clase.
TTienda::TTienda()
{
    //ctor
    strcpy(Nombre, "");
    strcpy(Direccion, "");
    strcpy(NomFiche, "");
    Estantes = NULL;
    NEstan = -1;
    Tamano = -1;
}

// Destructor que cerrará la tienda en caso de que el usuario no lo haya hecho.
TTienda::~TTienda()
{
    //dtor
    if (EstaAbierta()) {
        CerrarTienda();
    }
    delete [] Estantes;
}

// Devuelve los atributos nombre y dirección por parámetro.
void TTienda::DatosTienda(Cadena pNombTienda, Cadena pDirTienda) {
    strcpy(pNombTienda, Nombre);
    strcpy(pDirTienda, Direccion);
}

// Crea un fichero binario vacío con el nombre pasado por parámetro e inicializa los atributos nombre y
// dirección mediante los parámetros y a continuación lo cerrará. Devolverá true si ha podido crear el
// fichero.
// La tienda se abre
bool TTienda::CrearTienda(Cadena pNombTienda, Cadena pDirTienda, Cadena pNomFiche) {
    bool resultado = false;

    fstream fichero;
    fichero.open(pNomFiche, ios::binary | ios::out | ios::trunc);
    if (!fichero.fail()) {
        // Inicializan los atributos
        strcpy(Nombre, pNombTienda);
        strcpy(Direccion, pDirTienda);
        Estantes = new TEstante[Incremento];
        NEstan = 0;
        Tamano = Incremento;

        // TODO : escribir cabecera
        fichero.write((char*) Nombre, sizeof(Cadena));
        fichero.write((char*) Direccion, sizeof(Cadena));
        resultado = true;
    }
    fichero.close();
    return resultado;
}

// Abre un fichero y lo carga a memoria. Si ya había un fichero previamente cargado, guardará los datos
// de la tienda y procederá a cargar el nuevo fichero. Si el fichero es el mismo que el que está en
// memoria, eliminará los datos y procederá a cargar nuevamente los datos del fichero. Devolverá true
// si se ha podido cargar el fichero.
bool TTienda::AbrirTienda(Cadena pNomFiche) {
    bool resultado = false;

    if (EstaAbierta()) {
        GuardarTienda();
    }
    fstream fichero;
    fichero.open(pNomFiche, ios::binary | ios::out | ios::in);
    if (!fichero.fail()) {
        strcpy(NomFiche, pNomFiche);

        fichero.seekg(0);
        fichero.read((char*) Nombre, sizeof(Cadena));
        fichero.read((char*) Direccion, sizeof(Cadena));

        // Cargamos los estantes
        int i = -1;
        while(!fichero.eof()) {
            i++;
            fichero.read((char*) &Estantes[i], sizeof(TEstante*));
            if (i+1 == Tamano) {
                // Pedimos mas memoria
                TEstante *aux = new TEstante[Tamano+Incremento];
                for (int j = 0; j < Tamano; j++) {
                    aux[j] = Estantes[j];
                }
                Estantes = aux;
                delete [] aux;
            }
        }
        NEstan = i+1;
        resultado = true;
    }
    return resultado;
}

// Vuelca los datos de la memoria al fichero. Devolverá true si se han guardado los datos.
// TODO : truncar la tienda
bool TTienda::GuardarTienda() {
    fstream fichero;
    fichero.open(NomFiche, ios::binary | ios::out);
    fichero.seekp(0);
    fichero.write((char*) Nombre, sizeof(Cadena));
    fichero.write((char*) Direccion, sizeof(Cadena));
    fichero.write((char*) Estantes, sizeof(Estantes)*NEstan); // mas limpio
    return fichero.good();
}

// Guarda los datos de la memoria en el fichero y borra todos los atributos del objeto. Devuelve true
// si se ha podido guardar los datos.
bool TTienda::CerrarTienda() {
    bool resultado = false;
    if (GuardarTienda()) {
        strcpy(Nombre, "");
        strcpy(Direccion, "");
        strcpy(NomFiche, "");
        delete [] Estantes;
        NEstan = 0;
        Tamano = 0;
        resultado = true;
    }
    return resultado;
}

// Devuelve true si la tienda está abierta.
bool TTienda::EstaAbierta() {
    return NEstan == 0;
}

// Devuelve el número de estantes de la tienda.
int TTienda::NoEstantes() {
    return NEstan;
}

// Dado un código de estante, devuelve la posición dentro del vector donde se encuentra. Si no lo
// encuentra devuelve -1.
int TTienda::BuscarEstante(int pCodEstante) {
    int i = 0;
    while (i < Tamano && Estantes[i].CodEstante != pCodEstante) i++;
    if (i == Tamano) i = -1;
    return i;
}

// Dado la posición el estante que está en la posición indicada por parámetro.
TEstante TTienda::ObtenerEstante(int pPos) {
    return Estantes[pPos];
}

// Añade un estante nuevo al vector siempre que el estante no esté previamente almacenado en memoria.
// El vector de estantes debe siempre estar ordenado. Devolverá true si se ha añadido el estante.
bool TTienda::AnadirEstante(TEstante pEstante) {
    if (BuscarEstante(pEstante.CodEstante) == -1) {
        Estantes[NEstan] = pEstante;
        NEstan++;
        OrdenarProductos();
        return true;
    }
    return false;
}

// Dado la posición de un estante lo borra desplazando el resto de estantes una posición hacia abajo.
// Se debe verificar previamente que la posición sea correcta. Devuelve true si se ha eliminado el
// estante.
bool TTienda::EliminarEstante(int pPos) { // TODO: condicional de reducir memoria
    bool resultado = false;
    if (pPos >= 0 && pPos < NEstan) {
        NEstan--;
        for (int i = pPos; i < NEstan; i++) {
            Estantes[i] = Estantes[i+1];
        }
        resultado = true;
    }
    return resultado;
}

// Dada la posición de un estante, lo actualiza con los datos pasados por parámetros. Se debe verificar
// previamente que la posición sea correcta Devuelve true si se actualiza el estante.
bool TTienda::ActualizarEstante(int pPos, TEstante pEstante) {
    bool resultado = false;
    if (pPos >= 0 && pPos < NEstan) {
        Estantes[pPos] = pEstante;
        OrdenarProductos();
        resultado = true;
    }
    return resultado;
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
    int resultado = 0;
    if (Estantes[pPos].CodProd == pProduc.CodProd) {
        if (Estantes[pPos].Capacidad <= Estantes[pPos].NoProductos+pProduc.Cantidad) {
            pProduc.Cantidad -= Estantes[pPos].Capacidad - Estantes[pPos].NoProductos;
            Estantes[pPos].NoProductos = Estantes[pPos].Capacidad;
            resultado = 1;
        } else {
            Estantes[pPos].NoProductos += pProduc.Cantidad;
            pProduc.Cantidad = 0;
            resultado = 2;
        }
    }
    return resultado;
}
