#include "Multiconjunto.hpp"

template <typename T> Multiconjunto<T>::Multiconjunto() {
    //ctor
    num = 0;
}

template <typename T> bool Multiconjunto<T>::esVacio() const {
// Comprueba si el multiconjunto es o no vacío
    return num == 0;
}

template <typename T> int Multiconjunto<T>::cardinalidad() const {
// Devuelve el número de elementos
    return num;
}

template <typename T> void Multiconjunto<T>::anade(const T& objeto) {
// Añade un objeto de tipo T al multiconjunto
// Se permiten elementos repetidos
    c[num++] = objeto;
}

template <typename T> void Multiconjunto<T>::elimina(const T& objeto) {
// Elimina todas las ocurrencias del objeto
// pasado como parámetro

    // forma simple:
    for (int i = 0; i < num; i++)
        if (c[i] == objeto) {
            num--;
            for (int j = i--; j < num; j++)
                c[j] = c[j+1];
        }

    // posible mejora de eficiencia
    /*int eliminar[num];
    int n = 0;
    for (int i = 0; i < num; i++)
        if (c[i] == objeto) {
            eliminar[n++] = i;
            cout << "n = " << n-1 << " -> " << eliminar[n-1] << endl;
        }

    int cont = 1;
    for (int i = eliminar[0]; i < num; i++) {
        if (i+1 != num) { // Comprobamos que no sea el ultimo elemento
            int j; // cantidad de elementos que hay que saltar
            for (j = 1; j < (num - cont); j++) {
                if (c[eliminar[cont++]] != objeto) break;
            }
            c[i] = c[i+j];
        }
    }
    num = num - n;*/
}

template <typename T> bool Multiconjunto<T>::pertenece(const T& objeto) const {
// Comprueba si el objeto pasado como parámetro
// existe en el multiconjunto
    int i = 0;
    while ( !(c[i] == objeto) && i < num) i++;
    return i != num;
}
