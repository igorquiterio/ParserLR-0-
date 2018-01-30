/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;

/**
 *
 * @author gustavo
 */
public class TabelaTrace {
    
    private String[][] tabelaTrace;
    private int maxLinha;
    private LinkedList<Producao> producoes;
    private TabelaLR lr0;
    private int aceito = 0;
    
    public TabelaTrace(TabelaLR lr0, String pCadeia, LinkedList<Producao> producoes) {
        LinkedList<String> pilha = new LinkedList();
        LinkedList<String> cadeia;
        LinkedList<String> linhas = new LinkedList();
        int estado = 0;
        this.producoes = producoes;
        this.lr0 = lr0;
        
        cadeia = this.prepararCadeiaReconhecimento(pCadeia);
        pilha.add("i 0");
        String termo = cadeia.getFirst();
        
        do{
            int coluna = lr0.achaTermoNoIndice(termo);
            String acao = lr0.getTabela()[estado][coluna];
            linhas.add(this.aumentarLinhaTabelaTrace(pilha, cadeia, acao));
            String[] split = acao.split(" ");
            if(split.length == 2){
                if(split[0].compareTo("s") == 0){
                    System.out.println("Fazendo um shift");
                    if(pilha != null)
                        pilha.add(termo.concat(" ").concat(split[1]));
                    cadeia.removeFirst();
                    termo = cadeia.getFirst();
                }
                else if(split[0].compareTo("r") == 0){
                    System.out.println("Fazendo o reduce");
                    pilha = this.fazerReduce(pilha, Integer.parseInt(split[1]));
                    if(pilha == null)
                        this.aceito = -3;
                }
                else
                    this.aceito = -2;
            }
            else if(acao.compareTo("a") == 0)
                this.aceito = 1;
            else
                this.aceito = -1;
            if(pilha != null){
                estado = Integer.parseInt(pilha.getLast().split(" ")[1]);   
            }
            
        }while(aceito == 0);
        
//        System.out.println("################################################################################");
//        for(String str : linhas){
//            System.out.println(str);
//        }
//        System.out.println("################################################################################");
        
        this.tabelaTrace = construirMatriz(linhas);
        
        this.mostrarTabela();
        
        if(aceito == 1){
            System.out.println("Cadeia aceita");
        }
            else{
                System.out.println("Cadei não aceita, erro: "+aceito);
            }
    }
    
    private String[][] construirMatriz(LinkedList<String> linhas){
        String[][] tabela = new String[linhas.size()][4];
        this.setMaxLinha(linhas.size());
        for(int i=0; i<linhas.size(); i++){
            String[] split = linhas.get(i).split("@#");
            if(split.length == 3){
                tabela[i][0] = Integer.toString(i+1);
                tabela[i][1] = split[0];
                tabela[i][2] = split[1];
                tabela[i][3] = split[2];
            }
            else
                System.out.println("Deu merda");
        }
        
        return tabela;
    }
    
    public void mostrarTabela(){
        for(int i=0; i<this.getMaxLinha(); i++){
            System.out.print(this.getTabelaTrace()[i][0]);
            System.out.print("\t\t");
            System.out.print(this.getTabelaTrace()[i][1]);
            System.out.print("\t\t");
            System.out.print(this.getTabelaTrace()[i][2]);
            System.out.print("\t\t");
            System.out.println(this.getTabelaTrace()[i][3]);
        }
    }
    
    private String aumentarLinhaTabelaTrace(LinkedList<String> pilha, LinkedList<String> cadeia, String acao){
        String linha = "";
        for(int i=0; i<pilha.size(); i++){
            String[] aux = pilha.get(i).split(" ");
            linha = linha.concat(aux[0]);
            if(aux.length == 2)
                linha = linha.concat(aux[1]);
            linha = linha.concat(" ");
        }
        linha = linha.concat("\t@#\t\t");
        for(int i=0; i<cadeia.size(); i++){
            linha = linha.concat(cadeia.get(i)).concat(" ");
        }
        linha = linha.concat("\t@#\t\t");
        linha = linha.concat(acao);
        return linha; 
    }
    
    private LinkedList<String> fazerReduce(LinkedList<String> pilha, int producao){
        Producao prod = this.getProducoes().get(producao).copiarProducao();
        int i = prod.getCadeia().size() - 1;
        int j = pilha.size() - 1;
        
        while(i >= 0){
            if(pilha.get(j).split(" ")[0].compareTo(prod.getCadeia().get(i)) != 0){
                return null;
            }
            else
                pilha.remove(j);
            i--;
            j--;
        }
        int coluna = getLr0().achaTermoNoIndice(prod.getNaoTerminal());
        int linha = Integer.parseInt(pilha.getLast().split(" ")[1]);
        String goTo = getLr0().getTabela()[linha][coluna].split(" ")[1];
        pilha.add(prod.getNaoTerminal().concat(" ").concat(goTo));
        return pilha;
    }
    
    private LinkedList<String> prepararCadeiaReconhecimento(String cadeia){
        LinkedList<String> lista = new LinkedList();
        
        String[] split = cadeia.split(" ");
        for(int i=0; i<split.length; i++){
            lista.add(split[i]);
        }
        
        return lista;
    }

    /**
     * @return the tabelaTrace
     */
    public String[][] getTabelaTrace() {
        return tabelaTrace;
    }

    /**
     * @return the maxLinha
     */
    public int getMaxLinha() {
        return maxLinha;
    }

    /**
     * @param maxLinha the maxLinha to set
     */
    public void setMaxLinha(int maxLinha) {
        this.maxLinha = maxLinha;
    }

    /**
     * @return the producoes
     */
    public LinkedList<Producao> getProducoes() {
        return producoes;
    }

    /**
     * @param producoes the producoes to set
     */
    public void setProducoes(LinkedList<Producao> producoes) {
        this.producoes = producoes;
    }

    /**
     * @return the lr0
     */
    public TabelaLR getLr0() {
        return lr0;
    }

    /**
     * @param lr0 the lr0 to set
     */
    public void setLr0(TabelaLR lr0) {
        this.lr0 = lr0;
    }

    /**
     * @param tabelaTrace the tabelaTrace to set
     */
    public void setTabelaTrace(String[][] tabelaTrace) {
        this.tabelaTrace = tabelaTrace;
    }

    public int getAceito() {
        return aceito;
    }

    public void setAceito(int aceito) {
        this.aceito = aceito;
    }
    
    
}
