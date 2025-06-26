import java.util.ArrayList;

public class CommandCondicao extends Command{

	private String condition;
	private ArrayList<Command>lisTrue;
	private ArrayList<Command>listFalse;
	
	
	
	
	public String getCondition() {
		return condition;
	}




	public void setCondition(String condition) {
		this.condition = condition;
	}




	public ArrayList<Command> getLisTrue() {
		return lisTrue;
	}




	public void setLisTrue(ArrayList<Command> lisTrue) {
		this.lisTrue = lisTrue;
	}




	public ArrayList<Command> getListFalse() {
		return listFalse;
	}




	public void setListFalse(ArrayList<Command> listFalse) {
		this.listFalse = listFalse;
	}




	public CommandCondicao(String condition, ArrayList<Command> lisTrue, ArrayList<Command> listFalse) {
		super();
		this.condition = condition;
		this.lisTrue = lisTrue;
		this.listFalse = listFalse;
	}




	public String generateCode() {
		
		String str= "if("+condition+"){\n";
		
		for(Command cmd:lisTrue) {
			str+='\t';
			str+=cmd.generateCode();
		
		}
		 str+="}\n";
		
		 
		 if(listFalse.size()>0) {
			 str+="else {\n";
			
			 for(Command cmd:listFalse) {
				 str+='\t';
					str+=cmd.generateCode();
				
				}
				 str+="}\n";
		 }
		 
		 
		 
		 
		 
		return str;
	}

}
