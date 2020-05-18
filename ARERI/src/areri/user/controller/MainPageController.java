/**
 *<pre>
 * areri.user.controller
 * Class Name : MainPageController.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-11           최초생성
 *
 * @author 개발프레임웍크 실행환경 ARERI
 * @since 2019-12-11 
 * @version 1.0
 * 
 *
 *  Copyright (C) by ARERI All right reserved.
 * </pre>
 */
package areri.user.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <pre>
 * areri.user.controller 
 *    |_ MainPageController.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 9:14:14
 * @version : 
 * @author : 박지수
 */
public class MainPageController implements Initializable {
	@FXML Button MyPageBtn;
	@FXML Button MainBtn;
	@FXML Text stuId;
	@FXML Text stuName;
	
	String studentID = LoginController.studentID;
	String studentNAME = LoginController.studentNAME;
	
	/**
	 * initialize 
	 * @author 박지수
	 * @param initialize,resources
	 * @return
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MyPageBtn.setOnAction(event->gotoMyPage01(event));
		MainBtn.setOnAction(event->gotoMain01(event));
		
		stuId.setText(studentID);
		stuName.setText("(" +studentNAME +")");

	}
	
	/**
	 * 마이페이지 이동 버튼
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void gotoMyPage01(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
	        Parent root;
			root = FXMLLoader.load(getClass().getResource("/areri/userpage/view/MyPageFx.fxml"));
			Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("마이페이지");
	        primaryStage.show();
	         
	         Stage main = (Stage) MyPageBtn.getScene().getWindow();
	         main.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 강의조회페이지 이동 버튼
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void gotoMain01(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
	        Parent root;
			root = FXMLLoader.load(getClass().getResource("/areri/lecture/view/LectureSearchFx.fxml"));
			Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("강의조회");
	        primaryStage.show();
	         
	         Stage main = (Stage) MainBtn.getScene().getWindow();
	         main.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
