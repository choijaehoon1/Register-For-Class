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
package areri.userpage.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import areri.lecture.dao.LectureDao;
import areri.lecture.domain.LectrueFxVO;
import areri.lecture.domain.LectureVO;
import areri.user.controller.LoginController;
import areri.userpage.Const;
import areri.userpage.dao.UserPageDao2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <pre>
 * areri.userpage.controller 
 *    |_ MypageController.java
 * 
 * </pre>
 * @date : 2019. 12. 18.
 * @version : 
 * @author : 주정현
 */
public class MypageController implements Initializable {
	public static String lectureCode;
	
	@FXML Text studentId;						//자기 학번 표시
	@FXML Text studentName;						//자기 학번 표시
	@FXML Button timeTable;						//시간표 버튼
	@FXML Button userInfo;						//자기정보 수정
	@FXML Button userInfo1;						//자기정보 조회
	@FXML Button logoutBtn;						//로그아웃
	@FXML Button lecture;						//강의조회페이지이동
	@FXML Button selectPutLecture; 				//수강장바구니 수강 신청
	@FXML Button deleteCompleteLecture;			//수강취소
	@FXML Button deletePutLecture; 				//장바구니에서 삭제 
	@FXML TableView<LectrueFxVO> myLectureView;	//수강신청 장바구니 
	@FXML TableView<LectrueFxVO> completeLecture;

	public static List<LectrueFxVO> list;
	public static ObservableList lectureList;
	public static ObservableList comLectureList;
	
	Alert alert = new Alert(AlertType.WARNING);//에러메세지

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//화면상단 아이디
		studentId.setText(Const.studentID);
		studentName.setText("("+LoginController.studentNAME+")");
		
		//자기 정보 수정
		userInfo.setOnAction(event ->userInfoOpen(event));
		
		//자기 정보 조회
		userInfo1.setOnAction(event -> userInfoLook(event));
		
		//로그아웃
		logoutBtn.setOnAction(event->LogOutBtnAction(event));
		
		//강의조회페이지 이동
		lecture.setOnAction(event->lectureMove(event));
		
		//내 장바구니 강의 tableView에 표시
		putTableView();
		//내 수강완료 강의 tavleView 표시
		comTableView();
		
		//수강 장바구니 수강 신청
		selectPutLecture.setOnAction(event->saveSelectPutLecture(event));
		
		//수강 장바구니에서 삭제
		deletePutLecture.setOnAction(event->deletePutLecture(event));
		
		//수강 신청완료 수강 취소
		deleteCompleteLecture.setOnAction(event->deletCompleteLecture(event));
		
		//자기 시간표 출력
		timeTable.setOnAction(event ->timeTableOpen(event));
		
	}
	
	 /**
	    *  내 정보 수정 창 열기
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void userInfoOpen(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/areri/userpage/view/UserInfo.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setScene(scene);
			primaryStage.setTitle("내 정보 수정");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	    * 내 정보 조회창 열기
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void userInfoLook(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/areri/userpage/view/UserInfoLook.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setScene(scene);
			primaryStage.setTitle("내 정보 조회");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	    * 로그아웃(로그인창으로 이동)
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void LogOutBtnAction(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/areri/user/view/Login.fxml"));
			Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			primaryStage.show();
			
			Stage main = (Stage) logoutBtn.getScene().getWindow();
			main.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	    * 강의조회창으로 이동
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void lectureMove(ActionEvent event) {
		try {
		Stage primaryStage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/areri/lecture/view/LectureSearchFx.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
		primaryStage.setScene(scene);
		primaryStage.setTitle("강의조회");
		primaryStage.show();
		
		Stage main = (Stage) logoutBtn.getScene().getWindow();
		main.close();
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
	
	/**
	    * 강의 장바구니에서 강의 신청
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void saveSelectPutLecture(ActionEvent event) {
		if(myLectureView.getSelectionModel().getSelectedIndex()==-1) {
			alert.setTitle("확인창");
			alert.setHeaderText("강의를 선택해주세요");
			alert.showAndWait();
		}else {
			
			TablePosition pos = myLectureView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			String slecteLecture = myLectureView.getItems().get(row).getLectureCode();
			UserPageDao2 mydao = new UserPageDao2();		
			mydao.lectureSave(slecteLecture);
			lectureList.removeAll(lectureList);
		
			//수강장바구니 tableview 갱신
			putTableView();
			//수강신청완료  tableview갱신
			comTableView();
		}
	}
	
	/**
	    * 강의 장바구니 창에서 강의 삭제
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void deletePutLecture(ActionEvent event) {
		if(myLectureView.getSelectionModel().getSelectedIndex()==-1){
			alert.setTitle("확인창");
			alert.setHeaderText("강의를 선택해주세요");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("확인창");
			alert.setHeaderText("정말로 삭제 합니까?");
			((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("확인");

			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				TablePosition pos = myLectureView.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				String slecteLecture = myLectureView.getItems().get(row).getLectureCode();
				UserPageDao2 mydao = new UserPageDao2();		
				mydao.lectureDelete(slecteLecture);
				
				lectureList.removeAll(lectureList);
		
				//수강장바구니 tableview 갱신
				putTableView();
			}	else {
				// ... user chose CANCEL or closed the dialog
			}
		}
	}
	
	/**
	    *  강의신청창에서 강의 삭제
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void deletCompleteLecture(ActionEvent event) {
		if(completeLecture.getSelectionModel().getSelectedIndex()==-1) {
			alert.setTitle("확인창");
			alert.setHeaderText("강의를 선택해주세요");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("확인창");
			alert.setHeaderText("정말로 삭제 합니까?");
			((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("확인");
	
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				TablePosition pos = completeLecture.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				String slecteLecture = completeLecture.getItems().get(row).getLectureCode();
				UserPageDao2 mydao = new UserPageDao2();		
				mydao.comLectureDelete(slecteLecture);
				comLectureList.removeAll(comLectureList);
			
			
				//수강장바구니 tableview 갱신
				putTableView();
				//수강신청완료  tableview갱신
				comTableView();
			}
			else {
				// ... user chose CANCEL or closed the dialog
			}
		}
	}
	
	
	/**
	    *  시간표창 열기
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void timeTableOpen(ActionEvent event) {
      try {
         Parent root = FXMLLoader.load(getClass().getResource("/areri/userpage/view/TimeTableFx.fxml"));
          Scene scene = new Scene(root);
          Stage primaryStage = new Stage();
          primaryStage.setTitle("시간표");
            primaryStage.setScene(scene);
            primaryStage.show();
            
      } catch (IOException e) {
         e.printStackTrace();
      }  
	}


	/**
	    *  수강 장바구니 TableView 갱신
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void putTableView() {
		UserPageDao2 mydao = new UserPageDao2();		
		List<LectureVO> list1 = mydao.myLecture(Const.studentID);
		List<LectrueFxVO> list2 = new ArrayList();
		
		if(list1!=null) {
		for(LectureVO vo : list1) {
			LectrueFxVO tableVo = new LectrueFxVO(vo.getLectureCode(),vo.getLecturName(),
												vo.getProfessorCode(),vo.getLectureDay(),
												vo.getLectureStartTime(),vo.getLectureEndTime(),
												vo.getLectureRoom(),vo.getLectureStudent(),
												vo.getLecturePlan(),vo.getStarSum(),
												vo.getStarPeople(),vo.getStarAvg()
												);
			list2.add(tableVo);
		}
		
		lectureList = FXCollections.observableArrayList(list2);
		TableColumn tclectureCode = myLectureView.getColumns().get(0);
		tclectureCode.setCellValueFactory(new PropertyValueFactory("lectureCode"));
		tclectureCode.setStyle("-fx-alignment: Center");
		
		TableColumn tclectureName = myLectureView.getColumns().get(1);
		tclectureName.setCellValueFactory(new PropertyValueFactory("lecturName"));
		tclectureName.setStyle("-fx-alignment: Center-Left");
		
		TableColumn tcprofessorCode = myLectureView.getColumns().get(2);
		tcprofessorCode.setCellValueFactory(new PropertyValueFactory("professorCode"));
		tcprofessorCode.setStyle("-fx-alignment: Center");

		TableColumn tclectureDay = myLectureView.getColumns().get(3);
		tclectureDay.setCellValueFactory(new PropertyValueFactory("lectureDay"));
		tclectureDay.setStyle("-fx-alignment: Center");

		TableColumn tclectureStartTime = myLectureView.getColumns().get(4);
		tclectureStartTime.setCellValueFactory(new PropertyValueFactory("lectureStartTime"));
		tclectureStartTime.setStyle("-fx-alignment: Center");

		TableColumn tclectureEndTime = myLectureView.getColumns().get(5);
		tclectureEndTime.setCellValueFactory(new PropertyValueFactory("lectureEndTime"));
		tclectureEndTime.setStyle("-fx-alignment: Center");

		TableColumn tclectureRoom = myLectureView.getColumns().get(6);
		tclectureRoom.setCellValueFactory(new PropertyValueFactory("lectureRoom"));
		tclectureRoom.setStyle("-fx-alignment: Center");

		TableColumn tclectureStudent = myLectureView.getColumns().get(7);
		tclectureStudent.setCellValueFactory(new PropertyValueFactory("lectureStudent"));
		tclectureStudent.setStyle("-fx-alignment: Center");
		

		myLectureView.setItems(lectureList);
		}
	}
	
	/**
	    * 수강신청완료 강의 tableview 갱신
	    * @author 주정현
	    * @param event
	    * @return 
	    */
	public void comTableView() {
		UserPageDao2 mydao1 = new UserPageDao2();		
		//수강신청한 table 뷰
		List<LectureVO> list3 = mydao1.myCompleteLecture(Const.studentID);
		List<LectrueFxVO> list4 = new ArrayList();
		if(list3 !=null) {
		for(LectureVO vo : list3) {
			LectrueFxVO tableVo2 = new LectrueFxVO(vo.getLectureCode(),vo.getLecturName(),
												vo.getProfessorCode(),vo.getLectureDay(),
												vo.getLectureStartTime(),vo.getLectureEndTime(),
												vo.getLectureRoom(),vo.getLectureStudent(),
												vo.getLecturePlan(),vo.getStarSum(),
												vo.getStarPeople(),vo.getStarAvg()
												);
			list4.add(tableVo2);
		}
		comLectureList = FXCollections.observableArrayList(list4);
		TableColumn tclectureCode2 = completeLecture.getColumns().get(0);
		tclectureCode2.setCellValueFactory(new PropertyValueFactory("lectureCode"));
		tclectureCode2.setStyle("-fx-alignment: Center");
		
		TableColumn tclectureName2 = completeLecture.getColumns().get(1);
		tclectureName2.setCellValueFactory(new PropertyValueFactory("lecturName"));
		tclectureName2.setStyle("-fx-alignment: Center-Left");
		
		TableColumn tcprofessorCode2 = completeLecture.getColumns().get(2);
		tcprofessorCode2.setCellValueFactory(new PropertyValueFactory("professorCode"));
		tcprofessorCode2.setStyle("-fx-alignment: Center");

		TableColumn tclectureDay2 = completeLecture.getColumns().get(3);
		tclectureDay2.setCellValueFactory(new PropertyValueFactory("lectureDay"));
		tclectureDay2.setStyle("-fx-alignment: Center");

		TableColumn tclectureStartTime2 = completeLecture.getColumns().get(4);
		tclectureStartTime2.setCellValueFactory(new PropertyValueFactory("lectureStartTime"));
		tclectureStartTime2.setStyle("-fx-alignment: Center");

		TableColumn tclectureEndTime2 = completeLecture.getColumns().get(5);
		tclectureEndTime2.setCellValueFactory(new PropertyValueFactory("lectureEndTime"));
		tclectureEndTime2.setStyle("-fx-alignment: Center");

		TableColumn tclectureRoom2 = completeLecture.getColumns().get(6);
		tclectureRoom2.setCellValueFactory(new PropertyValueFactory("lectureRoom"));
		tclectureRoom2.setStyle("-fx-alignment: Center");

		TableColumn tclectureStudent2 = completeLecture.getColumns().get(7);
		tclectureStudent2.setCellValueFactory(new PropertyValueFactory("lectureStudent"));
		tclectureStudent2.setStyle("-fx-alignment: Center");
		

		completeLecture.setItems(comLectureList);
			}	
		}
	}
	