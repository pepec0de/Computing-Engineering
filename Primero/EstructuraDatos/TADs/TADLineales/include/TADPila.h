#ifndef TADPILA_H
#define TADPILA_H

# define MAX 30
# include <iostream>
using namespace std;
class TADPila {
int elementos[MAX]; //elementos de la pila
int tope ;//tope de la pila
public:
TADPila(); // constructor de la clase
void apilar(int e);
void desapilar();
int cima();
bool esvacia();
int longitud();
};

#endif // TADPILA_H
