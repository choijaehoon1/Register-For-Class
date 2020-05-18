/**
 *<pre>
 * areri.userpage.domain
 * Class Name : PutLectureVO
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
package areri.userpage.domain;

import areri.cmn.DTO;

/**
 * <pre>
 * areri.userpage.domain
 *    |_ PutLectureVO.java
 * 
 * </pre>
 * @date : 2019. 12. 18.
 * @version : 
 * @author : 주정현
 */
public class PutLectureVO extends DTO {
	
	/**수강장바구니 ID*/
	private String studentId;
	
	/**수강장바구니 강의*/
	private String lectureCode;
	
	public PutLectureVO() {}
	
	public PutLectureVO(String studentId) {
		super();
		this.studentId = studentId;
	}
	/**
	 * @param studentId
	 * @param lectureCode
	 */
	public PutLectureVO(String studentId, String lectureCode) {
		super();
		this.studentId = studentId;
		this.lectureCode = lectureCode;
	}
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	@Override
	public String toString() {
		return studentId + "," + lectureCode;
	}
	
	
	
}
