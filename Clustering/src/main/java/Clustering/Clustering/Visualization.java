package Clustering.Clustering;
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.AbstractLineRenderer2D;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;


@SuppressWarnings("serial")
public class Visualization extends JFrame {
    public Visualization(double[][] sortedTransMatrix) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        @SuppressWarnings("unchecked")
		DataTable data = new DataTable(Double.class, Double.class);
        
        getData(data, sortedTransMatrix); 
        
//        for (double x = -5.0; x <= 5.0; x+=0.25) {
//            double y = 5.0*Math.sin(x);
//            data.add(x, y);
//        }
//        
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        //plot.getAxisRenderer(data).setColor(color);
        //plot.getAxisRenderer(data).setColor(color);
    }
    
    public void getData(DataTable data, double[][] sortedTransMatrix)
    {
    	int len = sortedTransMatrix.length;
    	double[] xcoor = new double[len];
    	double[] ycoor = new double[len];
    	
    	for(int i=0;i<len;i++)
    	{
    		xcoor[i]=sortedTransMatrix[i][0];
    		ycoor[i]=sortedTransMatrix[i][1];
    	}
    	
    	for(int i=0;i<xcoor.length;i++)
    	{
    		data.add((double)xcoor[i], (double)ycoor[i]);
    	}
    }
//
//    public static void main(String[] args) {
//        Visualization frame = new Visualization();
//        frame.setVisible(true);
//    }
}

