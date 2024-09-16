#include <iostream>

using namespace std;

int main() {
	int nBytes;
	
	cout << "Indique un numero de bytes: ";
	cin >> nBytes;
	cout << endl;
	cout << "Kilobytes: " << nBytes/1024 << endl;
}
