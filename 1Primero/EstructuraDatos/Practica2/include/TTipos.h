#ifndef TTIPOS_H
#define TTIPOS_H
#include <string.h>
#include <iostream>
#include <fstream>
typedef char Cadena[90];
#define Incremento 4
struct TFecha {//Almacena una fecha
    int Dia;
    int Mes;
    int Anyo;
};
struct TProducto {
    Cadena CodProd; //Código de producto.
    int Cantidad; //Cantidad en el almacén.
    Cadena NombreProd; //Nombre del producto.
    float Precio; //Precio por unidad.
    Cadena Descripcion; //Descripción opcional del producto.
    TFecha Caducicidad; //Caducidad del producto.
};
struct TEstante
{
    int CodEstante; //Código del estante.
    int Posicion; //Posición del estante, valores (1-centrado, 2- arriba, 3- abajo).
    int Capacidad; //Máxima capacidad que admite el estante.
    Cadena CodProd; //Código del producto que contiene el estante.
    int NoProductos; //Número de productos que hay en el estante.
};
struct TPedido
{
    Cadena CodProd; //Nombre del producto pedido
    int CantidadPed; //Cantidad pedida del producto
    Cadena Nomtienda; //Nombre del fichero tienda
};
#endif // TTIPOS_H
