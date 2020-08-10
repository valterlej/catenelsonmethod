package cate.nelson;

import java.util.Comparator;

/**
 * Faz a comparação dos pares de valores (X,Y) pelo valor de X crescente
 *
 * @author Valter Estevam
 */
public class ComparatorPar implements Comparator<Par>{

    @Override
    public int compare(Par p1, Par p2) {
        if (p1.getX() > p2.getX()){
            return 1;
        }else if(p1.getX() < p2.getX()) {
            return -1;
        }else{
            return 0;
        }
    }           
}
