package ui;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;


public class Graph extends ChartPanel{
	
	private static final long serialVersionUID = -2608773479143419127L;
	
	/**
	 * Constructor
	 * @param yAxisLabel the label of the X axis
	 */
	public Graph(XYDataset dataset, String yAxisLabel, int minValue, int maxValue){
		super(createChart(dataset, yAxisLabel, minValue, maxValue));
        this.setPreferredSize(new java.awt.Dimension(700, 200)); 
	}
	/**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset, String yAxisTitle, int minValue, int maxValue) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            null,	// title
            null,	// x-axis label
            yAxisTitle,	// y-axis label
            dataset,	// data
            false,	// create legend?
            false,	// generate tooltips?
            false	// generate URLs?
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.black);
        plot.setDomainGridlinePaint(Color.green);
        plot.setRangeGridlinePaint(Color.green);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        //
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setTickUnit(new NumberTickUnit(1.0));
        numberAxis.setRange(minValue, maxValue);
        plot.setRangeAxis(numberAxis);
        
//        plot.setDomainAxis(new ValueAxis());
        
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(false);
            renderer.setBaseShapesFilled(false);
            renderer.setPaint(Color.GREEN);
            renderer.setDrawSeriesLineAsPath(true);
        }
        return chart;

    }
}
