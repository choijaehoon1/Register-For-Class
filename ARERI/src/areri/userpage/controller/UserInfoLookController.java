/**
 *<pre>
 * areri.userpage.controller
 * Class Name : UserInfoLookController
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-11           최초생성
 *
 * @author 개발프레임웍크 실행환경 ARERI
 * @since 2019-12-18 
 * @version 1.0
 * 
 *
 *  Copyright (C) by ARERI All right reserved.
 * </pre>
 */
package areri.userpage.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import areri.userpage.Const;
import areri.userpage.dao.UserInfoDao;
import areri.userpage.domain.StudentVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * <pre>
 * areri.userpage.controller 
 *    |_ UserInfoLookController.java
 * 
 * </pre>
 * @date : 2019. 12. 18.
 * @version : 
 * @author : 주정현
 */
public class UserInfoLookController implements Initializable {
	@FXML Button complete;	//확인버튼
	@FXML Button close;	//취소 버튼
	@FXML PasswordField password; //입력받은 비밀번호
	
	UserInfoDao dao = new UserInfoDao();
	List<StudentVO> list = dao.myList(Const.studentID);
	
	/**
	 *  initialize
	 * @author 주정현
	 * @param location
	 * @param resources
	 * @return 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//비밀 번호 확인
		complete.setOnAction(event -> pwOK(event));
		
		//창닫기
		close.setOnAction(event -> closeStage(event));
	}

	/**
	 *  입력받은 패스워드가 일치하는 지 확인
	 * @author 주정현
	 * @param envent
	 * @return 
	 */
	public void pwOK(ActionEvent envent) {
		if(password.getText().equals(list.get(0).getPassword())) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("확인창");
			alert.setHeaderText(list.get(0).getId() + "님의 개인정보입니다.");
			alert.setContentText("이름 : "+list.get(0).getName()+"\n"+"이메일 : " +list.get(0).getEmail());
			alert.showAndWait();
			((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("확인");
			
			Stage stage = (Stage) close.getScene().getWindow();
		    stage.close();
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("확인창");
			alert.setHeaderText("비밀번호가 틀립니다.");
			alert.showAndWait();
		}
	}
	
	/**
	 *  창닫기
	 * @author 주정현
	 * @param envent
	 * @return 
	 */
	public void closeStage(ActionEvent evnet) {
		  Stage stage = (Stage) close.getScene().getWindow();
		    stage.close();
	}
}
