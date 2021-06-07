#ifndef TADCOLA_H
#define TADCOLA_H

#include "TTipos.h"
#define INCREMENTO 4

using namespace std;
class Cola {
	TPedido *elementos; //elementos de la cola
	int inicio, fin; //principio y fin de la cola
	int Tama; //Capacidad de la tabla
	int ne; //No. de elementos

public:
	Cola(); // constructor de la clase
	~Cola();
	void encolar(TPedido e);
	void desencolar();
	TPedido primero();
	bool esvacia();
	int longitud() ;
};

#endif // TADCOLA_H
