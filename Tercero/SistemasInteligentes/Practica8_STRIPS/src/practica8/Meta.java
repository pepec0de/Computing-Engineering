package practica8;

public class Meta implements Apilable {

	private char meta;
	
	public Meta(char meta) {
		this.meta = meta;
	}

	public char getMeta() {
		return meta;
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

	public boolean esCierta(Estado e) {
		return e.abiertos.contains(meta);
	}

}
