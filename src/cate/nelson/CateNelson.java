package cate.nelson;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa o método Cate-Nelson
 * 
 * @author Valter Estevam
 */
public class CateNelson {

    /**
     * Conjunto de todos os pares de valores (X,Y)
     */
    private final Populacao populacaoTotal;

    
    public CateNelson() {
        populacaoTotal = new Populacao();
    }
    
            
    /**
     * Faz a leitura dos pares de dados a partir de um arquivo de texto passado por parâmetro
     * 
     * @param path caminho para o arquivo que contém os dados
     * @param colX coluna contendo os dados sobre o atributo do solo
     * @param colY coluna contendo os dados sobre o rendimento     
     */
    private void lerPares(String path, int colX, int colY)throws ReaderException{
        try {
            ReaderCSV reader = new ReaderCSV(path,",");
            String dados[][] = reader.read();
            ArrayList<Par> pares = new ArrayList<>();
            for (String s[]: dados){
                double x = Double.parseDouble(s[colX]);
                double y = Double.parseDouble(s[colY]);
                Par p = new Par(x, y);
                pares.add(p);
            }
            ComparatorPar cp = new ComparatorPar();
            Collections.sort(pares,cp);//orderar os pares pelo valor de X            
            populacaoTotal.setPares(pares);            
        } catch (ReaderException ex) {
            throw ex;
        }                
    }
    
    /**
     * Executa o método Cate-Nelson para os dados contidos em um arquivo de texto
     * com o atributo do solo indicado na coluna X e o valor de produtividade relativa 
     * indicado na coluna Y. Retorna uma lista de Iterações dados os pontos de limiar a 
     * serem testados.
     * 
     * @param path caminho do arquivo de dados
     * @param colX coluna contendo o atributo do solo
     * @param colY coluna contendo o rendimento relativo
     * @param limiares relação de pontos a realizar o cálculo de correlação
     * @return Conjunto de iterações com os resultados da execução do método.
     * 
     * @throws CateNelsonExeption 
     */
    public ArrayList<Iteracao> executar(String path, int colX, int colY,ArrayList<Double> limiares) throws CateNelsonExeption{        
        try {
            lerPares(path,0,1);//os pares da população já se encontram ordenados
            ArrayList<Iteracao> iteracoes = new ArrayList<>();
            //método "burro"
            for (double limiar: limiares){
                Iteracao iteracao = new Iteracao();
                try {
                    //ao dividir a população todos os cálculos relativos às novas populações já são executados
                    iteracao.dividirPopulacao(populacaoTotal,limiar);
                    iteracoes.add(iteracao);
                } catch (CateNelsonExeption ex) {                    
                    //não é necessário tratar, as populações não serão geradas
                }
            }
            
            //comparador utilizado para ordenar as iterações pelo ponto de limiar, tal como no artigo original
            ComparatorIteracao ci = new ComparatorIteracao();            
            Collections.sort(iteracoes,ci);//o array agora está ordenado pelo limiar crescente
            
            return iteracoes;                                                
        } catch (ReaderException ex) {
            throw new CateNelsonExeption(ex.getMessage());
        }                
    }

    /**
     * @return the populacaoTotal
     */
    public Populacao getPopulacaoTotal() {
        return populacaoTotal;
    }
            
}
