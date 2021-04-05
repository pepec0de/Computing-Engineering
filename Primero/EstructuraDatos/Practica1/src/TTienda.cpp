#include "../include/TTienda.h"

// Metodo privado
// M�todo privado para ordenar los estantes del vector. La ordenaci�n se realizar� por el c�digo de
// producto en orden ascendente. En caso de tener el mismo c�digo se ordenar� por la posici�n del
// producto en el estante en orden ascendente.
void TTienda::OrdenarProductos() { // Ordenacion de mayor a  menos
    for (int i = 0; i < NEstan; i++) {
        int max_index = i;
        for (int j = i+1; j < NEstan; j++) {
            if (strcmp(Estantes[i].CodProd, Estantes[j].CodProd) == 0) {
                if (Estantes[i].Posicion < Estantes[j].Posicion) {
                    max_index = j;
                }
            } else if (strcmp(Estantes[i].CodProd, Estantes[j].CodProd) < 0) {
                max_index = i;
            } else {
                max_index = j;
            }
        }
        TEstante aux = Estantes[i];
        Estantes[i] = Estantes[max_index];
        Estantes[max_index] = aux;
    }
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

// Destructor que cerrar� la tienda en caso de que el usuario no lo haya hecho.
TTienda::~TTienda()
{
    //dtor
    if (EstaAbierta()) {
        CerrarTienda();
    }
}

// Devuelve los atributos nombre y direcci�n por par�metro.
void TTienda::DatosTienda(Cadena pNombTienda, Cadena pDirTienda) {
    strcpy(pNombTienda, Nombre);
    strcpy(pDirTienda, Direccion);
}

// Crea un fichero binario vac�o con el nombre pasado por par�metro e inicializa los atributos nombre y
// direcci�n mediante los par�metros y a continuaci�n lo cerrar�. Devolver� true si ha podido crear el
// fichero.
bool TTienda::CrearTienda(Cadena pNombTienda, Cadena pDirTienda, Cadena pNomFiche) {
    bool resultado = false;

    fstream fichero;
    fichero.open(pNomFiche, ios::binary | ios::out | ios::trunc);
    if (!fichero.fail()) {
        // Inicializan los atributos
        strcpy(Nombre, pNombTienda);
        strcpy(Direccion, pDirTienda);
        strcpy(NomFiche, pNomFiche);
        NEstan = 0;
        Tamano = Incremento;
        Estantes = new TEstante[Tamano];

        fichero.write((char*) Nombre, sizeof(Cadena));
        fichero.write((char*) Direccion, sizeof(Cadena));
        resultado = true;
    }
    return resultado;
}

// Abre un fichero y lo carga a memoria. Si ya hab�a un fichero previamente cargado, guardar� los datos
// de la tienda y proceder� a cargar el nuevo fichero. Si el fichero es el mismo que el que est� en
// memoria, eliminar� los datos y proceder� a cargar nuevamente los datos del fichero. Devolver� true
// si se ha podido cargar el fichero.
bool TTienda::AbrirTienda(Cadena pNomFiche) {
    bool resultado = false;

    if (EstaAbierta()) {
        GuardarTienda();
    }
    fstream fichero;
    fichero.open(pNomFiche, ios::binary | ios::in);
    if (!fichero.fail()) {
        // Leemos el tama�o del archivo
        fichero.seekg(0, ios::end);
        int bytesFichero = fichero.tellg();

        strcpy(NomFiche, pNomFiche);

        fichero.seekg(0, ios::beg);
        fichero.read((char*) Nombre, sizeof(Cadena));
        fichero.read((char*) Direccion, sizeof(Cadena));
        // Cargamos los estantes
        int i = 0;
        while(fichero.tellg() < bytesFichero) {
            fichero.read((char*) &Estantes[i], sizeof(TEstante*));
            i++;
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
        NEstan = i;
        resultado = true;
    }
    return resultado;
}

// Vuelca los datos de la memoria al fichero. Devolver� true si se han guardado los datos.
bool TTienda::GuardarTienda() {
    bool resultado = false;
    fstream fichero;
    fichero.open(NomFiche, ios::binary | ios::out | ios::trunc);
    fichero.seekp(0, ios::beg);
    fichero.write((char*) Nombre, sizeof(Cadena));
    fichero.write((char*) Direccion, sizeof(Cadena));
    fichero.write((char*) Estantes, sizeof(TEstante)*NEstan); // mas limpio
    if (!fichero.fail()) {
        cout << "[GuardarTienda] Se ha guardado la tienda con exito.\n";
        resultado = true;
    }
    fichero.close();
    return resultado;
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
        NEstan = -1;
        Tamano = -1;
        resultado = true;
    }
    return resultado;
}

// Devuelve true si la tienda est� abierta.
bool TTienda::EstaAbierta() {
    return NEstan >= 0;
}

// Devuelve el n�mero de estantes de la tienda.
int TTienda::NoEstantes() {
    return NEstan;
}

// Dado un c�digo de estante, devuelve la posici�n dentro del vector donde se encuentra. Si no lo
// encuentra devuelve -1.
int TTienda::BuscarEstante(int pCodEstante) {
    int i = 0;
    while (i < NEstan && Estantes[i].CodEstante != pCodEstante) i++;
    if (i == NEstan) i = -1;
    return i;
}

// Dado la posici�n el estante que est� en la posici�n indicada por par�metro.
TEstante TTienda::ObtenerEstante(int pPos) {
    return Estantes[pPos];
}

// A�ade un estante nuevo al vector siempre que el estante no est� previamente almacenado en memoria.
// El vector de estantes debe siempre estar ordenado. Devolver� true si se ha a�adido el estante.
bool TTienda::AnadirEstante(TEstante pEstante) {
    if (BuscarEstante(pEstante.CodEstante) == -1) {
        Estantes[NEstan] = pEstante;
        NEstan++;
        OrdenarProductos();
        return true;
    }
    return false;
}

// Dado la posici�n de un estante lo borra desplazando el resto de estantes una posici�n hacia abajo.
// Se debe verificar previamente que la posici�n sea correcta. Devuelve true si se ha eliminado el
// estante.
// TODO: condicional de reducir memoria
bool TTienda::EliminarEstante(int pPos) {
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

// Dada la posici�n de un estante, lo actualiza con los datos pasados por par�metros. Se debe verificar
// previamente que la posici�n sea correcta Devuelve true si se actualiza el estante.
bool TTienda::ActualizarEstante(int pPos, TEstante pEstante) {
    bool resultado = false;
    if (pPos >= 0 && pPos < NEstan) {
        Estantes[pPos] = pEstante;
        OrdenarProductos();
        resultado = true;
    }
    return resultado;
}

// Dada la posici�n de un estante y un producto del almac�n, actualizar� el n�mero de productos del
// estante a su m�xima capacidad si hay suficientes unidades en el producto, en caso contrario se
// a�adir�n al estante la totalidad de unidades que est�n en el producto del almac�n. El mismo n�mero
// de unidades a�adidas en el estante deben reducirse del producto. Se debe verificar previamente que
// la posici�n sea correcta. El m�todo devuelve:
//  0 si la posici�n es incorrecta.
//  1 si se ha repuesto unidades hasta llegar a la capacidad m�xima del estante.
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
