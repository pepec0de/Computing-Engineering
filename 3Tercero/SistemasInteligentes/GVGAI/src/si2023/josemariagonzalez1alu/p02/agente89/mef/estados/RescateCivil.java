package si2023.josemariagonzalez1alu.p02.agente89.mef.estados;

import java.util.List;

import si2023.josemariagonzalez1alu.p02.agente89.mef.acciones.IrSalvarCivil;
import si2023.josemariagonzalez1alu.p02.ia.mef.Estado;
import tools.Vector2d;

public class RescateCivil extends Estado {

	public RescateCivil(List<Vector2d> posPeligro) {
		super();
		accion = new IrSalvarCivil(posPeligro);
	}

}
