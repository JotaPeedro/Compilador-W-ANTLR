import java.util.HashMap;

public class SymbolTable {
	private HashMap<String,Symbol>table;
	
	public SymbolTable() {
		table=new HashMap<String,Symbol>();
		
	}
	public void add(Symbol symbol) {
		table.put(symbol.getName(), symbol);
	}
	
	public boolean exists(String name) {
		return table.get(name)!=null;
	}
	
	public Symbol get(String SymbolName) {
		return table.get(SymbolName);
	}
}
