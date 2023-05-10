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
		poses = m.findPosicionesRocas();
		for (Posicion pos : poses) {
			inicial.abiertos.add(new PRoca(pos));
		}
		
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
	public void BusquedaSTRIPS() {
		boolean estadoValido = false;
		
		for (IPredicado p : actual.abiertos) {
			Posicion pos = p.getPos();
			if (pos != null && pos.equals(m.getKeyPos())) {
				estadoValido = true;
			}
		}
		
		if (estadoValido)
		if (actual.pila.peek().esOperador()) {
			Operador<IPredicado> op = (Operador<IPredicado>) actual.pila.peek();
			if (op.ejecutable(actual)) {				
				op.ejecutar(actual);
				actual.pila.pop();
			} else {
				// Introducir precondiciones del operador en la pila
				actual.pila.push(castPrecondiciones(op.precondiciones));
			}
			estados.push(actual);
		} else if (actual.pila.peek().esMeta()) {
			Meta<IPredicado> meta = (Meta<IPredicado>) actual.pila.peek();
			if (meta.esCierta(actual)) {
				actual.pila.pop();
				estados.push(actual);
			} else {
				// Comprobar si existe bucle
				if (!existeBucle(actual)) {
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
					
					ArrayList<Operador<IPredicado>> posiblesOps = new ArrayList<>();
					for (Operador<IPredicado> op : operadores) {
						if (op.adiciones.contains(meta.getMeta())) {
							posiblesOps.add(op);
						}
					}
					if (posiblesOps.size() == 1) {
						actual.pila.push(posiblesOps.get(0));
						estados.push(actual);
					} else {
						for (Operador<IPredicado> op : posiblesOps) {
							Estado<IPredicado> e = new Estado<>(actual);
							e.pila.push(op);
							actual.sucesores.add(e);
						}
					}
				}
			}
		} else if (actual.pila.peek().esMultiMeta()) {
			MultiMeta<IPredicado> mmeta = (MultiMeta<IPredicado>) actual.pila.peek();
			if (mmeta.esCierta(actual)) {
				actual.pila.pop();
				estados.push(actual);
			} else {
				// Generar como sucesores todas las posibles combinaciones de las metas
				ArrayList<ArrayList<Meta<IPredicado>>> metas = permutate(mmeta.metas);
				for (ArrayList<Meta<IPredicado>> arr : metas) {
					Estado<IPredicado> e = new Estado<>(actual);
					for (Meta<IPredicado> m : arr) {
						e.pila.push(m);
					}
					actual.sucesores.add(e);
				}
			}
		}
	}
	
}