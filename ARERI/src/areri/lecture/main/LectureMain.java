package areri.lecture.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LectureMain extends Application {
	//fxml
	//load
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/areri/lecture/view/LectureSearchFx.fxml"));
															
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("제목");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
