#include <iostream>
#include <string.h>

using namespace std;

typedef char cad[20];

class tprod {
    cad nombre;
    float precio;
    int stock;

public:
    tprod();
    void cambiarnombre(cad nom);
    void cambiarprecio(float prec);
    void cambiarstock(int stoc);
    void leenombre(cad nom);
    float leeprecio();
    void leestock(int &st);
    void vender(int cantidad);
};

// Constructor de la clase
tprod::tprod() {
    strcpy(nombre, "NO HAY PRODUCTO");
    precio = 0;
    stock = 0;
}

// Setter de la variable nombre
void tprod::cambiarnombre(cad nom) {
    strcpy(nombre, nom);
}

// Setter de la variable precio
void tprod::cambiarprecio(float prec) {
    precio = prec;
}

// Setter de la variable stock
void tprod::cambiarstock(int stoc) {
    stock = stoc;
}

// Getter de la variable nombre
void tprod::leenombre(cad nom) {
    // Los vectores en C/C++ se pasan por referencia.
    strcpy(nom, nombre);
}

// Getter de la variable precio
float tprod::leeprecio() {
    return precio;
}

// Getter de la variable stock
void tprod::leestock(int &st) {
    st = stock;
}

// Funcion para la venta del producto
void tprod::vender(int cantidad) {
    cout << "Venta del producto: " << nombre << endl;
    // Comprobamos que la cantidad no supera al stock del producto
    if (cantidad <= stock) {
        cout << "Precio: " << precio << " x " << cantidad 
            << " = " << precio*cantidad << "euros.\n";
        // Quitamos la cantidad al stock
        stock = stock - cantidad;
    } else {
        cout << "No se puede realizar la venta de " << cantidad << " unidad(es).\n";
    }
}

#define MAX 5
class almacen {
    tprod productos[MAX];
    int nprod;

public:
    almacen();
    void vaciar();
    int existe(cad nom);
    void verprod(int pos, tprod &prod);
    int insertar(tprod P);
    void vertabla();
    void vender(int pos, int cant);
};

// Constructor de la clase almacen
almacen::almacen() {
    nprod = 0;
}

void almacen::vaciar() {
    /*for (int i = 0; i < MAX; i++) {
        productos[i].cambiarnombre("NO HAY PRODUCTO");
        productos[i].cambiarprecio(0);
        productos[i].cambiarstock(0);
    }*/
    nprod = 0;
}

int almacen::existe(cad nom) {
    bool encontrado = false;
    int i = 0;
    cad nombreIter;
    while (i < nprod && !encontrado) {
        productos[i].leenombre(nombreIter);
        // Comparamos las strings del parametro y la de la posicion de la tabla
        if (strcmp(nombreIter, nom) == 0) {
            encontrado = true;
        } else {
            // Ponemos incremento de i para obtener la posicion
            i++;
        }
    }

    if (!encontrado) i = -1;
    return i;
}

// Método que pondrá en prod el contenido del producto que se encuentra 
// en la posición pos de la tabla de productos.
void almacen::verprod(int pos, tprod &prod) {
    prod = productos[pos];
}

int almacen::insertar(tprod P) {
    int resultado;
    // Si la tabla esta llena devolvemos 2
    if (nprod == MAX) {
        resultado = 2;
    } else {
        cad nombre;
        P.leenombre(nombre);
        // Comprobamos si el valor existe
        if (existe(nombre) != -1) {
            resultado = 1;
        } else {
            // Como no existe podemos insertar el producto
            productos[nprod] = P;
            nprod++;
            resultado = 0;
        }
    }
    return resultado;
}

void almacen::vertabla() {
    if (nprod == 0) {
        cout << "El almacen esta vacio.\n";
    } else {
        cad nombre;
        int stock;
        cout << "Listado de productos.\n";
        for (int i = 0; i < nprod; i++) {
            productos[i].leenombre(nombre);
            productos[i].leestock(stock);
            cout << "Producto: " << nombre << " con precio de " 
                << productos[i].leeprecio() << " y stock de " 
                << stock << " unidad(es).\n";
        } 
    }
}

void almacen::vender(int pos, int cant) {
    productos[pos].vender(cant);
}

char menu() {
    char accion;
    cout << "\n**************MENU****************\n";
    cout << "****A.- Visualizar la tabla.  ****\n";
    cout << "****B.- Insertar un producto. ****\n";
    cout << "****C.- Vender un producto.   ****\n";
    cout << "****D.- Vaciar el almacen.    ****\n";
    cout << "****E.- Salir.                ****\n";
    cout << "**********************************\n";
    cout << "Pon la opcion que deseas: ";
    cin >> accion;
    return accion;
}

int main() {
    almacen objeto;
    char accion;

    // Definimos variables temporales
    tprod nuevoProd;
    cad nombre;
    float precio;
    int stock;
    int pos;
    int cantidad;
    do {
        accion = menu();
        cout << endl;
        switch (accion) {
            case 'A':
            case 'a':
                objeto.vertabla();
                break;

            case 'B':
            case 'b': 
                cout << "Insertar producto nuevo\n";
                cout << "Indique el nombre: ";
                cin >> nombre;
                cout << "Indique el precio unitario: ";
                cin >> precio;
                cout << "Indique el stock: ";
                cin >> stock;

                nuevoProd.cambiarnombre(nombre);
                nuevoProd.cambiarprecio(precio);
                nuevoProd.cambiarstock(stock);
                switch(objeto.insertar(nuevoProd)) {
                    case 2:
                        cout << "El almacen esta lleno.\n";
                        break;
                    case 1:
                        cout << "El producto ya existe.\n";
                        break;
                    case 0:
                        cout << "Se ha insertado correctamente el producto.\n";
                        break;
                }
                break;

            case 'C':
            case 'c':
                cout << "Indique el producto que quiere comprar: ";
                cin >> nombre;
                pos = objeto.existe(nombre);
                if (pos > -1) {
                    cout << "El producto existe. Indique la cantidad: ";
                    cin >> cantidad;
                    objeto.vender(pos, cantidad);
                } else {
                    cout << "El objeto no existe.\n";
                }
                break;

            case 'D':
            case 'd':
                objeto.vaciar();
                break;

            case 'E':
            case 'e':
                break;

            default:
                cout << "Opcion incorrecta" << endl;
        }
        cout << endl;
    } while(accion != 'E' && accion != 'e');
    return 0;
}
