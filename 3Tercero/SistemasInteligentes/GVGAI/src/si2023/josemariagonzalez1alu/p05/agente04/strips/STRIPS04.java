package si2023.josemariagonzalez1alu.p05.agente04.strips;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.mente.Mundo04;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.*;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.*;
import si2023.josemariagonzalez1alu.p05.ia.strips.*;

public class STRIPS04 extends STRIPS<IPredicado> {

	private Mundo04 m;
	private Mover opMover;
	private MoverRoca opMoverPiedra;
	
	public STRIPS04(Mundo04 m) {
		super();
		this.m = m;
		this.meta.setMeta(new PMeta());
		//Nivel 1
		//this.meta.setMeta(new PAvatarEn(11, 4));
		//this.meta.setMeta(new PMeta());
		//this.meta.setMeta(new PRoca(6, 4));
		
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
		poses = m.findPosicionesFosos();
		for (Posicion pos : poses) {
			inicial.abiertos.add(new PFoso(pos));
		}
		
		opMover = new Mover();
		opMoverPiedra = new MoverRoca();
	}

	public ArrayList<ACTIONS> getSolucion() {
		long ini = System.currentTimeMillis();
			solucionarAnchura();
		//System.out.println("Visitados: " + visitados.size());
		System.out.println((System.currentTimeMillis() - ini));
		System.out.println(visitados.size());
		ArrayList<ACTIONS> acciones = new ArrayList<>();
//		System.out.println("Plan: ");
		if (actual != null)
			for (Operador<IPredicado> op : actual.plan) {
				if (op.getAction() != null) {
					//System.out.println(op.getAction());
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
			if (p.getClass() == PLibre.class || p.getClass() == PAvatarEn.class) {
				Posicion pos = p.getPos();
				if (pos != null && pos.equals(m.getKeyPos())) {
					estadoValido = true;
				}
			}
		}

		if (estadoValido) { // Generación de los sucesores
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
						// else generar un sucesor por cada instanciacion de operador que anade la meta
						Posicion P = meta.getMeta().getPos();
						if (P != null && m.isWall(P)) 
							return;

						initOperadores();
						
						if (P != null) {
							operadores.addAll(opMover.getOperadores(P));
							operadores.addAll(opMoverPiedra.getOperadores(P, meta.getMeta()));
							operadores.add(new TaparDown(P));
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
					Estado<IPredicado> e = new Estado<>(current);
					for (Meta<IPredicado> m : mmeta.metas) {
						e.pila.push(m);
					}
					current.sucesores.add(e);
				}
			}
		}
	}
	
}