/**
 *<pre>
 * areri.user.domain
 * Class Name : StudentVO
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-05           최초생성
 *
 * @author 개발프레임웍크 실행환경 ARERI
 * @since 2019-12-05 
 * @version 1.0
 * 
 *
 *  Copyright (C) by ARERI All right reserved.
 * </pre>
 */
package areri.user.domain;

import areri.cmn.DTO;

/**
 * <pre>
 * areri.user.domain 
 *    |_ StudentVO.java
 * 
 * </pre>
 * @date : 2019. 12. 5. 오후 5:42:43
 * @version : 
 * @author : 박지수
 */
public class StudentVO extends DTO {
	
	/**ID*/
	private String studentID;
	
	/**비밀번호*/
	private String studentPW;
	
	/**비밀번호 확인*/
	private String studentPW2;
	
	/**이름*/
	private String studentName;
	
	/**Email*/
	private String studentEmail;
	
	public StudentVO() {}

	/**
	 * @param studentID
	 * @param studentPW
	 * @param studentPW2
	 * @param studentName
	 * @param studentEmail
	 */
	public StudentVO(String studentID, String studentPW, String studentPW2, String studentName, String studentEmail) {
		super();
		this.studentID = studentID;
		this.studentPW = studentPW;
		this.studentPW2 = studentPW2;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
	}
	
	/**
	 * @param studentID
	 */
	public StudentVO(String studentID) {
		super();
		this.studentID = studentID;
	}


	/**
	 * @param studentID
	 * @param studentPW
	 */
	public StudentVO(String studentID, String studentPW) {
		super();
		this.studentID = studentID;
		this.studentPW = studentPW;
	}


	/**
	 * @param studentID
	 * @param studentPW2
	 * @param studentEmail
	 */
	public StudentVO(String studentID, String studentEmail, String studentPW2) {
		super();
		this.studentID = studentID;
		this.studentEmail = studentEmail;
		this.studentPW2 = studentPW2;
	}

	/**
	 * @param studentID
	 * @param studentPW
	 * @param studentName
	 * @param studentEmail
	 */
	public StudentVO(String studentID, String studentPW, String studentName, String studentEmail) {
		super();
		this.studentID = studentID;
		this.studentPW = studentPW;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStudentPW() {
		return studentPW;
	}

	public void setStudentPW(String studentPW) {
		this.studentPW = studentPW;
	}

	public String getStudentPW2() {
		return studentPW2;
	}

	public void setStudentPW2(String studentPW2) {
		this.studentPW2 = studentPW2;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	@Override
	public String toString() {
		return "StudentVO [studentID=" + studentID + ", studentPW=" + studentPW + ", studentPW2=" + studentPW2
				+ ", studentName=" + studentName + ", studentEmail=" + studentEmail + "]";
	}


	
}
