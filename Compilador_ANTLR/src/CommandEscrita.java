
public class CommandEscrita extends Command{
	private String id;
	private Symbol.Tipo type;
	
	

	public CommandEscrita(String id,Symbol.Tipo type) {
		super();
		this.id = id;
		this.type=type;
		
	}
	
	
	public String generateCode() {
        // Agora a classe escolhe o formato correto com base no tipo!
        switch (type) {
            case INT:
                return "\tprintf(\"%d\", " + id + ");\n";
            case REAL:
                return "\tprintf(\"%f\", " + id + ");\n";
            case CADEIA:

                return "\tprintf(\"%s\", " + id + ");\n";
            default:
                return "// ERRO: tipo de escrita desconhecido\n";
        }
    }
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
