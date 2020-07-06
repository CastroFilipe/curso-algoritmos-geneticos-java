package padrao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoritmoGenetico {

	private List<Individuo> populacao = new ArrayList<>();

	private Integer geracao;

	private Individuo melhorSolucao; // Guarda o individuo com a melhor solução.

	private List<Individuo> melhoresCromossomos = new ArrayList<>(); // Guarda os melhores indivíduos

	public void inicializaPopulacao() {
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

	public List<Individuo> getPopulacao() {
		return populacao;
	}

	public void setPopulacao(List<Individuo> populacao) {
		this.populacao = populacao;
	}

	public int getGeracao() {
		return geracao;
	}

	public void setGeracao(int geracao) {
		this.geracao = geracao;
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
