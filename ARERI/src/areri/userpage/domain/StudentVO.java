/**
 *<pre>
 * areri.userpage.domain
 * Class Name : StudentVO
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
 *areri.userpage.domain
 *    |_ StudentVO.java
 * 
 * </pre>
 * @date : 2019. 12. 18.
 * @version : 
 * @author : 주정현
 */
public class StudentVO extends DTO{
	private String Id;
	private String password;
	private String name;
	private String email;
	
	public StudentVO() {}

	/**
	 * @param id
	 * @param password
	 * @param name
	 * @param email
	 */
	public StudentVO(String id, String password, String name, String email) {
		super();
		Id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	/**
	 * @return 학생 아이디
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param 학생 아이디 입력
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return 비밀번호
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param 비밀번호 반환
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return 이름
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param 이름반환
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 이메일
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param 이메일 반환
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "StudentVO:학생정보 [Id=" + Id + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
