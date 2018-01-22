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
public class Producao {
    private String naoTerminal;
    private LinkedList<String> cadeia;
    private int pontoCorrente;
    
    public Producao (String gramatica){
        String[] linhas = gramatica.split("\n");
        for(int i=0; i<linhas.length; i++){
            String linha = linhas[i].trim();
            String nTerminal = linha.split("->")[0].trim();
            String cadeia = linha.split("->")[1].trim();
            System.out.println("Não terminal "+nTerminal);
            System.out.println("Cadeia "+ cadeia);
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
    
    
}
