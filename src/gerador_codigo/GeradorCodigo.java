package gerador_codigo;

import analisador_lexico.Tag;
import analisador_lexico.Token;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

public class GeradorCodigo {

    private String nome_arquivo;
    private ArrayList<Token> tokens;
    private String codigo;
    HashMap<String, Integer> variaveis;
    Iterator<Token> it;
    Token token;
    int offset;

    public GeradorCodigo(String nome_arquivo, ArrayList<Token> tokens) {
        this.nome_arquivo = nome_arquivo;
        this.tokens = tokens;
        variaveis = new HashMap<>();
        it = tokens.iterator();
        offset=0;
        codigo = "";
    }


    public void tratarDeclList() {
        int qtd = 0;
        while(token.tag == Tag.INT || token.tag == Tag.STR) {
            do {
                token = it.next();
                variaveis.put(token.getLexeme(), offset);
                offset++;
                token = it.next();
                qtd++;
            } while (it.hasNext() && token.tag != Tag.PV);
            token = it.next(); // Consome PV
        }
            // Empilha o valor inteiro 0
        codigo += "PUSHN " + qtd + '\n';
        //token = it.next(); // Consome PV
    }

    public void tratarSimpleExpr() {
        //simple-expr			::= term  simple-expr'
        tratarTerm();
        tratarSimpleExpressionPrime();
    }

    public void tratarTerm() {
        //term                ::= factor-a  term'
        tratarFactorA();
        tratarTermPrime();
    }

    public void tratarTermPrime() {
        //term'               ::= mulop factor-a term' | λ

    }

    public void tratarFactorA() {
        //factor-a			::= factor  |  !  factor  |  "-"  factor
        tratarFactor();
    }

    public void tratarFactor() {
        //factor          	::= identifier  |  constant  |  "("  expression  ")"
        switch (token.getTag()) {
            case Tag.ID:
                int pos = variaveis.get(token.getLexeme());
                // pega o valor da variavel
                codigo += "PUSHG " + pos + '\n';
                token = it.next(); // Consome PV
                break;
            case Tag.NUM:
                codigo += "PUSHS \"" + token.toString() + "\"\n";
                //codigo += "ATOI" + '\n';
                token = it.next(); // Consome PV
                break;
            case Tag.LIT:
                codigo += "PUSHS " + token.getLexeme() + '\n';
                token = it.next(); // Consome PV
                break;
            case Tag.AP:
                token = it.next(); // Consome AP
                tratarExpression();
                token = it.next(); // Consome FP
                break;
        }
    }

    public void tratarExpression() {
        //expression			::= simple-expr  expression'
        tratarSimpleExpr();
        tratarExpressionPrime();
    }

    public void tratarExpressionPrime() {
        //expression'			::= relop  simple-expr	|	λ

    }

    public void tratarSimpleExpression() {
        //simple-expr			::= term  simple-expr'
    }

    public void tratarSimpleExpressionPrime() {
        //simple-expr'		::= addop term simple-expr'  | λ
    }

    public void tratarStmtList() {
        tratarStmt();
        tratarStmtListPrime();
    }

    public void tratarStmtListPrime() {
        while(token.tag != Tag.END){
            tratarStmt();
        }
    }

    public void tratarStmt() {
        switch (token.getTag()) {
            case Tag.ID:
                tratarAssign();
                token = it.next(); // Consome PV
                break;
            case Tag.IF:
                tratarIf();
                break;
            case Tag.WHILE:
                tratarWhile();
                break;
            case Tag.SCAN:
                tratarRead();
                token = it.next(); // Consome PV
                break;
            case Tag.PRINT:
                tratarWrite();
                token = it.next(); // Consome PV
                break;
        }
    }

    public void tratarIf() {
        //if-stmt				::= if  expression  then  stmt-list  if-stmt' end
    }

    public void tratarIfPrime() {
        //if-stmt'			::=	else stmt-list | λ
    }

    public void tratarWhile() {
        //while-stmt			::= do stmt-list stmt-sufix
    }

    public void tratarStmtSufix() {
        //stmt-sufix			::= while expression end
    }

    public void tratarRead() {
        token = it.next(); //Consome Scan
        token = it.next(); //Consome AP
        int pos = variaveis.get(token.getLexeme());
        token = it.next(); //Consome ID
        token = it.next(); //Consome FP
        codigo += "READ\n";
        codigo += "STOREL " + pos + '\n';
    }

    public void tratarWrite() {
        token = it.next(); //Consome PRINT
        token = it.next(); //Consome AP
        tratarSimpleExpr();
        token = it.next(); //Consome FP
        codigo += "WRITES\n";
    }

    public void tratarAssign() {
        int pos = variaveis.get(token.getLexeme());
        // Pega proximo token
        token = it.next();
        // Consome igual
        token = it.next();
        tratarSimpleExpr();
        codigo += "STOREL " + pos + '\n';
    }

    // Varre os tokens e gera o código referente a eles
    public void gerar() {
        token = it.next();
        codigo += "START\n";
        token = it.next();
        tratarDeclList();
        tratarStmtList();
        codigo += "STOP\n";
        gravarCodigo();
    }


    public void gravarCodigo() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(nome_arquivo, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.write(codigo);
        writer.close();
    }



}
