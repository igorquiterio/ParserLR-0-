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
    private TabelaTrace tt;
    private int indEstados = 0;
    private int aceita = 0;

    public TabelaTrace getTt() {
        return tt;
    }

    public void setTt(TabelaTrace tt) {
        this.tt = tt;
    }
    
    public void iniciaTrace(String cadeia){
        this.tt =  new TabelaTrace(lr0, cadeia, producoes);
//        tt.getTabelaTrace();
    }
    public int getIndEstados() {
        return indEstados;
    }

    public void setIndEstados(int indEstados) {
        this.indEstados = indEstados;
    }

    public int getAceita() {
        return aceita;
    }

    public void setAceita(int aceita) {
        this.aceita = aceita;
    }

    /*
    Construção da gramática
    */
    public Gramatica (String gramatica){
        indEstados = 0;
        String[] linhas = gramatica.split("\n",-1);
        
        this.naoTerminais = new HashSet();
        this.producoes = new LinkedList();
        this.alfabeto = new HashSet<>();
        String primeiroNaoTerminal = "";
        
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
        this.setLr0(new TabelaLR(this.naoTerminais, this.alfabeto, this.estados));
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
                int existe = 0;
                for(int reduce : est.getReduce()){
                    if(reduce == prod.getIndice()){
                        existe = 1;
                        break;
                    }
                }
                if(existe == 0)
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
                            if(teste.getIndiceProducao() == prod.getIndice()){
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
                                    mud = new Mudanca(teste.getIndice(), termo, copia.getNaoTerminal(), copia.getIndice());
                                    novasMudancas.add(mud);
                                    break;
                                }
                            }
                        }
                        if(encontrouEstadoChave == 0){
                            Estado estadoNovo = criarEstado(copia);
                            novosEstados.add(estadoNovo);
                            mud = new Mudanca(estadoNovo.getIndice(), termo, copia.getNaoTerminal(), copia.getIndice());
                            est.getMudancas().add(mud);
                            while(this.adicionarProducaoEstado(estadoNovo) == 1);
                            
                        }
                    }
                    // fazer com que essa mudanca vá para o mesmo estado
                    else if(existeMudanca == 1){
                        int estadoSuspeito = mud.getEstadoDestino();
                        Estado teste = null;
                        if(this.estados.size() > estadoSuspeito)
                            teste = this.estados.get(estadoSuspeito);
                        if(teste == null){
                            for(int k=0; k<novosEstados.size(); k++){
                                if(novosEstados.get(k).getIndice() == estadoSuspeito){
                                    teste = novosEstados.get(k);
                                }
                            }
                        }
                        mud = new Mudanca(estadoSuspeito, termo, copia.getNaoTerminal(), copia.getIndice());
                        est.getMudancas().add(mud);
                        int chv = 0;
                        for(Producao chave : teste.getChave()){
                            if(chave.getIndice() == copia.getIndice()){
                                chv = 1;
                                break;
                            }
                        }
                        if(chv == 0){
                            teste.getChave().add(copia);
                            teste.getProducao().add(copia);   
                        }
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
                                int existe = 0;
                                for(Producao p : listaAdicionar){
                                    if(p.compararProducao(copia)){
                                        existe = 1;
                                        break;
                                    }
                                }
                                if(existe == 0){
                                    listaAdicionar.add(copia);
                                    o = 1;
                                }
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

    /**
     * @return the lr0
     */
    public TabelaLR getLr0() {
        return lr0;
    }

    /**
     * @param lr0 the lr0 to set
     */
    public void setLr0(TabelaLR lr0) {
        this.lr0 = lr0;
    }
    
}
