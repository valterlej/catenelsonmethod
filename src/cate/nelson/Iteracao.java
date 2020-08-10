package cate.nelson;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um processo iterativo do Método Cate-Nelson
 * 
 * @author Valter Estevam
 */
public class Iteracao {

    /**
     * Ponto de divisão dos valores de X em dois grupos
     */
    private double limiar;
    
    /**
     * Conjunto de pontos (X,Y) localizados ao lado esquerdo do limiar
     */
    private final Populacao esquerda;
    
    /**
     * Conjunto de pontos (X,Y) localizados ao lado direito do limiar
     */
    private final Populacao direita;
    
    /**
     * Coeficiente de correlação (R²) calculado para a iteração
     */
    private double coeficienteCorrelacao;

    public Iteracao() {
        esquerda = new Populacao();
        direita = new Populacao();
    }
        
    /**
     * Segmenta a população geral em dois grupos, esquerda e direita,
     * com base no valor do limiar.
     * 
     * @param geral conjunto de todos os pontos analisados
     * @param limiar valor de X usado para dividir o conjunto de pontos
     * 
     * @throws CateNelsonExeption 
     */
    public void dividirPopulacao(Populacao geral, double limiar) throws CateNelsonExeption{
        this.limiar = limiar;
        int count = 0;
        int i = 0;
        for (i = 0; i < geral.getPares().size(); i++) {
            if (geral.getPares().get(i).getX() < limiar) {
                count++;
            } else {
                break;
            }
            if (count >= 2) {
                break;
            }
        }
        if (count >= 2) {
            //dividir a população            
            //descobrir a posição do limiar
            int posLimiar = -1;
            for (Par p: geral.getPares()){
                if (p.getX() < limiar){
                    posLimiar++;
                }else{
                    break;
                }
            }            
            List<Par> paresEsquerda = geral.getPares().subList(0,posLimiar+1);
            ArrayList<Par> paresE = new ArrayList<>(paresEsquerda);
            List<Par> paresDireita = geral.getPares().subList(posLimiar+1,geral.getPares().size());
            ArrayList<Par> paresD = new ArrayList<>(paresDireita);
            esquerda.setPares(paresE);
            direita.setPares(paresD);
            calcularCoeficienteCorrelacao(geral.getSomaDosQuadradosCorrigida());
        }else{
            throw  new CateNelsonExeption("O limiar não atende à condição de possuir ao menos dois elementos");
        }

    }

    /**
     * Calcular o coeficiente de correlação com base nos valores da
     * soma dos quadrados corrigida para as duas populações divididas no ponto de limiar
     * 
     * @param somaDosQuadradosTotalCorrigida soma dos quadrados corrigida para o conjunto completo de pontos
     */
    private void calcularCoeficienteCorrelacao(double somaDosQuadradosTotalCorrigida) {
        double soma = esquerda.getSomaDosQuadradosCorrigida() + direita.getSomaDosQuadradosCorrigida();
        double subtracao = somaDosQuadradosTotalCorrigida - soma;
        coeficienteCorrelacao = subtracao / somaDosQuadradosTotalCorrigida;        
    }

    /**
     * @return the limiar
     */
    public double getLimiar() {
        return limiar;
    }

    /**
     * @return the esquerda
     */
    public Populacao getEsquerda() {
        return esquerda;
    }

    /**
     * @return the direita
     */
    public Populacao getDireita() {
        return direita;
    }

    public double getCoeficienteCorrelacao() {
        return this.coeficienteCorrelacao;
    }

}
