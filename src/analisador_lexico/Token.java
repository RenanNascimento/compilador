package analisador_lexico;

public class Token {

	public final int tag; //constante que representa o token


	public Token (int t) {
		tag = t;
	}


	public String toString() {
		return "" + tag;
	}


	public void imprimeToken(Token T) {
		String valor;
		switch (tag) {
			case Tag.PRG:
				valor = "program";
				break;
			case Tag.END:
				valor = "end";
				break;
			case Tag.INT:
				valor = "int";
				break;
			case Tag.STR:
				valor = "string";
				break;
			case Tag.IF:
				valor = "if";
				break;
			case Tag.THEN:
				valor = "then";
				break;
			case Tag.ELSE:
				valor = "else";
				break;
			case Tag.DO:
				valor = "do";
				break;
			case Tag.WHILE:
				valor = "while";
				break;
			case Tag.SCAN:
				valor = "scan";
				break;
			case Tag.PRINT:
				valor = "print";
				break;
			case Tag.EQ:
				valor = "==";
				break;
			case Tag.GE:
				valor = ">=";
				break;
			case Tag.LE:
				valor = "<=";
				break;
			case Tag.NE:
				valor = "!=";
				break;
			case Tag.AND:
				valor = "&&";
				break;
			case Tag.OR:
				valor = "||";
				break;
			case Tag.NUM:
				valor = "num";
				break;
			case Tag.ID:
				valor = "id";
				break;
			case Tag.LIT:
				valor = "literal";
				break;
			default:
				valor = "" + (char)tag;
		}
		if(tag == Tag.LIT || tag == Tag.ID || tag == Tag.NUM){
			System.out.println("< " + valor + ", " + T + " >");
		}else {
			System.out.println("< " + valor + " >");
		}
	}
}