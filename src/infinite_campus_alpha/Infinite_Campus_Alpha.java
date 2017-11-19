/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinite_campus_alpha;

import infinite_campus_alpha.login_Frame.LoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jeraldy
 */
public class Infinite_Campus_Alpha extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        /**
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("LoginFrame.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.resizableProperty().setValue(Boolean.FALSE);
        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        LoginController controller = (LoginController) fxmlLoader.getController();
    controller.registerStage(stage);
    stage.show();

*/
        

    
    stage.initStyle(StageStyle.UNDECORATED);
stage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader loader = new FXMLLoader(
          getClass().getResource(
            "login_Frame/LoginFrame.fxml"
                  
          )
        );

        stage.setScene(
          new Scene(
            (Parent) loader.load()
          )
        );

        /*
        LoginController controller = 
          loader.<LoginController>getController();
        */
        LoginController controller = (LoginController) loader.getController();
        controller.registerStage(stage);

        stage.show();

        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
