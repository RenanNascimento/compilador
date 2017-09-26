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
			case Tag.PV:
				valor = "PV";
				break;
			case Tag.VRG:
				valor = "VRG";
				break;
			case Tag.SUM:
				valor = "SUM";
				break;
			case Tag.ASS:
				valor = "ASS";
				break;
			case Tag.AP:
				valor = "AP";
				break;
			case Tag.FP:
				valor = "FP";
				break;
			case Tag.MIN:
				valor = "MIN";
				break;
			case Tag.MUL:
				valor = "MUL";
				break;
			case Tag.DIV:
				valor = "DIV";
				break;
			case Tag.GT:
				valor = "GT";
				break;
			case Tag.LT:
				valor = "LT";
				break;
			case Tag.NOT:
				valor = "NOT";
				break;
			case Tag.EQ:
				valor = "EQ";
				break;
			case Tag.GE:
				valor = "GE";
				break;
			case Tag.LE:
				valor = "LE";
				break;
			case Tag.NE:
				valor = "NE";
				break;
			case Tag.AND:
				valor = "AND";
				break;
			case Tag.OR:
				valor = "OR";
				break;
			case Tag.NUM:
				valor = "NUM";
				break;
			case Tag.ID:
				valor = "ID";
				break;
			case Tag.LIT:
				valor = "LITERAL";
				break;
			default:
				valor = "" + (char)tag;
		}
		if (tag == Tag.LIT || tag == Tag.ID || tag == Tag.NUM){
			System.out.println("< " + valor + ", " + T + " >");
		} else {
			System.out.println("< " + valor + " >");
		}
	}


	public static boolean isLetter(char ch) {
		int A = (int)'A';
		int Z = (int)'Z';
		int a = (int)'a';
		int z = (int)'z';

		if (((int)ch >= A && (int)ch <= Z) || ((int)ch >= a && (int)ch <= z)) {
			return true;
		}
		return false;
	}


	public static boolean isLetterOrDigit(char ch) {
		int zero = (int) '0';
		int nove = (int) '9';
		if (isLetter(ch) || ((int) ch >= zero && (int) ch <= nove)) {
			return true;
		}
		return false;
	}
}