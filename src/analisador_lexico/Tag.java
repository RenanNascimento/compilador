package analisador_lexico;

public class Tag {
	public final static int

	//Palavras reservadas
	PRG   = 256,
	END   = 257,
	INT   = 258,
	STR   = 259,
	IF    = 260,
	THEN  = 261,
	ELSE  = 262,
	DO 	  = 263,
	WHILE = 264,
	SCAN  = 265,
	PRINT = 266,

	//Operadores e pontuação
	EQ  = 288, // ==
	GE  = 289, // >=
	LE  = 290, // <=
	NE  = 291, // !=
	AND = 292, // &&
	OR  = 293, // ||

	//Outros tokens
	NUM   = 300,
	ID    = 301,
	CARAC = 302;
}