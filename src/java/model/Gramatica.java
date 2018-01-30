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
    private HashSet<String> alfabeto;
    private TabelaLR lr0;
    private static int indEstados = 0;

    /*
    Construção da gramática
    */
    public Gramatica (String gramatica){
        String[] linhas = gramatica.split("\n",-1);
        
        this.naoTerminais = new HashSet();
        this.producoes = new LinkedList();
        this.alfabeto = new HashSet<>();
        
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
                this.preencheNaoTerminais(nTerminal);
                this.preencheAlfabeto(nTerminal);
                for (String simbol: prod.getCadeia()){
                    this.preencheAlfabeto(simbol);
                }
            }
        }
        this.estados = new LinkedList();
    }
    
    public void inicializarGramatica(){
        this.gerarPrimeiroEstado(this.producoes.getFirst());
                
        LinkedList<Estado> novosEstados = new LinkedList();
        int gerador = 1;
        int i;

        while(gerador == 1){
            for(i=0;i<this.estados.size(); i++){
                novosEstados = this.gerarEstado(this.estados.get(i));
                if(!novosEstados.isEmpty())
                    break;
            }
            if(!novosEstados.isEmpty()){
                for(Estado est : novosEstados){
                    this.estados.add(est);
                }
            }
            else
                gerador = 0;
        }
        this.gerarTabelaEstados();
    }
    
    /*
    Geração do diagrama dos estados e da tabela lr(0)
    */
    private void gerarTabelaEstados(){
        this.lr0 = new TabelaLR(this.naoTerminais, this.alfabeto, this.estados);
    }
    
    public void reconhecerCadeia(String pCadeia){
        LinkedList<String> pilha = new LinkedList();
        LinkedList<String> cadeia;
        int estado = 1;
        
        cadeia = this.prepararCadeiaReconhecimento(pCadeia);
        pilha.add("i 1");
        
        for(String termo : cadeia){
            int coluna = this.lr0.achaTermoNoIndice("termo");
            String acao = this.lr0.getTabela()[estado][coluna];
            String[] split = acao.split(" ");
            if(split.length == 2){
                if(split[0].compareTo("s") == 0){
                    pilha.add(termo.concat(" ").concat(split[1]));
                }
                else if(split[0].compareTo("g") == 0){
                    String ultimo = pilha.getLast();
                    String[] ultSplit = ultimo.split(" ");
                    pilha.removeLast();
                    pilha.add(ultSplit[0].concat(" ").concat(split[1]));
                }
                else if(split[0].compareTo("r") == 0){
                    System.out.println("Fazer o reduce");
                }
                else
                    System.out.println("Não é nem reduce, nem shift, nem go to");
            }
            else
                System.out.println("Deu merda");
            
            estado = Integer.parseInt(pilha.getLast().split(" ")[1]);
        }
    }
    
    private LinkedList<String> prepararCadeiaReconhecimento(String cadeia){
        LinkedList<String> lista = new LinkedList();
        
        String[] split = cadeia.split(" ");
        for(int i=0; i<split.length; i++){
            lista.add(split[i]);
        }
        
        return lista;
    }
    
    @SuppressWarnings("empty-statement")
    private void gerarPrimeiroEstado(Producao prod){
        String naoTerminalCorrente = prod.getNaoTerminal();
        Producao copia = prod.copiarProducao();
        
        copia.setPontoCorrente(0);
        copia.getCadeia().add("$");
        Estado est = new Estado();
        est.setIndice(indEstados);
        est.getChave().add(copia);
        est.getProducao().add(copia);
        // inserindo produções daquele não terminal
        for(Producao teste : this.getProducoes()){
            if(teste.getNaoTerminal().compareTo(naoTerminalCorrente) == 0){
                if(!teste.compararProducao(copia)){
                    Producao inserir = teste.copiarProducao();
                    inserir.setPontoCorrente(0);
                    inserir.getCadeia().add("$");
                    est.getChave().add(inserir);
                    est.getProducao().add(inserir);
                }
            }
        }
        while(this.adicionarProducaoEstado(est) == 1);
        //est.mostrarEstado();
        this.getEstados().add(est);
    }
    
    /*
    Percorre o estado est para geração de novos estados ou apenas uma conexão com algum já existente
    */
    @SuppressWarnings("empty-statement")
    private LinkedList<Estado> gerarEstado(Estado est){
        LinkedList<Estado> novosEstados = new LinkedList();
        LinkedList<Mudanca> novasMudancas = new LinkedList();

        for(Producao prod : est.getProducao()){
            Producao copia = prod.copiarProducao();
            copia.setPontoCorrente(prod.getPontoCorrente()+1);
            // Caso a produção não tenha mais nada para ler
            if(prod.getCadeia().size() == prod.getPontoCorrente()){
                est.adicionarReduce(prod.getIndice());
            }
            else{
                String termo = prod.getCadeia().get(prod.getPontoCorrente());
                if(termo.compareTo("$") == 0){
                    est.adicionarReduce(-1);
                }
                else{
                    Mudanca mud = null;
                    int existeMudanca = 0;
                    // procurando no estado alguma mudanca com o mesmo termo
                    for(Mudanca teste : est.getMudancas()){
                        if(teste.getTermo().compareTo(termo) == 0){
                            mud = teste;
                            existeMudanca = 1;
                            // mudanca futura já existente
                            if(teste.getNaoTerminal().compareTo(prod.getNaoTerminal()) == 0){
                                mud = null;
                                existeMudanca = 2;
                                break;
                            }
                        }
                    }
                    // criar nova mudanca
                    if(existeMudanca == 0){
                        // verificar se algum estado tem essa produção como chave
                        int encontrouEstadoChave = 0;
                        for(int i=0;i<this.estados.size(); i++){
                            Estado teste = this.estados.get(i);
                            for(Producao chave : teste.getChave()){
                                if(chave.compararProducao(copia)){
                                    encontrouEstadoChave = 1;
                                    mud = new Mudanca(teste.getIndice(), termo, copia.getNaoTerminal());
                                    novasMudancas.add(mud);
                                    break;
                                }
                            }
                        }
                        if(encontrouEstadoChave == 0){
                            Estado estadoNovo = criarEstado(copia);
                            novosEstados.add(estadoNovo);
                            mud = new Mudanca(estadoNovo.getIndice(), termo, copia.getNaoTerminal());
                            est.getMudancas().add(mud);
                            while(this.adicionarProducaoEstado(estadoNovo) == 1);
                        }
                    }
                    // fazer com que essa mudanca vá para o mesmo estado
                    else if(existeMudanca == 1){
                        int estadoSuspeito = mud.getEstadoDestino();
                        Estado teste = this.estados.get(estadoSuspeito);
                        mud = new Mudanca(estadoSuspeito, termo, copia.getNaoTerminal());
                        est.getMudancas().add(mud);
                        teste.getChave().add(copia);
                        teste.getProducao().add(copia);
                        while(this.adicionarProducaoEstado(teste) == 1);
                    }
                }
            }
        }
        if(!novasMudancas.isEmpty()){
            for(Mudanca mud : novasMudancas){
                int criar = 1;
                for(Mudanca teste : est.getMudancas()){
                    if(teste.compararMudancas(mud)){
                        criar = 0;
                        break;
                    }
                }
                if(criar == 1){
                    est.getMudancas().add(mud);
                }
            }
        }
        return novosEstados;
    }
    
    /*
    Cria um estado com a produção prod sendo a chave
    */
    private Estado criarEstado(Producao prod){
        Estado est = new Estado();
        est.getChave().add(prod);
        est.getProducao().add(prod);
        int ultimoIndice = ++indEstados;
        est.setIndice(ultimoIndice);
        return est;
    }
    
    /*
    Adiciona ao estado produções
    Exemplos: A -> . B (sendo B um não terminal, deve-se adicionar as produções dele)
    */
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
                            Producao copia = nTerminalProd.copiarProducao();
                            copia.setPontoCorrente(0);
                            if(est.verificarProducaoEstado(copia) == 0){
                                listaAdicionar.add(copia);
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
    
    public void mostrarEstados(){
        for(Estado est : this.estados){
            est.mostrarEstado();
        }
    }
    
    private void preencheNaoTerminais(String nomTerm){
        if(!this.naoTerminais.contains(nomTerm)){
            this.getNaoTerminais().add(nomTerm);
        }
    }

    private void preencheAlfabeto(String simbol){
        if(!this.alfabeto.contains(simbol)){
            this.alfabeto.add(simbol);
        }
    }
    

    public HashSet<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(HashSet<String> alfabeto) {
        this.alfabeto = alfabeto;
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
    
}
