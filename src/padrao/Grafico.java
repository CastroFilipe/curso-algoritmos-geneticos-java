package padrao;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

public class Grafico extends ApplicationFrame{
	private static final long serialVersionUID = 1L;
	
	private List<Individuo> melhoresCromossomos = new ArrayList<>();
	
	public Grafico(String titulo, String descricao, String descricaoEixoX, String descricaoEixoY, List<Individuo> melhores) {
        super(titulo);
        this.melhoresCromossomos = melhores;
        
        JFreeChart graficoLinha = ChartFactory.createLineChart(descricao, 
        		descricaoEixoX,
        		descricaoEixoY, 
                carregarDados(), 
                PlotOrientation.VERTICAL, 
                true, true, false);
        ChartPanel janelaGrafico = new ChartPanel(graficoLinha);
        janelaGrafico.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(janelaGrafico);
    }
    
    private DefaultCategoryDataset carregarDados() {
        DefaultCategoryDataset dados = new DefaultCategoryDataset();
        
        //pega a nota de cada um dos cromossomos
        for (int i = 0; i < melhoresCromossomos.size(); i++) {
            dados.addValue(melhoresCromossomos.get(i).getNotaAvaliacao(), "Melhor solução", "" + i);
        }
        
        return dados;
    }
}
