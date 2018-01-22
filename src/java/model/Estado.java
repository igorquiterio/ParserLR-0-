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
    
    private Producao chave;
    private LinkedList<Producao> producao;

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
    
    
    
}
