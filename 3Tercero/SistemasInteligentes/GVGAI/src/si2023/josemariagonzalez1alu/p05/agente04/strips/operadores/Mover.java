package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Direccion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class Mover {

	public Mover() {}
	
	public ArrayList<Operador<IPredicado>> getOperadores(Posicion P) {
		ArrayList<Operador<IPredicado>> operadores = new ArrayList<>();
		operadores.add(new UP(P));
		operadores.add(new DOWN(P));
		operadores.add(new LEFT(P));
		operadores.add(new RIGHT(P));
		return operadores;
	}
	
	class UP extends Operador<IPredicado> {
		
		private Posicion P;
		
		public UP(Posicion P) {
			super();
			this.P = P;
			precondiciones.add(new PAvatarEn(Posicion.getPosicion(P, Direccion.DOWN)));
			precondiciones.add(new PLibre(P));
			
			adiciones.add(new PAvatarEn(P));
			
			supresiones.add(new PAvatarEn(Posicion.getPosicion(P, Direccion.DOWN)));
		}
		
		@Override
		public String toString() {
			return "UpTo(" + P + ")";
		}
		
		@Override
		public ACTIONS getAction() {
			return ACTIONS.ACTION_UP;
		}
	}
	
	class DOWN extends Operador<IPredicado> {
		
		private Posicion P;
		
		public DOWN(Posicion P) {
			super();
			this.P = P;
			precondiciones.add(new PAvatarEn(Posicion.getPosicion(P, Direccion.UP)));
			precondiciones.add(new PLibre(P));
			
			adiciones.add(new PAvatarEn(P));
			
			supresiones.add(new PAvatarEn(Posicion.getPosicion(P, Direccion.UP)));
		}

		@Override
		public String toString() {
			return "DownTo(" + P + ")";
		}
		
		@Override
		public ACTIONS getAction() {
			return ACTIONS.ACTION_DOWN;
		}
	}
	
	class LEFT extends Operador<IPredicado> {

		private Posicion P;
		
		public LEFT(Posicion P) {
			super();
			this.P = P;
			precondiciones.add(new PAvatarEn(Posicion.getPosicion(P, Direccion.RIGHT)));
			precondiciones.add(new PLibre(P));
			
			adiciones.add(new PAvatarEn(P));
			
			supresiones.add(new PAvatarEn(Posicion.getPosicion(P, Direccion.RIGHT)));
		}
		
		@Override
		public String toString() {
			return "LeftTo(" + P + ")";
		}
		
		@Override
		public ACTIONS getAction() {
			return ACTIONS.ACTION_LEFT;
		}
		
	}
	
	class RIGHT extends Operador<IPredicado> {

		private Posicion P;
		
		public RIGHT(Posicion P) {
			super();
			this.P = P;
			precondiciones.add(new PAvatarEn(Posicion.getPosicion(P, Direccion.LEFT)));
			precondiciones.add(new PLibre(P));
			
			adiciones.add(new PAvatarEn(P));
			
			supresiones.add(new PAvatarEn(Posicion.getPosicion(P, Direccion.LEFT)));
		}
		
		@Override
		public String toString() {
			return "RightTo(" + P + ")";
		}
		
		@Override
		public ACTIONS getAction() {
			return ACTIONS.ACTION_RIGHT;
		}
	}

}
