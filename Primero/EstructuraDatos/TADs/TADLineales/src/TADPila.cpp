#include "TADPila.h"

TADPila::TADPila(){tope=-1;}
void TADPila::apilar(int e){
if (tope+1<MAX){
tope++;
elementos[tope]=e;
}
}
int TADPila::longitud(){ return tope+1; }
void TADPila::desapilar(){tope--;}
int TADPila::cima(){return(elementos[tope]);}
bool TADPila::esvacia (){return (tope == -1);
}
