/**
 *<pre>
 * aaa
 * Class Name : AdminSearchFxController
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import areri.admin.dao.LectureAdminDao;
import areri.admin.domain.SearchVO;
import areri.admin.controller.LectrueFxVO;
import areri.lecture.domain.LectureVO;
import javafx.application.Platform;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * <pre>
 * areri.admin.controller
 *    |_ AdminSearchFxController.java
 * 
 * </pre>
 * @date : 2019. 12. 13.
 * @version : 
 * @author : 신찬미
 */
public class AdminSearchFxController implements Initializable {
	@FXML
	Button openBtn;
	@FXML
	Button modBtn;
	@FXML
	Button delBtn;
	@FXML
	Button saveBtn;
	@FXML
	Button searchBtn;
	@FXML
	Button logoutBtn;
	@FXML
	Text userId;
	@FXML
	ComboBox<String> selectCombo;
	@FXML
	TableView<LectrueFxVO> tableView;
	@FXML
	ComboBox<String> selectSearchCombo;//
	@FXML
	TextField txtBox;//검색어
	
	LectureVO vo1;
	areri.lecture.domain.LectrueFxVO vo;
	LectureAdminDao dao;
	
	public static List<LectrueFxVO> list;
	public static ObservableList lectureList;
	
	public static String code;
	public static String pcode;
	public static String name;
	public static String plan;
	public static String day;
	public static String starttime;
	public static String endtime;
	public static String room;
	public static String student;
	public static String sum;
	public static String people;
	public static String avg;
	

	private Alert alert = new Alert(AlertType.INFORMATION);
	
	/**
	 * handler와 event연결
	 * @author : 신찬미
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		dao = new LectureAdminDao();
		list = new ArrayList<LectrueFxVO>();
		saveBtn.setOnAction(event -> saveHandlerBtn(event));
		searchBtn.setOnAction(event -> searchHandlerBtn(event));
		logoutBtn.setOnAction(event->logoutBtnAction(event));
				
		//comboBox
		selectCombo.getItems().addAll(
				"전체",
	            "전공",
	            "교양"  
	        );
		selectCombo.setValue("전체");
		
	
		
		//Data TableView로 불러오기
		for(LectureVO vo : LectureAdminDao.lecture) {
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
		LectureAdminDao.lectureTableViewList.add(tableVO);
	}
		//TableColumr -> LectrueFxVO
		TableColumn tclectureCode = tableView.getColumns().get(0);
		tclectureCode.setCellValueFactory(new PropertyValueFactory("lectureCode"));
		tclectureCode.setStyle("-fx-alignment: Center");

		TableColumn tclecturName = tableView.getColumns().get(1);
		tclecturName.setCellValueFactory(new PropertyValueFactory("lecturName"));
		tclecturName.setStyle("-fx-alignment: Center_Left");

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
		

		//DATA를 TABLEVIEW에 지정
		tableView.setItems(LectureAdminDao.lectureTableViewList);		
	}
		
	/**
	 *전체,전공,교양 검색 event
	 *@author : 신찬미
	 * @param 
	 */
	public void saveHandlerBtn(ActionEvent event) {
			SearchVO searchVO = new SearchVO();
			//System.out.println("btn Action");
			
			String selectData = selectCombo.getSelectionModel().getSelectedItem();
			//System.out.println("selectData"+selectData);	
			List<LectureVO> list = new ArrayList<LectureVO>();
			if(selectData.equals("전공")) {
				searchVO.setSearchDiv("1");
				list =(List<LectureVO>) dao.do_allmajircls(searchVO);	
			}else if(selectData.equals("교양")) {
				searchVO.setSearchDiv("2");
				list =(List<LectureVO>) dao.do_allmajircls(searchVO);		
			}else {
				//tableView.setItems(LectureAdminDao.lectureTableViewList);
				list = LectureAdminDao.lecture;
			}
			
			if(list.size()>0) {
				ObservableList allmajirclsList = FXCollections.observableArrayList();
				for(LectureVO vo:list) {
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
					//LectureAdminDao.lectureTableViewList.add(allmajirclsList);
					allmajirclsList.add(tableVO);
					
					}
				LectureAdminDao.lectureTableViewList = allmajirclsList;
				//data tableview애 저장
				tableView.setItems(LectureAdminDao.lectureTableViewList);
		
			}
	
	}
	
		
	/**
	 * 강의코드,강의명,요일 검색 event
	 * @author : 신찬미
	 * @param 
	 */
	
	public void searchHandlerBtn(ActionEvent event) {
		try {
			SearchVO searchVO = new SearchVO();
			//System.out.println("btn Action");
			
			String selectData = selectSearchCombo.getSelectionModel().getSelectedItem();
			//System.out.println("selectData"+selectData);
			
			String searchTxt = txtBox.getText();
			
			List<LectureVO> list = new ArrayList<LectureVO>();
			if(selectData.equals("강의코드")) {
				searchVO.setSearchDiv("1");
				searchVO.setSearchWord(searchTxt);
				list =(List<LectureVO>) dao.do_codenameday(searchVO);	
			}else if(selectData.equals("강의명")) {
				searchVO.setSearchDiv("2");
				searchVO.setSearchWord(searchTxt);
				list =(List<LectureVO>) dao.do_codenameday(searchVO);	
			}else if(selectData.equals("요일")) {
				searchVO.setSearchDiv("3");
				searchVO.setSearchWord(searchTxt);
				list =(List<LectureVO>) dao.do_codenameday(searchVO);	
			}else {
				list = LectureAdminDao.lecture;
				//tableView.setItems(LectureAdminDao.lectureTableViewList);
			}
			
			if(list.size()>0) {
				ObservableList codenamedayList = FXCollections.observableArrayList();
				for(LectureVO vo:list) {
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
					codenamedayList.add(tableVO);
					
					}
				
				LectureAdminDao.lectureTableViewList = codenamedayList;
				//data tableview에 저장
				tableView.setItems(LectureAdminDao.lectureTableViewList);
				}		
		}catch(NullPointerException e) {
			alert.setTitle("검색기준 미선택");
			alert.setHeaderText("검색기준을 확인하세요.");
			alert.setContentText("");
			alert.showAndWait();
		}
	}

	
	
	/**
	 * 로그아웃 event 
	 * @author : 신찬미
	 * @param 
	 */
	public void logoutBtnAction(ActionEvent event) {
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
	 * 강의개설 event 
	 * @author : 신찬미
	 * @param 
	 */
	public void openBtn(ActionEvent event) {
		Stage primaryStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/areri/admin/view/AdminLectureAdd.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
			primaryStage.setTitle("강의 개설");
			primaryStage.setScene(scene);
			primaryStage.show();

			
//	            Stage main = (Stage) joinBtn2.getScene().getWindow();
//	            main.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 강의수정 event 
	 * @author : 신찬미
	 * @param 
	 */
	public void modBtn(ActionEvent event)  {
		//System.out.println("mod button click");
		
		// 선택된 줄 데이터 idy로 받기
		  try {
			  int idy = tableView.getSelectionModel().getSelectedIndex();
			  if(idy == -1) {
				  	alert = new Alert(AlertType.INFORMATION);
				  	alert.setTitle("데이터 미선택");
					alert.setHeaderText("수정할 데이터를 선택 하세요.");
					alert.setContentText("");
					alert.showAndWait();
					return;
				}
			  
			//다이얼 로그 :Stage
			  Stage dialog = new Stage(StageStyle.UTILITY);
			  dialog.initModality(Modality.WINDOW_MODAL);//Parent 사용할수 없다.
			  dialog.initOwner(modBtn.getScene().getWindow());
			  dialog.setTitle("강의 수정");
		
			  Parent root;
			  //화면
			  URL url = getClass().getResource("/areri/admin/view/AdminLectureModi.fxml");
			  
			  FXMLLoader load = new FXMLLoader(url);
			  Parent parent=load.load();
			  AdminLectureModiController upCtrl = load.getController();
				//------------------------------------------------------
				//Event처리 
				//------------------------------------------------------
				//AdminLectureModiController에 데이터 전달
			  LectrueFxVO modd = tableView.getSelectionModel().getSelectedItem();
			  upCtrl.setMod(modd);
			  
			  Scene scene=new Scene(parent);
				scene.getStylesheets().add(getClass().getResource("/areri/user/controller/app.css").toString());
				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.show();
			  
			  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
		/**
		 * 강의삭제 event
		 * @author : 신찬미
		 * @param event
		 */
		public void delBtn(ActionEvent event) {
			
			// 선택된 줄 데이터 idx로 받기
			  int idx = tableView.getSelectionModel().getSelectedIndex();
			  if(idx == -1) {

				  	alert.setTitle("데이터 미선택");
					alert.setHeaderText("삭제할 데이터를 선택 하세요.");
					alert.setContentText("");
					alert.showAndWait();
					return;
				}
			  
			  alert = new Alert(AlertType.WARNING);
				alert.setTitle("강의 삭제");
				alert.setHeaderText("강의 삭제");
			  alert.setContentText("강의를 정말로 삭제 하시겠습니까?\n데이터는 복구되지 않습니다.");
				
			Optional<ButtonType> result = alert.showAndWait();
			//System.out.println("result.get():"+result.get() );
			if (result.get() == ButtonType.OK){
				Stage dialog =(Stage) delBtn.getScene().getWindow();
				//dialog.close();
			} 
		
			  
			 // System.out.println("idx:"+idx);
			  LectrueFxVO sel =this.tableView.getSelectionModel().getSelectedItem();
			  
			  LectureVO delVO=new LectureVO(sel.getLectureCode()
					  ,	sel.getLecturName(),
					  	sel.getProfessorCode(),
						sel.getLectureDay(),
						sel.getLectureStartTime(),
						sel.getLectureEndTime(),
						sel.getLectureRoom(),
						sel.getLectureStudent(),
						sel.getLecturePlan(),
						sel.getStarSum(),
						sel.getStarPeople(),
						sel.getStarAvg()
					  	);
			  dao.do_delete(delVO);
			  dao.saveFile(LectureAdminDao.FILE_PATH);
			  boolean flag = LectureAdminDao.lectureTableViewList.remove(sel);
			 // System.out.println("flag:"+flag);	
	}
}

