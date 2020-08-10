package cate.nelson;

import java.util.ArrayList;

/**
 *
 * @author Valter Estevam
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String dados = "/home/valter/Cate-Nelson/data/dados.txt";
            CateNelson cateNelson = new CateNelson();    
            ArrayList<Double> limiares = new ArrayList<>();//para reproduzir o experimento do artigo original
            limiares.add(29.0);
            limiares.add(30.5);
            limiares.add(32.5);
            limiares.add(34.5);
            limiares.add(37.5);
            limiares.add(42.0);
            limiares.add(46.0);
            limiares.add(52.0);
            limiares.add(62.0);
            limiares.add(71.5);
            limiares.add(76.0);
            limiares.add(77.5);
            limiares.add(90.0);
            limiares.add(110.0);
            limiares.add(126.0);
            limiares.add(144.5);
            
            ArrayList<Iteracao> iteracoes = cateNelson.executar(dados,0,1,limiares);
            System.out.println(" População 1 \t\t\t População 2");
            for (Iteracao iter: iteracoes){
                System.out.printf("%.1f",iter.getEsquerda().getPares().get(iter.getEsquerda().getPares().size()-1).getX());
                System.out.print(" - ");
                System.out.printf("%.1f",iter.getEsquerda().getMediaY());
                System.out.print(" - ");
                System.out.printf("%.1f",iter.getEsquerda().getSomaDosQuadradosCorrigida());
                System.out.print("\t\t");
                
                //System.out.print(iter.getDireita().getPares().get(iter.getDireita().getPares().size()-1).getX());
                //System.out.print(" - ");
                System.out.printf("%.1f",iter.getDireita().getMediaY());
                System.out.print(" - ");
                System.out.printf("%.1f",iter.getDireita().getSomaDosQuadradosCorrigida());
                System.out.print(" - ");
                System.out.printf("%.1f",iter.getLimiar());
                System.out.print(" - ");
                System.out.printf("%.2f",iter.getCoeficienteCorrelacao());
                System.out.println("");
                
                //System.out.println("Limiar: "+iter.getLimiar()+" e R² = "+iter.getCoeficienteCorrelacao());
            }
        } catch (CateNelsonExeption ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
