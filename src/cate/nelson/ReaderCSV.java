package cate.nelson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Esta classe faz a leitura de um arquivo csv utilizando qualquer tipo de
 * caractere separador e iniciando em uma linha indicada pelo usuário ou pela
 * primeira
 *
 *
 * @author Valter Estevam
 */
public class ReaderCSV {

    private final File file;
    private final String separator;
    private final int startLine;

    /**
     * Instancia um objeto responsável pela leitura de um arquivo utilizando a
     * vírgula como separador e iniciando a leitura na primeira linha
     *
     * @param file
     */
    public ReaderCSV(String file) {
        this.file = new File(file);
        this.separator = ",";
        this.startLine = 1;
    }

    /**
     * Instancia um objeto responsável pela leitura de um arquivo iniciando a
     * leitura na linha indicada pelo usuário e a vírgula como separador
     *
     * @param file nome do arquivo que será lido
     * @param startLine linha de início. Se for informado um valor menor do que
     * 1 iniciará na primeira linha
     */
    public ReaderCSV(String file, int startLine) {
        this.file = new File(file);
        this.separator = ",";
        if (startLine >= 1) {
            this.startLine = startLine;
        } else {
            this.startLine = 1;
        }
    }

    /**
     * Instancia um objeto responsável pela leitura de um arquivo iniciando a
     * leitura na primeira linha, com o(s) caractere(s) desejado(s)
     *
     * @param file nome do arquivo que será lido
     * @param separator caracter(s) utilizado(s) para separar as colunas
     */
    public ReaderCSV(String file, String separator) {
        this.file = new File(file);
        this.separator = separator;
        this.startLine = 1;
    }

    /**
     * Instancia um objeto responsável pela leitura de um arquivo iniciando a
     * leitura na linha indicada pelo usuário e o(s) caractere(s) desejado(s)
     *
     * @param file nome do arquivo que será lido
     * @param separator caracter(s) utilizado(s) para separar as colunas
     * @param startLine linha de início. Se for informado um valor menor do que
     * 1 iniciará na primeira linha
     */
    public ReaderCSV(String file, String separator, int startLine) {
        this.file = new File(file);
        this.separator = separator;
        if (startLine >= 1) {
            this.startLine = startLine;
        } else {
            this.startLine = 1;
        }
    }

    /**
     * Faz a leitura do arquivo csv retornando uma tabela de strings
     *
     * @return tabela de strings contendo os dados
     * @throws ReaderException
     */
    public String[][] read() throws ReaderException {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String linha = "";
            int numeroColunas = 0;
            int numeroLinhas = 0;
            int i = 1;

            /*
             Descobrir o número de linhas e o número de colunas do arquivo
             */
            while ((linha = reader.readLine()) != null) {
                if (linha.lastIndexOf(separator) == linha.length() - 1) {
                    linha += " ";
                }
                String valores[] = linha.split(separator);
                if (numeroColunas == 0) {
                    numeroColunas = valores.length;
                } else if (numeroColunas != valores.length) {
                    throw new ReaderException("Falha na leitura do arquivo. A linha " + i + " contém " + valores.length + " colunas em vez de " + numeroColunas + ".");
                }
                i++;
                numeroLinhas++;
            }
            if (numeroLinhas < startLine) {
                throw new ReaderException("Não é possível iniciar a leitura a partir da linha " + startLine
                        + " pois o arquivo contém uma quantidade de linhas menor do que " + startLine);
            }

            String dados[][] = new String[numeroLinhas - startLine + 1][numeroColunas];

            FileInputStream fis2 = new FileInputStream(file);
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(fis2));

            i = 0;
            int j = 0;
            //leitura do arquivo
            int aux = 0;
            while ((linha = reader2.readLine()) != null) {
                aux++;
                if (aux >= startLine) {
                    String valores[] = linha.split(separator);
                    for (String s : valores) {
                        if (valores[j] == null) {
                            valores[j] = "";
                        }
                        if (valores[j].contains("\"")) {
                            valores[j] = valores[j].replaceAll("\"", "");
                        }
                        dados[i][j] = valores[j].toUpperCase();
                        j++;
                    }
                    i++;
                    j = 0;
                }
            }

            return dados;
        } catch (FileNotFoundException ex) {
            throw new ReaderException("Arquivo csv não encontrado!");
        } catch (IOException ex) {
            throw new ReaderException("Problema na leitura do arquivo!");
        }
    }

}
