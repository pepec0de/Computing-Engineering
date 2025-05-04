#ifndef _CARTELERA_H_
#define _CARTELERA_H_

#include <map>
#include <utility>
#include <string>

using namespace std;

class Cartelera {
public:
    Cartelera();
    void insertaEspectaculo(const string& espectaculo);

    // Si el espectaculo que se pasa por parametro no existe se crea
    void insertaSala(const string& espectaculo, const string& sala, const string& ciudad);
    void eliminaEspectaculo(const string& espectaculo);
    void eliminaSala(const string& espectaculo, const string& sala);
    void listaEspectaculos();
    void listaSalas(const string& espectaculo);
private:
    typedef map<string, string> DSalas;
    typedef pair<string, string> PSalas;
    typedef map<string, DSalas> DEspectaculos;
    typedef pair<string, DSalas> PEspectaculos;
    DEspectaculos espectaculos;
};


#endif
