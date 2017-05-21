package ui;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DataSetFactory {

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    public static XYDataset createDataset(int numberOfReadins) {

        XYSeries xySeries = new XYSeries("XY");
        for (int i = 1; i <= numberOfReadins; i++){
        	xySeries.add(i, i);
        }
        XYSeriesCollection seriesDataset = new XYSeriesCollection();
        seriesDataset.addSeries(xySeries);

        return seriesDataset;
    }
}
