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
	
    public void verificarMelhorIndividuo() {
    	//Verifica se na geração atual existe um Individuo melhor que na geração anterior.
        if (populacao.get(0).getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()) {
            this.melhorSolucao = populacao.get(0);
        }
    }
    
    /*
     * A soma das avaliações será usada na seleção dos pais, pelo método da Roleta Viciada.
     * */
    public Double somarAvaliacoes() {
        Double soma = 0.0;
        for (Individuo individuo: this.populacao) {
            soma += individuo.getNotaAvaliacao();
        }
        return soma;
    }

    //Selecionar a posição do progenitor(pai) sorteado no giro da Roleta
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
