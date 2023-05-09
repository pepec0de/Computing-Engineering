package si2023.josemariagonzalez1alu.p05.agente04.strips;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p05.agente04.mente.Mundo04;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.DespejarDown;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.DespejarLeft;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.DespejarRight;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.DespejarUp;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.DownTo;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.IMov;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.IrLlave;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.IrMeta;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.LeftTo;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.RightTo;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.UpTo;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PMeta;
import si2023.josemariagonzalez1alu.p05.ia.strips.Estado;
import si2023.josemariagonzalez1alu.p05.ia.strips.Meta;
import si2023.josemariagonzalez1alu.p05.ia.strips.MultiMeta;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;
import si2023.josemariagonzalez1alu.p05.ia.strips.STRIPS;

public class STRIPS04 extends STRIPS<IPredicado> {

	private Mundo04 m;
	
	public STRIPS04(Mundo04 m) {
		super();
		this.m = m;
		this.meta.setMeta(new PMeta());
		
		inicial.apilar(this.meta);
		inicial.abiertos.add(new PAvatarEn(m.getAvatarPos()));
		ArrayList<Posicion> posLibres = m.findPosicionesLibres();
		for (Posicion pos : posLibres) {
			inicial.abiertos.add(new PLibre(pos));
		}
	}

	public ArrayList<ACTIONS> getSolucion() {
		solucionar();
		ArrayList<ACTIONS> acciones = new ArrayList<>();
		for (Operador<IPredicado> op : actual.plan) {
			IMov mov = (IMov) op;
			if (mov.getAction() != null) {
				System.out.println(mov.getAction());
				acciones.add(mov.getAction());
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
	public void BusquedaSTRIPS(Estado<IPredicado> actual) {
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
				Estado<IPredicado> e = op.ejecutar(actual);
				e.pila.pop();
				actual.sucesores.add(e);
			} else {
				// Introducir precondiciones del operador en la pila
				Estado<IPredicado> e = new Estado<>(actual);
				e.pila.push(castPrecondiciones(op.precondiciones));
				actual.sucesores.add(e);
			}
		} else if (actual.pila.peek().esMeta()) {
			Meta<IPredicado> meta = (Meta<IPredicado>) actual.pila.peek();
			if (meta.esCierta(actual)) {
				Estado<IPredicado> e = new Estado<>(actual);
				e.pila.pop();
				actual.sucesores.add(e);
			} else {
				// Comprobar si existe bucle
				if (!existeBucle(actual)) {
					// else generar un sucesor por cada instanciacion de operador que añade la meta
					Posicion P = null;
					initOperadores();
					if (meta.getMeta().getClass() == PAvatarEn.class) {
						PAvatarEn p = (PAvatarEn) meta.getMeta();
						P = p.getPos();
						operadores.add(new LeftTo(P));
						operadores.add(new RightTo(P));
						operadores.add(new DownTo(P));
						operadores.add(new UpTo(P));
					} else if (meta.getMeta().getClass() == PLibre.class) {
						PLibre p = (PLibre) meta.getMeta();
						P = p.getPos();
						operadores.add(new DespejarLeft(P));
						operadores.add(new DespejarRight(P));
						operadores.add(new DespejarDown(P));
						operadores.add(new DespejarUp(P));
					}
					
					if (P != null) {
						if (m.isWall(P))
							return;
						//recalcOperadores(P);
					}
					
					for (Operador<IPredicado> op : operadores) {
						if (op.adiciones.contains(meta.getMeta())) {
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
				Estado<IPredicado> e = new Estado<>(actual);
				e.pila.pop();
				actual.sucesores.add(e);
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