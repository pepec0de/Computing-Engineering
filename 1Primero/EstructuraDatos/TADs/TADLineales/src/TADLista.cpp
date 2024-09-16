#include "TADLista.h"

lista::lista() {
	n = 0;
}
lista::lista(int e) {
	n = 1;
	elementos[0] = e;
}
void lista::insertar(int i, int e) {
	int postabla;
	postabla = i - 1;
	if(n < MAX) {
		for(int pos = n - 1; pos >= postabla; pos--)
			elementos[pos + 1] = elementos[pos]; //Desplazamiento

		elementos[postabla] = e;
		n++;
	}
}
void lista::eliminar(int i) {
	int postabla = i - 1;
	while(postabla < n - 1) {
		elementos[postabla] = elementos[postabla + 1];
//Desplazamiento
		postabla++;
	}
	n--;
}
void lista::modificar(int i, int e) {
	elementos[i - 1] = e;
}
int lista::observar(int i) {
	return(elementos[i - 1]);
}
bool lista::esvacia() {
	return (n == 0);
}
int lista::posicion(int e) {
	int i = 0;
	while((elementos[i] != e) && (i < n))
		i++;
	if(elementos[i] == e) return(i + 1);
	else return (-1);
}
int lista::longitud() {
	return n;
}
void lista::anadirIzq(int e) {
	insertar(1, e);
}
void lista::anadirDch(int e) {
	insertar(n + 1, e);
}

void lista::eliminarIzq() {
	for(int i = 0; i < n - 1; i++)
		elementos[i] = elementos[i + 1];
	n--;
}
void lista::eliminarDch() {
	n--;
}
int lista::observarIzq() {
	return(observar(1));
}
int lista::observarDch() {
	return(observar(n));
}
void lista::concatenar(lista l) {
	int lon = l.longitud();
	for(int i = 1; i <= lon; i++)
		insertar(n + 1, l.observar(i));
}
bool lista::pertenece(int e) {
	return (posicion(e) == -1 ? false : true);
}
