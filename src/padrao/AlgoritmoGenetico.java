package padrao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoritmoGenetico {

	private List<Individuo> populacao = new ArrayList<>();

	private Individuo melhorSolucao; // Guarda o individuo com a melhor solução.

	private List<Individuo> melhoresCromossomos = new ArrayList<>(); // Guarda os melhores indivíduos de cada geração para geração do gráfico

	public void inicializarPopulacao() {
		List<Double> espacos = new ArrayList<>();
		List<Double> valores = new ArrayList<>();
		List<String> nomes = new ArrayList<>();

		for (Produto produto : Executar.PRODUTOS) {
			espacos.add(produto.getEspaco());
			valores.add(produto.getValor());
			nomes.add(produto.getNome());
		}

		for (int i = 0; i < Parametros.TAMANHO_POPULACAO; i++) {
			this.populacao.add(new Individuo(espacos, valores, Parametros.GERACAO_INICIAL));
		}

		this.melhorSolucao = this.populacao.get(0);
	}

	public void ordenarPopulacao() {
		Collections.sort(this.populacao);
	}

	public void verificarMelhorIndividuo() {
		// Verifica se na geração atual existe um Individuo melhor que na geração anterior.
		if (populacao.get(0).getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()) {
			this.melhorSolucao = populacao.get(0);
		}
	}

	/*
	 * A soma das avaliações será usada na seleção dos pais, pelo método da Roleta.
	 */
	public Double somarAvaliacoes() {
		Double soma = 0.0;
		for (Individuo individuo : this.populacao) {
			soma += individuo.getNotaAvaliacao();
		}
		return soma;
	}

	// Selecionar a posição do progenitor(pai) (Algoritmo do Giro da Roleta)
	public Integer selecionarProgenitor(Double somaAvaliacao) {
		Integer posicaoProgenitor = -1;
		Double valorSorteado = Math.random() * somaAvaliacao;
		Double soma = 0.0;
		int i = 0;

		while (i < this.populacao.size() && soma < valorSorteado) {
			soma += this.populacao.get(i).getNotaAvaliacao();
			posicaoProgenitor++;
			i++;
		}

		return posicaoProgenitor;
	}

	public void printMelhorDaGeracao(Individuo melhor) {
        
        System.out.println("G: " + melhor.getGeracao() +
                " Nota: " + melhor.getNotaAvaliacao() +
                " Espaço: " + melhor.getEspacoUsado() +
                " Cromossomo: " + melhor.getCromossomo());

		System.out.println("===========================");
	}

	public Individuo resolver() {

        //Gerar população inicial
        this.inicializarPopulacao();
        
        //Avaliar e ordenar, pelo melhor Individuo,a população atual
        this.populacao.forEach(individuo -> individuo.avaliacao());
        this.ordenarPopulacao();
        this.printMelhorDaGeracao(this.populacao.get(0));
        
        //Laço de repetição para a criação da nova geração em substituição a antiga.
        for (int geracao = 0; geracao < Parametros.NUMERO_MAXIMO_GERACOES; geracao++) {
            Double totalAvaliacoesGeracaoAtual = this.somarAvaliacoes();
            List<Individuo> novaPopulacao = new ArrayList<>();
        	
            for (int i = 0; i < Parametros.TAMANHO_POPULACAO/2; i++) {
            	Integer posicaoProgenitor1 = this.selecionarProgenitor(totalAvaliacoesGeracaoAtual);
            	Integer posicaoProgenitor2 = this.selecionarProgenitor(totalAvaliacoesGeracaoAtual);
            	
             	//Gera 2 descendentes a partir dos progenitores
             	List<Individuo> filhos = this.populacao.get(posicaoProgenitor1)
             			.crossover(this.populacao.get(posicaoProgenitor2));
             	
             	filhos.forEach(filho -> filho.mutacao(Parametros.TAXA_MULTACAO));
             	
             	novaPopulacao.addAll(filhos);
     		}
            
            //descarta a população anterior
            this.setPopulacao(novaPopulacao);
            
            this.getPopulacao().forEach(individuo -> individuo.avaliacao());
            this.ordenarPopulacao();
            this.melhoresCromossomos.add(this.getPopulacao().get(0));
            this.verificarMelhorIndividuo();
            this.printMelhorDaGeracao(this.populacao.get(0));
            
		}
        
    	return this.getMelhorSolucao();
    }

	public List<Individuo> getPopulacao() {
		return populacao;
	}

	public void setPopulacao(List<Individuo> populacao) {
		this.populacao = populacao;
	}

	public Individuo getMelhorSolucao() {
		return melhorSolucao;
	}

	public void setMelhorSolucao(Individuo melhorSolucao) {
		this.melhorSolucao = melhorSolucao;
	}

	public List<Individuo> getMelhoresCromossomos() {
		return melhoresCromossomos;
	}

	public void setMelhoresCromossomos(List<Individuo> melhoresCromossomos) {
		this.melhoresCromossomos = melhoresCromossomos;
	}

}
