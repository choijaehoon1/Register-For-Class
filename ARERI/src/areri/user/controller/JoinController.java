/**
 *<pre>
 * areri.user.controller
 * Class Name : JoinTestController
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
import java.util.List;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import areri.user.controller.LoginController;
import areri.user.domain.StudentVO;
import areri.userpage.dao.UserPageDao2;
import areri.user.dao.UserDao;

/**
 * <pre>
 * areri.user.controller 
 *    |_ JoinTestController.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 4:11:48
 * @version : 
 * @author : 박지수
 */
public class JoinController implements Initializable {
	@FXML Button joinCancelBtn;//회원가입 취소버튼
	@FXML Button joinCheckBtn;//회원가입 확인버튼
	@FXML TextField joinId;//아이디 입력
	@FXML TextField joinName;//이름 입력
	@FXML TextField joinEmail;//이메일 입력
	@FXML PasswordField joinPw;//비밀번호 입력
	@FXML PasswordField joinPwCheck;//비밀번호 확인 입력
	@FXML Text pwTxt;
	
	//다국어
	@FXML Button koreanBtn;//한국어
	@FXML Button englishBtn;//영어
	@FXML Label labelId, labelPw, labelPwCheck, labelName;//라벨
			
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
		
		pwTxt.setText("비밀번호는 8자리 이상 12자리 이하이며 \n영문과 숫자, 특수문자를 반드시 포함해야 합니다.");

		joinCheckBtn.setOnAction(event->join01(event));
		
		koreanBtn.setOnAction(event->changeToKorean01(event));
		englishBtn.setOnAction(event->changeToEnglish01(event));
	}
	
	/**
	 * 회원가입 버튼
	 * 아이디, 비밀번호, 이메일 유효성검사 및 존재하는 데이터인지 확인
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void join01(ActionEvent event) {		
		String inputId = joinId.getText().trim();
		String inputPw = joinPw.getText().trim();
		String inputPw2 = joinPwCheck.getText().trim();
		String inputName = joinName.getText().trim();
		String inputEmail = joinEmail.getText().trim();
		
		if(inputId == null || "".equals(inputId)) {
			alert.setHeaderText("아이디를 입력하세요.");
			alert.showAndWait();
			
			joinId.requestFocus();
			
			return;
		}else if(inputPw == null || "".equals(inputPw)) {
			alert.setHeaderText("비밀번호를 입력하세요.");
			alert.showAndWait();
			
			joinPw.requestFocus();
			
			return;
		}else if(inputPw2 == null || "".equals(inputPw2)) {
			alert.setHeaderText("비밀번호를 확인하세요.");
			alert.showAndWait();
			
			joinPwCheck.requestFocus();
			
			return;
		}else if(inputName == null || "".equals(inputName)) {
			alert.setHeaderText("이름을 입력하세요.");
			alert.showAndWait();
			
			joinName.requestFocus();
			
			return;
		}else if(inputEmail == null || "".equals(inputEmail)) {
			alert.setHeaderText("이메일을 입력하세요.");
			alert.showAndWait();
			
			joinEmail.requestFocus();
			
			return;
		}
		
		if(dao.isIdTrue(inputId) == false) {
			joinId.requestFocus();
			return;
		} else if(dao.isIdExists(inputId) == true) {
			joinId.requestFocus();
			return;
		} else if(dao.isPwTrue(inputPw) == false) {
			joinPw.requestFocus();
			return;
		} else if(dao.isPwSame(inputPw, inputPw2) == false) {
			joinPwCheck.requestFocus();
			return;
		} else if(dao.isEmailTrue(inputEmail) == false) {
			joinEmail.requestFocus();
			return;
		} 

		dao.do_save(inputId, inputPw, inputName, inputEmail);	
		UserPageDao2 dao = new UserPageDao2();
		dao.joinStudentIdSave(inputId);
		alert.setHeaderText("가입에 성공하였습니다.");
		alert.showAndWait();
		
		try {
			Stage primaryStage = new Stage();
	        Parent root;
			root = FXMLLoader.load(getClass().getResource("/areri/user/view/Login.fxml"));
			Scene scene = new Scene(root);
	    	scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			primaryStage.show();

			Stage main = (Stage) joinCheckBtn.getScene().getWindow();
			main.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 회원가입 취소 버튼
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void joinCancel01(ActionEvent event) {

		try {
			Stage primaryStage = new Stage();
	        Parent root;
			root = FXMLLoader.load(getClass().getResource("/areri/user/view/Login.fxml"));
			Scene scene = new Scene(root);
	    	scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Login");
	        primaryStage.show();
	         
	         Stage main = (Stage) joinCancelBtn.getScene().getWindow();
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
			labelPw.setText((String) props.get("k02"));
			labelPwCheck.setText((String) props.get("k05"));
			labelName.setText((String) props.get("k06"));
			joinCheckBtn.setText((String) props.get("k07"));
			joinCancelBtn.setText((String) props.get("k08"));
			
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
			labelPw.setText((String) props.get("e02"));
			labelPwCheck.setText((String) props.get("e05"));
			labelName.setText((String) props.get("e06"));
			joinCheckBtn.setText((String) props.get("e07"));
			joinCancelBtn.setText((String) props.get("e08"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
