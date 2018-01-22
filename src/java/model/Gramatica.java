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
public class Gramatica {
    
    private LinkedList<Producao> producoes;
    private LinkedList<Estado> estados;
    
    public Gramatica (String gramatica){
        String[] linhas = gramatica.split("\n");
        
        this.producoes = new LinkedList();
        
        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i].trim();
            String nTerminal = linha.split("->")[0].trim();
            String cadeia = linha.split("->")[1].trim();
            Producao prod = new Producao(nTerminal, cadeia, i);
            this.producoes.add(prod);
        }
        for(Producao prod : this.producoes){
            System.out.print(prod.getNaoTerminal() + " -> ");
            for(String termo : prod.getCadeia()){
                System.out.print(termo + " ");
            }
            System.out.println("");
        }
        this.estados = new LinkedList();
    }
    
    public LinkedList<Estado> gerarPrimeiroEstado(){
        Producao primeira = this.producoes.get(0);
        Estado est = new Estado(primeira, 0);
        Producao copia = new Producao(primeira.getNaoTerminal(), primeira.getCadeia(), primeira.getIndice());
        copia.setPontoCorrente(0);
        if(!copia.getCadeia().isEmpty()){
            String next = copia.getCadeia().get(0);
            // verificar se o termo é não terminal para adicionar produções
            // fazer também tratamento de produção já tiver sido adicionada no estado com o ponto corrente certo
        }
        
        return null;
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
     * @return the estados
     */
    public LinkedList<Estado> getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(LinkedList<Estado> estados) {
        this.estados = estados;
    }
    
    
    
}
