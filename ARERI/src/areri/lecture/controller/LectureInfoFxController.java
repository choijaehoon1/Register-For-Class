/**
 *<pre>
 * areri.lecture.controller
 * Class Name : LectureInfoFxController
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
package areri.lecture.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import areri.lecture.domain.LectrueFxVO;
import areri.lecture.domain.LectureVO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * <pre>
 * areri.lecture.controller
 *    |_ LectureInfoFxController.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 4:11:48
 * @version : 
 * @author : 최재훈
 */
public class LectureInfoFxController implements Initializable {
	
	@FXML Text lectureName;
	@FXML Text professorClass;
	@FXML Text lectureDay;
	@FXML TextArea lecturePlan;
	@FXML Button exitBtn;
	
	/**
	 * initialize 
	 * 강의조회창에 선택한 강의에 대한 정보를 자세히 출력한다.
	 * @author 최재훈
	 * @param initialize,resources
	 * @return
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lectureName.setText(LectureSearchFxController.name);
		professorClass.setText(LectureSearchFxController.room);
		lectureDay.setText(LectureSearchFxController.day);
		lecturePlan.setText(LectureSearchFxController.plan);
		
	}
	
	/**
	 * 닫기버튼 - 창을 닫는다.
	 * @author 최재훈
	 * @param event
	 * @return
	 */
	public void handlerExitAction(ActionEvent event) {
		Stage f = (Stage) exitBtn.getScene().getWindow();
		f.close();
        
	}
	
}
