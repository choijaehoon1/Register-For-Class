/**
 *<pre>
 * aaa
 * Class Name : SearchVO
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
package areri.admin.domain;

import areri.cmn.DTO;

/**
 * <pre>
 * areri.admin.domain
 *    |_ SearchVO.java
 * 
 * </pre>
 * @date : 2019. 12. 18.
 * @version : 
 * @author : myyouthissivan
 */
public class SearchVO extends DTO {


		/**
		 * 검색구분
		 * @author : myyouthissivan
		 */
		private String searchDiv;

		
		/**
		 * 검색어
		 * @author : myyouthissivan
		 */
		private String searchWord;
		
		public SearchVO() {}
		
		/**
		 * @author : myyouthissivan
		 * @param searchDiv
		 * @param searchWord
		 */
		public SearchVO(String searchDiv, String searchWord) {
			super();
			this.searchDiv = searchDiv;
			this.searchWord = searchWord;
		}
		
		/**
		 *  @author : myyouthissivan
		 *  @return the searchDiv
		 */
		public String getSearchDiv() {
			return searchDiv;
		}

		/** 
		 * @author : myyouthissivan
		 *  @param searchDiv the searchDiv to set
		 */
		public void setSearchDiv(String searchDiv) {
			this.searchDiv = searchDiv;
		}

		/**
		 *  @author : myyouthissivan
		 * @return the searchWord
		 */
		public String getSearchWord() {
			return searchWord;
		}

		/** 
		 * @author : myyouthissivan
		 * @param searchWord the searchWord to set
		 */
		public void setSearchWord(String searchWord) {
			this.searchWord = searchWord;
		}
		
		
}
