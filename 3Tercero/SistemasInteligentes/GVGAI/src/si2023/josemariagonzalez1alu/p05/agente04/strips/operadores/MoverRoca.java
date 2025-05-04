package si2023.josemariagonzalez1alu.p05.agente04.strips.operadores;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Direccion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.IPredicado;
import si2023.josemariagonzalez1alu.p05.agente04.strips.Posicion;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PRoca;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;

public class MoverRoca {

	private IPredicado meta;
	
	public MoverRoca() {}
	
	public ArrayList<Operador<IPredicado>> getOperadores(Posicion P, IPredicado meta) {
		this.meta = meta;
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
			
			if (meta.getClass() == PAvatarEn.class) {
				Posicion down = Posicion.getPosicion(P, Direccion.DOWN);
				Posicion up = Posicion.getPosicion(P, Direccion.UP);
				precondiciones.add(new PAvatarEn(down));
				precondiciones.add(new PLibre(up));
				precondiciones.add(new PRoca(P));
				
				adiciones.add(new PLibre(P));
				adiciones.add(new PAvatarEn(P));
				adiciones.add(new PRoca(up));
				
				supresiones.add(new PAvatarEn(down));
				supresiones.add(new PLibre(up));
				supresiones.add(new PRoca(P));
			} else if (meta.getClass() == PRoca.class) {
				Posicion down = Posicion.getPosicion(P, Direccion.DOWN);
				Posicion dd = Posicion.getPosicion(down, Direccion.DOWN);
				precondiciones.add(new PAvatarEn(dd));
				precondiciones.add(new PLibre(P));
				precondiciones.add(new PRoca(down));
				
				adiciones.add(new PLibre(down));
				adiciones.add(new PAvatarEn(down));
				adiciones.add(new PRoca(P));
				
				supresiones.add(new PAvatarEn(dd));
				supresiones.add(new PLibre(P));
				supresiones.add(new PRoca(down));
			}
		}
		
		@Override
		public String toString() {
			return "RocaUp(" + P + ")";
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
			
			if (meta.getClass() == PAvatarEn.class) {
				Posicion up = Posicion.getPosicion(P, Direccion.UP);
				Posicion down = Posicion.getPosicion(P, Direccion.DOWN);
				precondiciones.add(new PAvatarEn(up));
				precondiciones.add(new PLibre(down));
				precondiciones.add(new PRoca(P));
				
				adiciones.add(new PLibre(P));
				adiciones.add(new PAvatarEn(P));
				adiciones.add(new PRoca(down));
				
				supresiones.add(new PAvatarEn(up));
				supresiones.add(new PLibre(down));
				supresiones.add(new PRoca(P));
			} else if (meta.getClass() == PRoca.class) {
				Posicion up = Posicion.getPosicion(P, Direccion.UP);
				Posicion upup = Posicion.getPosicion(up, Direccion.UP);
				precondiciones.add(new PAvatarEn(upup));
				precondiciones.add(new PLibre(P));
				precondiciones.add(new PRoca(up));
				
				adiciones.add(new PLibre(up));
				adiciones.add(new PAvatarEn(up));
				adiciones.add(new PRoca(P));
				
				supresiones.add(new PAvatarEn(upup));
				supresiones.add(new PLibre(P));
				supresiones.add(new PRoca(up));
			}
		}

		@Override
		public String toString() {
			return "RocaDown(" + P + ")";
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
			
			if (meta.getClass() == PAvatarEn.class) {
				Posicion right = Posicion.getPosicion(P, Direccion.RIGHT);
				Posicion left = Posicion.getPosicion(P, Direccion.LEFT);
				precondiciones.add(new PAvatarEn(right));
				precondiciones.add(new PLibre(left));
				precondiciones.add(new PRoca(P));
				
				adiciones.add(new PLibre(P));
				adiciones.add(new PAvatarEn(P));
				adiciones.add(new PRoca(left));
				
				supresiones.add(new PAvatarEn(right));
				supresiones.add(new PLibre(left));
				supresiones.add(new PRoca(P));
			} else if (meta.getClass() == PRoca.class) {
				Posicion right = Posicion.getPosicion(P, Direccion.RIGHT);
				Posicion rr = Posicion.getPosicion(right, Direccion.RIGHT);
				precondiciones.add(new PAvatarEn(rr));
				precondiciones.add(new PLibre(P));
				precondiciones.add(new PRoca(right));
				
				adiciones.add(new PLibre(right));
				adiciones.add(new PAvatarEn(right));
				adiciones.add(new PRoca(P));
				
				supresiones.add(new PAvatarEn(rr));
				supresiones.add(new PLibre(P));
				supresiones.add(new PRoca(right));
			}
		}
		
		@Override
		public String toString() {
			return "RocaLeft(" + P + ")";
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
			
			if (meta.getClass() == PAvatarEn.class) {
				Posicion left = Posicion.getPosicion(P, Direccion.LEFT);
				Posicion right = Posicion.getPosicion(P, Direccion.RIGHT);
				precondiciones.add(new PAvatarEn(left));
				precondiciones.add(new PLibre(right));
				precondiciones.add(new PRoca(P));
				
				adiciones.add(new PLibre(P));
				adiciones.add(new PAvatarEn(P));
				adiciones.add(new PRoca(right));
				
				supresiones.add(new PAvatarEn(left));
				supresiones.add(new PLibre(right));
				supresiones.add(new PRoca(P));
			} else if (meta.getClass() == PRoca.class) {
				Posicion left = Posicion.getPosicion(P, Direccion.LEFT);
				Posicion ll = Posicion.getPosicion(left, Direccion.LEFT);
				precondiciones.add(new PAvatarEn(ll));
				precondiciones.add(new PLibre(P));
				precondiciones.add(new PRoca(left));
				
				adiciones.add(new PLibre(left));
				adiciones.add(new PAvatarEn(left));
				adiciones.add(new PRoca(P));
				
				supresiones.add(new PAvatarEn(ll));
				supresiones.add(new PLibre(P));
				supresiones.add(new PRoca(left));
			}
			
		}
		
		@Override
		public String toString() {
			return "RocaRight(" + P + ")";
		}
		
		@Override
		public ACTIONS getAction() {
			return ACTIONS.ACTION_RIGHT;
		}
	}

}
