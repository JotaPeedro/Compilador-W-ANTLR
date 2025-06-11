import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

//Joao Pedro Cavani Meireles
public class Main {
	public static void main(String[] args) throws IOException {
		CharStream cs=CharStreams.fromFileName("programa0.gyh");
		GyhLangLexer lexer=new GyhLangLexer(cs);
		CommonTokenStream tokens=new CommonTokenStream(lexer);
		GyhLangParser parser=new GyhLangParser(tokens);
		parser.programa();
//		Token t;
//		
//		while((t=lexer.nextToken()).getType()!=Token.EOF) {
//			System.out.println("< "+GyhLangParser.VOCABULARY.getDisplayName(t.getType()) +", "+t.getText() +">");
//		}
		
	}

}
