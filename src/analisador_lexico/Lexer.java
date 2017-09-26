package analisador_lexico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import exception.InvalidTokenException;

public class Lexer {

	public static int line = 1; 	//contador de linhas
	private char ch = ' '; 			//caractere lido do arquivo
	private FileReader file;
	private Hashtable<String, Word> words = new Hashtable<>();

	/** Método para inserir palavras reservadas na HashTable */
	private void reserve(Word w) {
		words.put(w.getLexeme(), w); // lexema é a chave para entrada na
	}


	/** Método construtor */
	public Lexer(String fileName) throws FileNotFoundException {
		try {
			file = new FileReader(fileName);
		} catch(FileNotFoundException e){
			System.out.println("Arquivo não encontrado");
			throw e;
		}

		// Insere palavras reservadas na HashTable
		reserve(new Word("program", Tag.PRG));
		reserve(new Word("end", Tag.END));
		reserve(new Word("int", Tag.INT));
		reserve(new Word("string", Tag.STR));
		reserve(new Word("if", Tag.IF));
		reserve(new Word("then", Tag.THEN));
		reserve(new Word("else", Tag.ELSE));
		reserve(new Word("do", Tag.DO));
		reserve(new Word("while", Tag.WHILE));
		reserve(new Word("scan", Tag.SCAN));
		reserve(new Word("print", Tag.PRINT));
	}


	/*Lê o próximo caractere do arquivo*/
	private void readch() throws IOException {
		ch = (char) file.read();
	}


	/* Lê o próximo caractere do arquivo e verifica se é igual a c*/
	private boolean readch(char c) throws IOException{
		readch();
		if (ch != c) return false;
		ch = ' ';
		return true;
	}


	public Token scan() throws IOException, InvalidTokenException{
		boolean is_comentario_linha = false;
		boolean is_comentario_bloco = false;
		//Desconsidera delimitadores na entrada
		for (;; readch()) {
			if (ch == (char)Tag.EOF) {
				if (is_comentario_bloco)
					throw new InvalidTokenException("Error: comentário não fechado");
				break;
			}
			if(ch == '/'){
				readch();
				if(ch == '/' && !is_comentario_linha) {
					is_comentario_linha = true;
				} else if (ch == '*'){
					is_comentario_bloco = true;
				}
			} else if (ch == '\n') {
				is_comentario_linha = false;
				line++; //conta linhas
			} else if (ch == '*') {
				readch();
				if(ch == '/') {
					is_comentario_bloco = false;
				}
			} else if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\b' || is_comentario_linha || is_comentario_bloco)
				continue;
			else break;
		}


		switch(ch){
			//Operadores
			case '&':
				if (readch('&')) return Word.and;
				else return new Token('&');
			case '|':
				if (readch('|')) return Word.or;
				else return new Token('|');
			case '=':
				if (readch('=')) return Word.eq;
				else return new Token('=');
			case '<':
				if (readch('=')) return Word.le;
				else return new Token('<');
			case '>':
				if (readch('=')) return Word.ge;
				else return new Token('>');
			case '!':
				if (readch('=')) return Word.ne;
				else return new Token('!');
		}

		//	Números
		if (Character.isDigit(ch)){
			int value=0;
			do{
				value = 10*value + Character.digit(ch,10);
				readch();
			}while(Character.isDigit(ch));
			return new Num(value);
		}

		// Literais
		if(ch == '\"'){
			StringBuffer sb = new StringBuffer();
			do{
				sb.append(ch);
				readch();
				if(ch == '\n' || ch == (char)Tag.EOF){
					throw new InvalidTokenException(line, '\"');
				}
			}while(ch != '\"');
			sb.append('\"');
			readch();
			String s = sb.toString();
			Word w = new Word(s, Tag.LIT);
			return w;
		}

		// Identificadores
		if (Character.isLetter(ch)){
			StringBuffer sb = new StringBuffer();
			do {
				sb.append(ch);
				readch();
			} while(Character.isLetterOrDigit(ch));
			String s = sb.toString();
			Word w = words.get(s);
			if (w != null)
				return w; 		//palavra já existe na HashTable
			w = new Word (s, Tag.ID);
			words.put(s, w);
			return w;
		}

		// Caracteres ASCII validos
		if(Tag.validASCIITokens.contains(ch) || ch == (char)Tag.EOF){
			Token t = new Token(ch);
			ch = ' ';
			return t;
		}else{
			throw new InvalidTokenException(line, ch);
		}
	}

	/* Imprime todas as entradas da tabela de símbolos */
	public void imprimirTabela() {
		System.out.println("\n\n\n**** Tabela de símbolos ****");
		for (Map.Entry<String, Word> entrada: words.entrySet()) {
			System.out.println(entrada.getKey() + ":\t\t" + entrada.getValue());
		}
	}

}