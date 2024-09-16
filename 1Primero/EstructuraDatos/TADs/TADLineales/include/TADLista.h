#ifndef TADLISTA_H
#define TADLISTA_H

# define MAX 30
# include <iostream>
using namespace std;

class lista {
    int elementos[MAX];//elementos de la lista
    int n;//nº de elementos que tiene la lista

public:
    lista();
    lista(int e);
    bool esvacia();
    int longitud();
    void anadirIzq(int e);
    void anadirDch(int e);
    void eliminarIzq();
    void eliminarDch();
    int observarIzq();
    int observarDch();
    void concatenar(lista l);
    bool pertenece(int e);
    void insertar(int i, int e);
    void eliminar(int i);
    void modificar(int i, int e);
    int observar(int i);
    int posicion(int e);
};

#endif // TADLISTA_H
