package javafx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import solver.Path;

public class PathLineChart extends Application {

    private static Path path;

    @Override
    public void start(Stage stage) {
        stage.setTitle("");
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Cumulative Score Index");
        xAxis.setLabel("Letter");
        xAxis.setStartMargin(0);
        xAxis.setGapStartAndEnd(false);
        //creating the chart
        final LineChart<String,Number> lineChart =
                new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Optimal Path, Graphed");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Optimal Path");
        double cumScore = 0;
        try {
            String[][] data = path.scoresArr();
            series.getData().add(new XYChart.Data("0",0.0));
            for(int i=0; i<data[0].length; i++){
                cumScore+=Double.valueOf(data[1][i]);
                series.getData().add(new XYChart.Data(data[0][i], cumScore));
            }

            XYChart.Series meanLine = new XYChart.Series();
            meanLine.setName("Average Path");
            meanLine.getData().add(new XYChart.Data("0",0.0));
            meanLine.getData().add(new XYChart.Data(data[0][data[0].length-1], cumScore));

            lineChart.getData().add(series);
            lineChart.getData().add(meanLine);
        }catch(NullPointerException e){
            System.out.println("Null path");
        }

        Scene scene  = new Scene(lineChart,800,600);

        stage.setScene(scene);
        stage.show();
    }


    public static void graph(Path p) {
        path = p;
        launch();
    }
}
