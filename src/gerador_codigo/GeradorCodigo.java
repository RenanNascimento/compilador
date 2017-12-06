package gerador_codigo;

import analisador_lexico.Tag;
import analisador_lexico.Token;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class GeradorCodigo {

    private String nome_arquivo;
    private ArrayList<Token> tokens;
    private Hashtable<String, Integer> TS;

    private String codigo;

    public GeradorCodigo(String nome_arquivo, ArrayList<Token> tokens, Hashtable<String, Integer> TS) {
        this.nome_arquivo = nome_arquivo;
        this.tokens = tokens;
        this.TS = TS;
        codigo = "";
    }



    public void declararInteiros(int qtd) {
        // Empilha o valor inteiro 0
        codigo += "PUSHN " + qtd + '\n';
    }

    // Varre os tokens e gera o código referente a eles
    public void gerar() {
        // Varre os tokens
        Iterator<Token> it = tokens.iterator();
        while (it.hasNext()) {
            Token token = it.next();
            switch (token.tag) {
                case Tag.PRG:
                    codigo += "START\n";
                    break;
                case Tag.INT:
                    int qtd = 0;
                    
                    declararInteiros(qtd);
                    break;
                case Tag.STR:
                    break;
            }
        }
    }

}
