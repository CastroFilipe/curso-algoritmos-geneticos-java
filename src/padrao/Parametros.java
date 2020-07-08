package padrao;

/**
 * Parametros para configuração:
 * 
 * GERACAO_INICIAL: Indica a geração inicial do algoritmo.
 * 
 * TAXA_MUTACAO: A probabilidade de um Genoma sofrer mutação. Exemplo: O valor 0.05 indica a probabilidade de %5
 * 
 * TAMANHO_POPULACAO: Indica o número de Indivíduos(o número de Possíveis soluções)
 * 
 * LIMITE_ESPACO: Limite de espaco em m³ da mochila/caminhão
 * 
 * NUMERO_MAXIMO_GERACOES: O algoritmo irá para quando atingir o número máximo.
 * 
 * TAXA_ELITISMO: Usado no processo de elitimos. Indica a porcentagem dos melhores indivíduos de uma geração que serão levados para a próxima geração.
 * Ex: TAXA_ELITISMO = 0.01 e TAMANHO_POPULACAO = 200, Indica que os 2 melhores serão levados para a próxima geração.
 * Para não utilizar o elitismo, definir o valor 0.0
 * 
 * */
public class Parametros {
	
	public static final Integer GERACAO_INICIAL = 0;
	
	public static final Double TAXA_MUTACAO = 0.05;

	public static final Integer TAMANHO_POPULACAO = 200;
	
	public static final Double LIMITE_ESPACO = 4.0;
	
	public static final Integer NUMERO_MAXIMO_GERACOES = 100;
	
	public static final Double TAXA_ELITISMO = 0.01;
}
