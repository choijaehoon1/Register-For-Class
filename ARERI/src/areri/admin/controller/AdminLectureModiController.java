/**
 *<pre>
 * Class Name : AdminLectureModiController
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-06           최초생성
 *
 * @author 개발프레임웍크 실행환경 ARERI
 * @since 2019-12-06 
 * @version 1.0
 * 
 *
 *  Copyright (C) by ARERI All right reserved.
 * </pre>
 */
package areri.admin.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import areri.admin.dao.LectureAdminDao;
import areri.lecture.domain.LectureVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * <pre>
 * areri.admin.controller
 *    |_ AdminLectureModiController.java
 * 
 * </pre>
 * @date : 2019. 12. 13.
 * @version : 
 * @author : 신찬미
 */
public class AdminLectureModiController implements Initializable {
	@FXML private Button btnMake;//등록
	@FXML private Button btnDel;//취소
	@FXML private Text txtlectureCode;
	@FXML private TextField txtlecturName;
	@FXML private Text txtprofessorCode;
	@FXML private ComboBox<String> txtlectureDay;
	@FXML private ComboBox<String> txtlectureStartTime;
	@FXML private ComboBox<String> txtlectureEndTime;	
	@FXML private TextField txtlectureRoom;
	@FXML private TextField txtlectureStudent;
	@FXML private TextField txtlecturePlan;
	@FXML private TextField txtstarSum;
	@FXML private TextField txtstarPeople; 
	@FXML private TextField txtstarAvg;

	
	LectureAdminDao dao = new LectureAdminDao();
	private LectrueFxVO modd;
	
	
	/**
	 * 강의 수강 데이터 입력
	 * 입력 양식 검사,데이터 유효성 검사 event (강의실)
	 * Table view 강의삭제, 추가 event
	 * @author : 신찬미
	 * @param modd
	 */
	public void setMod(LectrueFxVO modd) {
		this.modd = modd;
		txtlectureCode.setText(modd.getLectureCode());
		txtlecturName.setText(modd.getLecturName());
		txtprofessorCode.setText(modd.getProfessorCode());
		txtlectureDay.setValue(modd.getLectureDay());
		txtlectureStartTime.setValue(modd.getLectureStartTime());
		txtlectureEndTime.setValue(modd.getLectureEndTime());
		txtlectureRoom.setText(modd.getLectureRoom());
		txtlectureStudent.setText(modd.getLectureStudent());
		txtlecturePlan.setText(modd.getLecturePlan());
		txtstarSum.setText(modd.getStarSum());
		txtstarPeople.setText(modd.getStarPeople());
		txtstarAvg.setText(modd.getStarAvg());
	}
	
	
	
	private Alert alert = new Alert(AlertType.WARNING);

	
	/**
	 * 강의 수정 데이터 입력
	 * 입력 양식 검사,데이터 유효성 검사 event (강의실)
	 * Table view 강의삭제, 추가 event
	 * @author : 신찬미
	 * @param event
	 */
	public void btnMakeAction(ActionEvent event) {
		String lectureCode = txtlectureCode.getText().trim();
		if(null == lectureCode||"".equals(lectureCode)||(!lectureCode.matches(".*[0-9]+.*"))) {
			alert = new Alert(AlertType.WARNING);
			alert.setContentText("강의코드를 숫자로 입력하십시오.");
			alert.showAndWait();
			txtlectureCode.requestFocus();
			return;
		}
		
		String lecturName = txtlecturName.getText().trim();
		if(null == lecturName||"".equals(lecturName)) {
			alert.setContentText("강의명을 입력하십시오.");
			alert.showAndWait();
			txtlecturName.requestFocus();
			return;
		}
		
		String professorCode = txtprofessorCode.getText().trim();
		if(null == professorCode||"".equals(professorCode)) {
			alert.setContentText("교수명을 입력하십시오.");
			alert.showAndWait();
			txtprofessorCode.requestFocus();
			return;
		}
		
		String lectureDay = txtlectureDay.getValue().trim();
		if(null == lectureDay||"".equals(lectureDay)||(!lectureDay.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))) {
			alert.setContentText("요일을 [월요일]형식으로 입력하십시오.");
			alert.showAndWait();
			txtlectureDay.requestFocus();
			return;
		}
		
	
		String lectureStartTime = txtlectureStartTime.getValue().trim();
		if(null == lectureStartTime||"".equals(lectureStartTime)||(!lectureStartTime.matches("^([01][0-9]|2[0-3]):([0-5][0-9])$"))) {
			alert.setContentText("강의(시작)시간을[13:00]형식으로 입력하십시오.");
			alert.showAndWait();
			txtlectureStartTime.requestFocus();
			return;
		}
		
		String lectureEndTime = txtlectureEndTime.getValue().trim();
		if(null == lectureEndTime||"".equals(lectureEndTime)||(!lectureEndTime.matches("^([01][0-9]|2[0-3]):([0-5][0-9])$"))) {
			alert.setContentText("강의(끝)시간을 [14:00]형식으로 입력하십시오.");
			alert.showAndWait();
			txtlectureEndTime.requestFocus();
			return;
		}
		
		String lectureRoom = txtlectureRoom.getText().trim();
		if(null == lectureRoom||"".equals(lectureRoom)) {
			alert.setContentText("강의실을 입력하십시오.");
			alert.showAndWait();
			txtlectureRoom.requestFocus();
			return;
		}
		
		String lectureStudent = txtlectureStudent.getText().trim();
		if(null == lectureStudent||"".equals(lectureStudent)||(!lectureStudent.matches(".*[0-9]+.*"))) {
			alert.setContentText("신청인원을 [10/50]형식으로 입력하십시오.");
			alert.showAndWait();
			txtlectureStudent.requestFocus();
			return;
		}
		
		String lecturePlan = txtlecturePlan.getText().trim();
		if(null == lecturePlan||"".equals(lecturePlan)) {
			alert.setContentText("강의계획서를 입력하십시오.");
			alert.showAndWait();
			txtlecturePlan.requestFocus();
			return;
		}
		
		String starSum = txtstarSum.getText().trim();
		if(null == starSum||"".equals(starSum)||(!starSum.matches(".*[0-9]+.*"))) {
			alert.setContentText("별점합을 숫자로 입력하십시오.");
			alert.showAndWait();
			txtstarSum.requestFocus();
			return;
		}
		
		String starPeople = txtstarPeople.getText().trim();
		if(null == starPeople||"".equals(starPeople)||(!starPeople.matches(".*[0-9]+.*"))) {
			alert.setContentText("별점 누적인원을 숫자로 입력하십시오.");
			alert.showAndWait();
			txtstarPeople.requestFocus();
			return;
		}
		
		String starAvg = Double.toString(Double.valueOf(starSum)/Double.valueOf(starPeople));
		if(null == starAvg||"".equals(starAvg)||(!starAvg.matches(".*[0-9]+.*"))) {
			alert.setContentText("평균별점을 숫자로 입력하십시오.");
			alert.showAndWait();
			txtstarAvg.requestFocus();
			return;
		}
		
		if(true == dao.isLectureroomExistsModi(lectureRoom,lectureDay,lectureCode,lectureStartTime,lectureEndTime)
				) {
			alert = new Alert(AlertType.ERROR);
			alert.setContentText("사용중인 강의실 입니다.");
			alert.showAndWait();
			txtlectureRoom.requestFocus();
			return;
		}
		
		if(true == dao.lectureStartTimeExists(lectureStartTime,lectureEndTime)) {
			alert.setContentText("수업 끝 시간이 수업 시간 시작보다 빠릅니다..");
			alert.showAndWait();
			txtprofessorCode.requestFocus();
			return;
		}


		
		alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("수정 하시겠습니까?");
		Optional<ButtonType> result = alert.showAndWait();
	
		
		if (result.get() == ButtonType.OK) {
		
			LectureVO vo = new LectureVO(
					lectureCode,lecturName,professorCode,lectureDay,lectureStartTime,lectureEndTime,
					lectureRoom,lectureStudent,lecturePlan,starSum,starPeople,starAvg);
			
			LectureAdminDao dao = new LectureAdminDao();
			int flag = dao.do_update(vo);
			dao.saveFile(LectureAdminDao.FILE_PATH);
			//파일에 기록
			


			alert.setContentText("강의가 수정 되었습니다.");
			alert.showAndWait();
			//dialog.close();
		
		
		// TABLE Data 묻기
		LectrueFxVO tableVO = new LectrueFxVO(
				vo.getLectureCode(),
				vo.getLecturName(),
				vo.getProfessorCode(),
				vo.getLectureDay(),
				vo.getLectureStartTime(),
				vo.getLectureEndTime(),
				vo.getLectureRoom(),
				vo.getLectureStudent(),
				vo.getLecturePlan(),
				vo.getStarSum(),
				vo.getStarPeople(),
				vo.getStarAvg()
				);
		
		
 
		//for(LectrueFxVO modd: LectureAdminDao.lectureTableViewList) {
			if(tableVO.getLectureCode().equals(modd.getLectureCode())) {
				LectureAdminDao.lectureTableViewList.remove(modd);
		//		break;
		//	}
		}
			


			LectureAdminDao.lectureTableViewList.add(tableVO);
			Stage dialog = (Stage) btnMake.getScene().getWindow();
			dialog.close();
		
		}
	}
	
	/**
	 * 강의 개설 취소 event
	 * @author : 신찬미
	 * @param event
	 */
	public void btnDelAction(ActionEvent event) {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("강의 개설을 취소 하시겠습니까?");
		
		Optional<ButtonType> result = alert.showAndWait();
		//System.out.println("result.get():"+result.get() );
		if (result.get() == ButtonType.OK){
			Stage dialog =(Stage) btnDel.getScene().getWindow();
			dialog.close();
		} 
	}
		
	
	
	/**
	 * 개설,취소  event와 연결
	 * @author : 신찬미
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		alert.setTitle("강의 수정");
		alert.setHeaderText("데이터 오류");
		// 등록버튼 이벤트 감지
		btnMake.setOnAction(event->btnMakeAction(event));
		btnDel.setOnAction(event->btnDelAction(event));

		//comboBox
		txtlectureDay.getItems().addAll(
						"월요일",
						"화요일",
						"수요일",
						"목요일",
						"금요일"
			        );
		txtlectureStartTime.getItems().addAll(
				"09:00",
				"10:00",
				"11:00",
				"12:00",
				"13:00",
				"14:00",
				"15:00",
				"16:00",
				"17:00"
	        );
		
		txtlectureEndTime.getItems().addAll(
				"10:00",
				"11:00",
				"12:00",
				"13:00",
				"14:00",
				"15:00",
				"16:00",
				"17:00",
				"18:00"
	        );
		
	}
	

	
}	
	
	
	
	