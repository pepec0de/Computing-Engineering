#ifndef MULTICONJUNTO_HPP
#define MULTICONJUNTO_HPP

#include "Persona.hpp"

template <typename T>
class Multiconjunto {

public:
    Multiconjunto ();
    // Constructor

    bool esVacio() const;
    // Comprueba si el multiconjunto es o no vac�o

    int cardinalidad() const;
    // Devuelve el n�mero de elementos

    void anade(const T& objeto);
    // A�ade un objeto de tipo T al multiconjunto
    // Se permiten elementos repetidos

    void elimina(const T& objeto);
    // Elimina todas las ocurrencias del objeto
    // pasado como par�metro

    bool pertenece(const T& objeto) const;
    // Comprueba si el objeto pasado como par�metro
    // existe en el multiconjunto

    private:
        T c[100];
        // Vector de almacenamiento de elementos
        int num;
        // Indica el n�mero de elementos en el multiconjunto
};

template class Multiconjunto<int>;
template class Multiconjunto<char>;
template class Multiconjunto<Persona>;

#endif // MULTICONJUNTO_HPP
