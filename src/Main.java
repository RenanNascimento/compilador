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
import java.util.Scanner;
import java.util.ArrayList;
import analisador_sintatico.Parser;

public class Main {

	private static String[] testes = {
			"testes/teste1.txt",
			"testes/teste2.txt",
			"testes/teste3.txt",
			"testes/teste4.txt",
			"testes/teste5.txt",
			"testes/teste6.txt",
			"testes/teste7.txt",
			"testes/teste8.txt",
			"testes/teste9.txt",
			"testes/teste10.txt",
			"testes/teste11.txt",
			"testes/teste12.txt",
	};


	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Entre com o numero do teste (1-12): ");
		int arq = input.nextInt()-1;
		ArrayList<Token> tokens = new ArrayList<Token> ();
		Lexer L = null;
		int line = -5;
		try {
			L = new Lexer(testes[arq]);
			System.out.println("**** Tokens lidos ****");
			// Apenas para entrar no laço
			Token T = new Token(0, line);
			while (T.tag != Tag.EOF) {
				try {
					T = L.scan();
					if(T.tag == Tag.EOF)
						break;
					//T.imprimeToken(T);
					tokens.add(T);
					line = T.line;
				} catch (InvalidTokenException | IOException e) {
					System.out.println(e.getMessage());
					try {
						L.readch();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			line++;
			tokens.add(new Token(Tag.EOF, line));
			//L.imprimirTabela();
			Parser P = new Parser(tokens);
			System.out.println("\n\n\n**** Inicio Parser ****");
			P.init();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
