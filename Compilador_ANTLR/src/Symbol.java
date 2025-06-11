

public class Symbol {
	private static String name;
	private int type;
	private String value;
	public static final int REAL =0;
	public static final int INT =1;
	public Symbol(String name, int type, String value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
	}
	public static String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public static int getReal() {
		return REAL;
	}
	public static int getInt() {
		return INT;
	}
	
	@Override
	public String toString() {
		return"Symbol {name: " +this.name+" Type "+type +" value: "+ value +"]";
	}
	
}
