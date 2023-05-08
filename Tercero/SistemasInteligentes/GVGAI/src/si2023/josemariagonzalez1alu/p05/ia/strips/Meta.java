package si2023.josemariagonzalez1alu.p05.ia.strips;

public class Meta<T> implements IApilable {

	private T meta;
	
	public Meta(T meta) {
		this.meta = meta;
	}

	public T getMeta() {
		return meta;
	}
	
	public String toString() {
		return "" + meta.toString();
	}
	
	@Override
	public boolean esMultiMeta() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esMeta() {
		return true;
	}

	@Override
	public boolean esOperador() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean esCierta(Estado<T> e) {
		return e.abiertos.contains(meta);
	}

}
