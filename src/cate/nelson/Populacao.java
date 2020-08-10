package cate.nelson;

import java.util.ArrayList;

/**
 * Classe usada para modelar uma população formada por um conjunto de dados (X,Y)
 * 
 * @author Valter Estevam
 */
public class Populacao {

    /**
     * Conjunto de pares contido na população
     */
    private ArrayList<Par> pares;
    
    /**
     * Valor médio do atributo do solo na população
     */
    private double mediaX;
    
    /**
     * Valor médio do rendimento relativo na população
     */
    private double mediaY;
    
    /**
     * Soma dos quadrados corrigida para a população
     */
    private double somaDosQuadradosCorrigida;
    

    public Populacao() {
        pares = new ArrayList<>();
    }
        
    /**
     * @return the pares
     */
    public ArrayList<Par> getPares() {
        return pares;
    }

    /**
     * Adicionar um conjunto completo de pares à população
     * 
     * @param pares the pares to set
     */
    public void setPares(ArrayList<Par> pares) {
        this.pares = pares;
        double somaX = 0;
        double somaY = 0;
        for (Par p: this.pares){
            somaX += p.getX();
            somaY += p.getY();
        }
        this.mediaX = somaX / this.pares.size();
        this.mediaY = somaY / this.pares.size();
        calcularSomaDosQuadradosCorrigida();
    }
    
    /**
     * Adicionar um novo par à população
     * 
     * @param par 
     */
    public void adicionarPar(Par par){
        double auxX = this.pares.size() * this.mediaX;
        double auxY = this.pares.size() * this.mediaY;
        auxX = auxX + par.getX();
        auxY = auxY + par.getY();
        this.pares.add(par);
        this.mediaX = auxX / this.pares.size();
        this.mediaY = auxY / this.pares.size();
        calcularSomaDosQuadradosCorrigida();
    }
    
    /**
     * Remover um par da população
     * 
     * @param par 
     */
    public void removerPar(Par par){
        double auxX = this.pares.size() * this.mediaX;
        double auxY = this.pares.size() * this.mediaY;
        auxX = auxX - par.getX();
        auxY = auxY - par.getY();
        this.pares.remove(par);
        this.mediaX = auxX / this.pares.size();
        this.mediaY = auxY / this.pares.size();
        calcularSomaDosQuadradosCorrigida();
    }       
    
    /**
     * Calcular a soma dos quadrados corrigida para a população com base no 
     * estado atual dos pontos (X,Y) pertencentes a ela.
     */
    private void calcularSomaDosQuadradosCorrigida(){
        double parcelaA = 0;
        double parcelaB = 0;
        for (Par p: this.pares){
            parcelaA = parcelaA + ( p.getY() * p.getY() );
            parcelaB = parcelaB + p.getY();            
        }
        this.somaDosQuadradosCorrigida = parcelaA - (parcelaB * parcelaB) / pares.size();         
    }
    
    /**
     * @return the mediaX
     */
    public double getMediaX() {
        return mediaX;
    }       

    /**
     * @return the somaDosQuadradosCorrigida
     */
    public double getSomaDosQuadradosCorrigida() {
        return somaDosQuadradosCorrigida;
    }

    /**
     * @return the mediaY
     */
    public double getMediaY() {
        return mediaY;
    }   
    
}
