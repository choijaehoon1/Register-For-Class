package areri.admin.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMain extends Application {
	//fxml
	//load
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/areri/admin/view/AdminSearchFx.fxml"));
															
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("관리자 수강신청");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
