package areri.userpage.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMainEx01 extends Application {
	//fxml
	//load
	@Override
	public void start(Stage primaryStage) throws Exception {
		//FXML load
		Parent root = FXMLLoader.load(getClass().getResource("/areri/userpage/view/MyPageFx.fxml")); //AnchorPaneFx.fxml
		
		//Scene에 추가
		Scene scene = new Scene(root);
		
		//stage에 추가
		primaryStage.setTitle("마이 페이지");
		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
