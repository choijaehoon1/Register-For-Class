/**
 *<pre>
 * areri.lecture.dao
 * Class Name : WorkDiv.java
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

import java.util.List;

import areri.lecture.domain.LectureVO;



/**
 * <pre>
 * areri.lecture.dao 
 *    |_ WorkDiv.java
 * 
 * </pre>
 * @date : 2019. 12. 5. 오후 5:06:04
 * @version : 
 * @author : sist128
 */
public interface WorkDiv {
	/**
	 * 등록기능을 구현하세요.
	 * @param dto
	 * 등록성공 : 1, 실패: 1이외의 값
	 * @return
	 */
	public abstract int do_save(DTO dto);				
	/**
	 * 수정기능을 구현하세요.
	 * @param dto
	 * 등록성공 : 1, 실패: 1이외의 값
	 * @return
	 */
	public abstract int do_update(DTO dto);		
	/**
	 * 삭제기능을 구현하세요.
	 * @param dto
	 * 등록성공 : 1, 실패: 1이외의 값
	 * @return
	 */
	public abstract int do_delete(DTO dto);		
	/**
	 * 단건조회을 구현하세요.
	 * @param dto
	 * @return DTO
	 */
	public abstract DTO do_selectOne(DTO dto);		
	/**
	 * 목록조회을 구현하세요.
	 * @param dto
	 * @return List<DTO>
	 */
	public abstract List<?> do_retrieve(DTO dto);
	/**
	 * @param dto
	 * @return
	 */
}
