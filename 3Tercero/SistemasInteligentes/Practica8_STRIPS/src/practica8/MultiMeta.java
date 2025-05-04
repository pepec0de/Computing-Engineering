package practica8;

import java.util.ArrayList;

public class MultiMeta implements Apilable {

	public ArrayList<Meta> metas;
	
	public MultiMeta() {
		metas = new ArrayList<>();
	}

	public boolean esCierta(Estado e) {
		for (Meta m : metas) {
			if (!m.esCierta(e))
				return false;
		}
		return true;
	}

	public void addMeta(Meta meta) {
		metas.add(meta);
	}

	public String toString() {
		String str = "";
		for (Meta m : metas) {
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
