package padrao;

import java.util.Arrays;
import java.util.List;

public class Executar {
	
	public static final List<Produto> PRODUTOS = Arrays.asList(
			new Produto("Geladeira Dako", 0.751, 999.90),
			new Produto("Iphone 6", 0.0089, 2911.12),
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
			new Produto("Notebook Asus", 0.527, 3999.00),
			new Produto("Mackbook", 0.491, 5999.90),
			new Produto("Chromebook", 0.518, 1899.90),
			new Produto("Cafeteira Elétrica", 0.0792, 459.90),
			new Produto("Cafeteira Simples", 0.0654, 229.90),
			new Produto("Liquidificador ARNO", 0.0980, 199.90),
			new Produto("Liquidificador Eletrolux 400W", 0.1121, 210.90),
			new Produto("Ar condicionado", 0.620, 999.90),
			new Produto("Central de AR Brastemp", 0.350, 2999.90),
			new Produto("Smartphone Sansung", 0.00998, 1799.90),
			new Produto("Smartphone Motorola", 0.01050, 1599.90),
			new Produto("Smartphone Alcatel", 0.01298, 1399.90),
			new Produto("Smartphone LG", 0.01179, 1899.90),
			new Produto("CD linkin Park", 0.0102, 49.90),
			new Produto("CD Grands Hits 2020", 0.0202, 89.90),
			new Produto("CD Arraial do Pavulagem", 0.0210, 69.90),
			new Produto("CD Banda Calypso", 0.0102, 39.90),
			new Produto("CD Calcinha Preta", 0.099, 29.90),
			new Produto("CD Metallica", 0.0102, 49.90),
			new Produto("CD Chico Gonzaga", 0.0102, 29.90),
			new Produto("Aspirador de Pó", 0.398, 1099.90),
			new Produto("Guarda Roupa Madeira", 0.730, 2999.90),
			new Produto("Guarda Roupa 100% MDF", 0.690, 3399.90),
			new Produto("Caixa de Som", 0.199, 879.90),
			new Produto("Cadeira de Praia", 0.316, 89.90),
			new Produto("JOGO: The last of us", 0.239, 199.90),
			new Produto("JOGO: GTA 5", 0.256, 299.90),
			new Produto("JOGO: FIFA 2020", 0.212, 149.90),
			new Produto("JOGO: PES 2019", 0.265, 139.90),
			new Produto("Playstation 4", 0.199, 1879.90));
	
	public static void main(String[] args) {
		
        AlgoritmoGenetico algoritmo = new AlgoritmoGenetico();
        
        Individuo melhorSolucao = algoritmo.resolver();
        
        gerarRelatorioFinal(melhorSolucao);
       
        Grafico g = new Grafico(
        		"Algoritmo genético", 
        		"Evolução das soluções (Baseado nas notas)",
        		"Geração",
        		"Valor em R$", 
        		algoritmo.getMelhoresCromossomos());
        
        g.pack();
        g.setVisible(true);
	}
	
	public static void gerarRelatorioFinal(Individuo melhorSolucao) {
		System.out.println("######### EXECUÇÃO FINALIZADA #########");
		System.out.println("\n### RELATÓRIO FINAL ###");
		System.out.println("Parâmetros utilizados: "
				+"\n  Número máximo de gerações: "+Parametros.NUMERO_MAXIMO_GERACOES
				+"\n  Tamanho População: "+Parametros.TAMANHO_POPULACAO
				+"\n  Taxa mutação genoma: "+Parametros.TAXA_MUTACAO + "("+Parametros.TAXA_MUTACAO*100+"%)"
				+"\n  Taxa de Elitismo: "+Parametros.TAXA_ELITISMO + "("+Parametros.TAXA_ELITISMO*100+"%)"
				+" -> "+(int)(Parametros.TAMANHO_POPULACAO*Parametros.TAXA_ELITISMO)+" melhores indivíduo(s) selecionados"
				+"\n  Limite de espaço mochila/caminhão: "+Parametros.LIMITE_ESPACO + " m³"
				+"\n  Primeira Geração: "+Parametros.GERACAO_INICIAL);
		
		System.out.println("\nCritérios de parada: "
				+"\n  Atingir o número máximo de gerações");
		
		System.out.println("\nMelhor solução encontrada: ");
	    System.out.println("  Geração: " + melhorSolucao.getGeracao()
	    	+"  Nota: " + Utils.formatar(melhorSolucao.getNotaAvaliacao())
	    	+"  Espaço utilizado: " + Utils.formatar(melhorSolucao.getEspacoUsado()) +" m³"
	    	+"\n  Cromossomo: " + melhorSolucao.getCromossomo());
	        
	        System.out.println("\nProdutos Selecionados na solução: ");
	        int numeroProdutosImpressos = 0;
	        for (int i = 0; i < Executar.PRODUTOS.size(); i++) {
	            if (melhorSolucao.getCromossomo().get(i).equals(1)) {
	            	System.out.print("  "+Executar.PRODUTOS.get(i).getNome()+"  | ");
	            	numeroProdutosImpressos++;
	            }
	            
	            if(numeroProdutosImpressos == 4) {
            		System.out.println();
            		numeroProdutosImpressos = 0;
            	}
	        }    
	}
	
}