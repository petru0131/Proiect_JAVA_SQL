/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproiect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.EventHandler;
//import javafx.geometry.Side;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.scene.chart.*;
//import javafx.scene.Group;
//import javafx.scene.control.Label;
//import javafx.scene.control.Tooltip;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.paint.Color;
/**
 *
 * @author Petru
 */
public class ChartTest //extends Application 
{
//    @Override public void start(Stage stage) {
//        Scene scene = new Scene(new Group());
//        stage.setTitle("Produse");
//        stage.setWidth(500);
//        stage.setHeight(500);
// 
//        try(Connection conn = ConnectionJDBC.getConnection();
//                PreparedStatement stmt = conn.prepareStatement("select valutaschimb,sum(suma) from beneficiar group by valutaschimb");
//                ResultSet rs = stmt.executeQuery();){
//                 ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
//                    while (rs.next()){
//                       
//                     pieChartData.add(new PieChart.Data(rs.getString("valutaschimb"),rs.getInt("sum(suma)")));
//                    }
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setTitle("Procent Valuta Schimbata");
//        chart.setLabelLineLength(10);
//        chart.setLegendSide(Side.LEFT);
//        chart.getData().forEach(data ->{
//                    String percentage = String.format("%.2f%%", (data.getPieValue()/100));
//                    Tooltip toolTip = new Tooltip(percentage);
//                    Tooltip.install(data.getNode(), toolTip);
//                });
//        
//       
//
//        ((Group) scene.getRoot()).getChildren().add(chart);
//        stage.setScene(scene);
//        stage.show();
//         final Label caption = new Label("");
//caption.setTextFill(Color.DARKORANGE);
//caption.setStyle("-fx-font: 24 arial;");
//
//for (final PieChart.Data data : chart.getData()) {
//    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//        new EventHandler<MouseEvent>() {
//            @Override public void handle(MouseEvent e) {
//                caption.setTranslateX(e.getSceneX());
//                caption.setTranslateY(e.getSceneY());
//                caption.setText(String.valueOf(data.getPieValue()) + "%");
//             }
//        });
//}
//    }   catch (ClassNotFoundException ex) {
//            Logger.getLogger(PieChartSample.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(PieChartSample.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
// 
//    public static void main(String[] args) {
//        launch(args);
//        
//    }
//}
}
