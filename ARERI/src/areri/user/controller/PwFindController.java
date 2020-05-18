/**
 *<pre>
 * areri.user.controller
 * Class Name : PwFindController
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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import areri.user.controller.LoginController;
import areri.user.domain.StudentVO;
import areri.user.dao.UserDao;

/**
 * <pre>
 * areri.user.controller
 *    |_ PwFindController.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 4:23:19
 * @version : 
 * @author : 박지수
 */
public class PwFindController implements Initializable {
	@FXML Button pwFindCancelBtn;//비번찾기 취소버튼
	@FXML TextField pwFindId;//비번찾기 아이디 입력
	@FXML TextField pwFindEmail;//비번찾기 이메일 입력
	@FXML Button pwFindCheckBtn;//비번찾기 확인버튼
	
	//다국어
	@FXML Label labelId;//라벨 아이디
	@FXML Button koreanBtn;//한국어
	@FXML Button englishBtn;//영어

	StudentVO vo;
	UserDao dao;
	private Alert alert = new Alert(AlertType.INFORMATION);
	
	/**
	 * initialize 
	 * @author 박지수
	 * @param initialize,resources
	 * @return
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new UserDao();
		LoginController.studentList = new ArrayList<StudentVO>();
		LoginController.readFile(LoginController.FILE_PATH);
		pwFindCheckBtn.setOnAction(event->pwfind01(event));
		
		koreanBtn.setOnAction(event->changeToKorean01(event));
		englishBtn.setOnAction(event->changeToEnglish01(event));
	}
	
	/**
	 * pw찾기 버튼
	 * 입력받은 아이디 이메일과 데이터 비교
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void pwfind01(ActionEvent event) {
		String inputId = pwFindId.getText().trim();
		String inputEmail = pwFindEmail.getText().trim();
		
		if(inputId == null || "".equals(inputId)) {
			alert.setHeaderText("아이디를 입력하세요.");
			alert.showAndWait();
			
			pwFindId.requestFocus();
			
			return;
		}else if(inputEmail == null || "".equals(inputEmail)) {
			alert.setHeaderText("이메일을 입력하세요.");
			alert.showAndWait();
			
			pwFindEmail.requestFocus();
			
			return;
		}
		
		if(dao.pwFind_IdCheck(inputId) == true) {
			if(dao.pwFind_EmailCheck(inputId, inputEmail) == false) {
				alert.setHeaderText("잘못된 이메일입니다.");
				alert.showAndWait();
				pwFindEmail.requestFocus();
				return;
			} else {
				try {
					Stage primaryStage = new Stage();
			        Parent root;
					root = FXMLLoader.load(getClass().getResource("/areri/user/view/Login.fxml"));
					Scene scene = new Scene(root);
			    	scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
					primaryStage.setScene(scene);
					primaryStage.setTitle("Login");
					primaryStage.show();

					Stage main = (Stage) pwFindCheckBtn.getScene().getWindow();
					main.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}else {
			alert.setHeaderText("잘못된 학번입니다.");
			alert.showAndWait();
			pwFindId.requestFocus();
			return;	
		}		
	}
	
	/**
	 * pw찾기 취소 버튼
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void pwfindCancel01(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
	        Parent root;
			root = FXMLLoader.load(getClass().getResource("/areri/user/view/Login.fxml"));
			Scene scene = new Scene(root);
	    	scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Login");
	        primaryStage.show();
	         
	         Stage main = (Stage) pwFindCancelBtn.getScene().getWindow();
	         main.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 다국어변환(한국어) 버튼
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void changeToKorean01(ActionEvent event) {
		Properties props = new Properties();
		BufferedInputStream bis = null;
		try {
			FileInputStream fis = new FileInputStream(LoginController.korProp);
			bis = new BufferedInputStream(fis);
			props.load(bis);
			
			labelId.setText((String) props.get("k01"));
			pwFindCheckBtn.setText((String) props.get("k07"));
			pwFindCancelBtn.setText((String) props.get("k08"));
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 다국어변환(영어) 버튼
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void changeToEnglish01(ActionEvent event) {
		Properties props = new Properties();
		BufferedInputStream bis = null;
		try {
			FileInputStream fis = new FileInputStream(LoginController.engProp);
			bis = new BufferedInputStream(fis);
			props.load(bis);
			
			labelId.setText((String) props.get("e01"));
			pwFindCheckBtn.setText((String) props.get("e07"));
			pwFindCancelBtn.setText((String) props.get("e08"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
