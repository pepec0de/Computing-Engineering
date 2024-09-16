package si2023.josemariagonzalez1alu.p02.agente89.mef;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones.HayCivilCayendo;
import si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones.HayMalos;
import si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones.NoHayCivilCayendo;
import si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones.NoHayCiviles;
import si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones.TripaLlena;
import si2023.josemariagonzalez1alu.p02.agente89.mef.condiciones.TripaNoLlena;
import si2023.josemariagonzalez1alu.p02.agente89.mef.estados.MatarMalos;
import si2023.josemariagonzalez1alu.p02.agente89.mef.estados.PosicionDefensiva;
import si2023.josemariagonzalez1alu.p02.agente89.mef.estados.RescateCivil;
import si2023.josemariagonzalez1alu.p02.agente89.mef.estados.VaciarTripa;
import si2023.josemariagonzalez1alu.p02.ia.mef.Estado;
import si2023.josemariagonzalez1alu.p02.ia.mef.MEF;
import si2023.josemariagonzalez1alu.p02.ia.mef.Transicion;
import si2023.josemariagonzalez1alu.p02.ia.mente.Mundo;
import tools.Vector2d;

public class MEF89 extends MEF {
	
	public MEF89(Mundo m) {
		super(m);
		
		ArrayList<Vector2d> posCivilCayendo = new ArrayList<>(),
							posMalos = new ArrayList<>();
		
		estados.put("PosicionDefensiva", new PosicionDefensiva());
		estados.put("RescateCivil", new RescateCivil(posCivilCayendo));
		estados.put("MatarMalos", new MatarMalos(posMalos));
		estados.put("VaciarTripa", new VaciarTripa());
		
		estados.get("PosicionDefensiva").transiciones.add(
				new Transicion(new HayCivilCayendo(posCivilCayendo), estados.get("RescateCivil")));
		estados.get("PosicionDefensiva").transiciones.add(new Transicion(new NoHayCiviles(), estados.get("MatarMalos")));
		estados.get("RescateCivil").transiciones.add(new Transicion(new NoHayCivilCayendo(), estados.get("PosicionDefensiva")));
		estados.get("RescateCivil").transiciones.add(
				new Transicion(new HayCivilCayendo(posCivilCayendo), estados.get("RescateCivil")));
		estados.get("MatarMalos").transiciones.add(new Transicion(new TripaLlena(), estados.get("VaciarTripa")));
		estados.get("MatarMalos").transiciones.add(new Transicion(new HayMalos(posMalos), estados.get("MatarMalos")));
		estados.get("VaciarTripa").transiciones.add(new Transicion(new TripaNoLlena(), estados.get("MatarMalos")));
		
		eActual = estados.get("PosicionDefensiva");
	}

	@Override
	public Estado disparo() {
		for (Transicion t : eActual.transiciones) {
			if (t.seDispara(m))
				return t.siguienteEstado();
		}
		return null;
	}

	@Override
	public ACTIONS pensar() {
		Estado disparo = disparo();
		if (disparo != null)
			eActual = disparo;
		/*String estadoActual = "";
		for (Entry<String, Estado> e : estados.entrySet()) {
			if (e.getValue() == eActual) {
				estadoActual = e.getKey();
				break;
			}
		}
		System.out.println(m.getMundo().getGameTick() + ": " + estadoActual);*/
		return eActual.getAccion().doAction(m);
	}
	

}
