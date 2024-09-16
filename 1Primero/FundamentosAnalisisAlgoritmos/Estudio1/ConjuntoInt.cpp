#include "ConjuntoInt.h"


void ConjuntoInt::GeneraVector(vector<int>& v, int talla) {
	for (int i = 0; i < talla; i++) {
		v.push_back(rand() % 1000);
	}
}

void ConjuntoInt::escribe(vector<int> v) {
	for (unsigned i = 0; i < v.size(); i++) {
		cout << v[i] << " ";
	}
	cout << endl;
}