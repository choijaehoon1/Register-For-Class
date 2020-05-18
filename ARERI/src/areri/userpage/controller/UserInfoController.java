/**
 *<pre>
 * areri.userpage.controller
 * Class Name : UserInfoController
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-18  주정현         최초생성
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import areri.user.controller.LoginController;
import areri.userpage.Const;
import areri.userpage.dao.UserInfoDao;
import areri.userpage.domain.StudentVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <pre>
 * areri.userpage.controller 
 *    |_ UserInfoController.java
 * 
 * </pre>
 * @date : 2019. 12. 18.
 * @version : 
 * @author : 주정현
 */
public class UserInfoController implements Initializable {
	@FXML Button close;
	@FXML Button save;
	@FXML Text id;
	
	@FXML PasswordField pass1; //첫번째 입력받은 비밀번호
	@FXML PasswordField pass2; //두번째 입력받은 비밀번호
	@FXML TextField name; //입력받은 이름
	@FXML TextField email; // 입력받은 이메일
	
	Alert alert = new Alert(AlertType.WARNING);//에러메세지
	UserInfoDao dao = new UserInfoDao();
	List<StudentVO> mylist = dao.myList(Const.studentID);
	
	 /**
	    *  initialize
	    * @author 주정현
	    * @param location
	    * @param resources
	    * @return 
	    */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setText(Const.studentID);
		
		
		//자기정보 불러와 Textfield에 저장
		pass1.setText(mylist.get(0).getPassword());
		pass2.setText(mylist.get(0).getPassword());
		name.setText(mylist.get(0).getName());
		email.setText(mylist.get(0).getEmail());
		
		save.setOnAction(event ->userInfosave(event));
		close.setOnAction(event ->userInfoClose(event));
		
	}

	 /**
	    *  저장 및 유효성 검사
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void userInfosave(ActionEvent event) {
		String pwPattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z]).{8,12}$";
		Matcher match = Pattern.compile(pwPattern).matcher(pass1.getText());
		if(match.matches()) {//1.첫번쨰 비밀번호 형식
			if(pass2.getText().equals(pass1.getText())){ // 2 첫번째 비밀번호와 두번째 비밀번호가 같을떄
				if(!(name.getText().equals(""))&&name.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣].*")) { // 3. 이름이 비어 있지 않고, 한글만 입력된경우
					if(email.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) { //4. 이메일 양식 검사				

						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("확인창");
						alert.setHeaderText("정보를 수정합니까?");
						((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("확인");
						
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK){
							StudentVO inVO = new StudentVO(Const.studentID,pass2.getText(),name.getText(),email.getText());
							for(int i=Const.studentList.size()-1;i>=0;i--) {
								if(Const.studentList.get(i).getId().equals(Const.studentID)) {

									int delFlag = dao.do_delete((StudentVO)Const.studentList.get(i));

									int flag = dao.do_save(inVO);
									LoginController.studentNAME=name.getText();
								    Stage stage = (Stage) close.getScene().getWindow();
								    stage.close();
									break;
								} 
							}
						}else {
							// ... user chose CANCEL or closed the dialog
						}
					
					}else { // 이메일이 틀릴 경우
						alert.setHeaderText("이메일 형식이 틀립니다.");
						alert.showAndWait();
					}
				}else if(name.getText().equals("")) { // 3-1 이름이 비워져 있을떄
					alert.setHeaderText("이름을 입력해 주세요");
					alert.showAndWait();
				}else {	//3-2 이름칸에 한글이 아닐때
					alert.setHeaderText("이름에 한글을 입력해 주세요");
					alert.showAndWait();
				}
			}else { // 2.첫 번째 비밀번호와 다를시
				alert.setHeaderText("비밀번호가 같지 않습니다.");
				alert.showAndWait();
			}
		}else { // 1.첫번쨰 비밀번호가 틀릴시
			alert.setHeaderText("비밀번호 형식이 틀립니다.");
			alert.showAndWait();
		}
	
}	
	
	 /**
	    *  창닫기
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void userInfoClose(ActionEvent envet) {
	    Stage stage = (Stage) close.getScene().getWindow();
	    stage.close();
	}
	
}

