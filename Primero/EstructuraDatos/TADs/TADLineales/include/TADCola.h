#ifndef TADCOLA_H
#define TADCOLA_H

# define MAX 30
# include <iostream>
#include <string>
using namespace std;
class TADCola {
	int elementos[MAX]; //elementos de la cola
	int inicio, fin;//principio y fin de la cola
public:
	TADCola(); // constructor de la clase
	void encolar(int e);
	void desencolar();
	int primero();
	bool esvacia();
	int longitud() ;
};

#endif // TADCOLA_H
