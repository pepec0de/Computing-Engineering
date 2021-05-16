# include "../include/TADLista.h"

Lista::Lista() {
	elementos = new TPedido[INCREMENTO];
	if(elementos != NULL) {
		Tama = INCREMENTO;
		n = 0;
	} else {
		Tama = n = -1;
	}
}

Lista::~Lista() {
	if(elementos != NULL)
		delete [] elementos;
	elementos = NULL;
	Tama = n = 0;
}

void Lista::insertar(int i, TPedido e) {
	int pos;
	if(n == Tama) {
		TPedido *NuevaZona = new TPedido[Tama + INCREMENTO];
		if(NuevaZona != NULL) {
			for(int i = 0; i < n; i++)
				NuevaZona[i] = elementos[i];
			Tama += INCREMENTO;
			delete [] elementos;
			elementos = NuevaZona;
		}
	}
	if(n < Tama) {
		for(pos = n - 1; pos >= i - 1; pos--)
			elementos[pos + 1] = elementos[pos]; // Desplazamiento
		elementos[i - 1] = e;
		n++;
	}
}

void Lista::modificar(int i, TPedido e) {
	elementos[i - 1] = e;
}

TPedido Lista::observar(int i) {
	return(elementos[i - 1]);
}

bool Lista::esvacia() {
	return (n == 0);
}

void Lista::eliminar(int i) {
	while(i < n) {
		elementos[i - 1] = elementos[i]; // Desplazamiento
		i++;
	}
	n--;
	if(Tama - n >= INCREMENTO && Tama > INCREMENTO) {
		TPedido *NuevaZona = new TPedido[Tama - INCREMENTO];
		if(NuevaZona != NULL) {
			Tama -= INCREMENTO;
			for(int i = 0; i < Tama; i++)
				NuevaZona[i] = elementos[i];
			delete [] elementos;
			elementos = NuevaZona;
		};
	};
}

int Lista::posicion(TPedido e) {
	int i = 0;
	while( !(strcmp(elementos[i].CodProd, e.CodProd) == 0 && strcmp(elementos[i].Nomtienda, e.Nomtienda) == 0 && elementos[i].CantidadPed == e.CantidadPed) && i < n) i++;
	return (i == n ? -1 : i + 1);
}

int Lista::longitud() {
	return n;
}

Lista::Lista(TPedido e) {
	elementos = new TPedido[INCREMENTO];
	if(elementos != NULL) {
		Tama = INCREMENTO;
		n = 1;
		elementos[0] = e;
	} else {
		Tama = n = -1;
	}
}

void Lista::anadirIzq(TPedido e) {
	insertar(1, e);
}

void Lista::anadirDch(TPedido e) {
	insertar(n + 1, e);
}

void Lista::eliminarIzq() {
	eliminar(1);
}

void Lista::eliminarDch() {
	eliminar(n);
}

TPedido Lista::observarIzq() {
	return(observar(1));
}
TPedido Lista::observarDch() {
	return(observar(n));
}

void Lista::concatenar(Lista l) {
	int lon = l.longitud();
	for(int i = 1; i <= lon; i++) insertar(n + 1, l.observar(i));
}

bool Lista::pertenece(TPedido e) {
	return (posicion(e) != -1);
}
