import java.util.ArrayList;

// A classe herda de Command, então ela precisa implementar o método generateCode.
public class CommandRepeticao extends Command {

    private String condition;
    private ArrayList<Command> loopCommands;

    /**
     * Construtor da classe.
     * @param condition A string da condição (ex: "num1 > 10 && num2 != 0").
     * @param commands A lista de comandos que estão dentro do bloco ENQTO.
     */
    public CommandRepeticao(String condition, ArrayList<Command> commands) {
        this.condition = condition;
        this.loopCommands = commands;
    }

    @Override
    public String generateCode() {
        // Usamos um StringBuilder para montar o código C de forma eficiente.
        StringBuilder str = new StringBuilder();

        // 1. Inicia o laço 'while' com a condição.
        str.append("while (").append(this.condition).append(") {\n");

        // 2. Gera o código para cada comando dentro do laço.
        // A mágica da orientação a objetos acontece aqui: cada comando sabe se gerar.
        for (Command cmd : this.loopCommands) {
            str.append("\t").append(cmd.generateCode()); // Adiciona uma tabulação para indentação
        }

        // 3. Fecha o bloco do 'while'.
        str.append("}\n");

        return str.toString();
    }
}