/**

 *<pre>
 * areri.lecture.dao
 * Class Name : lectureVO.java
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



/**
 * <pre>
 ** areri.lecture.dao 
 *    |_ lectureVO.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 4:11:48
 * @version : 
 * @author : 최재훈
 */
public class LectureVO extends DTO implements Comparator{
	private String lectureCode; 	//강의코드
	private String lecturName;		//강의명
	private String professorCode;	//교수번호
	private String lectureDay;		//요일
	private String lectureStartTime;//시작시간
	private String lectureEndTime;	//끝시간
	private String lectureRoom;		//강의실
	private String lectureStudent;	//신청인원
	private String lecturePlan;		//강의계획서
	private String starSum;			//별점합
	private String starPeople;		//별점누적인원
	private String starAvg;			//평균별점
	
	public LectureVO() {}
	

	public LectureVO(String lectureCode, String lecturName, String professorCode, String lectureDay,
			String lectureStartTime, String lectureEndTime, String lectureRoom, String lectureStudent,
			String lecturePlan, String starSum, String starPeople, String starAvg) {
		super();
		this.lectureCode = lectureCode;
		this.lecturName = lecturName;
		this.professorCode = professorCode;
		this.lectureDay = lectureDay;
		this.lectureStartTime = lectureStartTime;
		this.lectureEndTime = lectureEndTime;
		this.lectureRoom = lectureRoom;
		this.lectureStudent = lectureStudent;
		this.lecturePlan = lecturePlan;
		this.starSum = starSum;
		this.starPeople = starPeople;
		this.starAvg = starAvg;
	}

	/**
	 * @return the lectureCode
	 */
	public String getLectureCode() {
		return lectureCode;
	}

	/**
	 * @param lectureCode the lectureCode to set
	 */
	public void setLectureCode(String lectureCode) {
		this.lectureCode = lectureCode;
	}

	/**
	 * @return the lecturName
	 */
	public String getLecturName() {
		return lecturName;
	}

	/**
	 * @param lecturName the lecturName to set
	 */
	public void setLecturName(String lecturName) {
		this.lecturName = lecturName;
	}

	/**
	 * @return the professorCode
	 */
	public String getProfessorCode() {
		return professorCode;
	}

	/**
	 * @param professorCode the professorCode to set
	 */
	public void setProfessorCode(String professorCode) {
		this.professorCode = professorCode;
	}

	/**
	 * @return the lectureDay
	 */
	public String getLectureDay() {
		return lectureDay;
	}

	/**
	 * @param lectureDay the lectureDay to set
	 */
	public void setLectureDay(String lectureDay) {
		this.lectureDay = lectureDay;
	}

	/**
	 * @return the lectureStartTime
	 */
	public String getLectureStartTime() {
		return lectureStartTime;
	}

	/**
	 * @param lectureStartTime the lectureStartTime to set
	 */
	public void setLectureStartTime(String lectureStartTime) {
		this.lectureStartTime = lectureStartTime;
	}

	/**
	 * @return the lectureEndTime
	 */
	public String getLectureEndTime() {
		return lectureEndTime;
	}

	/**
	 * @param lectureEndTime the lectureEndTime to set
	 */
	public void setLectureEndTime(String lectureEndTime) {
		this.lectureEndTime = lectureEndTime;
	}

	/**
	 * @return the lectureRoom
	 */
	public String getLectureRoom() {
		return lectureRoom;
	}

	/**
	 * @param lectureRoom the lectureRoom to set
	 */
	public void setLectureRoom(String lectureRoom) {
		this.lectureRoom = lectureRoom;
	}

	/**
	 * @return the lectureStudent
	 */
	public String getLectureStudent() {
		return lectureStudent;
	}

	/**
	 * @param lectureStudent the lectureStudent to set
	 */
	public void setLectureStudent(String lectureStudent) {
		this.lectureStudent = lectureStudent;
	}

	/**
	 * @return the lecturePlan
	 */
	public String getLecturePlan() {
		return lecturePlan;
	}

	/**
	 * @param lecturePlan the lecturePlan to set
	 */
	public void setLecturePlan(String lecturePlan) {
		this.lecturePlan = lecturePlan;
	}

	/**
	 * @return the starSum
	 */
	public String getStarSum() {
		return starSum;
	}

	/**
	 * @param starSum the starSum to set
	 */
	public void setStarSum(String starSum) {
		this.starSum = starSum;
	}

	/**
	 * @return the starPeople
	 */
	public String getStarPeople() {
		return starPeople;
	}

	/**
	 * @param starPeople the starPeople to set
	 */
	public void setStarPeople(String starPeople) {
		this.starPeople = starPeople;
	}

	/**
	 * @return the starAvg
	 */
	public String getStarAvg() {
		return starAvg;
	}

	/**
	 * @param starAvg the starAvg to set
	 */
	public void setStarAvg(String starAvg) {
		this.starAvg = starAvg;
	}


	

	@Override
	public String toString() {
		return "LectureVO [lectureCode=" + lectureCode + ", lecturName=" + lecturName + ", professorCode="
				+ professorCode + ", lectureDay=" + lectureDay + ", lectureStartTime=" + lectureStartTime
				+ ", lectureEndTime=" + lectureEndTime + ", lectureRoom=" + lectureRoom + ", lectureStudent="
				+ lectureStudent + ", lecturePlan=" + lecturePlan + ", starSum=" + starSum + ", starPeople="
				+ starPeople + ", starAvg=" + starAvg + "]";
	}


	@Override
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Comparable && o2 instanceof Comparable) {
			Comparable c1 = (Comparable) o1;
			Comparable c2 = (Comparable) o2;
			
			//정렬의 방식을 역정렬로 변경   (-1을 곱하므로)
			return c1.compareTo(c2)*(-1); 			//1로바꾸면 asc
		}
		return -1; 									//1로바꾸면 asc
	}

	
	
	
}
