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
public class Estado {
    
    private int indice;
    private Producao chave;
    private LinkedList<Producao> producao;
    private LinkedList<Mudanca> mudancas;
    
    public Estado (Producao pChave, int pIndice){
        this.chave = pChave;
        this.indice = pIndice;
        this.mudancas = new LinkedList();
        this.producao = new LinkedList();
    }

    /**
     * @return the chave
     */
    public Producao getChave() {
        return chave;
    }

    /**
     * @param chave the chave to set
     */
    public void setChave(Producao chave) {
        this.chave = chave;
    }

    /**
     * @return the producao
     */
    public LinkedList<Producao> getProducao() {
        return producao;
    }

    /**
     * @param producao the producao to set
     */
    public void setProducao(LinkedList<Producao> producao) {
        this.producao = producao;
    }

    /**
     * @return the mudancas
     */
    public LinkedList<Mudanca> getMudancas() {
        return mudancas;
    }

    /**
     * @param mudancas the mudancas to set
     */
    public void setMudancas(LinkedList<Mudanca> mudancas) {
        this.mudancas = mudancas;
    }

    /**
     * @return the indice
     */
    public int getIndice() {
        return indice;
    }

    /**
     * @param indice the indice to set
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    
    
}
