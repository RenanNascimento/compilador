package gerador_codigo;

import analisador_lexico.Tag;
import analisador_lexico.Token;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GeradorCodigo {

    private String nome_arquivo;
    private ArrayList<Token> tokens;
    private String codigo;
    private HashMap<String, Integer> variaveis;
    private Iterator<Token> it;
    private Token token;
    private int offset;
    private int cont_label = 'A';
    private int label_atual = 0;

    HashMap<Integer, Integer> labels = new HashMap<>();  // guarda labelDestino -> labelVolta

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

    public void tratarSimpleExpression() {
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
        int tag = token.getTag();
        if(tag == Tag.MUL || tag == Tag.DIV || tag == Tag.AND){
            token = it.next(); // Consome * ou / ou &&
            codigo += "ATOI" + '\n'; // Converte o primeiro termo em int
            tratarFactorA();
            codigo += "ATOI" + '\n'; // Converte o segundo termo em int

            // Operacao
            if(tag == Tag.MUL) {
                codigo += "MUL" + '\n';
            }else {
                if (tag == Tag.DIV)
                    codigo += "DIV" + '\n';
                //else Tem que ver o operando para &&
            }
            codigo += "STRI" + '\n'; // Converte o resultado da operacao em string
            tratarTermPrime();
        }

    }

    public void tratarFactorA() {
        //factor-a			::= factor  |  !  factor  |  "-"  factor
        boolean menos=false;
        if(token.tag == Tag.MIN)
            menos= true;
        tratarFactor();
        if(menos) {
            codigo += "ATOI" + '\n';
            codigo += "PUSHS -1" + '\n';
            codigo += "MUL" + '\n';
            codigo += "STRI" + '\n';
        }
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
        tratarSimpleExpression();
        tratarExpressionPrime();
    }


    public int tratarRelop(){
        //relop				::= "==" |  ">" | "<" | "!=" | ">=" | "<="
        int tag = token.getTag();
        switch (tag) {
            case Tag.EQ:    // ==
                break;
            case (int)'>':
                break;
            case (int)'<':
                break;
            case Tag.NE:    // !=
                break;
            case Tag.GE:    // >=
                break;
            case Tag.LE:    // <=
                break;
            default:        // lambda
                return -1;
        }
        token = it.next(); // Consome o operador
        return tag;
    }

    public void tratarExpressionPrime() {
        //expression'			::= relop  simple-expr	|	λ
        int tagRelop = tratarRelop();
        if (tagRelop == -1)
            return;     // Não é relop, então é λ
        tratarSimpleExpression();
        switch (tagRelop) {
            case Tag.EQ:    // ==
                codigo += "EQUAL\n";
                break;
            case (int) '>':
                codigo += "SUP\n";
                break;
            case (int) '<':
                codigo += "INF\n";
                break;
            case Tag.NE:    // !=
                codigo += "EQUAL\n";
                codigo += "NOT\n";
                break;
            case Tag.GE:    // >=
                codigo += "SUPEQ\n";
                break;
            case Tag.LE:    // <=
                codigo += "INFEQ\n";
                break;
        }
        codigo += "NOT\n";  // Nega o que está no topo da pilha pois o jz verifica se é 0 para saltar
        int destino = cont_label++;
        int volta = cont_label++;
        label_atual = destino;
        labels.put(destino, volta);
        codigo += "JZ " + (char)destino + '\n';
        codigo += "JUMP " + (char)volta + '\n';
        codigo += (char)destino + ":\n";
    }


    public void tratarSimpleExpressionPrime() {
        //simple-expr'		::= addop term simple-expr'  | λ
        int tag = token.getTag();
        if(tag == Tag.SUM || tag == Tag.MIN || tag == Tag.OR){
            token = it.next(); // Consome + ou - ou ||
            codigo += "ATOI" + '\n'; // Converte o primeiro termo em int
            tratarFactorA();

            // Operacao
            if(tag == Tag.SUM) {
                tratarTermPrime();
                codigo += "ATOI" + '\n'; // Converte o segundo termo em int
                codigo += "ADD" + '\n';
            }else {
                if (tag == Tag.MIN)
                    tratarTermPrime();
                    codigo += "ATOI" + '\n'; // Converte o segundo termo em int
                    codigo += "SUB" + '\n';
                //else Tem que ver o operando para &&
            }
            codigo += "STRI" + '\n'; // Converte o resultado da operacao em string
            tratarSimpleExpressionPrime();
        }

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
        int parenteses = 0;
        token = it.next();  // Consome if
        /*while (token.tag == Tag.AP) {
            parenteses++;
            token = it.next();
        }*/
        tratarExpression();
        
        token = it.next();  // Consome then
        tratarStmtList();
        tratarIfPrime();
        token = it.next();  // Consome end
        voltaFluxoNormal();
    }


    public void voltaFluxoNormal() {
        int volta = labels.get(label_atual);
        codigo += "JUMP " + (char)volta + '\n';
        label_atual = volta;
        codigo += (char)volta + ":\n";
    }

    public void tratarIfPrime() {
        //if-stmt'			::=	else stmt-list | λ
        int tag = token.getTag();
        if (tag != Tag.ELSE) {
            // Sai do if
            /*int volta = labels.get(label_atual);
            codigo += "JUMP " + (volta);*/
            return;
        }

        tratarStmtList();
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
        tratarSimpleExpression();
        token = it.next(); //Consome FP
        codigo += "WRITES\n";
    }

    public void tratarAssign() {
        // assign-stmt			::= identifier "=" simple_expr
        int pos = variaveis.get(token.getLexeme());
        // Pega proximo token
        token = it.next();
        // Consome igual
        token = it.next();
        tratarSimpleExpression();
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