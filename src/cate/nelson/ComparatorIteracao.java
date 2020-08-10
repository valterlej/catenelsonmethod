package cate.nelson;

import java.util.Comparator;

/**
 * Classe utilizada para fazer a ordenação de um array de Iterações.
 *
 * @author Valter Estevam
 */
public class ComparatorIteracao implements Comparator<Iteracao>{

    /**
     * Comparador utilizado para ordenar um array de iterações em
     * ordem crescente de limiar.
     * 
     * @param i1
     * @param i2
     * @return 
     */
    @Override
    public int compare(Iteracao i1, Iteracao i2) {
        if (i1.getLimiar() > i2.getLimiar()){
            return 1;
        }else if (i1.getLimiar() < i2.getLimiar()){
            return -1;
        }else{
            return 0;
        }
    }       
}
