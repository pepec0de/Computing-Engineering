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

    friend ostream& operator<< (ostream& os, TADCola const & cola ) {
	    string msg = "[";
	    for (int i = 0; i < cola.longitud(); i++) {
            msg += to_string(cola.primero()) + ", ";
	    }
	    msg += "]";
        return os << msg << endl;
    }
};

#endif // TADCOLA_H
