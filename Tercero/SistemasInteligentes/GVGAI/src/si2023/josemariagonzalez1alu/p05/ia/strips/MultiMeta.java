package si2023.josemariagonzalez1alu.p05.ia.strips;

import java.util.ArrayList;

public class MultiMeta<T> implements IApilable {

	public ArrayList<Meta<T>> metas;
	
	public MultiMeta() {
		metas = new ArrayList<>();
	}

	public boolean esCierta(Estado<T> e) {
		for (Meta<T> m : metas) {
			if (!m.esCierta(e))
				return false;
		}
		return true;
	}

	public void addMeta(Meta<T> meta) {
		metas.add(meta);
	}

	public String toString() {
		String str = "";
		for (Meta<T> m : metas) {
			str += m.toString() + ", ";
		}
		return str;
	}
	@Override
	public boolean esMultiMeta() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean esMeta() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esOperador() {
		// TODO Auto-generated method stub
		return false;
	}
}
