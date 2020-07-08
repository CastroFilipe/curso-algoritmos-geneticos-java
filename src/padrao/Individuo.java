package padrao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * O Indivíduo é membro de uma população e representa uma possível solução para o problema. 
 * Um Indivíduo pode ser o cromossomo em si ou pode possuí-lo como atributo.
 * O cromossomo é de fato uma possível solução para o problema. 
 * Neste exemplo, o Indivíduo contém um atributo cromossomo que é um vetor binario(0 ou 1, true ou false).
 * Cada posição do vetor cromossomo é chamado de GENOMA e que indica quais produtos devem ser selecionados para 
 * o transporte no caminhão/mochila.
 * 
 * */
public class Individuo implements Comparable<Individuo> {

	private List<Double> espacos = new ArrayList<>(); //o Individuo conhece o espaco(em m²) que cada Produto pode ocupar. 

	private List<Double> valores = new ArrayList<>(); //O individuo conhece os valores monetários de cada Produto.
	
	private Double espacoUsado; //representa quanto de espaço físico os produtos estão ocupando na solução do cromossomo

	private Double notaAvaliacao; //guardará a nota da avaliação feita pela função de avalaiação(fitness)

	private Integer geracao;// geração a qual o individuo pertence.

	private List<Boolean> cromossomo = new ArrayList<>();// combinação de 1's e 0's que indica quais produtos serão levados no caminhão/mochila

	public Individuo(List<Double> espacos, List<Double> valores, Integer geracao) {
		super();
		this.espacos = espacos;
		this.valores = valores;
		this.espacoUsado = 0.0;
		this.notaAvaliacao = 0.0;
		this.geracao = geracao;
		
		//Apenas se o Individuo fizer parte da primeira geração
		if(this.geracao == Parametros.GERACAO_INICIAL) {
			gerarCromossomoAleatorioInicial();
		}
	}
	
	/**
	 * Regra para gerar o cromossomo inicial de forma aleatória. 
	 * A função só será chamada na criação de Individuos da primeira geração.
	 * A probilidade é de 50% para gerar o valor zero/false e 50% para gerar o valor um/true.
	 * 
	 * Cada posição(Genoma) do cromossomo indica se o produto será ou não carregado no caminhão/mochila.
	 * 
	 */
    private void gerarCromossomoAleatorioInicial() {

		for (int i = 0; i < this.espacos.size(); i++) {
			if (Math.random() < 0.5) {
				this.cromossomo.add(false);// indica que o produto não fará parte do carregamento
			} else {
				this.cromossomo.add(true);// indica que o produto fará parte
			}
		}
    }

	/*
	 * A função de avalaição(fitness) fará a avaliação da solução contida no cromossomo, 
	 * determinando se é uma boa solução ou não. A nota atribuída irá levar em consideração os valores de 
	 * cada produto e a capacidade máxima do caminhão/mochila. 
	 * Se a quantidade total de espaços ocupados pelos produtos exceder a capacidade do caminhão, a avaliação 
	 * retornará uma nota baixa(nota = 1) como penalidade, indicando que o cromossomo possuí uma solução não boa.
	 * */
    public void avaliacao() {
        Double nota = 0.0;
        Double somaEspacos = 0.0;
        for (int i = 0; i < this.cromossomo.size(); i++) {
            if (this.cromossomo.get(i).equals(true)) {
                nota += (Double) this.valores.get(i);
                somaEspacos += (Double) this.espacos.get(i);
            }
        }

        if (somaEspacos > Parametros.LIMITE_ESPACO) {
            nota = 1.0; 
        }
        
        this.notaAvaliacao = nota;
        this.espacoUsado = somaEspacos;
    }
    
    /*
     * Função(operador genético) de Reprodução/Recombinação.
     * 
     * A técnica de Recombinação utilizada é a técnica "Recombinação em um ponto" onde um ponto de corte é selecionado 
     * para ambos os progenitores e os cromossomos destes são misturados para a geração dos filhos.
     * 
     * @retun retorna os dois filhos gerados pelo cruzamentos dos progenitores
     * */
    public List<Individuo> crossover(Individuo outroIndividuo) {
    	//Seleciona o ponto de corte de forma aleatória
        int corte = (int) Math.round(Math.random() * this.cromossomo.size());
        
        //Faz o cruzamento entre cromossomos
        List<Boolean> cromossomoFilho1 = new ArrayList<>();
        cromossomoFilho1.addAll(outroIndividuo.getCromossomo().subList(0, corte));
        cromossomoFilho1.addAll(this.cromossomo.subList(corte, this.cromossomo.size()));
        
        List<Boolean> cromossomoFilho2 = new ArrayList<>();
        cromossomoFilho2.addAll(this.cromossomo.subList(0, corte));
        cromossomoFilho2.addAll(outroIndividuo.getCromossomo().subList(corte, this.cromossomo.size()));
        
        List<Individuo> filhos = Arrays.asList(
        			new Individuo(this.espacos, this.valores, this.geracao+1),
        			new Individuo(this.espacos, this.valores, this.geracao+1));
        
        filhos.get(0).setCromossomo(cromossomoFilho1);
        filhos.get(1).setCromossomo(cromossomoFilho2);
        
        return filhos;
    }
    
    /*
     * Função(operador genético) de MUTAÇÃO.
     * Na mutação existe a possibilidade de mudança do valor de Genomas(elemento i do vetor).
     * A taxaMutacao representa a probabilidade(em porcentagem) que um Genoma(elemento i do vetor) 
     * poderá sofrer mutação.
     * */
    public Individuo mutacao(Double taxaMutacao) {  
        Boolean genomaAptoParaMutacao = false;
        
        for (int i = 0; i < this.cromossomo.size(); i++) {
        	genomaAptoParaMutacao = Math.random() < taxaMutacao ? true : false;
        	
            if (genomaAptoParaMutacao) {
                if (this.cromossomo.get(i).equals(true)) {
                    this.cromossomo.set(i, false);
                } else {
                    this.cromossomo.set(i, true);
                }
            }
        }
        
        return this;
    }
    
	public List<Double> getEspacos() {
		return espacos;
	}

	public void setEspacos(List<Double> espacos) {
		this.espacos = espacos;
	}

	public List<Double> getValores() {
		return valores;
	}

	public void setValores(List<Double> valores) {
		this.valores = valores;
	}

	public Double getEspacoUsado() {
		return espacoUsado;
	}

	public void setEspacoUsado(Double espacoUsado) {
		this.espacoUsado = espacoUsado;
	}

	public Double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(Double notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}

	public Integer getGeracao() {
		return geracao;
	}

	public void setGeracao(Integer geracao) {
		this.geracao = geracao;
	}

	public List<Boolean> getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(List<Boolean> cromossomo) {
		this.cromossomo = cromossomo;
	}

	@Override
	public int compareTo(Individuo o) {
		int result = this.notaAvaliacao < o.getNotaAvaliacao() ? 1 : this.notaAvaliacao > o.getNotaAvaliacao() ? -1 : 0;
		
		return result;
	}

}
