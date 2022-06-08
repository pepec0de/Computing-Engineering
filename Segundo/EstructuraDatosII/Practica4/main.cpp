#include <iostream>
#include <cstdlib>
#include "grafo.h"
#include "conjunto.h"
#include <queue>
#include <stack>
#include <sstream>
#include <map>


using namespace std;

//Ejercicio 1

/// Version bastante ineficiente
//template <typename T>
//T verticeMaxCoste1(const Grafo<T, float>& G) {
//	T result;
//	if (!G.esVacio()) {
//		Conjunto<Vertice<T>> conjVertices = G.vertices();
//		Conjunto<Arista<T, float> > conjAristas;
//		float maxCoste = 0, currCoste;
//
//		while(!conjVertices.esVacio()) {
//			Vertice<T> currVert = conjVertices.quitar();
//			conjAristas = G.aristas();
//			currCoste = 0;
//			// INEFICIENCIA : Se itera por todo el conjunto de aristas tantas veces como el numero de vertices
//			while(!conjAristas.esVacio()) {
//				Arista<T, float> currArista = conjAristas.quitar();
//				if (currArista.getOrigen() == currVert.getObj()) {
//					currCoste += currArista.getEtiqueta();
//				}
//			}
//			if (maxCoste < currCoste) {
//				maxCoste = currCoste;
//				result = currVert.getObj();
//			}
//		}
//	}
//	return result;
//}

template <typename T>
T verticeMaxCoste(const Grafo<T, float>& G) {
	if (!G.esVacio()) {
		T vResultado;
		float maxCoste = 0;
		map<T, float> costeVertices;
		Conjunto<Vertice<T>> conjVertices = G.vertices();
		while (!conjVertices.esVacio()) {
			costeVertices[conjVertices.quitar().getObj()] = 0;
		}
		Conjunto<Arista<T, float>> conjAristas = G.aristas();
		while (!conjAristas.esVacio()) {
			Arista<T, float> currA = conjAristas.quitar();
			costeVertices[currA.getOrigen()] += currA.getEtiqueta();
		}
		for (auto it = costeVertices.begin(); it != costeVertices.end(); it++) {
			if (maxCoste < it->second) {
				maxCoste = it->second;
				vResultado = it->first;
			}
		}
		return vResultado;
	}
	return T();
}

//Ejercicio 2
template <typename T, typename U>
void inaccesibles(const Grafo<T, U>& G) {
	if (!G.esVacio()) {
		Conjunto<Vertice<T>> cVertices = G.vertices();
		map<T, bool> accesibles;
		while (!cVertices.esVacio()) {
			accesibles[cVertices.quitar().getObj()] = false;
		}
		Conjunto<Vertice<T>> cAux;
		cVertices = G.vertices();
		while (!cVertices.esVacio()) {
			cAux = G.adyacentes(cVertices.quitar().getObj());
			while (!cAux.esVacio()) {
				accesibles[cAux.quitar().getObj()] = true;
			}
		}
		for (auto it = accesibles.begin(); it != accesibles.end(); it++)
			if (!it->second)
				cout << it->first << " - ";
		cout << endl;
	}
}


// Ejercicio 3
/// NO RECURSIVO Y USANDO QUEUE
/// ARREGLAR
template <typename T, typename U>
bool caminoEntreDos(const Grafo<T, U>& G, const T& vo, const T& vd) {
	if (G.esVacio())
		return false;

	queue<T> cola;
	T v;
	Conjunto<Vertice<T>> adyacentes = G.adyacentes(vo);
	int n = 0;
	do {
		while (!adyacentes.esVacio()) {
			v = adyacentes.quitar().getObj();
			cola.push(v);
			n++;
			if (v == vd)
				return true;
		}
		do {
			if (n > G.numVertices() || cola.empty())
				return false;
			adyacentes = G.adyacentes(cola.front());
			cola.pop();
		} while (adyacentes.esVacio());
	} while (!adyacentes.esVacio());
	return false;
}

/// EXTENSION
// RECURSIVO
template <typename T, typename U>
void mostrarCaminos(const Grafo<T, U>& G, const T& vo, const T& vd) {
	if (!G.esVacio()) {
		map<T, bool> visitados;
		Conjunto<Vertice<T>> cVertices = G.vertices();
		while (!cVertices.esVacio())
			visitados[cVertices.quitar().getObj()] = false;

		queue<T> camino;
		if (!buscarCaminos(G, vo, vd, visitados, camino)) {
			cout << "No existen caminos.\n";
		}
	}
}

template <typename T, typename U>
bool buscarCaminos(const Grafo<T, U>& G, const T& vo, const T& vd, map<T, bool> &visitados, queue<T> camino) {
	bool result = false;
	if (vo != vd) {
		visitados[vo] = true;
		camino.push(vo);
		Conjunto<Vertice<T>> cAdy = G.adyacentes(vo);
		T v;
		while (!cAdy.esVacio()) {
			v = cAdy.quitar().getObj();
			if (!visitados[v]) {
				result = buscarCaminos(G, v, vd, visitados, camino);
			}
		}
		visitados[vo] = false;
	} else {
		cout << "CAMINO ENCONTRADO: ";
		while (!camino.empty()) {
			cout << camino.front() << " - ";
			camino.pop();
		}
		cout << vd << endl;
		return true;
	}
	return result;
}



//Ejercicio 4
/// RECURSIVO
// Consideramos que el grafo es dirigido

template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste) {
	queue<T> aux;
	cout << endl;
	caminosAcotados(G, u, maxCoste, aux);
	cout << endl;
}

template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste, queue<T> aux) {
	Conjunto<Vertice<T>> adyacentes = G.adyacentes(u);
	Conjunto<Arista<T, float>> aristas;
	float coste;

	aux.push(u);
	if(aux.size() != 1) {
		for (int i = 0; i < aux.size(); i++) {
			cout << aux.front() << " - ";
			aux.push(aux.front());
			aux.pop();
		}cout << endl;
	}

	while(!adyacentes.esVacio()) {
		T currVert = adyacentes.quitar().getObj();

		// Tomamos el coste de la arista
		aristas = G.aristas();
		while (!aristas.esVacio()) {
			Arista<T, float> currArista = aristas.quitar();
			// Buscamos la arista para calcular el coste
			if (currArista.getOrigen() == u && currArista.getDestino() == currVert) {
				coste = maxCoste - currArista.getEtiqueta();
				break;
			}
		}
		//---------------------------

		if(coste >= 0) {
			caminosAcotados(G, currVert, coste, aux);
		}
	}
}

//Ejercicio 5
template <typename T, typename U>
T outConectado(const Grafo<T, U>& G) {
	T resultado;
	if (!G.esVacio()) {
		// Diccionario que por cada vertice almacenara la diferencia: N Adyacentes - N Antecesores
		map<T, int> difVert;
		Conjunto<Vertice<T>> vertices = G.vertices();
		while (!vertices.esVacio())
			difVert[vertices.quitar().getObj()] = 0;

		Conjunto<Arista<T, U>> aristas = G.aristas();
		while (!aristas.esVacio()) {
			Arista<T, U> currArista = aristas.quitar();
			difVert[currArista.getOrigen()]++;
			difVert[currArista.getDestino()]--;
		}

		for (auto it = difVert.begin(); it != difVert.end(); it++) {
			if (it->second > 0) {
				resultado = it->first;
				break;
			}
		}
	}
	return resultado;
}

//Ejercicio 6
template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v) {
	map<T, bool> visitados;
	Conjunto<Vertice<T>> cv = G.vertices();
	while (!cv.esVacio()) {
		visitados[cv.quitar().getObj()] = false;
	}
	recorrido_profundidad(G, v, visitados);
	cout << endl;
}

template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v, map<T, bool>& visitados) {
	cout << v << " - ";
	visitados[v] = true;
	Conjunto<Vertice<T>> adys = G.adyacentes(v);
	T w;
	while (!adys.esVacio()) {
		w = adys.quitar().getObj();
		if (!visitados[w]) recorrido_profundidad(G, w, visitados);
	}
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
    H.insertarVertice("Matalascañas");

    H.insertarArista("Lepe", "Huelva", 4);
    H.insertarArista("Huelva", "Niebla", 3);
    H.insertarArista("Huelva", "Mazagon", 2);
    H.insertarArista("Huelva", "Aljaraque", 1);
    H.insertarArista("Mazagon", "Almonte", 3);
    H.insertarArista("Mazagon", "Matalascañas", 4);
    H.insertarArista("Aljaraque", "Mazagon", 5);
    H.insertarArista("Almonte", "Huelva", 6);


//    cout << " Vertice de maximo coste en G: " << verticeMaxCoste(G) << endl;
//    cout << " Vertice de maximo coste en H: " << verticeMaxCoste(H) << endl;


//    cout << endl << " Vertices inaccesibles en G: ";
//    inaccesibles(G);


//    cout << endl << " Camino entre Dos en H de Lepe a Almonte: ";
//    cout << (caminoEntreDos(H, string("Lepe"), string("Almonte")) ? " SI " : " NO ") << endl;
//    cout << endl << " Camino entre Dos en H de Aljaraque a Lepe: ";
//    cout << (caminoEntreDos(H, string("Aljaraque"), string("Lepe")) ? " SI " : " NO ") << endl;
//    cout << endl << " Camino entre Dos en H de Niebla a Mazagon: ";
//    cout << (caminoEntreDos(H, string("Niebla"), string("Mazagon")) ? " SI " : " NO ") << endl;

	mostrarCaminos(H, string("Lepe"), string("Almonte"));

//    cout << endl << " Caminos acotados en G a coste 9 desde el vertice 2:" << endl;
//    caminosAcotados(G, 2, 9);


//    cout << endl << endl << " Vertice outConectado en G: " << outConectado(G);
//    cout << endl << " Vertice outConectado en H: " << outConectado(H);

//    cout << endl << endl << " Recorrido en profundidad de H desde el vertice Huelva:  ";
//    recorrido_profundidad(H, string("Huelva"));
//    cout << endl << endl;
//
//    /// PRUEBA
//	cout << endl << endl << " Recorrido en profundidad de G desde el vertice 2:  ";
//    recorrido_profundidad(G, 2);
//    cout << endl << endl;


    //system("PAUSE");
    return 0;
}
