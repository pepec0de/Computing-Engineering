#ifndef _EXCEP_ARBIN_H_
#define _EXCEP_ARBIN_H_

#include "excepcion.h"

using namespace std;

class PosicionArbolExcepcion: public Excepcion {
  public:
     PosicionArbolExcepcion(): Excepcion("La posici�n del iterador en el �rbol no permite realizar esa operaci�n") {};
};

#endif

