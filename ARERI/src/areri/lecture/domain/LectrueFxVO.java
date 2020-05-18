/**
 *<pre>
 * areri.lecture.dao
 * Class Name : lectureFxVO.java
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
package areri.lecture.domain;

import java.util.Comparator;

import areri.cmn.DTO;
import javafx.beans.property.SimpleStringProperty;

/**
 * <pre>
 ** areri.lecture.dao 
 *    |_ lectureFxVO.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 4:11:48
 * @version : 
 * @author : 최재훈
 */
public class LectrueFxVO extends DTO implements Comparator{
	private SimpleStringProperty lectureCode; 	//강의코드
	private SimpleStringProperty lecturName;		//강의명
	private SimpleStringProperty professorCode;	//교수번호
	private SimpleStringProperty lectureDay;		//요일
	private SimpleStringProperty lectureStartTime;//시작시간
	private SimpleStringProperty lectureEndTime;	//끝시간
	private SimpleStringProperty lectureRoom;		//강의실
	private SimpleStringProperty lectureStudent;	//신청인원
	private SimpleStringProperty lecturePlan;		//강의계획서
	private SimpleStringProperty starSum;			//별점합
	private SimpleStringProperty starPeople;		//별점누적인원
	private SimpleStringProperty starAvg;			//평균별점
	
	public LectrueFxVO(){
		lectureCode = new SimpleStringProperty();
		lecturName = new SimpleStringProperty();
		professorCode = new SimpleStringProperty();
		lectureDay = new SimpleStringProperty();
		lectureStartTime = new SimpleStringProperty();
		lectureEndTime = new SimpleStringProperty();
		lectureRoom = new SimpleStringProperty();
		lectureStudent = new SimpleStringProperty();
		lecturePlan = new SimpleStringProperty();
		starSum = new SimpleStringProperty();
		starPeople = new SimpleStringProperty();
		starAvg = new SimpleStringProperty();
	}
	
	public LectrueFxVO(String lectureCode, String lecturName, String professorCode, 
			String lectureDay,String lectureStartTime,String lectureEndTime,String lectureRoom,
			String lectureStudent,String lecturePlan,String starSum,String starPeople,
			String starAvg) {
		this.lectureCode = new SimpleStringProperty(lectureCode);
		this.lecturName = new SimpleStringProperty(lecturName);
		this.professorCode = new SimpleStringProperty(professorCode);
		this.lectureDay = new SimpleStringProperty(lectureDay);
		this.lectureStartTime = new SimpleStringProperty(lectureStartTime);
		this.lectureEndTime = new SimpleStringProperty(lectureEndTime);
		this.lectureRoom = new SimpleStringProperty(lectureRoom);
		this.lectureStudent = new SimpleStringProperty(lectureStudent);
		this.lecturePlan = new SimpleStringProperty(lecturePlan);
		this.starSum = new SimpleStringProperty(starSum);
		this.starPeople = new SimpleStringProperty(starPeople);
		this.starAvg = new SimpleStringProperty(starAvg);
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Comparable && o2 instanceof Comparable) {
			Comparable c1 = (Comparable) o1;
			Comparable c2 = (Comparable) o2;
			
			//정렬의 방식을 역정렬로 변경   (-1을 곱하므로)
			return c1.compareTo(c2)*(-1); 			//1로바꾸면 asc
		}
		return -1; 			
	}

	/**
	 * @return the lectureCode
	 */
	public String getLectureCode() {
		return lectureCode.get();
	}

	/**
	 * @param lectureCode the lectureCode to set
	 */
	public void setLectureCode(String lectureCode) {
		this.lectureCode.set(lectureCode);
	}

	/**
	 * @return the lecturName
	 */
	public String getLecturName() {
		return lecturName.get();
	}

	/**
	 * @param lecturName the lecturName to set
	 */
	public void setLecturName(String lecturName) {
		this.lecturName.set(lecturName);
	}

	/**
	 * @return the professorCode
	 */
	public String getProfessorCode() {
		return professorCode.get();
	}

	/**
	 * @param professorCode the professorCode to set
	 */
	public void setProfessorCode(String professorCode) {
		this.professorCode.set(professorCode);
	}

	/**
	 * @return the lectureDay
	 */
	public String getLectureDay() {
		return lectureDay.get();
	}

	/**
	 * @param lectureDay the lectureDay to set
	 */
	public void setLectureDay(String lectureDay) {
		this.lectureDay.set(lectureDay);
	}

	/**
	 * @return the lectureStartTime
	 */
	public String getLectureStartTime() {
		return lectureStartTime.get();
	}

	/**
	 * @param lectureStartTime the lectureStartTime to set
	 */
	public void setLectureStartTime(String lectureStartTime) {
		this.lectureStartTime.set(lectureStartTime);
	}

	/**
	 * @return the lectureEndTime
	 */
	public String getLectureEndTime() {
		return lectureEndTime.get();
	}

	/**
	 * @param lectureEndTime the lectureEndTime to set
	 */
	public void setLectureEndTime(String lectureEndTime) {
		this.lectureEndTime.set(lectureEndTime);
	}

	/**
	 * @return the lectureRoom
	 */
	public String getLectureRoom() {
		return lectureRoom.get();
	}

	/**
	 * @param lectureRoom the lectureRoom to set
	 */
	public void setLectureRoom(String lectureRoom) {
		this.lectureRoom.set(lectureRoom);
	}

	/**
	 * @return the lectureStudent
	 */
	public String getLectureStudent() {
		return lectureStudent.get();
	}

	/**
	 * @param lectureStudent the lectureStudent to set
	 */
	public void setLectureStudent(String lectureStudent) {
		this.lectureStudent.set(lectureStudent);
	}

	/**
	 * @return the lecturePlan
	 */
	public String getLecturePlan() {
		return lecturePlan.get();
	}

	/**
	 * @param lecturePlan the lecturePlan to set
	 */
	public void setLecturePlan(String lecturePlan) {
		this.lecturePlan.set(lecturePlan);
	}

	/**
	 * @return the starSum
	 */
	public String getStarSum() {
		return starSum.get();
	}

	/**
	 * @param starSum the starSum to set
	 */
	public void setStarSum(String starSum) {
		this.starSum.set(starSum);
	}

	/**
	 * @return the starPeople
	 */
	public String getStarPeople() {
		return starPeople.get();
	}

	/**
	 * @param starPeople the starPeople to set
	 */
	public void setStarPeople(String starPeople) {
		this.starPeople.set(starPeople);
	}

	/**
	 * @return the starAvg
	 */
	public String getStarAvg() {
		return starAvg.get();
	}

	/**
	 * @param starAvg the starAvg to set
	 */
	public void setStarAvg(String starAvg) {
		this.starAvg.set(starAvg);
	}

	@Override
	public String toString() {
		return "LectrueFxVO [lectureCode=" + lectureCode + ", lecturName=" + lecturName + ", professorCode="
				+ professorCode + ", lectureDay=" + lectureDay + ", lectureStartTime=" + lectureStartTime
				+ ", lectureEndTime=" + lectureEndTime + ", lectureRoom=" + lectureRoom + ", lectureStudent="
				+ lectureStudent + ", lecturePlan=" + lecturePlan + ", starSum=" + starSum + ", starPeople="
				+ starPeople + ", starAvg=" + starAvg + ", toString()=" + super.toString() + "]";
	}

	
}
