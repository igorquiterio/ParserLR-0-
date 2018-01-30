/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author quiterio
 */
public class TabelaLR {
    private String [][] tabela;
    private LinkedList<String> naoTerminais;
    private LinkedList<String> terminais;
    private String indice[];
    private int maxLinha;
    private int maxColuna;

    public TabelaLR( HashSet<String> pNterminais, HashSet<String> alfabeto,
            LinkedList<Estado> pEstados) {
        
        naoTerminais = new LinkedList<>();
        terminais = new LinkedList<>();
        System.out.println("Tamanho do alfabeto "+alfabeto.size());
        indice = new String[alfabeto.size() + 1];
        System.out.println("Tamanho do indice "+indice.length);
        this.maxLinha = pEstados.size();
        this.maxColuna = (alfabeto.size() + 1);
        
        /* aloca a tabela tendo espaço para os shift (nao terminais + $)
            e para os goto(terminais) */
        tabela = new String[this.maxLinha][maxColuna];
        
        for (int i = 0; i < this.maxLinha; i++) {
            for (int j = 0; j < maxColuna; j++) {
                tabela[i][j] = new String("");
            }
        }
        
        //separa terminal e nao terminal
        for (Iterator<String> iterator = alfabeto.iterator();
                iterator.hasNext();) {
            String next = iterator.next();
            if(pNterminais.contains(next)){naoTerminais.add(next);}
            else{terminais.add(next);}
        }
        terminais.add("$");
        
        //ordem da tabela = Naoterminais(shift) e dps Terminais(go to)
        int j = 0;
        for (int i = 0; i < indice.length; i++) {
            if (i < terminais.size()) {indice[i] = terminais.get(i);}
            else{indice[i] = naoTerminais.get(j++);}
        }
        
        for (int i = 0; i < pEstados.size(); i++) {
            Estado get = pEstados.get(i);
            for (int k = 0; k < get.getMudancas().size(); k++) {
                String termo = get.getMudancas().get(k).getTermo();
                int destino = get.getMudancas().get(k).getEstadoDestino();
                
                int coluna = this.achaTermoNoIndice(termo);
                
                tabela[i][coluna] = (this.naoTerminais.contains(termo))?
                        tabela[i][coluna].concat("g "+destino+ " ") 
                        :tabela[i][coluna].concat("s "+destino+ " "); 
            }
            
            for (int k = 0; k < get.getReduce().size(); k++) {
                int prodRed = get.getReduce().get(k);
                if (prodRed == -1) {
                    int dollar = this.achaTermoNoIndice("$");
                    tabela[i][dollar] = tabela[i][dollar].concat("Acc");
                }else{
                    for (int l = 0; l < tabela[i].length; l++) {
                        tabela[i][l] = tabela[i][l].concat("r "+ prodRed);
                    }
                }
            }                      
            
        }
        
        
        for (int i = 0; i < indice.length; i++) {
            System.out.print(indice[i]+ "    ");
        }
        System.out.println("");
        System.out.println("aqui");
        for (int i = 0; i < pEstados.size(); i++) {
            for (int k = 0; k < (alfabeto.size() + 1); k++) {
                System.out.print(tabela[i][k]+" ");
            }
            System.out.println("");
        }
    }

    public int getMaxLinha() {
        return maxLinha;
    }

    public void setMaxLinha(int maxLinha) {
        this.maxLinha = maxLinha;
    }

    public int getMaxColuna() {
        return maxColuna;
    }

    public void setMaxColuna(int maxColuna) {
        this.maxColuna = maxColuna;
    }

    public String[] getIndice() {
        return indice;
    }

    public void setIndice(String[] indice) {
        this.indice = indice;
    }

    

    public String[][] getTabela() {
        return tabela;
    }

    public void setTabela(String[][] tabela) {
        this.tabela = tabela;
    }

    public LinkedList<String> getNaoTerminais() {
        return naoTerminais;
    }

    public void setNaoTerminais(LinkedList<String> naoTerminais) {
        this.naoTerminais = naoTerminais;
    }

    public LinkedList<String> getTerminais() {
        return terminais;
    }

    public void setTerminais(LinkedList<String> terminais) {
        this.terminais = terminais;
    }
    
    public int achaTermoNoIndice(String termo){
        for (int i = 0; i < indice.length; i++) {
            if(termo.compareTo(indice[i]) == 0){
                return i;
            } 
        }
        return -1;
    }
    
}
