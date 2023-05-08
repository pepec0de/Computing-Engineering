package si2023.josemariagonzalez1alu.p05.agente04.strips;

import java.util.ArrayList;

import si2023.josemariagonzalez1alu.p05.agente04.mente.Mundo04;
import si2023.josemariagonzalez1alu.p05.agente04.strips.operadores.*;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PAvatarEn;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PLibre;
import si2023.josemariagonzalez1alu.p05.agente04.strips.predicados.PMeta;
import si2023.josemariagonzalez1alu.p05.ia.strips.Estado;
import si2023.josemariagonzalez1alu.p05.ia.strips.Meta;
import si2023.josemariagonzalez1alu.p05.ia.strips.Operador;
import si2023.josemariagonzalez1alu.p05.ia.strips.STRIPS;

public class STRIPS04 {

	private Mundo04 m;
	private Estado<IPredicado> inicial;
	private Meta<IPredicado> meta;
	
	public STRIPS04(Mundo04 m) {
		this.m = m;
		this.meta = new Meta<>(new PMeta());
		this.inicial = getInitState();
		
		
		ArrayList<Operador<IPredicado>> operadores = new ArrayList<>(); 
		operadores.add(new IrMeta(m));
		operadores.add(new IrLlave(m));
		operadores.add(new UpTo());
		operadores.add(new DownTo());
		operadores.add(new RightTo());
		operadores.add(new LeftTo());
		operadores.add(new DespejarUp());
		operadores.add(new DespejarDown());
		operadores.add(new DespejarRight());
		operadores.add(new DespejarLeft());
		STRIPS<IPredicado> strips = new STRIPS<IPredicado>(inicial, meta, operadores);
	}
	
	public Estado<IPredicado> getInitState() {
		Estado<IPredicado> e = new Estado<>();

		e.apilar(this.meta);
		
		e.abiertos.add(new PAvatarEn(m.getAvatarPos()));
		ArrayList<Posicion> posLibres = m.findPosicionesLibres();
		for (Posicion pos : posLibres) {
			System.out.println(pos);
			e.abiertos.add(new PLibre(pos));
		}
		
		return e;
	}
	
}