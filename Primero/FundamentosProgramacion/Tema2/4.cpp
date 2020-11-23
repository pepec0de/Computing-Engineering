#include <iostream>

using namespace std;

int main() {
	int n1, n2, temp;
	cout << "Inserte el valor de n1: ";
	cin >> n1;
	cout << "Inserte el valor de n2: ";
	cin >> n2;
	cout << endl;
	temp = n1;
	n1 = n2;
	n2 = temp;
	cout << "Valor de n1: " << n1 << endl;
	cout << "Valor de n2: " << n2 << endl;
}
