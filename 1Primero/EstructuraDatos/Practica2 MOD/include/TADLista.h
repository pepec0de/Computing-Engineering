#ifndef TADLISTA_H
#define TADLISTA_H

#define INCREMENTO 4
#include "TTipos.h"

class Lista {
    TPedido *elementos; //elementos de la lista
    int n;//nº de elementos que tiene la lista
    int Tama;  // Capacidad de la tabla

public:
    Lista();
    ~Lista();
    Lista(TPedido e);
    bool esvacia();
    int longitud();
    void anadirIzq(TPedido e);
    void anadirDch(TPedido e);
    void eliminarIzq();
    void eliminarDch();
    TPedido observarIzq();
    TPedido observarDch();
    void concatenar(Lista l);
    bool pertenece(TPedido e);
    void insertar(int i, TPedido e);
    void eliminar(int i);
    void modificar(int i, TPedido e);
    TPedido observar(int i);
    int posicion(TPedido e);
};

#endif // TADLISTA_H
