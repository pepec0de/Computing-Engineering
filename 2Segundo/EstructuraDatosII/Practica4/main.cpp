#include <iostream>
#include <cstdlib>
#include "grafo.h"
#include "conjunto.h"
#include <queue>
#include <stack>
#include <sstream>
#include <map>


using namespace std;

template <typename A, typename B>
void printMap(const map<A, B>& mapV) {
    for (auto it = mapV.begin(); it != mapV.end(); it++) {
        cout << "{ " << it->first << " : " << it->second << "}\n";
    }
}

template <typename T>
void printQueue(const queue<T>& fifo) {
    queue<T> print = fifo;
    cout << "{ ";
    while (!print.empty()) {
        cout << print.front() << (print.size() == 1 ? " }\n" : ", ");
        print.pop();
    }
}

//Ejercicio 1
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

/// Mostrar todos los posibles caminos
// RECURSIVO
template <typename T, typename U>
void mostrarCaminos(const Grafo<T, U>& G, const T& vo, const T& vd) {
    if (!G.esVacio()) {
        map<T, bool> visitados;
        Conjunto<Vertice<T>> cVertices = G.vertices();
        while (!cVertices.esVacio())
            visitados[cVertices.quitar().getObj()] = false;

        queue<T> camino;
        bool check = false;
        buscarCaminos(G, vo, vd, visitados, camino, check);
        if (!check) {
            cout << "No existen caminos.\n";
        }
    }
}

template <typename T, typename U>
void buscarCaminos(const Grafo<T, U>& G, const T& vo, const T& vd, map<T, bool> &visitados, queue<T> camino, bool &check) {
    if (vo != vd) {
        visitados[vo] = true;
        camino.push(vo);
        Conjunto<Vertice<T>> cAdy = G.adyacentes(vo);
        T v;
        while (!cAdy.esVacio()) {
            v = cAdy.quitar().getObj();
            //cout << v << " - ";
            if (!visitados[v]) {
                buscarCaminos(G, v, vd, visitados, camino, check);
            }
        }
        visitados[vo] = false;
    } else {
        cout << "Camino: ";
        while (!camino.empty()) {
            cout << camino.front() << " - ";
            camino.pop();
        }
        cout << vd << endl;
        check = true;
    }
}

/// Mostrar camino mas corto
template <typename T, typename U>
void mostrarCaminoMasCorto(const Grafo<T, U>& G, const T& vo, const T& vd) {
    if (!G.esVacio()) {
        map<T, bool> visitados;
        Conjunto<Vertice<T>> cVertices = G.vertices();
        while (!cVertices.esVacio())
            visitados[cVertices.quitar().getObj()] = false;

        queue<T> camino;
        int caminoMin = G.numVertices();
        buscarCaminoMasCorto(G, vo, vd, visitados, camino, camino, 0, caminoMin);
        if (camino.empty()) {
            cout << "No existen caminos.\n";
        } else {
            cout << "Ruta mas corta: " << caminoMin << " -> ";
            printQueue(camino);
        }
    }
}

template <typename T, typename U>
void buscarCaminoMasCorto(const Grafo<T, U>& G, const T& vo, const T& vd, map<T, bool> &visitados,
                          queue<T> camino, queue<T>& resultado, int currCamino, int& caminoMin) {
    if (vo != vd) {
        visitados[vo] = true;
        if (currCamino < caminoMin) {
            camino.push(vo);
            Conjunto<Vertice<T>> cAdys = G.adyacentes(vo);
            T v;
            while (!cAdys.esVacio()) {
                v = cAdys.quitar().getObj();
                if (!visitados[v]) {
                    buscarCaminoMasCorto(G, v, vd, visitados, camino, resultado, currCamino + 1, caminoMin);
                }
            }
            visitados[vo] = false;
        }
    } else {
        caminoMin = currCamino;
        resultado = camino;
    }
}

template <typename T, typename U>
void muestra_ruta_mas_corta(const Grafo<T, U>& G, const T& vert_origen, const T& vert_destino) {
    queue<T> ruta;
    Conjunto<T> visitados;
    bool encontrado = calcular_ruta_mas_corta(G, vert_origen, vert_destino, ruta, visitados);

    if(!encontrado) {
        cout << "No existe ruta entre los vertices indicados\n";
    } else {
        while(!ruta.empty()) {
            cout << ruta.front() << " ";
            ruta.pop();
        }
        cout << endl;
    }
}

template <typename T, typename U>
bool calcular_ruta_mas_corta(const Grafo<T, U>& G, const T& vert_actual, const T& vert_destino, queue<T>& ruta_actual, Conjunto<T>& visitados) {
    bool encontrado_ruta = false;

    visitados.anadir(vert_actual);

    Conjunto<T> copia_visitados;
    queue<T> copia_original = ruta_actual;
    copia_original.push(vert_actual);

    Conjunto<Vertice<T>> adyacentes = G.adyacentes(vert_actual);

    if(adyacentes.esVacio()) {
        return false;
    }

    while(!adyacentes.esVacio()) {
        T item = adyacentes.quitar().getObj();

        if(item == vert_destino) {
            copia_original.push(vert_destino);
            ruta_actual = copia_original;
            return true;
        }

        if(visitados.pertenece(item)) continue;

        queue<T> copia_aux;
        copia_aux = copia_original;
        copia_visitados = visitados;

        bool encontrado = calcular_ruta_mas_corta(G, item, vert_destino, copia_aux, copia_visitados);

        if(encontrado) {
            if(!encontrado_ruta) {
                ruta_actual = copia_aux;
                encontrado_ruta = true;
            } else if(ruta_actual.size() >= copia_aux.size()) {
                ruta_actual = copia_aux;
            }
        }
    }
    return encontrado_ruta;
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
        }
        cout << endl;
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

template <typename T, typename U>
void recorrido_anchura(const Grafo<T, U>& G, const T& v) {
	map<T, bool> visitados;
	Conjunto<Vertice<T>> cVertices = G.vertices();
	while (!cVertices.esVacio()) {
		visitados[cVertices.quitar().getObj()] = false;
	}
	cout << v << " - ";
	visitados[v] = true;
	recorrido_anchura(G, v, visitados);
	cout << endl;
}

template <typename T, typename U>
void recorrido_anchura(const Grafo<T, U>& G, const T& v, map<T, bool>& visitados) {
	Conjunto<Vertice<T>> adys = G.adyacentes(v);
	queue<T> fifo;
	T w;
	while (!adys.esVacio()) {
		w = adys.quitar().getObj();
		if (!visitados[w]) {
			cout << w << " - ";
			visitados[w] = true;
			fifo.push(w);
		}
	}
	while (!fifo.empty()) {
		recorrido_anchura(G, fifo.front(), visitados);
		fifo.pop();
	}
}


// EXAMEN 2

struct conexion {
    bool salida = false;
    bool entrada = false;
};

template <typename T, typename U>
void vertices_internos(const Grafo<T, U>& G){
    // Vertices conectados
    map<T, conexion> vConectados;
    Conjunto<Arista<T, U>> cAristas = G.aristas();
    while(!cAristas.esVacio()) {
        Arista<T, U> currArista = cAristas.quitar();
        vConectados[currArista.getOrigen()].salida = true;
        vConectados[currArista.getDestino()].entrada = true;
    }

    typename map<T, conexion>::iterator it;
    for (it = vConectados.begin(); it != vConectados.end(); it++) {
        if (!(it->second.entrada && it->second.salida)) {
            vConectados.erase(it->first);
        }
    }
    cout << "Los vertices internos serian " << vConectados.size() << ": ";
    for (it = vConectados.begin(); it != vConectados.end(); it++) {
        cout << it->first << " ";
    }
    cout << endl << endl;
}

template <typename T, typename U>
void ruta_n_saltos(const Grafo<T, U>& G, const T& origen, int numsaltos){
    // Necesario un diccionario de visitados para que no existan ciclos
    map<T, bool> visitados;
    Conjunto<Vertice<T>> vertices = G.vertices();
    while(!vertices.esVacio()) {
        visitados[vertices.quitar().getObj()] = false;
    }
    queue<T> camino;
    ruta_n_saltos(G, origen, numsaltos, camino, visitados);
    cout << endl;
}

template <typename T, typename U>
void ruta_n_saltos(const Grafo<T, U>& G, const T& v, int numsaltos, queue<T> camino, map<T, bool>& visitados) {
    camino.push(v);
    if (numsaltos > 0) {
        visitados[v] = true;

        Conjunto<Vertice<T>> adyacentes = G.adyacentes(v);
        T w;
        while (!adyacentes.esVacio()) {
            w = adyacentes.quitar().getObj();
            if (!visitados[w])
                ruta_n_saltos(G, w, numsaltos - 1, camino, visitados);
        }
    } else {
        while (!camino.empty()) {
            cout << camino.front() << (camino.size() != 1 ? " - " : "\n");
            camino.pop();
        }
    }
}

//********************************************************************//
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

    Grafo<string, float> Prueba(2);
    Prueba.insertarVertice("Lepe");
    Prueba.insertarVertice("Huelva");
    Prueba.insertarArista("Lepe", "Huelva", 4);

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

    //mostrarCaminos(H, string("Lepe"), string("Matalascañas"));
    //mostrarCaminoMasCorto(H, string("Lepe"), string("efsfsf"));
    //muestra_ruta_mas_corta(H, string("Lepe"), string("sesgseg"));

//    cout << endl << " Caminos acotados en G a coste 9 desde el vertice 2:" << endl;
//    caminosAcotados(G, 2, 9);


//    cout << endl << endl << " Vertice outConectado en G: " << outConectado(G);
//    cout << endl << " Vertice outConectado en H: " << outConectado(H);

//    cout << endl << endl << " Recorrido en profundidad de H desde el vertice Huelva:  ";
//    recorrido_profundidad(H, string("Huelva"));
//    cout << endl << endl;
//    cout << endl << endl << " Recorrido en anchura de H desde el vertice Huelva:  ";
//    recorrido_anchura(H, string("Huelva"));
//    cout << endl << endl;

//
//    /// PRUEBA
//	cout << endl << endl << " Recorrido en profundidad de G desde el vertice 2:  ";
//    recorrido_profundidad(G, 2);
//    cout << endl << endl;

	cout<<"Vertices internos de G\n";
    vertices_internos(G);

    cout<<"Vertices internos de H\n";
    vertices_internos(H);

    cout<<"Rutas de 3 saltos desde el vertice 1\n";
    ruta_n_saltos(G, 1, 3);

    cout<<"\nRutas de 4 saltos desde el vertice Huelva\n";
    ruta_n_saltos(H, string("Huelva"), 4);

    cout<<"\nRutas de 3 saltos desde el vertice Huelva\n";
    ruta_n_saltos(H, string("Huelva"), 3);

    cout<<"\nRutas de 4 saltos desde el vertice Lepe\n";
    ruta_n_saltos(H, string("Lepe"), 4);

    cout<<"\nRutas de 4 saltos desde el vertice Matalascañas\n";
    ruta_n_saltos(H, string("Matalascañas"), 4);

    cout<<"\nRutas de 2 saltos desde el vertice Aljaraque\n";
    ruta_n_saltos(H, string("Aljaraque"), 2);


    //system("PAUSE");
    return 0;
}
