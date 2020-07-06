package padrao;

/**
 * Um Produto que pode ser levado em um caminhão/mochila.
 * O produto contém o espaço que ocupa, dado em m², e um valor monetário.
 * */
public class Produto {

	private String nome;

	private Double espaco;

	private Double valor;

	public Produto(String nome, Double espaco, Double valor) {
		super();
		this.nome = nome;
		this.espaco = espaco;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getEspaco() {
		return espaco;
	}

	public void setEspaco(Double espaco) {
		this.espaco = espaco;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", espaco=" + espaco + ", valor=" + valor + "]";
	}
}
