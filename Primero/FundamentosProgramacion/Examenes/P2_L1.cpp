#include <iostream>
#include <cstring>
#include <ctime>
#include <stdlib.h>
#define N 5

using namespace std;

typedef char cadena[20];

class Atleta {
	int dorsal;
	cadena nombre;
	float marca;

public:
	Atleta();
	void set_dorsal(int d);
	void set_nombre(cadena nom);
	void set_marca(float mar);
	int get_dorsal();
	void get_nombre(cadena nom);
	float get_marca();
};

Atleta::Atleta()
{
    dorsal=0;
    strcpy(nombre, " ");
    marca = 0.0;
}

void Atleta::set_dorsal(int d)
{
    dorsal = d;
}

void Atleta::set_nombre(cadena nom)
{
    strcpy(nombre, nom);
}

void Atleta::set_marca(float mar)
{
	marca = mar;
}

int Atleta::get_dorsal()
{
	return dorsal;
}

void Atleta::get_nombre(cadena nom)
{
	strcpy(nom, nombre);
}

float Atleta::get_marca()
{
	return marca;
}

class Maraton {
	Atleta Carrera[N];
	int numAtletas;
public:
    Maraton();
	void setNumAtletas(int natl);
	int getNumAtletas();
	bool Completa(int maximo);
	Atleta Consultar(int pos);
	void Insertar(Atleta s);
	void Actualizar (Atleta a, int pos);
	int Buscar(int dor);
	void Eliminar(int i);
	void SimularCarrera();
};

Maraton::Maraton()
{
	numAtletas = 0;
}

void Maraton::setNumAtletas(int natl)
{
	numAtletas = natl;
}

int Maraton::getNumAtletas()
{
	return numAtletas;
}

bool Maraton::Completa(int maximo)
{
	if (numAtletas == maximo)
		 return true;
	else return false;
}

Atleta Maraton::Consultar(int posicion)
{
	return Carrera[posicion];
}

void Maraton::Insertar(Atleta a)
//Método que inserta el Atleta a al final del atributo Carrera: 0,5 puntos
{
	//A IMPLEMENTAR EN LA PRUEBA
    Carrera[numAtletas] = a;
    numAtletas++;
}

void Maraton::Actualizar(Atleta a, int pos)
{
	Carrera[pos] = a;
}

int Maraton::Buscar(int dor)
//Método que busca el dorsal dor en la Carrera y devuelve la posición si está o -1 si no está: 0,5 puntos
{
    int resultado = -1;
	//A IMPLEMENTAR EN LA PRUEBA
	int i = 0;
	// Bucle de busqueda que sume la cuenta mientras que no se encuentre la posicion
	while(i < numAtletas && Carrera[i].get_dorsal() != dor) i++;

	if (i != numAtletas) resultado = i;
	return resultado;
}

void Maraton::Eliminar(int i)
//Método que elimina el Atleta que se encuentra en la posicion i de la Carrera: 0,5 puntos
{
	//A IMPLEMENTAR EN LA PRUEBA
	// Restamos la cuenta -1 para que el ultimo atleta no sea igual a uno que no exista
	numAtletas--;
	for (int x = i; x < numAtletas; x++) {
        Carrera[x] = Carrera[x+1];
	}
}

void Maraton::SimularCarrera()
{
	int i;
	cadena nombre;
	int dorsal;
	Atleta A;
	srand(time(NULL));
	float marca;
	for (i=0;i<numAtletas;i++)
	{
		marca=rand()%25000 + 7000;
		dorsal = Consultar(i).get_dorsal();
		A.set_dorsal(dorsal);
		Consultar(i).get_nombre(nombre);
		A.set_nombre(nombre);
		A.set_marca(marca);
		Actualizar(A, i);
	}
}

void Clasificacion (Maraton M, int &primero, int &segundo, int &tercero)
//Función genérica que permite conocer los 3 primeros puestos de la maraton: 1 punto
{
	//A IMPLEMENTAR EN LA PRUEBA
	// Declaramos variables auxiliares
    Atleta atletaAux; // variable atleta temporal
	// Hacemos un algoritmo que ordene las marcas de menor a mayor
    for (int i = 0; i < M.getNumAtletas(); i++) {
        int atleta_max = i; // Atleta con la marca maxima
        // Buscamos una marca mas pequeña en lo que queda de vector de atletas
        for (int j = i+1; j < M.getNumAtletas(); j++) {
            if (M.Consultar(atleta_max).get_marca() > M.Consultar(j).get_marca()) {
                atleta_max = j;
            }
        }
        // Intercambiamos la posicion de los atletas ayundandonos de una variable auxiliar
        atletaAux = M.Consultar(i);
        M.Actualizar(M.Consultar(atleta_max), i);
        M.Actualizar(atletaAux, atleta_max);
    }

    // Devolvemos las tres primeras posiciones
    primero = M.Consultar(0).get_dorsal();
    segundo = M.Consultar(1).get_dorsal();
    tercero = M.Consultar(2).get_dorsal();
}

char menu(int nAtletas) {
   char opcion;
   ///system ("cls");
   cout << "\n\nMaraton\n";
   cout << "------------------------------------------\n";
   cout << "Atletas: "<<nAtletas<<"\n\n\n";
   cout << "         1. Consulta de inscripciones\n";
   cout << "         2. Inscripcion a la maraton\n";
   cout << "         3. Eliminar una inscripcion \n";
   cout << "         4. Simular Carrera\n";
   cout << "         5. Mostrar Podium\n";
   cout << "         6. Salir\n";
   cout << "\n";
   cout << " Indique la opcion deseada : ";
   cin >> opcion;
   return opcion;
}

int main()//Completar main 1 punto: 0,25 cada apartado
{
	Maraton M;
	char op;
	int i, oro, plata, bronce;
	cadena name;
	float mark;
	int dorsal=0;
	Atleta A;
	do {
        //**************************************(1)
        op = menu(M.getNumAtletas());
        switch(op)
        {
       	case'1': //Listado de las inscripciones a la Carrera
		         for(i=0;i<M.getNumAtletas();i++)
       	         {
	       			A = M.Consultar(i);
	       			cout<<"Dorsal: "<<A.get_dorsal()<<'\t';
	       			A.get_nombre(name);cout<<"Nombre: "<<name;
	       			mark=A.get_marca();
	       			if (mark!=0) cout<<"\tMarca: "<<mark<<endl;
	       			else cout<<endl;
       		  	 }
       		  	 ///system("pause");
       		  	 break;
       	case'2': //dorsal++;
       	         //(2)Inscripcion de un nuevo Atleta en la Carrera (solo si no esta completa)
		 		 //......
                if (M.Completa(N)) {
                    cout << "No se puede inscribir. La carrera se ha llenado.\n";
                } else {
                    cout << "Inscripcion de un nuevo atleta.\n";
                    cout << "Indique el nombre del atleta: ";
                    cin >> name;
                    A.set_nombre(name);
                    cout << "Indique el dorsal del atleta: ";
                    cin >> dorsal;
                    cout << "El atleta " << name << " tiene el dorsal: " << dorsal << ".\n";
                    A.set_dorsal(dorsal);
                    M.Insertar(A);
                }
                break;

        case'3': //(3)Eliminar la inscripcion de un Atleta por el dorsal
                 //......
                cout << "Indique el dorsal a eliminar: ";
                cin >> dorsal;
                if (M.Buscar(dorsal) == -1) {
                    cout << "No se ha podido eliminar el dorsal " << dorsal << ". No existe.\n";
                } else {
                    M.Eliminar(M.Buscar(dorsal));
                    cout << "El dorsal " << dorsal << " se ha eliminado con exito.\n";
                }
                break;


        case'4': M.SimularCarrera();
                 break;


        case'5': //(4)Mostrar Podium de la Carrera
                 //......
                 Clasificacion(M, oro, plata, bronce);
                 cout << "1: Dorsal " << oro << endl;
                 cout << "2: Dorsal " << plata << endl;
                 cout << "3: Dorsal " << bronce << endl;
                 break;

        case'6':break;
       }
   }
   while (op!='6');
   return 0;
}
