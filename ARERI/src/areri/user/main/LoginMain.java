package areri.user.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/areri/user/view/Login.fxml"));
            Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }        
        
	}

	public static void main(String[] args) {
		launch(args);

	}

}
