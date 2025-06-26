import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class GyhProgram {
	private SymbolTable varTable;
	private ArrayList<Command> command;
	
	public void generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("#include<stdio.h>\n");
		str.append("#include<stdlib.h>\n\n");
		str.append("void main(){ \n ");
		
		for(Symbol symbol: varTable.getAll()) {
			str.append(symbol.generateCode());
		}
		
		
		for(Command cmd: command) {
			str.append(cmd.generateCode());
		}
		
		
		str.append("} \n");
		
		try {
			FileWriter file	 = new FileWriter(new File("programagyh.c"));
			file.write(str.toString());
			file.close();
		}catch(Exception ex) {
			
		}
		
		
		
	}

	public ArrayList<Command> getCommand() {
		return command;
	}

	public void setCommand(ArrayList<Command> command) {
		this.command = command;
	}

	public SymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(SymbolTable varTable) {
		this.varTable = varTable;
	}
	
	
}
