/**
 *<pre>
 * areri.user.controller
 * Class Name : LoginController
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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import areri.user.domain.StudentVO;
import areri.user.dao.UserDao;

/**
 * <pre>
 * areri.user.controller 
 *    |_ LoginController.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 4:11:48
 * @version : 
 * @author : 박지수
 */
public class LoginController implements Initializable {
	
	@FXML TextField loginId;//아이디 입력
	@FXML PasswordField loginPw;//비밀번호 입력
	@FXML Button loginBtn;//로그인 버튼
	@FXML Button joinBtn;//회원가입 버튼
	@FXML Button pwfindBtn;//PW찾기 버튼
	
	//다국어
	@FXML Label labelId;//ID 라벨
	@FXML Label labelPw;//PW 라벨
	@FXML Button englishBtn;//영어 버튼
	@FXML Button koreanBtn;//한국어 버튼
	
	StudentVO vo;
	UserDao dao;
	public static List<StudentVO> studentList;
	public static final String FILE_PATH = "D:\\HR_20191130\\04_SPRING\\workspace\\ARERI\\studentList.csv";
	public static String engProp="src/areri/user/controller/eng_prop.properties";
	public static String korProp="src/areri/user/controller/kor_prop.properties";
	
	private Alert alert = new Alert(AlertType.INFORMATION);
	public static String studentID;
	public static String studentNAME;

	/**
	 * initialize 
	 * @author 박지수
	 * @param initialize,resources
	 * @return
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		dao = new UserDao();
		studentList = new ArrayList<StudentVO>();
		loginBtn.setOnAction(event->login01(event));
		studentList = readFile(FILE_PATH);
		
		koreanBtn.setOnAction(event->changeToKorean01(event));
		englishBtn.setOnAction(event->changeToEnglish01(event));
	}

	/**
	 * readFile메소드
	 * 지정된 경로의 파일 읽기
	 * @author 박지수
	 * @param filePath
	 * @return studentList
	 */
	public static List<StudentVO> readFile(String filePath) {
		BufferedReader br = null;

		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] dataArray = line.split(",");
				StudentVO vo = new StudentVO(dataArray[0], dataArray[1], dataArray[2], dataArray[3]);

				studentList.add(vo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		return studentList;
	}
	
	/**
	 * 로그인 버튼
	 * 입력받은 아이디, 비밀번호와 데이터 비교
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void login01 (ActionEvent event) {
				
		String inputId = loginId.getText().trim();
		if(inputId == null || "".equals(inputId)) {
			alert.setHeaderText("아이디를 입력하세요.");
			alert.showAndWait();
			
			loginId.requestFocus();
			
			return;
		}
		
		String inputPw = loginPw.getText().trim();
		if(inputPw == null || "".equals(inputPw)) {
			alert.setHeaderText("비밀번호를 입력하세요.");
			alert.showAndWait();
			
			loginPw.requestFocus();
			
			return;
		}
			
		if(dao.logIn_IdCheck(inputId)==true) {
			if(dao.logIn_PwCheck(inputId,inputPw)==false) {
				alert.setHeaderText("잘못된 비밀번호입니다.");
				alert.showAndWait();
				loginPw.requestFocus();
				return;
			}	         
		} else {
			alert.setHeaderText("잘못된 학번입니다.");
			alert.showAndWait();
			loginId.requestFocus();
			return;
		}
		
		studentID = loginId.getText().trim();
		studentNAME = dao.getStudentName(inputId);

        if(studentID.equals("admin")) {
        	try {
	        	Stage primaryStage = new Stage();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/areri/admin/view/AdminSearchFx.fxml"));
				Scene scene = new Scene(root);
	    		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
				primaryStage.setScene(scene);
				primaryStage.setTitle("관리자페이지");
				primaryStage.show();

				Stage main = (Stage) loginBtn.getScene().getWindow();
				main.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	return;
	     }
        
        try {
	        Stage primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/areri/user/view/MainPage.fxml"));
			Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setScene(scene);
			primaryStage.setTitle("MainPage");
			primaryStage.show();

			Stage main = (Stage) loginBtn.getScene().getWindow();
			main.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원가입페이지 이동 버튼
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void join01(ActionEvent event) {
		try {
			 Stage primaryStage = new Stage();
	         Parent root;
	
			root = FXMLLoader.load(getClass().getResource("/areri/user/view/Join.fxml"));
			Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setScene(scene);
			primaryStage.setTitle("회원가입");
			primaryStage.show();
	
			Stage main = (Stage) joinBtn.getScene().getWindow();
			main.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	/**
	 * pw찾기페이지 이동 버튼
	 * @author 박지수
	 * @param event
	 * @return
	 */
	public void pwfind01(ActionEvent event) {
		try {
			 Stage primaryStage = new Stage();
	         Parent root;
			 root = FXMLLoader.load(getClass().getResource("/areri/user/view/PwFind.fxml"));
			 Scene scene = new Scene(root);
	    	 scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
	         primaryStage.setScene(scene);
	         primaryStage.setTitle("비밀번호 찾기");
	         primaryStage.show();
	         
	         Stage main = (Stage) pwfindBtn.getScene().getWindow();
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
			FileInputStream fis = new FileInputStream(korProp);
			bis = new BufferedInputStream(fis);
			props.load(bis);
			
			labelId.setText(props.getProperty("k01"));
			labelPw.setText(props.getProperty("k02"));
			joinBtn.setText(props.getProperty("k03"));
			pwfindBtn.setText(props.getProperty("k04"));
			
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
			FileInputStream fis = new FileInputStream(engProp);
			bis = new BufferedInputStream(fis);
			props.load(bis);
			
			labelId.setText(props.getProperty("e01"));
			labelPw.setText(props.getProperty("e02"));
			joinBtn.setText(props.getProperty("e03"));
			pwfindBtn.setText(props.getProperty("e04"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
