#include <cstdlib>
#include <iostream>
# include "cartelera.h"

using namespace std;

int main(int argc, char *argv[])
{

/// PASAR DE UN PUNTERO A INT PARA TOMAR LA DIRECCION
//	#include <stdint.h>
//	int foo;
//	long long int dir = reinterpret_cast<long long int>(&foo);
//	cout << foo << endl;
//	cout << &foo << endl;
//	cout << dir << endl;
//	cout << sizeof(dir) << endl;


//	map<int, string> prueba;
//	prueba.insert({1, "hola"});
//	auto itr = prueba.find(3);
//	if (prueba.find(3) == prueba.end())
//		cout << "no existe\n";
//	else
//		cout << itr->second << endl;
//	return 0;

    Cartelera c;

    c.insertaEspectaculo("Ready Player One");
    c.insertaEspectaculo("La Tribu");
    c.insertaEspectaculo("Black Panther");
    c.insertaEspectaculo("Ghost in the Shell");
    c.insertaEspectaculo("La Momia");
    c.insertaEspectaculo("Blade Runner 2049");
    c.insertaEspectaculo("Jumanji");
    c.listaEspectaculos();
    cout << endl;
    c.insertaSala("Jumanji", "Al Andalus", "Punta Umbria");
    c.insertaSala("Jumanji", "Cinebox Aqualon Puerto", "Huelva");
    c.insertaSala("Jumanji", "La Dehesa", "Isla Antilla");
    c.insertaSala("Jumanji", "Artesiete Holea", "Huelva");
    c.insertaEspectaculo("Jumanji");

    c.listaSalas("Jumanji");
    cout << endl;

    c.eliminaSala("Jumanji", "La Dehesa");
    cout << "Despues de eliminar la sala La Dehesa del espectaculo **Jumanji**" << endl;
    c.listaSalas("Jumanji");
    cout << endl;

    c.eliminaEspectaculo("Jumanji");
    cout << "Despues de eliminar el espectaculo **Jumanji**" << endl;
    c.listaEspectaculos();
    cout << endl;

    cout << "**Jumanji** ya se elimino como espectaculo." << endl;
    cout << "Despues de eliminar la sala La Dehesa de un espectaculo que no existe" << endl;

    c.eliminaSala("Jumanji", "La Dehesa");
    c.listaEspectaculos();
    cout << endl;

    c.listaSalas("Jumanji");
    c.listaEspectaculos();

    system("PAUSE");
    return EXIT_SUCCESS;
}

