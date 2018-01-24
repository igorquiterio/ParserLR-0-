/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author gustavo
 */
public class Gramatica {
    
    private LinkedList<Producao> producoes;
    private LinkedList<Estado> estados;
    private HashSet<String> naoTerminais;
    private int ultimoIndiceEstado;
    
    public Gramatica (String gramatica){
        String[] linhas = gramatica.split("\n",-1);
        
        this.naoTerminais = new HashSet();
        this.producoes = new LinkedList();
        this.ultimoIndiceEstado = -1;
        
        for (int i = 0; i < linhas.length; i++) {
            if(linhas[i].compareTo("") != 1){
                String linha = linhas[i].trim();
                String[] splitou = linha.split("->");
                String nTerminal = splitou[0].trim();
                String cadeia = "";
                if(splitou.length > 1){
                    cadeia = splitou[1].trim();
                }
                Producao prod = new Producao(nTerminal, cadeia, i);
                this.producoes.add(prod);
            }
        }
        for(Producao prod : this.producoes){
            System.out.print(prod.getNaoTerminal() + " -> ");
            preencheNaoTerminais(prod.getNaoTerminal());
            for(String termo : prod.getCadeia()){
                System.out.print(termo + " ");
            }
            System.out.println("");
        }
        this.estados = new LinkedList();

        PrintaNaoTerminais();
    }
    
    
    @SuppressWarnings("empty-statement")
    public void gerarEstado(Producao prod, int indice){
        String naoTerminalCorrente = prod.getNaoTerminal();
        Producao copia = prod.copiarProducao();

        if(this.getEstados().isEmpty()){
            copia.setPontoCorrente(0);
            Estado est = new Estado();
            est.setIndice(indice);
            est.getChave().add(copia);
            est.getProducao().add(copia);
            // inserindo produções daquele não terminal
            for(Producao teste : this.getProducoes()){
                if(teste.getNaoTerminal().compareTo(naoTerminalCorrente) == 0){
                    if(!teste.compararProducao(copia)){
                        Producao inserir = teste.copiarProducao();
                        inserir.setPontoCorrente(0);
                        est.getChave().add(inserir);
                        est.getProducao().add(inserir);
                    }
                }
            }
            while(this.adicionarProducaoEstado(est) == 1);
            est.mostrarEstado();
            this.getEstados().add(est);
            this.ultimoIndiceEstado = indice;
        }
        else{
            Estado est = this.estados.get(this.ultimoIndiceEstado);
            
        }
    }
    
    private int adicionarProducaoEstado(Estado est){
        int o = 0;
        LinkedList<Producao> listaAdicionar = new LinkedList();
        String termo;
        
        for(Producao prod : est.getProducao()){
            if(prod.getCadeia().size() > prod.getPontoCorrente()){
                termo = prod.getCadeia().get(prod.getPontoCorrente());
                if(this.getNaoTerminais().contains(termo)){
                    for(Producao nTerminalProd : this.getProducoes()){
                        if(nTerminalProd.getNaoTerminal().compareTo(termo) == 0){
                            if(est.verificarProducaoEstado(nTerminalProd) == 0){
                                Producao copia = nTerminalProd.copiarProducao();
                                copia.setPontoCorrente(0);
                                listaAdicionar.add(copia);
                                copia.mostrarProducao();
                                o = 1;
                            }
                        }
                    }
                }
            }
        }
        listaAdicionar.forEach((prod) -> {
            est.getProducao().add(prod);
        });
        return o;
    }

    
    public LinkedList<Estado> gerarPrimeiroEstado(){
        // pegando primeira produção
        Producao primeira = this.getProducoes().get(0);
        String naoTerminalCorrente = primeira.getNaoTerminal();
        // criando novo estado
        Estado est = new Estado();
        // procurando mais produções desse não terminal
        for(Producao prod : this.getProducoes()){
            if(prod.getNaoTerminal().compareTo(naoTerminalCorrente) == 1){
                 // fazendo cópia da produção para adicionar ao estado
                Producao copia = new Producao(primeira.getNaoTerminal(), primeira.getCadeia(), primeira.getIndice());
                copia.setPontoCorrente(0);
                // adicionando produção à lista de produções do estado
                est.getProducao().add(copia);
            }
        }
        // pegando proximo simbolo para mais produções ou não
//        String proximo = copia.getCadeia().get(0);
//        // se símbolo for um não terminal
//        if(this.naoTerminais.contains(proximo)){
//            // procura produções daquele não terminal
//            for(int i=0; i<producoes.size(); i++){
//                Producao prod = producoes.get(i);
//                // encontrei uma produção daquele não terminal
//                if(prod.getNaoTerminal().compareTo(proximo) == 1){
//                    // verifica se já foi adicionado ao estado
//                    if(est.verificarProducaoEstado(prod) == 0){
//                        copia = new Producao(prod.getNaoTerminal(), prod.getCadeia(), prod.getIndice());
//                    }
//                }
//            }
//        }
        // preciso repensar nos métodos a fazer e na ordem de execução deles..
        // verificar se o termo é não terminal para adicionar produções
        // fazer também tratamento de produção já tiver sido adicionada no estado com o ponto corrente certo
        
        return null;
    }
    
    public void mostrarEstados(){
        for(Estado est : this.getEstados()){
            est.mostrarEstado();
        }
    }
    
    private void preencheNaoTerminais(String nomTerm){
        if(!this.naoTerminais.contains(nomTerm)){
            this.getNaoTerminais().add(nomTerm);
        }
    }
    
    //funcao usada somente para teste, remover na versao final
    public void PrintaNaoTerminais(){
        System.out.print("não terminais: ");
        Iterator<String> iterator = this.getNaoTerminais().iterator();
	while (iterator.hasNext()) {
		System.out.print(iterator.next() + " ");
        }
        System.out.println("");
    }

    public HashSet<String> getNaoTerminais() {
        return naoTerminais;
    }

    public void setNaoTerminais(HashSet<String> naoTerminais) {
        this.naoTerminais = naoTerminais;
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

    /**
     * @return the estados
     */
    public LinkedList<Estado> getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(LinkedList<Estado> estados) {
        this.estados = estados;
    }

    /**
     * @return the ultimoIndiceEstado
     */
    public int getUltimoIndiceEstado() {
        return ultimoIndiceEstado;
    }

    /**
     * @param ultimoIndiceEstado the ultimoIndiceEstado to set
     */
    public void setUltimoIndiceEstado(int ultimoIndiceEstado) {
        this.ultimoIndiceEstado = ultimoIndiceEstado;
    }
    
    
    
}
