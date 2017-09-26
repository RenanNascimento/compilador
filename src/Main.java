/*
Na etapa 1, o compilador deverá exibir a sequência de tokens identificados e os símbolos (identificadores e
palavras reservadas) instalados na Tabela de Símbolos. Nas etapas seguintes, isso não deverá ser exibido.
 */

import analisador_lexico.Lexer;
import analisador_lexico.Tag;
import analisador_lexico.Token;
import exception.InvalidTokenException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	private static String[] testes = {
			"testes/teste1.txt",
			"testes/teste2.txt",
			"testes/teste3.txt",
			"testes/teste4.txt",
			"testes/teste5.txt",
			"testes/teste6.txt",
	};


	public static void main(String[] args) {
		try {
			Lexer L = new Lexer(testes[5]);
			System.out.println("**** Tokens lidos ****");
			Token T = L.scan();
			while (T.tag != Tag.EOF) { // acho q 65535 é fim de arquivo, olhar se acha uma condição de termino melhor
				T.imprimeToken(T);
				T = L.scan();
			}
			L.imprimirTabela();
		} catch (InvalidTokenException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
