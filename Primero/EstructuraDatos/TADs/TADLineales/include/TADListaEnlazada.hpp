#ifndef TADLISTAENLAZADA_HPP
#define TADLISTAENLAZADA_HPP

#include <iostream>
using namespace std;

struct TNodo_Lista {
	float Datos; // Dato a almacenar en cada nodo
	TNodo_Lista *Siguiente; // Puntero al siguiente nodo
};

class TADListaEnlazada {
	TNodo_Lista *elementos; // Puntero al primer nodo de la lista.
	int n; // nº de nodos que tiene la lista
// Devuelve un puntero al elemento anterior al elemento i
// Devuelve NULL si es el primero
	TNodo_Lista *Anterior(int i);
public:
	TADListaEnlazada(); // constructor de la clase
	~TADListaEnlazada(); // destructor de la clase
	TADListaEnlazada(float e);
	bool esvacia();
	int longitud();
	void anadirIzq(float e);
	void anadirDch(float e);
	void eliminarIzq();
	void eliminarDch();
	float observarIzq();
	float observarDch();
	void concatenar(TADListaEnlazada l);
	bool pertenece(float e);
	void insertar(int i, float e);
	void eliminar(int i);
	void modificar(int i, float e);
	float observar(int i);
	int posicion(float e);
};
#endif // TADLISTAENLAZADA_HPP
