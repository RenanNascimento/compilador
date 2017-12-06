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



    public void declararVariaveis() {
        int qtd = 0;
        do {
            token = it.next();
            variaveis.put(token.getLexeme(),offset);
            offset++;
            token = it.next();
            qtd++;
        } while (it.hasNext() && token.tag != Tag.PV);
        // Empilha o valor inteiro 0
        codigo += "PUSHN " + qtd + '\n';
    }

    public void tratarAssign() {
        int pos = variaveis.get(token.getLexeme());
        // Pega proximo token
        token = it.next();
        // Consome igual
        token = it.next();
        boolean neg = false;
        if(token.tag == Tag.MIN){
            neg = true;
            token = it.next();
        }
                    /*
                    simple-expr			::= factor  term' simple-expr'
                    simple-expr'		::= addop term simple-expr'  | λ
                    term'               ::= mulop factor-a term' | λ
                    factor          	::= identifier  |  constant  |  "("  expression  ")"
                    addop				::= "+" 	|  "-"  |  "||"
                    mulop               ::= "*" 	|  "/"  |  "&&"
                    constant            ::= integer_const  |  literal
                    */
    }

    // Varre os tokens e gera o código referente a eles
    public void gerar() {
        // Varre os tokens
        while (it.hasNext()) {
            token = it.next();
            switch (token.tag) {
                case Tag.PRG:
                    codigo += "START\n";
                    break;
                case Tag.INT:
                case Tag.STR:
                    declararVariaveis();
                    break;
                case Tag.ID:
                    tratarAssign();
                    break;
                case Tag.IF:
                    break;
                case Tag.WHILE:
                    break;
                case Tag.SCAN:
                    break;
                case Tag.PRINT:
                    break;
                case Tag.END:
                    codigo += "STOP\n";
                    break;
            }
        }
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
