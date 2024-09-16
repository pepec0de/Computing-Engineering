#include "TADCola.h"

TADCola::TADCola() {
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
	for(int i = 0; i < fin; i++)
		elementos[i] = elementos[i + 1]; //Desplazamiento
	fin--;
}
int TADCola::primero() {
	return(elementos[0]);
}
bool TADCola::esvacia() {
	return (fin == -1);
}
