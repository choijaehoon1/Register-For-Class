/**
 *<pre>
 * areri.lecture.dao
 * Class Name : DTO.java
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
package areri.cmn;

/**
 *<pre>
 * areri.lecture.dao
 * Class Name : DTO.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-05           최초생성
 *
 * @author 개발프레임웍크 실행환경 ARERI
 * @since 2019-12-06 
 * @version 1.0
 * 
 *
 *  Copyright (C) by ARERI All right reserved.
 * </pre>
 */

public class DTO {
	/** 글 번호 */
	private String no;
	
	/**검색구분*/
	private String searchDiv;
	
	/**검색어*/
	private String searchWord;
	
	
	
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}


	/**
	 * @return the searchDiv
	 */
	public String getSearchDiv() {
		return searchDiv;
	}

	/**
	 * @param searchDiv the searchDiv to set
	 */
	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	/**
	 * @return the searchWord
	 */
	public String getSearchWord() {
		return searchWord;
	}

	/**
	 * @param searchWord the searchWord to set
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * Method Name : toString
	 * @since : 2019. 12. 6.
	 * @author : sist
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		sist				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see java.lang.Object#toString()
	 * @return
	 */ 	
	@Override
	public String toString() {
		return "DTO [no=" + no + ", searchDiv=" + searchDiv + ", searchWord=" + searchWord + "]";
	}



}

