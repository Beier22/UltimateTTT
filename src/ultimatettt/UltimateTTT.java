/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author mads_
 */
public class UltimateTTT extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ultimatettt/gui/view/StartWindow.fxml"));
        
        Scene scene = new Scene(root);
        Image icon = new Image("/ultimatettt/gui/image/TTTIcon.png");
        stage.getIcons().add(icon);
        stage.setMinHeight(400);
        stage.setMinWidth(700);
        stage.setHeight(400);
        stage.setWidth(700);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
