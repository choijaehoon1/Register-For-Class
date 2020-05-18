/**
 *<pre>
 * areri.userpage.controller
 * Class Name : TimeTableController
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
import java.util.ResourceBundle;

import areri.userpage.dao.UserPageDao2;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <pre>
 * areri.userpage.controller 
 *    |_ TimeTableController.java
 * 
 * </pre>
 * @date : 2019. 12. 18.
 * @version : 
 * @author : 주정현
 */
public class TimeTableController implements Initializable {
	@FXML Button close;
	@FXML GridPane timeTable;

	 /**
	    *  initialize
	    * @author 주정현
	    * @param location
	    * @param resources
	    * @return 
	    */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		TimeTableValue();

		close.setOnAction(event ->timeTableClose(event));
		
	}
	
	 /**
	    *  닫기
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void timeTableClose(ActionEvent envet) {
	    Stage stage = (Stage) close.getScene().getWindow();
	    stage.close();
	}
	
	 /**
	    *  시간표에 값 넣기
	    * @author 주정현
	    * @param
	    * @return 
	    */
	public void TimeTableValue() {
		UserPageDao2 dao = new UserPageDao2();
		String[][] time = dao.TimeTable();
		
		timeTable.setAlignment(Pos.CENTER); // 글씨 가운데 정렬
		timeTable.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
	
		for(int i=1;i<10;i++) {
			for(int j=1;j<6;j++) {
				Label label1 = new Label(time[i-1][j-1]);
				if(label1!=null)timeTable.add(label1, j+1, i);
			}
		}
	}
}


