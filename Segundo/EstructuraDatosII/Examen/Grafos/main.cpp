#include <iostream>
#include <cstdlib>
#include "include/grafo.h"
#include "include/conjunto.h"
#include <queue>
#include <stack>
#include <sstream>
#include <map>

using namespace std;

template <typename T>
void Dijkstra(const Grafo<T, int>& G, const T& origen) {
	Conjunto<T> novisitados;
	map<T, int> distancias;
	map<T, T> antecesores;
	int costeMin, coste;
	const int n = G.numVertices();
	Conjunto<Arista<T, int>> aristas;
	T w, u;
	// Inicializamos valores
	Conjunto<Vertice<T>> vertices = G.vertices();
	while (!vertices.esVacio()) {
		w = vertices.quitar().getObj();
		distancias[w] = -1;
		antecesores[w] = 0;
		novisitados.anadir(w);
	}
	distancias[origen] = 0;
	for (int i = 0; i < n-1; i++) {
		costeMin = -1;
		Conjunto<T> aux = novisitados;
		while(!aux.esVacio()) {
			u = aux.quitar();
			if (costeMin == -1 || distancias[u] <= costeMin) {
				w = u;
				costeMin = distancias[u];
			}
		}
		novisitados.quitar(w);
		aux = novisitados;
		while (!aux.esVacio()) {
			u = aux.quitar();
			// Determinamos el coste
			aristas = G.aristas();
			while (!aristas.esVacio()) {
				Arista<T, int> currArista = aristas.quitar();
				if (currArista.getOrigen() == w && currArista.getDestino() == u) {
					coste = currArista.getEtiqueta();
					break;
				}
			}
			if ( (distancias[w] + coste) < distancias[u] || distancias[u] == -1) {
				distancias[u] = distancias[w] + coste;
				antecesores[u] = w;
			}
		}
	}

}

int main() {
	Grafo<int, float> G(7);
    for (int i = 1; i <= 7; i++) G.insertarVertice(i);
    G.insertarArista(2, 1, 4);
    G.insertarArista(1, 3, 3);
    G.insertarArista(1, 4, 2);
    G.insertarArista(1, 6, 1);
    G.insertarArista(6, 4, 5);
    G.insertarArista(4, 7, 3);
    G.insertarArista(5, 1, 6);

    Grafo<string, float> H(7);
    H.insertarVertice("Huelva");
    H.insertarVertice("Lepe");
    H.insertarVertice("Niebla");
    H.insertarVertice("Mazagon");
    H.insertarVertice("Almonte");
    H.insertarVertice("Aljaraque");
    H.insertarVertice("Matalascañas");

    H.insertarArista("Lepe", "Huelva", 4);
    H.insertarArista("Huelva", "Niebla", 3);
    H.insertarArista("Huelva", "Mazagon", 2);
    H.insertarArista("Huelva", "Aljaraque", 1);
    H.insertarArista("Mazagon", "Almonte", 3);
    H.insertarArista("Mazagon", "Matalascañas", 4);
    H.insertarArista("Aljaraque", "Mazagon", 5);
    H.insertarArista("Almonte", "Huelva", 6);

    return 0;
}
