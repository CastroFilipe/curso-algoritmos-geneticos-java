package padrao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Executar {
	
	public static final List<Produto> PRODUTOS = Arrays.asList(
			new Produto("Geladeira Dako", 0.751, 999.90),
			new Produto("Iphone 6", 0.000089, 2911.12),
			new Produto("TV 55' ", 0.400, 4346.99),
			new Produto("TV 50' ", 0.290, 3999.90),
			new Produto("TV 42' ", 0.200, 2999.00),
			new Produto("Notebook Dell", 0.00350, 2499.90),
			new Produto("Ventilador Panasonic", 0.496, 199.90),
			new Produto("Microondas Electrolux", 0.0424, 308.66),
			new Produto("Microondas LG", 0.0544, 429.90),
			new Produto("Microondas Panasonic", 0.0319, 299.29),
			new Produto("Geladeira Brastemp", 0.635, 849.00),
			new Produto("Geladeira Consul", 0.870, 1199.89),
			new Produto("Notebook Lenovo", 0.498, 1999.90),
			new Produto("Notebook Asus", 0.527, 3999.00));
	
	public static void main(String[] args) {
		
        AlgoritmoGenetico algoritmo = new AlgoritmoGenetico();
        
        //Gerar população inicial
        algoritmo.inicializaPopulacao();
        
        //Avaliar população
        algoritmo.getPopulacao().forEach(individuo -> individuo.avaliacao());
        algoritmo.ordenarPopulacao();
        algoritmo.verificarMelhorIndividuo();
        
        System.out.println("MELHOR SOLUÇÃO: ");
        printIndividuo(algoritmo.getMelhorSolucao());
        
        System.out.println("POPULAÇÃO COMPLETA: ");
        algoritmo.getPopulacao().forEach(individuo -> printIndividuo(individuo));
        
        Double totalAvaliacoesGeracaoAtual = algoritmo.somarAvaliacoes();
        
        List<Individuo> novaPopulacao = new ArrayList<>();
        for (int i = 0; i < Parametros.TAMANHO_POPULACAO/2; i++) {
        	Integer posicaoProgenitor1 = algoritmo.selecionarProgenitor(totalAvaliacoesGeracaoAtual);
        	Integer posicaoProgenitor2 = algoritmo.selecionarProgenitor(totalAvaliacoesGeracaoAtual);
        	
        	//Gera 2 descendentes a partir dos progenitores
        	List<Individuo> filhos = algoritmo.getPopulacao().get(posicaoProgenitor1)
        			.crossover(algoritmo.getPopulacao().get(posicaoProgenitor2));
        	
        	filhos.forEach(filho -> filho.mutacao(Parametros.TAXA_MULTACAO));
        	
        	novaPopulacao.addAll(filhos);
		}
         
        //descarta a população anterior
        algoritmo.setPopulacao(novaPopulacao);
        
        algoritmo.getPopulacao().forEach(individuo -> individuo.avaliacao());
        algoritmo.ordenarPopulacao();
        algoritmo.verificarMelhorIndividuo();
        totalAvaliacoesGeracaoAtual = algoritmo.somarAvaliacoes();
        
        System.out.println("MELHOR SOLUÇÃO: ");
        printIndividuo(algoritmo.getMelhorSolucao());
        
        System.out.println("POPULAÇÃO COMPLETA: ");
        algoritmo.getPopulacao().forEach(individuo -> printIndividuo(individuo));
        
	}

	public static void printIndividuo(Individuo individuo) {
		System.out.println("Nota da avaliação: "+ individuo.getNotaAvaliacao());
        System.out.println("Espaço total usado: "+ individuo.getEspacoUsado());
        System.out.println("Solução/Cromossomo: "+ individuo.getCromossomo());
        System.out.println("Geração: "+ individuo.getGeracao());
        System.out.println("Produtos Selecionados na solução: ");
        for (int i = 0; i < PRODUTOS.size(); i++) {
            if (individuo.getCromossomo().get(i)) {
                System.out.println(" "+PRODUTOS.get(i).getNome());
            }
        }
        
        System.out.println("===========================");
	}
}

