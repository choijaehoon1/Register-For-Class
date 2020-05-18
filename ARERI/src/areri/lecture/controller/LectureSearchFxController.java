/**
 *<pre>
 * areri.lecture.controller
 * Class Name : LectureSearchFxController
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import areri.lecture.dao.LectureDao;
import areri.lecture.domain.LectrueFxVO;
import areri.lecture.domain.LectureVO;
import areri.userpage.dao.UserPageDao2;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * <pre>
 * areri.lecture.controller
 *    |_ LectureSearchFxController.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 4:11:48
 * @version : 
 * @author : 최재훈
 */
public class LectureSearchFxController implements Initializable {
	public static String name;
	public static String day;
	public static String room;
	public static String plan;
	
	@FXML Button joinChart;
	@FXML Button joinInfo;
	@FXML Button saveBtn;
	@FXML Button retrieveBtn;
	@FXML Button searchBtn;
	@FXML Text userId;
	@FXML Text studentName;
	@FXML ComboBox<String> selectCombo;
	@FXML TableView<LectrueFxVO> tableView;
	@FXML ComboBox<String> selectSearchCombo;
	@FXML TextField txtBox;
	@FXML Button mypageBtn;
	@FXML Button logoutBtn;
	LectrueFxVO vo;
	LectureDao dao;
	LectureVO vo1;

	
	private Alert alert = new Alert(AlertType.INFORMATION);
	
	public static List<LectureVO> list1;
	public static List<LectrueFxVO> list;	
	public static TablePosition pos; 
	public static ObservableList lectureList;
	String id;
	String student_Name;
	
	/**
	 * initialize 
	 * 로그인한 학번과 이름을 출력한다.
	 * @author 최재훈
	 * @param initialize,resources
	 * @return
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new LectureDao();
		id = dao.student_ID;
		userId.setText(id);
		
		student_Name = dao.student_NAME;
		studentName.setText("(" +student_Name +")");
		
		vo = dao.getSearchData();
		
				
		retrieveBtn.setOnAction(event -> retreiveHandlerBtn(event));
		searchBtn.setOnAction(event -> searchHandlerBtn(event));
		joinInfo.setOnAction(event-> joinInfoHandlerBtn(event));
		saveBtn.setOnAction(event -> saveHandlerBtn(event));
		logoutBtn.setOnAction(event -> LogOutBtnAction(event));
		mypageBtn.setOnAction(event -> mypageBtnAction(event));
	}
	
	/**
	 * 마이페이지로 이동한다.
	 * @author 최재훈
	 * @param event
	 * @return
	 */
	public void mypageBtnAction(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/areri/userpage/view/MyPageFx.fxml"));
			Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setScene(scene);
			primaryStage.setTitle("MyPage");
			primaryStage.show();
			
			Stage main = (Stage) logoutBtn.getScene().getWindow();
			main.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 로그아웃해서 로그인화면으로 이동한다.
	 * @author 최재훈
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
	 * Mypage에 장바구니에 저장(수강인원 1증가해서 저장)한다.
	 * @author 최재훈
	 * @param event
	 * @return
	 */
	public void saveHandlerBtn(ActionEvent event) {

		try {
			pos = tableView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			
			LectrueFxVO inVO = new LectrueFxVO(tableView.getItems().get(row).getLectureCode(),
										tableView.getItems().get(row).getLecturName(),
										tableView.getItems().get(row).getProfessorCode(),
										tableView.getItems().get(row).getLectureDay(),
										tableView.getItems().get(row).getLectureStartTime(),
										tableView.getItems().get(row).getLectureEndTime(),
										tableView.getItems().get(row).getLectureRoom(),
										tableView.getItems().get(row).getLectureStudent(),
										tableView.getItems().get(row).getLecturePlan(),
										tableView.getItems().get(row).getStarSum(),
										tableView.getItems().get(row).getStarPeople(),
										tableView.getItems().get(row).getStarAvg()
					);
			UserPageDao2 dao2 = new UserPageDao2(); 	
			list1= dao2.myLecture(id);

			if(!(list1==null)) {
				for(int i =list1.size()-1;i>=0;i--) {
					if(tableView.getItems().get(row).getLectureDay().equals("월요일")) {
						if(list1.get(i).getLectureDay().equals("월요일")) {
							int alreadyStart = Integer.valueOf(list1.get(i).getLectureStartTime().split(":")[0]);
							int endTime =	   Integer.valueOf(list1.get(i).getLectureEndTime().split(":")[0]);
							if(alreadyStart == Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0])
								||(endTime > Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0]))&&(alreadyStart<Integer.valueOf(tableView.getItems().get(row).getLectureEndTime().split(":")[0]))) {
								 
								alert.setTitle("요일별 수강시간 중복.");
								alert.setHeaderText("요일별 수강시간이 겹칩니다.");
								alert.showAndWait();
								return;
								
							}
						}
					} else if(tableView.getItems().get(row).getLectureDay().equals("화요일")) {
						if(list1.get(i).getLectureDay().equals("화요일")) {
							int alreadyStart = Integer.valueOf(list1.get(i).getLectureStartTime().split(":")[0]);
							int endTime =	   Integer.valueOf(list1.get(i).getLectureEndTime().split(":")[0]);
							if(alreadyStart == Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0])
									||(endTime > Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0]))&&(alreadyStart<Integer.valueOf(tableView.getItems().get(row).getLectureEndTime().split(":")[0]))) {
									 
									alert.setTitle("요일별 수강시간 중복.");
									alert.setHeaderText("요일별 수강시간이 겹칩니다.");
									alert.showAndWait();
									return;
									
							}
						}
					} else if(tableView.getItems().get(row).getLectureDay().equals("수요일")) {
						if(list1.get(i).getLectureDay().equals("수요일")) {
							int alreadyStart = Integer.valueOf(list1.get(i).getLectureStartTime().split(":")[0]);
							int endTime =	   Integer.valueOf(list1.get(i).getLectureEndTime().split(":")[0]);
							if(alreadyStart == Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0])
									||(endTime > Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0]))&&(alreadyStart<Integer.valueOf(tableView.getItems().get(row).getLectureEndTime().split(":")[0]))) {
									 
									alert.setTitle("요일별 수강시간 중복.");
									alert.setHeaderText("요일별 수강시간이 겹칩니다.");
									alert.showAndWait();
									return;
									
							}
						}
					} else if(tableView.getItems().get(row).getLectureDay().equals("목요일")) {
						if(list1.get(i).getLectureDay().equals("목요일")) {
							int alreadyStart = Integer.valueOf(list1.get(i).getLectureStartTime().split(":")[0]);
							int endTime =	   Integer.valueOf(list1.get(i).getLectureEndTime().split(":")[0]);
							if(alreadyStart == Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0])
									||(endTime > Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0]))&&(alreadyStart<Integer.valueOf(tableView.getItems().get(row).getLectureEndTime().split(":")[0]))) {
									 
									alert.setTitle("요일별 수강시간 중복.");
									alert.setHeaderText("요일별 수강시간이 겹칩니다.");
									alert.showAndWait();
									return;
									
							}
						}
					} else if(tableView.getItems().get(row).getLectureDay().equals("금요일")) {
						if(list1.get(i).getLectureDay().equals("금요일")) {
							int alreadyStart = Integer.valueOf(list1.get(i).getLectureStartTime().split(":")[0]);
							int endTime =	   Integer.valueOf(list1.get(i).getLectureEndTime().split(":")[0]);
							if(alreadyStart == Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0])
									||(endTime > Integer.valueOf(tableView.getItems().get(row).getLectureStartTime().split(":")[0]))&&(alreadyStart<Integer.valueOf(tableView.getItems().get(row).getLectureEndTime().split(":")[0]))) {
									 
									alert.setTitle("요일별 수강시간 중복.");
									alert.setHeaderText("요일별 수강시간이 겹칩니다.");
									alert.showAndWait();
									return;		
							}
						}
					}
					
				}
						
				for(int i=list.size()-1;i>=0;i--) {
					if(list.get(i).getLectureCode().equals(inVO.getLectureCode())) {
						int delFlag = dao.do_delete(list.get(i));
						int flag = dao.do_save(inVO);
						break;
					}
				}
				
				dao2.attending(tableView.getItems().get(row).getLectureCode()); 		
				tableView.getItems().remove(row);
				
				alert.setTitle("장바구니 담기 성공");
				alert.setHeaderText("장바구니에 저장되었습니다.");
				alert.showAndWait();
			}else {
				
				for(int i=list.size()-1;i>=0;i--) {
					if(list.get(i).getLectureCode().equals(inVO.getLectureCode())) {
						int delFlag = dao.do_delete(list.get(i));
						int flag = dao.do_save(inVO);
						break;
					}
				}
				//지금 putlecture.csv에 쓰는것이 11로 되어있어서 저장이 안됨!
				dao2.attending(tableView.getItems().get(row).getLectureCode());
				tableView.getItems().remove(row);
				
				alert.setTitle("장바구니 담기 성공");
				alert.setHeaderText("장바구니에 저장되었습니다.");
				alert.showAndWait();
			}
		} catch(IndexOutOfBoundsException e) {
				alert.setTitle("장바구니에 이동 강의 미선택");
				alert.setHeaderText("장바구니로 이동할 강의를 선택 하세요.");
				alert.showAndWait();
			}	
	}
	
	/**
	 * 강의코드, 강의명, 요일별로 강의내용 검색한다.
	 * @author 최재훈
	 * @param event
	 * @return
	 */
	public void searchHandlerBtn(ActionEvent event) {
		try {	
			if (selectSearchCombo.getValue().equals("강의코드")) {
				vo.setSearchDiv(txtBox.getText());
				list = (List<LectrueFxVO>) dao.do_searchCode(vo);
				lectureList = FXCollections.observableList(list);
	
				TableColumn tclectureCode = tableView.getColumns().get(0);
				tclectureCode.setCellValueFactory(new PropertyValueFactory("lectureCode"));
				tclectureCode.setStyle("-fx-alignment: Center");
	
				TableColumn tclecturName = tableView.getColumns().get(1);
				tclecturName.setCellValueFactory(new PropertyValueFactory("lecturName"));
				tclecturName.setStyle("-fx-alignment: Center_LEFT");
	
				TableColumn tcprofessorCode = tableView.getColumns().get(2);
				tcprofessorCode.setCellValueFactory(new PropertyValueFactory("professorCode"));
				tcprofessorCode.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureDay = tableView.getColumns().get(3);
				tclectureDay.setCellValueFactory(new PropertyValueFactory("lectureDay"));
				tclectureDay.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStartTime = tableView.getColumns().get(4);
				tclectureStartTime.setCellValueFactory(new PropertyValueFactory("lectureStartTime"));
				tclectureStartTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureEndTime = tableView.getColumns().get(5);
				tclectureEndTime.setCellValueFactory(new PropertyValueFactory("lectureEndTime"));
				tclectureEndTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureRoom = tableView.getColumns().get(6);
				tclectureRoom.setCellValueFactory(new PropertyValueFactory("lectureRoom"));
				tclectureRoom.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStudent = tableView.getColumns().get(7);
				tclectureStudent.setCellValueFactory(new PropertyValueFactory("lectureStudent"));
				tclectureStudent.setStyle("-fx-alignment: Center");
	
				tableView.setItems(lectureList);
				
			} else if (selectSearchCombo.getValue().equals("강의명")) {
				vo.setSearchDiv(txtBox.getText());
				list = (List<LectrueFxVO>) dao.do_searchName(vo);
				lectureList = FXCollections.observableList(list);
	
				TableColumn tclectureCode = tableView.getColumns().get(0);
				tclectureCode.setCellValueFactory(new PropertyValueFactory("lectureCode"));
				tclectureCode.setStyle("-fx-alignment: Center");
	
				TableColumn tclecturName = tableView.getColumns().get(1);
				tclecturName.setCellValueFactory(new PropertyValueFactory("lecturName"));
				tclecturName.setStyle("-fx-alignment: Center_LEFT");
	
				TableColumn tcprofessorCode = tableView.getColumns().get(2);
				tcprofessorCode.setCellValueFactory(new PropertyValueFactory("professorCode"));
				tcprofessorCode.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureDay = tableView.getColumns().get(3);
				tclectureDay.setCellValueFactory(new PropertyValueFactory("lectureDay"));
				tclectureDay.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStartTime = tableView.getColumns().get(4);
				tclectureStartTime.setCellValueFactory(new PropertyValueFactory("lectureStartTime"));
				tclectureStartTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureEndTime = tableView.getColumns().get(5);
				tclectureEndTime.setCellValueFactory(new PropertyValueFactory("lectureEndTime"));
				tclectureEndTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureRoom = tableView.getColumns().get(6);
				tclectureRoom.setCellValueFactory(new PropertyValueFactory("lectureRoom"));
				tclectureRoom.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStudent = tableView.getColumns().get(7);
				tclectureStudent.setCellValueFactory(new PropertyValueFactory("lectureStudent"));
				tclectureStudent.setStyle("-fx-alignment: Center");
	
				tableView.setItems(lectureList);
				} else if (selectSearchCombo.getValue().equals("요일")) {
					vo.setSearchDiv(txtBox.getText());
					list = (List<LectrueFxVO>) dao.do_searchDay(vo);
					lectureList = FXCollections.observableList(list);
		
					TableColumn tclectureCode = tableView.getColumns().get(0);
					tclectureCode.setCellValueFactory(new PropertyValueFactory("lectureCode"));
					tclectureCode.setStyle("-fx-alignment: Center");
		
					TableColumn tclecturName = tableView.getColumns().get(1);
					tclecturName.setCellValueFactory(new PropertyValueFactory("lecturName"));
					tclecturName.setStyle("-fx-alignment: Center_LEFT");
		
					TableColumn tcprofessorCode = tableView.getColumns().get(2);
					tcprofessorCode.setCellValueFactory(new PropertyValueFactory("professorCode"));
					tcprofessorCode.setStyle("-fx-alignment: Center");
		
					TableColumn tclectureDay = tableView.getColumns().get(3);
					tclectureDay.setCellValueFactory(new PropertyValueFactory("lectureDay"));
					tclectureDay.setStyle("-fx-alignment: Center");
		
					TableColumn tclectureStartTime = tableView.getColumns().get(4);
					tclectureStartTime.setCellValueFactory(new PropertyValueFactory("lectureStartTime"));
					tclectureStartTime.setStyle("-fx-alignment: Center");
		
					TableColumn tclectureEndTime = tableView.getColumns().get(5);
					tclectureEndTime.setCellValueFactory(new PropertyValueFactory("lectureEndTime"));
					tclectureEndTime.setStyle("-fx-alignment: Center");
		
					TableColumn tclectureRoom = tableView.getColumns().get(6);
					tclectureRoom.setCellValueFactory(new PropertyValueFactory("lectureRoom"));
					tclectureRoom.setStyle("-fx-alignment: Center");
		
					TableColumn tclectureStudent = tableView.getColumns().get(7);
					tclectureStudent.setCellValueFactory(new PropertyValueFactory("lectureStudent"));
					tclectureStudent.setStyle("-fx-alignment: Center");
		
					tableView.setItems(lectureList);
				
					}
			}catch(NullPointerException e) {
				alert.setTitle("검색기준 미선택");
				alert.setHeaderText("검색기준을 확인하세요.");
				alert.setContentText("");
				alert.showAndWait();
			}
	}

	/**
	 * 전공,교양,전체(전공+교양)과목을 조회한다.
	 * @author 최재훈
	 * @param event
	 * @return
	 */
	public void retreiveHandlerBtn(ActionEvent event) {
		try {
			if (selectCombo.getValue().equals("전공")) {
				list = (List<LectrueFxVO>) dao.do_major(vo);
				lectureList = FXCollections.observableList(list);
	
				TableColumn tclectureCode = tableView.getColumns().get(0);
				tclectureCode.setCellValueFactory(new PropertyValueFactory("lectureCode"));
				tclectureCode.setStyle("-fx-alignment: Center");
	
				TableColumn tclecturName = tableView.getColumns().get(1);
				tclecturName.setCellValueFactory(new PropertyValueFactory("lecturName"));
				tclecturName.setStyle("-fx-alignment: Center_LEFT");
	
				TableColumn tcprofessorCode = tableView.getColumns().get(2);
				tcprofessorCode.setCellValueFactory(new PropertyValueFactory("professorCode"));
				tcprofessorCode.setStyle("-fx-alignment: Center");
							
				TableColumn tclectureDay = tableView.getColumns().get(3);
				tclectureDay.setCellValueFactory(new PropertyValueFactory("lectureDay"));
				tclectureDay.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStartTime = tableView.getColumns().get(4);
				tclectureStartTime.setCellValueFactory(new PropertyValueFactory("lectureStartTime"));
				tclectureStartTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureEndTime = tableView.getColumns().get(5);
				tclectureEndTime.setCellValueFactory(new PropertyValueFactory("lectureEndTime"));
				tclectureEndTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureRoom = tableView.getColumns().get(6);
				tclectureRoom.setCellValueFactory(new PropertyValueFactory("lectureRoom"));
				tclectureRoom.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStudent = tableView.getColumns().get(7);
				tclectureStudent.setCellValueFactory(new PropertyValueFactory("lectureStudent"));
				tclectureStudent.setStyle("-fx-alignment: Center");
	
				tableView.setItems(lectureList);
		
			} else if (selectCombo.getValue().equals("교양")) {
				list = (List<LectrueFxVO>) dao.do_liberal(vo);
	
				lectureList = FXCollections.observableList(list);
	
				TableColumn tclectureCode = tableView.getColumns().get(0);
				tclectureCode.setCellValueFactory(new PropertyValueFactory("lectureCode"));
				tclectureCode.setStyle("-fx-alignment: Center");
	
				TableColumn tclecturName = tableView.getColumns().get(1);
				tclecturName.setCellValueFactory(new PropertyValueFactory("lecturName"));
				tclecturName.setStyle("-fx-alignment: Center_LEFT");
	
				TableColumn tcprofessorCode = tableView.getColumns().get(2);
				tcprofessorCode.setCellValueFactory(new PropertyValueFactory("professorCode"));
				tcprofessorCode.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureDay = tableView.getColumns().get(3);
				tclectureDay.setCellValueFactory(new PropertyValueFactory("lectureDay"));
				tclectureDay.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStartTime = tableView.getColumns().get(4);
				tclectureStartTime.setCellValueFactory(new PropertyValueFactory("lectureStartTime"));
				tclectureStartTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureEndTime = tableView.getColumns().get(5);
				tclectureEndTime.setCellValueFactory(new PropertyValueFactory("lectureEndTime"));
				tclectureEndTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureRoom = tableView.getColumns().get(6);
				tclectureRoom.setCellValueFactory(new PropertyValueFactory("lectureRoom"));
				tclectureRoom.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStudent = tableView.getColumns().get(7);
				tclectureStudent.setCellValueFactory(new PropertyValueFactory("lectureStudent"));
				tclectureStudent.setStyle("-fx-alignment: Center");
	
				tableView.setItems(lectureList);
	
			} else {
				list = (List<LectrueFxVO>) dao.do_retrieve(vo);
	
				lectureList = FXCollections.observableList(list);
	
				TableColumn tclectureCode = tableView.getColumns().get(0);
				tclectureCode.setCellValueFactory(new PropertyValueFactory("lectureCode"));
				tclectureCode.setStyle("-fx-alignment: Center");
	
				TableColumn tclecturName = tableView.getColumns().get(1);
				tclecturName.setCellValueFactory(new PropertyValueFactory("lecturName"));
				tclecturName.setStyle("-fx-alignment: Center_LEFT");
	
				TableColumn tcprofessorCode = tableView.getColumns().get(2);
				tcprofessorCode.setCellValueFactory(new PropertyValueFactory("professorCode"));
				tcprofessorCode.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureDay = tableView.getColumns().get(3);
				tclectureDay.setCellValueFactory(new PropertyValueFactory("lectureDay"));
				tclectureDay.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStartTime = tableView.getColumns().get(4);
				tclectureStartTime.setCellValueFactory(new PropertyValueFactory("lectureStartTime"));
				tclectureStartTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureEndTime = tableView.getColumns().get(5);
				tclectureEndTime.setCellValueFactory(new PropertyValueFactory("lectureEndTime"));
				tclectureEndTime.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureRoom = tableView.getColumns().get(6);
				tclectureRoom.setCellValueFactory(new PropertyValueFactory("lectureRoom"));
				tclectureRoom.setStyle("-fx-alignment: Center");
	
				TableColumn tclectureStudent = tableView.getColumns().get(7);
				tclectureStudent.setCellValueFactory(new PropertyValueFactory("lectureStudent"));
				tclectureStudent.setStyle("-fx-alignment: Center");
	
				tableView.setItems(lectureList);
	
			}
		} catch(NullPointerException e) {
			alert.setTitle("강의조회기준 미선택");
			alert.setHeaderText("조회기준을 확인하세요.");
			alert.setContentText("");
			alert.showAndWait();
		}
	}

	/**
	 * 강의선호도 도표로 이동한다. 
	 * @author 최재훈
	 * @param event
	 * @return
	 */
	public void joinChart(ActionEvent event) {
		Stage primaryStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/areri/lecture/view/LectureChartFx.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setTitle("강의선호도 도표");
			primaryStage.setScene(scene);
			primaryStage.show();				

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 강의정보조회 화면으로 이동한다.
	 * @author 최재훈
	 * @param event
	 * @return
	 */
	public void joinInfoHandlerBtn(ActionEvent event) {
		Stage primaryStage = new Stage();
		Parent root;
		try {
			pos = tableView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();

			name =tableView.getItems().get(row).getLecturName();
			room =tableView.getItems().get(row).getLectureRoom();
			plan =tableView.getItems().get(row).getLecturePlan();
			day =tableView.getItems().get(row).getLectureDay();
			root = FXMLLoader.load(getClass().getResource("/areri/lecture/view/LectureInfoFx.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setTitle("강의정보 조회");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			alert.setTitle("강의정보 미선택");
			alert.setHeaderText("선택한 강의정보가 없습니다.");
			alert.setContentText("");
			alert.showAndWait();
		}
	}

}
