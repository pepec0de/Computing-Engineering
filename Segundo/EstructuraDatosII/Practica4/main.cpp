#include <iostream>
#include <cstdlib>
#include "grafo.h"
#include "conjunto.h"
#include <queue>
#include <sstream>
#include <map>


using namespace std;

//Ejercicio 1
// Bastante ineficiente
template <typename T>
T verticeMaxCoste1(const Grafo<T, float>& G) {
	T result;
	if (!G.esVacio()) {
		Conjunto<Vertice<T>> conjVertices = G.vertices();
		Conjunto<Arista<T, float> > conjAristas;
		float maxCoste = 0, currCoste;

		while(!conjVertices.esVacio()) {
			Vertice<T> currVert = conjVertices.quitar();
			conjAristas = G.aristas();
			currCoste = 0;
			while(!conjAristas.esVacio()) {
				Arista<T, float> currArista = conjAristas.quitar();
				if (currArista.getOrigen() == currVert.getObj()) {
					currCoste += currArista.getEtiqueta();
				}
			}
			if (maxCoste < currCoste) {
				maxCoste = currCoste;
				result = currVert.getObj();
			}
		}
	}
	return result;
}

// Version eficiente
template <typename T>
T verticeMaxCoste(const Grafo<T, float>& G) {
	T result;
	if (!G.esVacio()) {
		map<T, int> costeVertices;

	}
	return result;
}

//Ejercicio 2
/// USAR LOS ANTECESORES ES MUCHO MAS COSTOSO QUE USAR LOS ADYACENTES
template <typename T, typename U>
void inaccesibles(const Grafo<T, U>& G) {
	if (!G.esVacio()) {
		Conjunto<T> resultado;
		Conjunto<Vertice<T>> conjVertices = G.vertices();
		while(!conjVertices.esVacio()) {
			T currObj = conjVertices.quitar().getObj();
			if (G.antecesores(currObj).esVacio()) {
				resultado.anadir(currObj);
			}
		}
		while (!resultado.esVacio()) {
			cout << resultado.quitar() << " - ";
		}
		cout << endl;
	}
}


// Ejercicio 3
/// NO RECURSIVO Y USANDO QUEUE
template <typename T, typename U>
bool caminoEntreDos(const Grafo<T, U>& G, const T& vo, const T& vd) {
	if (G.esVacio())
		return false;

	queue<T> cola;
	Conjunto<Vertice<T> > adyacentes = G.adyacentes(vo);
	while (!adyacentes.esVacio()) {
		cola.push(adyacentes.quitar().getObj());
		adyacentes = G.adyacentes(cola.front());
		if (cola.front() == vd) {
			return true;
		}
		cola.pop();
	}
	return false;
}

//Ejercicio 4
/// RECURSIVO
// Consideramos que el grafo es dirigido
template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste) {
	Conjunto<Vertice<T>> adyacentes = G.adyacentes(u);
	Conjunto<Arista<T, float>> aristas;
	while(!adyacentes.esVacio()) {
		T currVert = adyacentes.quitar().getObj();
		// Tomamos etiqueta de la arista
		float camino;
		aristas = G.aristas();
		while (!aristas.esVacio()) {
			Arista<T, float> currArista = aristas.quitar();
			if (currArista.getOrigen() == u && currArista.getDestino() == currVert) {
				camino = currArista.getEtiqueta();
			}
		}
		if (maxCoste - camino >= 0) {
			cout << endl << u << " - ";
			caminosAcotados(G, currVert, maxCoste-camino);
		}
	}
}

//template <typename T>
//bool posibleCamino(const Grafo<T, float>& G, const T& u, float maxCoste) {
//	if (maxCoste < 0)
//		return false;
//
//	Conjunto<Vertice<T>> adyacentes = G.adyecentes(u);
//}


//Ejercicio 5
template <typename T, typename U>
T outConectado(const Grafo<T, U>& G)
{
}


//Ejercicio 6
template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v)
{
}


//********************************************************************//
int main()
{
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
    H.insertarVertice("Huelva"); H.insertarVertice("Lepe"); H.insertarVertice("Niebla");
    H.insertarVertice("Mazagon"); H.insertarVertice("Almonte"); H.insertarVertice("Aljaraque");
    H.insertarVertice("Matalasca�as");
    H.insertarArista("Lepe", "Huelva", 4);
    H.insertarArista("Huelva", "Niebla", 3);
    H.insertarArista("Huelva", "Mazagon", 2);
    H.insertarArista("Huelva", "Aljaraque", 1);
    H.insertarArista("Mazagon", "Almonte", 3);
    H.insertarArista("Mazagon", "Matalasca�as", 4);
    H.insertarArista("Aljaraque", "Mazagon", 5);
    H.insertarArista("Almonte", "Huelva", 6);


    cout << " Vertice de maximo coste en G: " << verticeMaxCoste(G) << endl;
    cout << " Vertice de maximo coste en H: " << verticeMaxCoste(H) << endl;


    cout << endl << " Vertices inaccesibles en G: ";
    inaccesibles(G);


    cout << endl << " Camino entre Dos en H de Lepe a Almonte: ";
    cout << (caminoEntreDos(H, string("Lepe"), string("Almonte")) ? " SI " : " NO ") << endl;
    cout << endl << " Camino entre Dos en H de Aljaraque a Lepe: ";
    cout << (caminoEntreDos(H, string("Aljaraque"), string("Lepe")) ? " SI " : " NO ") << endl;

    /// PRUEBAS
    cout << endl << " Camino entre Dos en H de Lepe a Matalasca�as: ";
    cout << (caminoEntreDos(H, string("Lepe"), string("Matalasca�as")) ? " SI " : " NO ") << endl;


    cout << endl << " Caminos acotados en G a coste 9 desde el vertice 2:" << endl;
    caminosAcotados(G, 2, 9);

/*
    cout << endl << endl << " Vertice outConectado en G: " << outConectado(G);
    cout << endl << " Vertice outConectado en H: " << outConectado(H);

    cout << endl << endl << " Recorrido en profundidad de H desde el vertice Huelva:  ";
    recorrido_profundidad(H, string("Huelva"));
    cout << endl << endl;
*/

    system("PAUSE");
    return 0;
}