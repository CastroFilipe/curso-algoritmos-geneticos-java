package padrao;

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
        algoritmo.inicializaPopulacao();
        
//        Individuo individuo1 = new Individuo(espacos, valores, AlgoritmoGenetico.GERACAO_INICIAL);
//        individuo1.avaliacao();
//        
//        Individuo individuo2 = new Individuo(espacos, valores, AlgoritmoGenetico.GERACAO_INICIAL);
//        individuo2.avaliacao();
//        
//        printIndividuo(individuo1);
//        printIndividuo(individuo2);
//        
//        individuo1.crossover(individuo2);
//        individuo1.mutacao(AlgoritmoGenetico.TAXA_MULTACAO);
//        individuo2.mutacao(AlgoritmoGenetico.TAXA_MULTACAO);
        
	}

	public static void printIndividuo(Individuo individuo) {
		System.out.println("Nota: "+ individuo.getNotaAvaliacao());
        System.out.println("Espaço usado: "+ individuo.getEspacoUsado());
        System.out.println("Solução:"+ individuo.getCromossomo());
        
        for (int i = 0; i < PRODUTOS.size(); i++) {
            if (individuo.getCromossomo().get(i)) {
                System.out.println("Nome: " + PRODUTOS.get(i).getNome());
            }
        }
        
        System.out.println("===========================");
	}
}

