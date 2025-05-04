package si2023.josemariagonzalez1alu.p02.agente89.mef.estados;

import java.util.List;

import si2023.josemariagonzalez1alu.p02.agente89.mef.acciones.IrMatarMalos;
import si2023.josemariagonzalez1alu.p02.ia.mef.Estado;
import tools.Vector2d;;

public class MatarMalos extends Estado {

	public MatarMalos(List<Vector2d> posMalos) {
		super();
		accion = new IrMatarMalos(posMalos);
	}

}
