package si2023.josemariagonzalez1alu.p05.agente04.strips;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.mente.Mundo04;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.*;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.*;
import si2023.josemariagonzalez1alu.p05.ia.strips.*;

public class STRIPS04 extends STRIPS<IPredicado> {

	private Mundo04 m;
	
	public STRIPS04(Mundo04 m) {
		super();
		this.m = m;
		this.meta.setMeta(new PMeta());
		
		inicial.apilar(this.meta);
		inicial.abiertos.add(new PAvatarEn(m.getAvatarPos()));
		ArrayList<Posicion> poses = m.findPosicionesLibres();
		for (Posicion pos : poses) {
			inicial.abiertos.add(new PLibre(pos));
		}
//		poses = m.findPosicionesRocas();
//		for (Posicion pos : poses) {
//			inicial.abiertos.add(new PRoca(pos));
//		}
		
	}

	public ArrayList<ACTIONS> getSolucion() {
		solucionar();
		ArrayList<ACTIONS> acciones = new ArrayList<>();
		for (Operador<IPredicado> op : actual.plan) {
			if (op.getAction() != null) {
				System.out.println(op.getAction());
				acciones.add(op.getAction());
			}
		}
		
		return acciones;
	}
	private void initOperadores() {
		operadores.clear();
		operadores.add(new IrMeta(m));
		operadores.add(new IrLlave(m));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void BusquedaSTRIPS(Estado<IPredicado> current) {
		boolean estadoValido = false;
		
		for (IPredicado p : current.abiertos) {
			Posicion pos = p.getPos();
			if (pos != null && pos.equals(m.getKeyPos())) {
				estadoValido = true;
			}
		}

		if (estadoValido)
		if (current.pila.peek().esOperador()) {
			Operador<IPredicado> op = (Operador<IPredicado>) current.pila.peek();
			Estado<IPredicado> e = new Estado<>(current);
			if (op.ejecutable(current)) {
				op.ejecutar(e);
				e.pila.pop();
			} else {
				// Introducir precondiciones del operador en la pila
				e.pila.push(castPrecondiciones(op.precondiciones));
			}
			current.sucesores.add(e);
		} else if (current.pila.peek().esMeta()) {
			Meta<IPredicado> meta = (Meta<IPredicado>) current.pila.peek();
			if (meta.esCierta(current)) {
				Estado<IPredicado> e = new Estado<>(current);
				e.pila.pop();
				current.sucesores.add(e);
			} else {
				// Comprobar si existe bucle
				if (!existeBucle(current)) {
					// else generar un sucesor por cada instanciacion de operador que añade la meta
					Posicion P = null;
					initOperadores();
					if (meta.getMeta().getClass() == PAvatarEn.class) {
						PAvatarEn p = (PAvatarEn) meta.getMeta();
						P = p.getPos();
						if (m.isWall(P)) 
							return;
						operadores.add(new LeftTo(P));
						operadores.add(new RightTo(P));
						operadores.add(new DownTo(P));
						operadores.add(new UpTo(P));
					} else if (meta.getMeta().getClass() == PLibre.class) {
						PLibre p = (PLibre) meta.getMeta();
						P = p.getPos();
						if (m.isWall(P)) 
							return;
						operadores.add(new DespejarLeft(P));
						operadores.add(new DespejarRight(P));
						operadores.add(new DespejarDown(P));
						operadores.add(new DespejarUp(P));
					}
					
					for (Operador<IPredicado> op : operadores) {
						if (op.adiciones.contains(meta.getMeta())) {
							Estado<IPredicado> e = new Estado<>(current);
							e.pila.push(op);
							current.sucesores.add(e);
						}
					}
				}
			}
		} else if (current.pila.peek().esMultiMeta()) {
			MultiMeta<IPredicado> mmeta = (MultiMeta<IPredicado>) current.pila.peek();
			if (mmeta.esCierta(current)) {
				Estado<IPredicado> e = new Estado<>(current);
				e.pila.pop();
				current.sucesores.add(e);
			} else {
				// Generar como sucesores todas las posibles combinaciones de las metas
				ArrayList<ArrayList<Meta<IPredicado>>> permutacionesMetas = permutate(mmeta.metas);
				for (ArrayList<Meta<IPredicado>> permutacion : permutacionesMetas) {
					Estado<IPredicado> e = new Estado<>(current);
					for (Meta<IPredicado> m : permutacion) {
						e.pila.push(m);
					}
					current.sucesores.add(e);
				}
			}
		}
	}
	
}