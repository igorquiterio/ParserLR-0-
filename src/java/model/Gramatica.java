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
    
    
    
}
