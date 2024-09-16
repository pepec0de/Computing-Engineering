#ifndef TALMACEN_H
#define TALMACEN_H
#include "TTipos.h"

using namespace std;

class TAlmacen {

    Cadena Nombre; //Nombre del almacén.
    Cadena Direccion; //Dirección del almacén.
    fstream FicheProductos; //Fichero que almacena los productos del almacén.
    int NProduc; //Número de productos que hay en el almacén. Si el almacén está cerrado
    //deberá tener el valor -1.

public:
    TAlmacen(); //Constructor que debe inicializar los atributos de la clase.
    ~TAlmacen(); //Destructor que cerrará el almacén en caso de que el usuario no lo haya hecho.
    //Devuelve los atributos nombre y dirección por parámetro.
    void DatosAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen);
    //Crea un fichero binario vacío con el nombre pasado por parámetro. Crea la cabecera del fichero
    //y lo deja abierto para su utilización. Devuelve true si se ha creado.
    bool CrearAlmacen(Cadena pNomFiche);
    //Igual que el método anterior, pero actualizando los atributos nombre y dirección con los valores
    //pasados por parámetro. Devuelve true si ha podido crear el fichero.
    bool CrearAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen, Cadena pNomFiche);
    //Abre un fichero y actualiza los atributos de la clase con los datos de cabecera del fichero y lo
    //lo deja abierto. No se puede abrir un fichero si previamente el almacén está abierto. Devuelve true
    //si ha podido abrir el fichero.
    bool AbrirAlmacen(Cadena pNomFiche);
    //Cierra el fichero e inicializa los atributos a valores iniciales. Devuelve true si se ha cerrado el
    //almacén.
    bool CerrarAlmacen();
    bool EstaAbierto(); //Devuelve true si el fichero está abierto.
    int NProductos(); //Devuelve el número de productos.
    //Dado un código de producto, devuelve la posición dentro del fichero donde se encuentra. Si no
    //lo encuentra devuelve -1.
    int BuscarProducto(Cadena pCodProd);
    //Dado la posición devuelve el producto del fichero situado en dicha posición. Debe verificar
    //previamente que la posición sea correcta. Si la posición no es correcta devolverá un producto cuyo
    //código tendrá el valor “NULO”.
    TProducto ObtenerProducto(int pPos);
    //Dado un producto, lo busca en el fichero y si no lo encuentra lo añade al final del fichero.
    //Devuelve true si se ha añadido el producto.
    bool AnadirProducto(TProducto pProduc);
    //Dada la posición de un producto en el fichero lo actualiza con la información del producto pasado
    //por parámetro. Devuelve true si se ha actualizado el producto. Se debe verificar previamente que la
    //posición sea correcta.
    bool ActualizarProducto(int pPos, TProducto pProduc);
    //Dado la posición de un producto en el fichero lo borra. Devuelve true si se ha podido borrar. Se
    //debe verificar que la posición sea correcta.
    bool EliminarProducto(int pPos);
};
#endif // TALMACEN_H
