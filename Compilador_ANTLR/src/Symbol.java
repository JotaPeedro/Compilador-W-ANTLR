

public class Symbol {
	private  String name;
	private Tipo type;
	private String value;
	public enum Tipo { INT, REAL, INVALIDO, CADEIA }
	
	
	public String generateCode() {
		String str;
		if(type==Tipo.INT) {
			str="\tint "+name+";\n";
		}else if(type==Tipo.REAL) {
			str="\tdouble "+name+";\n";
		}else {
			str="";
		}
		return str;
	}
	
	  public Symbol(String name, Tipo type) {
	        this.name = name;
	        this.type = type;
	    }
	public Symbol(String name, Tipo type, String value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
	}
	public  String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Tipo getType() {
		return type;
	}
	public void setType(Tipo type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return"Symbol {name: " +this.name+" Type "+type +" value: "+ value +"]";
	}
	
}
