/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author gustavo
 */
public class Producao {
    private String naoTerminal;
    private LinkedList<String> cadeia;
    private int pontoCorrente;
    private int indice;
    
    public Producao(String pNaoTerminal, String pCadeia, int index){
        this.naoTerminal = pNaoTerminal;
        this.cadeia = new LinkedList();
        if(pCadeia.length() > 0){
            String[] termos = pCadeia.split(" ");
            this.cadeia.addAll(Arrays.asList(termos));
        }
        this.indice = index;
        this.pontoCorrente = -1;
    }
    
    public Producao(String pNaoTerminal, LinkedList<String> pCadeia, int index){
        this.naoTerminal = pNaoTerminal;
        this.cadeia = pCadeia;
        this.indice = index;
        this.pontoCorrente = -1;
    }
    
    /*
    Retorna um cópia da produção
    */
    public Producao copiarProducao(){
        Producao prod = new Producao(this.naoTerminal, this.cadeia, this.indice);
        prod.setPontoCorrente(this.pontoCorrente);
        return prod;
    }
    
    public boolean compararProducao(Producao prod){
        if(prod.getIndice() == this.indice){
            if(prod.getPontoCorrente() == this.pontoCorrente){
                return true;
            }
            else if(prod.getPontoCorrente() == -1 || this.pontoCorrente == -1){
                return true;
            }
        }
        return false;
    }
    
    public void mostrarProducao(){
        System.out.print(this.naoTerminal + " ->");
        int i = 0;
        if(this.cadeia.isEmpty() && this.pontoCorrente != -1){
            System.out.println(" .");
        }
        else{
            for(String termo : this.cadeia){
                if(i == this.pontoCorrente){
                    System.out.print(" .");
                }
                i++;
                System.out.print(" " + termo);
            }
            if(i == this.pontoCorrente){
                System.out.print(" .");
            }
            System.out.println();
        }
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
     * @return the cadeia
     */
    public LinkedList<String> getCadeia() {
        return cadeia;
    }

    /**
     * @param cadeia the cadeia to set
     */
    public void setCadeia(LinkedList<String> cadeia) {
        this.cadeia = cadeia;
    }

    /**
     * @return the pontoCorrente
     */
    public int getPontoCorrente() {
        return pontoCorrente;
    }

    /**
     * @param pontoCorrente the pontoCorrente to set
     */
    public void setPontoCorrente(int pontoCorrente) {
        this.pontoCorrente = pontoCorrente;
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
