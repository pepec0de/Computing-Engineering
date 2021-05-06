#include "../include/TADCola.h"

Cola::Cola() {
	elementos = new TPedido[INCREMENTO];
	if(elementos != NULL) {
		ne = fin = inicio = 0;
		Tama = INCREMENTO;
	} else {
		ne = fin = inicio = -1;
		Tama = -1;
	}
}
Cola::~Cola() {
	if(elementos != NULL)
		delete [] elementos;
	elementos = NULL;
	ne = fin = inicio = -1;
	Tama = 0;
}
TPedido Cola::primero() {
	return elementos[inicio];
}
bool Cola::esvacia() {
	return (ne == 0);
}
int Cola::longitud() {
	return ne;
}

void Cola::encolar(TPedido e) {
	if(ne == Tama) {
		TPedido *NuevaZona = new TPedido[Tama + INCREMENTO];
		if(NuevaZona != NULL) {
			for(int i = 0; i < ne; i++) {
				NuevaZona[i] = elementos[inicio];
				inicio++;
				if(inicio == Tama) // inicio=(inicio+1)%Tama
					inicio = 0;
			}
			inicio = 0;
			fin = ne;
			Tama += INCREMENTO;
			delete elementos;
			elementos = NuevaZona;
		}
	}

	if(ne < Tama) {
		elementos[fin] = e;
		fin = (fin + 1) % Tama;
		ne++;
	}
}

void Cola::desencolar() {
	inicio++; // inicio=(inicio+1)%Tama;
	if(inicio == Tama)
		inicio = 0;
	ne--;
	if(Tama - ne >= INCREMENTO && Tama > INCREMENTO) {
		TPedido *NuevaZona = new TPedido[Tama - INCREMENTO];
		if(NuevaZona != NULL) {
			for(int i = 0; i < ne; i++) {
				NuevaZona[i] = elementos[inicio++];
				if(inicio == Tama)
					inicio = 0;
			}
			Tama -= INCREMENTO;
			inicio = 0;
			fin = Tama;
			delete [] elementos;
			elementos = NuevaZona;
		}
	}
}
