/**
 *<pre>
 * areri.lecture.controller
 * Class Name : LectureChartFxController
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import areri.lecture.dao.LectureDao;
import areri.lecture.domain.LectrueFxVO;
import areri.lecture.domain.LectureVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

import javafx.scene.control.Button;
import javafx.stage.Stage;



/**
 * <pre>
 * areri.lecture.controller
 *    |_ LectureChartFxController.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 4:11:48
 * @version : 
 * @author : 최재훈
 */
public class LectureChartFxController implements Initializable {
	@FXML Button exitBtn;
	@FXML PieChart pieChart;
	
	LectrueFxVO vo;
	List<LectrueFxVO> list;
	LectureDao dao;
	
	/**
	 * initialize 
	 * 파이차트 - 과별로 수강인기과목 5개 출력해서 보여준다.
	 * @author 최재훈
	 * @param initialize,resources
	 * @return
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new LectureDao();
		vo = dao.getSearchData();
		
		list = (List<LectrueFxVO>) dao.do_StarAvg(vo);		//평균별점 호출후 리스트에 등록
		List<Data> pieData = new ArrayList<Data>();
		
		pieData.add(new PieChart.Data(list.get(0).getLecturName(), Double.valueOf(list.get(0).getStarAvg())*10));
		pieData.add(new PieChart.Data(list.get(1).getLecturName(), Double.valueOf(list.get(1).getStarAvg())*10));
		pieData.add(new PieChart.Data(list.get(2).getLecturName(), Double.valueOf(list.get(2).getStarAvg())*10));
		pieData.add(new PieChart.Data(list.get(3).getLecturName(), Double.valueOf(list.get(3).getStarAvg())*10));
		pieData.add(new PieChart.Data(list.get(4).getLecturName(), Double.valueOf(list.get(4).getStarAvg())*10));
		
		ObservableList<Data> pieList = FXCollections.observableArrayList(pieData);
		pieChart.setData(pieList);
		
		
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
