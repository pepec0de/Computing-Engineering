package practica8;

import java.util.ArrayList;

public class Mundo {

	public Mundo() {
		Operador op1 = new Operador("OP1");
		op1.precondiciones.add('b');
		op1.precondiciones.add('d');
		op1.adiciones.add('c');
		op1.adiciones.add('e');
		op1.supresiones.add('d');
		
		Operador op2 = new Operador("OP2");
		op1.precondiciones.add('b');
		op1.adiciones.add('a');
		op1.supresiones.add('c');
		op1.supresiones.add('d');
		
		Operador op3 = new Operador("OP3");
		op1.precondiciones.add('a');
		op1.adiciones.add('d');
		
		MultiMeta meta = new MultiMeta();
		meta.addMeta(new Meta('a'));
		meta.addMeta(new Meta('d'));
		
		Estado inicial = new Estado();
		inicial.apilar(meta);
		inicial.addAbiertos('b');
		inicial.addAbiertos('d');
		inicial.addAbiertos('e');

		ArrayList<Operador> operadores = new ArrayList<>();
		operadores.add(op1);
		operadores.add(op2);
		operadores.add(op3);
		STRIPS strips = new STRIPS(inicial, meta, operadores);
		strips.solucionar();
	}
	
	public static void main(String[] args) {
		new Mundo();
	}

}
