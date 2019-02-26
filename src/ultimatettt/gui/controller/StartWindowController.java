/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mads_
 */
public class StartWindowController implements Initializable {

    @FXML
    private JFXButton btnPVP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void playPVP(ActionEvent event) throws IOException {
        openGameWindow(1);
    }
    
    private void openGameWindow(int i) throws IOException{
        
        Stage st = (Stage) btnPVP.getScene().getWindow();
        st.close();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ultimatettt/gui/view/Main.fxml"));
        MainController controller = loader.getController();
        controller.setDistinguisher(i);
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
    
}
