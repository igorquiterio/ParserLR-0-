/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gustavo
 */
public class Mudanca {
    private int estadoDestino;
    private String termo;
    
    public Mudanca (int pEstadoDestino, String pTermo){
        this.estadoDestino = pEstadoDestino;
        this.termo = pTermo;
    }

    /**
     * @return the estadoDestino
     */
    public int getEstadoDestino() {
        return estadoDestino;
    }

    /**
     * @param estadoDestino the estadoDestino to set
     */
    public void setEstadoDestino(int estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    /**
     * @return the termo
     */
    public String getTermo() {
        return termo;
    }

    /**
     * @param termo the termo to set
     */
    public void setTermo(String termo) {
        this.termo = termo;
    }
    
    
}
