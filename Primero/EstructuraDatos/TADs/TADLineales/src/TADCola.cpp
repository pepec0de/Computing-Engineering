#include "TADCola.h"

TADCola::TADCola() {
	inicio = 0;
	fin = -1;
}

void TADCola::encolar(int e) {
	if(fin + 1 < MAX) {
		fin++;
		elementos[fin] = e;
	}
}
int TADCola::longitud() {
	return fin + 1;
}
void TADCola::desencolar() {
	for(int i = inicio; i < fin; i++)
		elementos[i] = elementos[i + 1]; //Desplazamiento
	fin--;
}
int TADCola::primero() {
	return(elementos[inicio]);
}
bool TADCola::esvacia() {
	return (fin == -1);
}
