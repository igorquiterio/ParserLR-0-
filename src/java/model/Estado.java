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
    private LinkedList<Producao> chave;
    private LinkedList<Producao> producao;
    private LinkedList<Mudanca> mudancas;
    private LinkedList<Integer> reduce;
    private LinkedList<Integer> goTo;
    private LinkedList<Integer> shift;
    
    
    public Estado(){
        this.chave = new LinkedList();
        this.producao = new LinkedList();
        this.mudancas = new LinkedList();
        this.indice = -1;
        this.reduce = new LinkedList();
        this.goTo = new LinkedList();
        this.shift = new LinkedList();
    }
    
    public int verificarProducaoEstado(Producao teste){
        for(Producao prod : this.getProducao()){
            if(prod.compararProducao(teste)){
                return 1;
            }
        }
        return 0;
    }
    
    public void adicionarReduce(int indiceProducao){
        this.getReduce().add(indiceProducao);
    }
    
    public void adicionarGoTo(int indiceProducao){
        this.getGoTo().add(indiceProducao);
    }
    
    public void adicionarShift(int indiceProducao){
        this.getShift().add(indiceProducao);
    }
    
    public void mostrarEstado(){
        System.out.println("Estado: " + this.getIndice());
        System.out.println("Chaves:");
        for(Producao prod : this.getChave()){
            prod.mostrarProducao();
        }
        System.out.println("Produções");
        for(Producao prod : this.getProducao()){
            prod.mostrarProducao();
        }
    }

    /**
     * @return the chave
     */
    public LinkedList<Producao> getChave() {
        return chave;
    }

    /**
     * @param chave the chave to set
     */
    public void setChave(LinkedList<Producao> chave) {
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

    /**
     * @return the reduce
     */
    public LinkedList<Integer> getReduce() {
        return reduce;
    }

    /**
     * @param reduce the reduce to set
     */
    public void setReduce(LinkedList<Integer> reduce) {
        this.reduce = reduce;
    }

    /**
     * @return the goTo
     */
    public LinkedList<Integer> getGoTo() {
        return goTo;
    }

    /**
     * @param goTo the goTo to set
     */
    public void setGoTo(LinkedList<Integer> goTo) {
        this.goTo = goTo;
    }

    /**
     * @return the shift
     */
    public LinkedList<Integer> getShift() {
        return shift;
    }

    /**
     * @param shift the shift to set
     */
    public void setShift(LinkedList<Integer> shift) {
        this.shift = shift;
    }
    
    
    
}
