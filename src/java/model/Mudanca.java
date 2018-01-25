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
    private String naoTerminal;
    
    public Mudanca (int pEstadoDestino, String pTermo, String pNaoTerminal){
        this.estadoDestino = pEstadoDestino;
        this.termo = pTermo;
        this.naoTerminal = pNaoTerminal;
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

    /**
     * @return the naoTerminal
     */
    public String getNaoTerminal() {
        return naoTerminal;
    }

    /**
     * @param naoTerminal the naoTerminal to set
     */
    public void setNaoTerminal(String naoTerminal) {
        this.naoTerminal = naoTerminal;
    }
    
    
}
