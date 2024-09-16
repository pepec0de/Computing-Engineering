#include <iostream>
#include <cstdlib>
#include <queue>
#include "arbin.h"
#include "abb.h"
#include <math.h>

// Recorridos

template <typename T>
void inorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if (!r.arbolVacio()) {
        inorden(a, a.subIzq(r));
        cout << r.observar() << " ";
        inorden(a, a.subDer(r));
    }
}

template <typename T>
void preorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if (!r.arbolVacio()) {
        cout << r.observar() << " ";
        preorden(a, a.subIzq(r));
        preorden(a, a.subDer(r));
    }
}

template <typename T>
void postorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if (!r.arbolVacio()) {
        postorden(a, a.subIzq(r));
        postorden(a, a.subDer(r));
        cout << r.observar() << " ";
    }
}

template <typename T>
void anchura(const Arbin<T>& a) {
    if (!a.esVacio()) {
        queue<typename Arbin<T>::Iterador> c;
        typename Arbin<T>::Iterador ic = a.getRaiz();
        c.push(ic);
        while (!c.empty()) {
             ic = c.front();
             c.pop();
             cout << ic.observar() << " ";
             if (!a.subIzq(ic).arbolVacio())
                c.push(a.subIzq(ic));
             if (!a.subDer(ic).arbolVacio())
                c.push(a.subDer(ic));
        }
    }
}

/***************************************************************************/
/****************************** EJERCICIOS *********************************/
/***************************************************************************/
//Ejercicio 1

template <typename T> int numHojas(const Arbin<T>& a) {
    if (!a.esVacio()) {
        return contarHojas(a, a.getRaiz());
    }
    return 0;
}

template <typename T> int contarHojas(const Arbin<T>& a, const typename Arbin<T>::Iterador& it) {
    int nhojas = 0;
    if (!it.arbolVacio()) {
        if (!a.subIzq(it).arbolVacio())
            nhojas += contarHojas(a, a.subIzq(it));

        if (!a.subDer(it).arbolVacio())
            nhojas += contarHojas(a, a.subDer(it));

        if (a.subIzq(it).arbolVacio() && a.subDer(it).arbolVacio()) {
            nhojas++;
        }
    }
    return nhojas;
}

//Ejercicio 2


//Ejercicio 3

template <typename T> void recorridoZigzag(const Arbin<T>& a, char sentido) {
    if (!a.esVacio())
        switch (sentido) {
        case 'I':
            recorridoZigzag(a, a.getRaiz(), true);
            break;

        case 'D':
            recorridoZigzag(a, a.getRaiz(), false);
            break;
        }
}

template <typename T> void recorridoZigzag(const Arbin<T>& a, const typename Arbin<T>::Iterador& it, bool izq) {
    if (!it.arbolVacio()) {
        cout << it.observar() << " - ";
        recorridoZigzag(a, izq ? a.subIzq(it) : a.subDer(it), !izq);
    }
}

//Ejercicio 4

//template <typename T> int numNodos(const Arbin<T>& a, const typename Arbin<T>::Iterador& it) {
//    int num = 0;
//    if (!it.arbolVacio()) {
//        num++;
//        num += numNodos(a, a.subIzq(it));
//        num += numNodos(a, a.subDer(it));
//    }
//    return num;
//}

template <typename T> bool compensado(const Arbin<T>& a) {
    return compensado(a, a.getRaiz());
}

template <typename T> bool compensado(const Arbin<T>& a, const typename Arbin<T>::Iterador& it) {
    if (!it.arbolVacio()) {
        if (compensado(a, a.subIzq(it)) && compensado(a, a.subDer(it)))
         return abs( numNodos(a, a.subIzq(it)) - numNodos(a, a.subDer(it)) ) <= 1;
        return false;
    }
    return true;
}

//Ejercicio 5
template <typename T> void palabras(const Arbin<T>& a) {
    if (!a.esVacio()) {
        vector<typename Arbin<T>::Iterador> listaIt;
        palabras(a, a.getRaiz(), "");
    }
}

// primera version
template <typename T> void palabras(const Arbin<T>& a, const typename Arbin<T>::Iterador& it, vector<typename Arbin<T>::Iterador>& listaIt) {
    if (!it.arbolVacio()) {
        if (tieneHijos(a, it)) { // Caso recursivo : el nodo actual no es hoja
            listaIt.push_back(it);
            if (!a.subIzq(it).arbolVacio()) {
                vector<typename Arbin<T>::Iterador> lista1 = listaIt;
                palabras(a, a.subIzq(it), lista1);
            }
            if (!a.subDer(it).arbolVacio()) {
                vector<typename Arbin<T>::Iterador> lista2 = listaIt;
                palabras(a, a.subDer(it), lista2);
            }
        } else { // Caso base
            for (typename Arbin<T>::Iterador ite : listaIt) {
                cout << ite.observar();
            }
            listaIt.pop_back();
            cout << it.observar() << endl;
        }
    }
}

// segunda version
void palabras(const Arbin<char>& a, const Arbin<char>::Iterador& it, string s) {
    if (!it.arbolVacio()) {
        s.push_back(it.observar());
        if (!a.subIzq(it).arbolVacio()) {
            palabras(a, a.subIzq(it), s);
        }
        if (!a.subDer(it).arbolVacio()) {
            palabras(a, a.subDer(it), s);
        } else { // Caso base
            cout << s << endl;
        }
    }
}

//Ejercicio 6

//void siguienteMayor(const ABB<int>& a, const ABB<int>::Iterador& it, int x, int &n) throw(NoHaySiguienteMayor) {
//    if (!it.arbolVacio()) {
//        int curr = it.observar();
//        if (x >= curr) {
//            siguienteMayor(a, a.subDer(it), x, n);
//        } else if (x < curr) {
//            n = curr;
//            siguienteMayor(a, a.subIzq(it), x, n);
//        }
//    }
//}//
//int siguienteMayor(const ABB<int>& a, int x) throw(NoHaySiguienteMayor) {
//    if (!a.esVacio()) {
//        int n = 0;
//        siguienteMayor(a, a.getRaiz(), x, n);
//        if (n != 0) {
//            return n;
//        }
//    }
//    throw NoHaySiguienteMayor();
//}

//Ejercicio 7

//int posicionInorden(const ABB<int>& a, const ABB<int>::Iterador &it, int x, int &recorrido) {
//	int pos = 0;
//    if (!it.arbolVacio()) {
//        pos = posicionInorden(a, a.subIzq(it), x, recorrido);
//        if (pos == 0) {
//			recorrido++;
//			if (it.observar() == x)
//				pos = recorrido;
//			else
//				pos = posicionInorden(a, a.subDer(it), x, recorrido);
//        }
//    }
//	return pos;
//}

int posicionInorden(const ABB<int>& a, const ABB<int>::Iterador &it, int x, int &recorrido) {
	if (it.arbolVacio()) return 0;

	int left = posicionInorden(a, a.subIzq(it), x, recorrido);
	if (left != 0)
		return left;

	recorrido++;

	if (it.observar() == x) {
		return recorrido;
	}

	left = posicionInorden(a, a.subDer(it), x, recorrido);
	if (left != 0)
		return left;
	else
		return 0;
}

int posicionInorden(const ABB<int>& a, int x) {
    int res = 0;
    if (!a.esVacio()) {
		return posicionInorden(a, a.getRaiz(), x, res);
		//return res;
    }

    return 0;
}

//Ejercicio 8

bool haySumaCamino(const Arbin<int>& a, int suma, const Arbin<int>::Iterador& it) {
	if (!it.arbolVacio())
		return (haySumaCamino(a, suma - it.observar(), a.subIzq(it)) || haySumaCamino(a, suma - it.observar(), a.subDer(it)));
	else if (suma == 0)
		return true;
	return false;
}

bool haySumaCamino(const Arbin<int>& a, int suma) {
	if (!a.esVacio()) {
		return haySumaCamino(a, suma, a.getRaiz());
	}
	return false;
}
template <typename T>
const Arbin<T>& simetrico(const Arbin<T>& a) {
	return *simetrico(a, a.getRaiz());
}

template <typename T>
const Arbin<T>* simetrico(const Arbin<T> &a, const typename Arbin<T>::Iterador &it) {
	return it.arbolVacio() ?
		new Arbin<T>()
	:
		new Arbin<T>(it.observar(), *simetrico(a, a.subDer(it)), *simetrico(a, a.subIzq(it)));
}

template <typename T>
bool cargaPar(const Arbin<T>& a) {
	if (!a.esVacio()) {
		return cargaPar(a, a.getRaiz());
	}
	return false;
}

template <typename T> int numNodos(const Arbin<T>& a, const typename Arbin<T>::Iterador& it) {
    int num = 0;
    if (!it.arbolVacio()) {
        num++;
        num += numNodos(a, a.subIzq(it));
        num += numNodos(a, a.subDer(it));
    }
    return num;
}

template <typename T>
bool cargaPar(const Arbin<T>& a, const typename Arbin<T>::Iterador& it) {
	if (!it.arbolVacio()) {
		if (cargaPar(a, a.subIzq(it)) && cargaPar(a, a.subDer(it)))
			return true;
		int r = abs(numNodos(a, a.subIzq(it)) - numNodos(a, a.subDer(it)));
		return r == 0 || r == 2;
	}
	return false;
}


/*
	En el L3 de Prácticas de ED2 entró el te da una palabra y si coincide con un camino de tu árbol devuelves true y si no false
	Y el otro ejercicio era el mismo que las prácticas de el siguiente mayor pero el menor
*/

bool tienePalabra(const Arbin<char>& a, const Arbin<char>::Iterador& it, string palabra) {
	if (palabra.size() != 0 && !it.arbolVacio()) {
		if (it.observar() == palabra[0]) {
			if (palabra.size() == 1)
				return true;
			string nueva;
			for (int i = 1; i < (int)palabra.size(); i++) nueva.push_back(palabra[i]);
			palabra = nueva;
		} else
			return false;

		if (tienePalabra(a, a.subIzq(it), palabra) || tienePalabra(a, a.subDer(it), palabra)) {
			return true;
		}
	}
	return false;
}

bool tienePalabra(const Arbin<char>& a, string palabra) {
	if (!a.esVacio())
		return tienePalabra(a, a.getRaiz(), palabra);
	return false;
}
int nit = 0;

template <typename T>
const T& siguienteMenor(const ABB<T>& a, const typename ABB<T>::Iterador &it, const T& x) throw (NoHaySiguienteMenor) {
	if (!it.arbolVacio()) {
		const T& v = it.observar();
		try {
			if (v >= x)
				return siguienteMenor(a, a.subIzq(it), x);
			else
				return siguienteMenor(a, a.subDer(it), x);
		} catch(const NoHaySiguienteMenor& e) {
			if (v < x)
				return v;
		}
	}
	throw NoHaySiguienteMenor();
}

template <typename T>
const T& siguienteMenor(const ABB<T>& a, const T& x) throw (NoHaySiguienteMenor) {
	if (!a.esVacio()) {
		return siguienteMenor(a, a.getRaiz(), x);
	}
	throw NoHaySiguienteMenor();
}

template <typename T>
const T& siguienteMayor(const ABB<T>& a, const typename ABB<T>::Iterador &it, const T& x) throw (NoHaySiguienteMayor) {
	if (!it.arbolVacio()) {
		const T& v = it.observar();
		try {
			return v <= x ? siguienteMayor(a, a.subDer(it), x) : siguienteMayor(a, a.subIzq(it), x);
		} catch (const NoHaySiguienteMayor& e) {
			if (v > x)
				return v;
		}
	}
	throw NoHaySiguienteMayor();
}

template <typename T>
const T& siguienteMayor(const ABB<T>& a, const T& x) throw (NoHaySiguienteMayor) {
	if (!a.esVacio()) {
		return siguienteMayor(a, a.getRaiz(), x);
	}
	throw NoHaySiguienteMayor();
}


int main(int argc, char *argv[])
{
    Arbin<char> A('t', Arbin<char>('m', Arbin<char>(),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));

    Arbin<char> B('t', Arbin<char>('n', Arbin<char>(),
                                        Arbin<char>('d', Arbin<char>('e', Arbin<char>(), Arbin<char>()),
                                                         Arbin<char>())),
                       Arbin<char>('m', Arbin<char>('f', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('n', Arbin<char>(), Arbin<char>())));

    Arbin<char> D('t', Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()),
                       Arbin<char>('m', Arbin<char>(),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())));

    Arbin<char> E('o', Arbin<char>('r', Arbin<char>(),
                                        Arbin<char>('o', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('l', Arbin<char>('a', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('e', Arbin<char>(), Arbin<char>())));

    Arbin<int> F(2, Arbin<int>(7, Arbin<int>(2, Arbin<int>(), Arbin<int>()),
                                  Arbin<int>(6, Arbin<int>(5, Arbin<int>(), Arbin<int>()),
                                                Arbin<int>(11, Arbin<int>(), Arbin<int>()))),
                    Arbin<int>(5, Arbin<int>(),
                                  Arbin<int>(9, Arbin<int>(),
                                                  Arbin<int>(4, Arbin<int>(), Arbin<int>()))));

    ABB<int> BB6, BB7;

	cout << "Tiene A cargaPar? " << (cargaPar(A) ? "SI" : "NO") << endl;

	cout << "Tiene E la palabra oro? " << (tienePalabra(E, "oro") ? "SI" : "NO") << endl;
	cout << "Tiene E la palabra ola? " << (tienePalabra(E, "ola") ? "SI" : "NO") << endl;
	cout << "Tiene E la palabra ori? " << (tienePalabra(E, "ori") ? "SI" : "NO") << endl;
	cout << "Tiene E la palabra ole? " << (tienePalabra(E, "ole") ? "SI" : "NO") << endl;
	cout << "Tiene E la palabra aei? " << (tienePalabra(E, "aei") ? "SI" : "NO") << endl;

	BB6.insertar(8); BB6.insertar(3); BB6.insertar(10); BB6.insertar(1); BB6.insertar(6);
    BB6.insertar(14); BB6.insertar(4); BB6.insertar(7); BB6.insertar(13);
	try
    {
        cout << "Siguiente menor en BB6 de 5: " << siguienteMenor(BB6, 5) << endl;
        cout << "Siguiente menor en BB6 de 8: " << siguienteMenor(BB6, 8) << endl;
        cout << "Siguiente menor en BB6 de 13: " << siguienteMenor(BB6, 13) << endl;
        cout << "Siguiente menor en BB6 de 14: " << siguienteMenor(BB6, 14) << endl;
        cout << "Siguiente menor en BB6 de 1: " << siguienteMenor(BB6, 1) << endl;
    }
    catch(const NoHaySiguienteMenor& e)
    {
        cout << e.Mensaje() << endl << endl;
    }
    system("PAUSE");


    // NUMERO HOJAS //
    cout << "Num. hojas del arbol B: " << numHojas(B) << endl;
    cout << "Num. hojas del arbol E: " << numHojas(E) << endl << endl;


    // COPIA SIMETRICA //
    Arbin<char> C = simetrico(B);
    cout << "Recorrido en inorden del arbol B: " << endl;
    inorden(B, B.getRaiz());
    cout << endl << "Recorrido en inorden del arbol C: " << endl;
    inorden(C, C.getRaiz());
    cout << endl << endl;

    // RECORRIDO EN ZIG-ZAG //
    cout << "Recorrido en zigzag I de B:\n";
    recorridoZigzag(B, 'I');
    cout << endl;
    cout << "Recorrido en zigzag D de C:\n";
    recorridoZigzag(C, 'D');
    cout << endl << endl;


    // COMPENSADO //
    cout << "Esta A compensado?:";
    cout << (compensado(A) ? " SI" : " NO") << endl;
    cout << "Esta B compensado?:";
    cout << (compensado(B) ? " SI" : " NO") << endl << endl;


    // PALABRAS DE UN ARBOL //
    cout << "PALABRAS DE E:\n";
    palabras(E);
    cout << "PALABRAS DE B:\n";
    palabras(B);
    cout << endl;

    // SIGUIENTE MAYOR
    BB6.insertar(8); BB6.insertar(3); BB6.insertar(10); BB6.insertar(1); BB6.insertar(6);
    BB6.insertar(14); BB6.insertar(4); BB6.insertar(7); BB6.insertar(13);
    try
    {
        cout << "Siguiente mayor en BB6 de 5: " << siguienteMayor(BB6, 5) << endl;
        cout << "Siguiente mayor en BB6 de 8: " << siguienteMayor(BB6, 8) << endl;
        cout << "Siguiente mayor en BB6 de 13: " << siguienteMayor(BB6, 13) << endl;
        cout << "Siguiente mayor en BB6 de 14: " << siguienteMayor(BB6, 14) << endl;
    }
    catch(const NoHaySiguienteMayor& e)
    {
        cout << e.Mensaje() << endl << endl;
    }

    // POSICION INORDEN //
    BB7.insertar(5); BB7.insertar(1); BB7.insertar(3); BB7.insertar(8); BB7.insertar(6);
    inorden(BB7, BB7.getRaiz());
    cout << endl;
    cout << "Posicion Inorden en BB7 de 3: ";
    cout << posicionInorden(BB7, 3);
    cout << endl << "Posicion Inorden en BB7 de 8: ";
    cout << posicionInorden(BB7, 8);
    cout << endl << "Posicion Inorden en BB7 de 7: ";
    cout << posicionInorden(BB7, 7);
    cout << "\nPosicion Inorden en BB7 de 1: ";
    cout << posicionInorden(BB7, 1);
    cout << endl << endl;

    // SUMA CAMINO
    cout << "Hay un camino de suma 26 en F?:";
    cout << (haySumaCamino(F, 26) ? " SI" : " NO") << endl;
    cout << "Hay un camino de suma 9 en F?:";
    cout << (haySumaCamino(F, 9) ? " SI" : " NO") << endl;


    system("PAUSE");
    return 0;
}

