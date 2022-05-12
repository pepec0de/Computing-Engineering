#include "cartelera.h"
#include <iostream>

using namespace std;

Cartelera::Cartelera(): espectaculos() {

}

void Cartelera::insertaEspectaculo(const string& espectaculo) {
	PEspectaculos par;
	par.first = espectaculo;
	espectaculos.insert(par);
}

void Cartelera::insertaSala(const string& espectaculo, const string& sala, const string& ciudad) {
	// Comprobamos que exista el espectaculo
	if (espectaculos.find(espectaculo) == espectaculos.end()) {
		// Añadimos el espectaculo
		insertaEspectaculo(espectaculo);
	}

	espectaculos.at(espectaculo).emplace(sala, ciudad);

	/// FORMA CORRECTA
	espectaculos[espectaculo].emplace(sala, ciudad);
}

void Cartelera::eliminaEspectaculo(const string& espectaculo) {
	espectaculos.erase(espectaculo);
}

void Cartelera::eliminaSala(const string& espectaculo, const string& sala) {
	// Comprobamos que exista el espectaculo
//	auto itr = espectaculos.find(espectaculo);
//	if (itr != espectaculos.end()) {
//		itr->second.erase(sala);
//	} else {
//		cout << "No existe el espectaculo " << espectaculo << endl;
//	}

	/// FORMA CORRECTA
	DEspectaculos::iterator itr = espectaculos.find(espectaculo);
	if (itr != espectaculos.end())
		itr->second.erase(sala);
}

void Cartelera::listaEspectaculos() {
	if (espectaculos.empty()) {
		cout << "No hay espectaculos\n";
	} else {
		cout << "LISTA DE ESPECTACULOS\n";
		cout << "*********************\nPELICULA\n";
		for (PEspectaculos p : espectaculos) {
			cout << p.first << endl;
		}
		cout << "*********************\n";
	}
}

void Cartelera::listaSalas(const string& espectaculo) {
	DEspectaculos::iterator itr = espectaculos.find(espectaculo);
	if (itr != espectaculos.end()) {
		if (itr->second.empty()) {
			cout << "No hay salas para el espectaculo : " << espectaculo << endl;
		} else {
			cout << "LISTA DE SALAS de ESPECTACULO : " << espectaculo << endl;
			cout << "********************************";
			for (int i = 0; i < (int)espectaculo.size(); i++) cout << "*";
			cout << endl;
			cout << "SALA\tCIUDAD\n";

			for (PSalas s : itr->second) {
				cout << s.first << "\t" << s.second << endl;
			}

			cout << "********************************";
			for (int i = 0; i < (int)espectaculo.size(); i++) cout << "*";
			cout << endl;
		}
	} else {
		cout << "No existe el espectaculo " << espectaculo << endl;
	}
}

