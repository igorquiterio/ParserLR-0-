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
    private int indiceProducao;
    
    public Mudanca (int pEstadoDestino, String pTermo, String pNaoTerminal, int pIndiceProducao){
        this.estadoDestino = pEstadoDestino;
        this.termo = pTermo;
        this.naoTerminal = pNaoTerminal;
        this.indiceProducao = pIndiceProducao;
    }
    
    
    /*
    Compara duas Mundanças e caso sejam iguais retorna true
    */
    public boolean compararMudancas(Mudanca mud){
        if(mud.getEstadoDestino() == this.getEstadoDestino()){
            if(mud.getNaoTerminal().compareTo(this.getNaoTerminal()) == 0){
                if(mud.getTermo().compareTo(this.getTermo()) == 0){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void mostrarMudanca(){
        System.out.println(this.getNaoTerminal() + " " + this.getTermo() + " " + this.getEstadoDestino());
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

    /**
     * @return the indiceProducao
     */
    public int getIndiceProducao() {
        return indiceProducao;
    }

    /**
     * @param indiceProducao the indiceProducao to set
     */
    public void setIndiceProducao(int indiceProducao) {
        this.indiceProducao = indiceProducao;
    }
    
    
}
