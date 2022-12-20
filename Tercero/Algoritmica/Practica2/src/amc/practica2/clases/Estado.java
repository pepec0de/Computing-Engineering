package amc.practica2.clases;

public class Estado {
	
	private String label;
	private int id;
	
	public Estado(String label) {
		this.label = label;
		this.id = Integer.parseInt(label.replaceAll("[^0-9]", ""));
	}

	public String getLabel() {
		return label;
	}

	public int getId() {
		return id;
	}
	
	
}
