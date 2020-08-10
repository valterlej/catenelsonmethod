package cate.nelson;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

/**
 * 
 * Implementação do método Cate-Nelson para discretização de atributos químicos do solo
 * 
 * CATE JR., R. B,; NELSON, L. A. A simple statistical procedure for particioning soil test 
 * correlation data into two classes. Soil Science American Proceedings, v. 35, p. 658-660, 1971.
 * 
 *
 * @author Valter Estevam
 */
public class AppNew {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            
            JOptionPane.showMessageDialog(null, "Selecione um arquivo .csv no formato:\nsoil test, percentage yeld --->  x,y\nO arquivo não deve conter cabeçalho e deve possuir um registro por linha.", "Instrução de Uso", JOptionPane.INFORMATION_MESSAGE);
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);
            // int returnValue = jfc.showSaveDialog(null);
            if (returnValue != JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "Arquivo não selecionado. O programa será encerrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());

            //String dados = "/home/valter/Cate-Nelson/data/dados.txt";
            String dados = selectedFile.getAbsolutePath();
            CateNelson cateNelson = new CateNelson();
            
            
            /**
             * Para reproduzir o experimento do artigo original usar os valores abaixo
             * 
             * Em caso de experimento com outros dados os limiares a serem experimentados devem ser trocados.
             */
            ArrayList<Double> limiares = new ArrayList<>();
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

            ArrayList<Iteracao> iteracoes = cateNelson.executar(dados, 0, 1, limiares);
            System.out.println(" População 1 \t\t\t População 2");
            System.out.println("último valor x - valor médio y - soma quadrados corrigida  ---- último valor x - valor médio y - soma quadrados corrigida --- limiar - coef correlação");
            for (Iteracao iter : iteracoes) {
                System.out.printf("%.1f", iter.getEsquerda().getPares().get(iter.getEsquerda().getPares().size() - 1).getX());
                System.out.print(" - ");
                System.out.printf("%.1f", iter.getEsquerda().getMediaY());
                System.out.print(" - ");
                System.out.printf("%.1f", iter.getEsquerda().getSomaDosQuadradosCorrigida());
                System.out.print("\t\t");
                System.out.print(iter.getDireita().getPares().get(iter.getDireita().getPares().size()-1).getX());
                System.out.print(" - ");
                System.out.printf("%.1f", iter.getDireita().getMediaY());
                System.out.print(" - ");
                System.out.printf("%.1f", iter.getDireita().getSomaDosQuadradosCorrigida());
                System.out.print(" - ");
                System.out.printf("\t\t%.1f", iter.getLimiar());
                System.out.print(" - ");
                System.out.printf("%.2f", iter.getCoeficienteCorrelacao());
                System.out.println("");
            }
        } catch (CateNelsonExeption ex) {
            System.out.println(ex.getMessage());
        }
    }

}
