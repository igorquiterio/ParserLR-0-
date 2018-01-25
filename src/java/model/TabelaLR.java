/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author quiterio
 */
public class TabelaLR {
    private String [][] tabela;
    private LinkedList<String> naoTerminais;
    private LinkedList<String> terminais;
    private String indice[];

    public TabelaLR( HashSet<String> pNterminais, HashSet<String> alfabeto,
            LinkedList<Estado> pEstados) {
        
        naoTerminais = new LinkedList<>();
        terminais = new LinkedList<>();
        indice = new String[alfabeto.size() + 1];
        
        /* aloca a tabela tendo espaço para os shift (nao terminais + $)
            e para os goto(terminais) */
        tabela = new String[pEstados.size()][alfabeto.size() + 1];
        
        //separa terminal e nao terminal
        for (Iterator<String> iterator = alfabeto.iterator();
                iterator.hasNext();) {
            String next = iterator.next();
            if(pNterminais.contains(next)){naoTerminais.add(next);}
            else{terminais.add(next);}
        }
        terminais.add("$");
        
        //ordem da tabela = Naoterminais(shift) e dps Terminais(go to)
        int j = 0;
        for (int i = 0; i < indice.length; i++) {
            if (i < terminais.size()) {indice[i] = terminais.get(i);}
            else{indice[i] = naoTerminais.get(j++);}
        }
        
    }

    public String[] getIndice() {
        return indice;
    }

    public void setIndice(String[] indice) {
        this.indice = indice;
    }

    

    public String[][] getTabela() {
        return tabela;
    }

    public void setTabela(String[][] tabela) {
        this.tabela = tabela;
    }

    public LinkedList<String> getNaoTerminais() {
        return naoTerminais;
    }

    public void setNaoTerminais(LinkedList<String> naoTerminais) {
        this.naoTerminais = naoTerminais;
    }

    public LinkedList<String> getTerminais() {
        return terminais;
    }

    public void setTerminais(LinkedList<String> terminais) {
        this.terminais = terminais;
    }
    
}
