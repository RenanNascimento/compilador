package analisador_sintatico;

import analisador_lexico.Tag;
import analisador_lexico.Token;
import analisador_semantico.VerificadorSemantico;
import java.util.ArrayList;

public class Parser {
    private Token tok;
    private int tag;
    private int i;
    private int line;
    private ArrayList<Token> tokens = new ArrayList<Token> ();
    private VerificadorSemantico vs;

    public Parser(ArrayList<Token> tokens){
        this.tokens=tokens;
        i=0;
        tok=tokens.get(i);
        tag=tok.tag;
        line=tok.line;
        vs = new VerificadorSemantico();
    }

    public void init(){
        program();
    };

    private void advance(){
        i++;
        tok=tokens.get(i);
        tag=tok.tag;
        line=tok.line;
    }

    private void error(){
        if(tag==Tag.EOF) {
            if(line==-4)
                System.out.println("Arquivo de entrada vazio");
            return;
        }
        System.out.print("Error(" + line + "): Token não esperado:");
        tok.imprimeToken(tok);
        while(tag!=Tag.EOF)
            advance();
    }

    private void eat(int t){
        if(tag==t){
            //Checa se a tag e um tipo basico
            if(tag == Tag.INT || tag == Tag.STR){
                vs.setCurType(tag);
            }
            //Caso ; deve resetar o resultado esperado de uma expressao
            if(tag == Tag.PV){
                vs.resetResultExprType();
            }
            System.out.print("Token Consumido("+line+"): ");
            tok.imprimeToken(tok);
            advance();
        }
        else error();
    }

    private void program(){
        switch(tag) {
            // program ::= program decl-list stmt-list end
            case Tag.PRG:
                eat(Tag.PRG); declList(); stmtList();
                if (tag == Tag.EOF)
                    System.out.println("Fim de arquivo inesperado.");
                else
                    eat(Tag.END);
                    vs.imprimirTL();
                break;
            default:
                error();
        }
    }

    private void declList(){
        switch(tag) {
            // decl-list ::= decl decl-list
            case Tag.INT:
            case Tag.STR:
                decl(); declList();
                break;
            //decl-list ::= λ
            case Tag.ID:
            case Tag.IF:
            case Tag.DO:
            case Tag.SCAN:
            case Tag.PRINT:
                break;
            default:
                error();
        }
    }

    private void decl(){
        switch(tag) {
            //decl ::= type ident-list ";"
            case Tag.INT:
            case Tag.STR:
                type(); identList(); eat(Tag.PV);
                break;
            default:
                error();
        }
    }

    private void identList(){
        switch(tag) {
            //ident-list ::= identifier ident-list'
            case Tag.ID:
                vs.putTL(tok, line); eat(Tag.ID); identListPrime();
                break;
            default:
                error();
        }
    }

    private void identListPrime(){
        switch(tag) {
            //ident-list' ::= "," identifier ident-list'
            case Tag.VRG:
                eat(Tag.VRG); vs.putTL(tok, line); eat(Tag.ID); identListPrime();
                break;
            //ident-list' λ
            case Tag.PV:
                break;
            default:
                error();
        }
    }

    private void type(){
        switch(tag) {
            //type ::= int
            case Tag.INT:
                eat(Tag.INT);
                break;
            //type ::= string
            case Tag.STR:
                eat(Tag.STR);
                break;
            default:
                error();
        }
    }

    private void stmtList(){
        switch(tag) {
            //stmt-list ::= stmt stmt-list'
            case Tag.ID:
            case Tag.IF:
            case Tag.DO:
            case Tag.SCAN:
            case Tag.PRINT:
                stmt(); stmtListPrime();
                break;
            default:
                error();
        }
    }

    private void stmtListPrime(){
        switch(tag) {
            //stmt-list' ::= stmt stmt-list'
            case Tag.ID:
            case Tag.IF:
            case Tag.DO:
            case Tag.SCAN:
            case Tag.PRINT:
                stmt(); stmtListPrime();
                break;
            //stmt-list' ::= λ
            case Tag.ELSE:
            case Tag.WHILE:
            case Tag.END:
                break;
            default:
                error();
        }
    }

    private void stmt(){
        switch(tag) {
            //stmt ::= assign-stmt ";"
            case Tag.ID:
                assignStmt(); eat(Tag.PV);
                break;
            //stmt ::= if-stmt
            case Tag.IF:
                ifStmt();
                break;
            //stmt ::= while-stmt
            case Tag.DO:
                whileStmt();
                break;
            //stmt ::= read-stmt ";"
            case Tag.SCAN:
                readStmt(); eat(Tag.PV);
                break;
            //stmt ::= write-stmt ";"
            case Tag.PRINT:
                writeStmt(); eat(Tag.PV);
                break;
            default:
                error();
        }
    }

    private void assignStmt(){
        switch(tag) {
            //assign-stmt ::= identifier "=" simple_expr
            case Tag.ID:
                vs.setCurAssignStmtType(tok, line); eat(Tag.ID); eat(Tag.ASS); simpleExpr();
                break;
            default:
                error();
        }
    }

    private void ifStmt(){
        switch(tag) {
            //if-stmt ::= if  expression  then  stmt-list  if-stmt' end
            case Tag.IF:
                eat(Tag.IF); expression(); eat(Tag.THEN); stmtList(); ifStmtPrime(); eat(Tag.END);
                break;
            default:
                error();
        }
    }

    private void ifStmtPrime(){
        switch(tag) {
            //if-stmt' ::= else stmt-list
            case Tag.ELSE:
                eat(Tag.ELSE); stmtList();
                break;
            //if-stmt' ::= λ
            case Tag.END:
                break;
            default:
                error();
        }
    }

    private void whileStmt(){
        switch(tag) {
			//while-stmt ::= do stmt-list stmt-sufix
            case Tag.DO:
                eat(Tag.DO); stmtList(); stmtSufix();
                break;
            default:
                error();
        }
    }

    private void stmtSufix(){
        switch(tag) {
            //stmt-sufix ::= while expression end
            case Tag.WHILE:
                eat(Tag.WHILE); expression(); eat(Tag.END);
                break;
            default:
                error();
        }
    }

    private void readStmt(){
        switch(tag) {
            //read-stmt ::= scan  "("  identifier  ")"
            case Tag.SCAN:
                eat(Tag.SCAN); eat(Tag.AP); eat(Tag.ID); eat(Tag.FP);
                break;
            default:
                error();
        }
    }

    private void writeStmt(){
        switch(tag) {
            //write-stmt ::= print  "("  simple-expr  ")"
            case Tag.PRINT:
                eat(Tag.PRINT); eat(Tag.AP); simpleExpr(); eat(Tag.FP);
                break;
            default:
                error();
        }
    }

    private void expression(){
        switch(tag) {
            //expression ::= simple-expr  expression'
            case Tag.ID:
            case Tag.NUM:
            case Tag.LIT:
            case Tag.MIN:
            case Tag.NOT:
            case Tag.AP:
                simpleExpr(); expressionPrime();
                break;
            default:
                error();
        }
    }

    private void expressionPrime(){
        switch(tag) {
            //expression' ::= relop  simple-expr
            case Tag.GT:
            case Tag.LT:
            case Tag.LIT:
            case Tag.GE:
            case Tag.LE:
            case Tag.NE:
            case Tag.EQ:
                relop(); simpleExpr();
                break;
            //expression' ::= λ
            case Tag.THEN:
            case Tag.END:
            case Tag.FP:
                break;
            default:
                error();
        }
    }

    private void simpleExpr(){
        switch(tag) {
            //simple-expr ::= term  simple-expr'
            case Tag.ID:
            case Tag.NUM:
            case Tag.LIT:
            case Tag.MIN:
            case Tag.NOT:
            case Tag.AP:
                term();  simpleExprPrime();
                break;
            default:
                error();
        }
    }

    private void simpleExprPrime(){
        switch(tag) {
            //simple-expr' ::= addop term simple-expr'
            case Tag.MIN:
            case Tag.SUM:
            case Tag.OR:
                addop(); term(); simpleExprPrime();
                break;
            //simple-expr' ::= λ
            case Tag.THEN:
            case Tag.END:
            case Tag.GT:
            case Tag.LT:
            case Tag.FP:
            case Tag.GE:
            case Tag.LE:
            case Tag.NE:
            case Tag.EQ:
            case Tag.PV:
                break;
            default:
                error();
        }
    }

    private void term(){
        switch(tag) {
            //term ::= factor-a  term'
            case Tag.ID:
            case Tag.NUM:
            case Tag.LIT:
            case Tag.MIN:
            case Tag.NOT:
            case Tag.AP:
                factorA(); termPrime();
                break;
            default:
                error();
        }
    }

    private void termPrime(){
        switch(tag) {
            //term' ::= mulop factor-a term'
            case Tag.MUL:
            case Tag.DIV:
            case Tag.AND:
                mulop(); factorA(); termPrime();
                break;
            //term' ::= λ
            case Tag.THEN:
            case Tag.END:
            case Tag.MIN:
            case Tag.GT:
            case Tag.LT:
            case Tag.SUM:
            case Tag.OR:
            case Tag.FP:
            case Tag.GE:
            case Tag.LE:
            case Tag.NE:
            case Tag.EQ:
            case Tag.PV:
                break;
            default:
                error();
        }
    }

    private void factorA(){
        switch(tag) {
            //factor-a ::= factor
            case Tag.ID:
            case Tag.NUM:
            case Tag.LIT:
            case Tag.AP:
                factor();
                break;
            //factor-a ::= !  factor
            case Tag.NOT:
                eat(Tag.NOT); factor();
                break;
            //factor-a ::= "-"  factor
            case Tag.MIN:
                eat(Tag.MIN); factor();
                break;
            default:
                error();
        }
    }

    private void factor(){
        switch(tag) {
            //factor ::= identifier
            case Tag.ID:
                vs.checkExprIDType(tok, line); eat(Tag.ID);
                break;
            //factor ::= constant
            case Tag.NUM:
            case Tag.LIT:
                constant();
                break;
            //factor ::= "("  expression  ")"
            case Tag.AP:
                eat(Tag.AP); expression(); eat(Tag.FP);
                break;
            default:
                error();
        }
    }

    private void relop(){
        switch(tag) {
            //relop ::= "=="
            case Tag.EQ:
                eat(Tag.EQ);
                break;
            //relop ::= ">"
            case Tag.GT:
                eat(Tag.GT);
                break;
            //relop ::= "<"
            case Tag.LT:
                eat(Tag.LT);
                break;
            //relop ::= "!="
            case Tag.NE:
                eat(Tag.NE);
                break;
            //relop ::= ">="
            case Tag.GE:
                eat(Tag.GE);
                break;
            //relop ::= "<="
            case Tag.LE:
                eat(Tag.LE);
                break;
            default:
                error();
        }
    }

    private void addop(){
        switch(tag) {
            //addop ::= "+"
            case Tag.SUM:
                eat(Tag.SUM);
                break;
            //addop ::= "-"
            case Tag.MIN:
                eat(Tag.MIN);
                break;
            //addop ::= "||"
            case Tag.OR:
                eat(Tag.OR);
                break;
            default:
                error();
        }
    }

    private void mulop(){
        switch(tag) {
            //mulop ::= "*"
            case Tag.MUL:
                eat(Tag.MUL);
                break;
            //mulop ::= "/"
            case Tag.DIV:
                eat(Tag.DIV);
                break;
            //mulop ::= "&&"
            case Tag.AND:
                eat(Tag.AND);
                break;
            default:
                error();
        }
    }

    private void constant() {
        switch (tag) {
            //constant ::= integer_const
            case Tag.NUM:
                vs.checkExprNLType(tok, line); eat(Tag.NUM);
                break;
            //constant ::= literal
            case Tag.LIT:
                vs.checkExprNLType(tok, line); eat(Tag.LIT);
                break;
            default:
                error();
        }
    }
}
