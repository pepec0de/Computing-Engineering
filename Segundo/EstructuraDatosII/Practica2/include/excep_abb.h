#ifndef _EXCEP_ABB_H_
#define _EXCEP_ABB_H_

#include "excepcion.h"

using namespace std;

class ArbolVacioExcepcion: public Excepcion {
  public:
     ArbolVacioExcepcion(): Excepcion("Arbol vacio") {};
};

class NoHaySiguienteMayor : public Excepcion {
public:
    NoHaySiguienteMayor() : Excepcion("No hay siguiente mayor") {}
};

class NoHaySiguienteMenor : public Excepcion {
public:
    NoHaySiguienteMenor() : Excepcion("No hay siguiente menor") {}
};
#endif

