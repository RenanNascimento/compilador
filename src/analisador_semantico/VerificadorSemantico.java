package analisador_semantico;

import java.util.Hashtable;
import analisador_lexico.Tag;
import analisador_lexico.Token;
import analisador_lexico.Num;
import java.util.Map;

public class VerificadorSemantico {

    private int curType;
    private int resultExprType;
    private Hashtable<String, Integer> TL = new Hashtable<>();

    public VerificadorSemantico(){
        curType = Tag.VOID;
        resultExprType = Tag.VOID;
    }

    public void putTL(Token tok, int line){
        //Veriifica se tok ja foi declarado na TL
        if(TL.containsKey(tok.getLexeme())){
            System.out.println("Error(" + line + "): Redefinição de '"+tok.getLexeme()+"'");
            System.out.println("Fim de arquivo inesperado.");
            System.exit(0);
        }else{
            TL.put(tok.getLexeme(), curType);
        }
    }

    private String type2String(int type){
        String tipo = "int";
        //Definir o tipo
        if(type == Tag.STR){
            tipo = "string";
        }
        return tipo;
    }

    public void imprimirTL(){
        System.out.println("\n\n\n**** Identificadores na Tabela de símbolos ****\nEntrada - Tipo");
        for (Map.Entry<String, Integer> entrada: TL.entrySet()) {
            System.out.println(entrada.getKey()+" - "+type2String(entrada.getValue()));
        }
    }


    private void wrongExprType(int line, String tipo, String lexeme, String tokTipo){
        System.out.println("Error(" + line + "): Tipo '" + tipo + "' do "+ tokTipo +" '" + lexeme + "' incompatível com a expressão.");
        System.out.println("Esperava '"+type2String(resultExprType)+"'");
        System.out.println("Fim de arquivo inesperado.");
        System.exit(0);
    }

    /* Checa se o tipo dos termos (identificador) que estao formando a expressao estao corretos */
    public void checkExprIDType(Token tok, int line){
        if(resultExprType != Tag.VOID) {
            //Caso id nao tenha sido declarado
            if (!TL.containsKey(tok.getLexeme())) {
                System.out.println("Error(" + line + "): Identificador '" + tok.getLexeme() + "' não declarado");
                System.out.println("Fim de arquivo inesperado.");
                System.exit(0);
            } else {
                //Caso seja um identificador
                int idType = TL.get(tok.getLexeme());
                if (resultExprType != idType) {
                    wrongExprType(line, type2String(idType), tok.getLexeme(), "identificador");
                }
            }
        }
    }

    /* Checa se o tipo dos termos (num ou literal - NL) que estao formando a expressao estao corretos */
    public void checkExprNLType(Token tok, int line){
        if(resultExprType != Tag.VOID) {
            //Caso seja um NUM
            if (tok instanceof Num) {
                if (resultExprType != Tag.INT) {
                    wrongExprType(line, "int", String.valueOf(((Num) tok).value), "num");
                }
            } else {
                //Caso contrario so pode ser um lit
                if (resultExprType != Tag.STR) {
                    wrongExprType(line, "string", tok.getLexeme(), "literal");
                }
            }
        }
    }


    public void resetResultExprType(){
        resultExprType = Tag.VOID;
    }

    public void setCurType(int tipo){
        curType = tipo;
    }

    public void setCurAssignStmtType(Token tok, int line){
        if(resultExprType == Tag.VOID){
            //Caso id nao tenha sido declarado
            if(!TL.containsKey(tok.getLexeme())){
                System.out.println("Error(" + line + "): Identificador '"+tok.getLexeme()+"' não declarado");
                System.out.println("Fim de arquivo inesperado.");
                System.exit(0);
            }else{
                //Uma nova expressao de igualdade sera criada entao muda o tipo esperado da var que recebe
                //o resultado final
                resultExprType = TL.get(tok.getLexeme());
            }
        }
    }

}
