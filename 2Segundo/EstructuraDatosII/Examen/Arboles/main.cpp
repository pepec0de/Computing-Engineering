#include <iostream>
#include <cstdlib>
#include <queue>
#include "include/abb.h"
#include "include/arbin.h"

template <typename T>
int posicionPostorden(const ABB<T>& a, const T& obj) {
	int pos = 1;
	return posicionPostorden(a, obj, a.getRaiz(), &pos);
}

template <typename T>
int posicionPostorden(const ABB<T>& a, const T& obj, const typename ABB<T>::Iterador it, int *pos) {
	if (!it.arbolVacio()) {
		if (posicionPostorden(a, obj, a.subIzq(it), pos) != 0) {
			return *pos;
		}

		if (posicionPostorden(a, obj, a.subDer(it), pos) != 0) {
			return *pos;
		}

		if (it.observar() == obj)
			return *pos;

		(*pos)++;
	}
	return 0;
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

    ABB<int> BB6, ABB1;

	BB6.insertar(8); BB6.insertar(3); BB6.insertar(10); BB6.insertar(1); BB6.insertar(6);
    BB6.insertar(14); BB6.insertar(4); BB6.insertar(7); BB6.insertar(13);

    ABB1.insertar(5); ABB1.insertar(1); ABB1.insertar(3); ABB1.insertar(8);
    ABB1.insertar(6);

    cout << posicionPostorden(ABB1, 3) << endl;

    return 0;
}

