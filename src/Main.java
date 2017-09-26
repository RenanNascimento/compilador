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
		Lexer L = null;
		try {
			L = new Lexer(testes[5]);
			System.out.println("**** Tokens lidos ****");
			// Apenas para entrar no laço
			Token T = new Token(0);
			while (T.tag != Tag.EOF) {
				try {
					T = L.scan();
					if(T.tag == Tag.EOF)
						break;
					T.imprimeToken(T);
				} catch (InvalidTokenException | IOException e) {
					System.out.println(e.getMessage());
					try {
						L.readch();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			L.imprimirTabela();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
