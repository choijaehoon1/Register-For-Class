/**
 *<pre>
 * areri.userpage.controller
 * Class Name : MypageController
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
package areri.userpage;

import java.util.ArrayList;
import java.util.List;

import areri.user.controller.LoginController;
import areri.userpage.domain.StudentVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Const {
	
	/*로그인된 아이디*/
	public static String studentID = LoginController.studentID; //"200011999"; //LoginController.studentID
	public static String studentName =LoginController.studentNAME; //"주정현"; // LoginController.studentNAME
	//File -> List
	public static List<StudentVO> studentList = new ArrayList();
	//FileSave Path
	public static final String FILE_PATH="C:\\Users\\sist\\git\\Register-For-Class\\ARERI\\studentList.csv";
	//TableView Data
	public static ObservableList StudentTableViewList = FXCollections.observableArrayList();
	
}
