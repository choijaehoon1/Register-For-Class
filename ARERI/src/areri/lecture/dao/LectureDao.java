/**
 *<pre>
 * areri.lecture.dao
 * Class Name : lectureDao.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2019-12-11           최초생성
 *
 * @author 개발프레임웍크 실행환경 ARERI
 * @since 2019-12-05 
 * @version 1.0
 * 
 *
 *  Copyright (C) by ARERI All right reserved.
 * </pre>
 */

package areri.lecture.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


import areri.cmn.DTO;
import areri.cmn.WorkDiv;
import areri.lecture.domain.LectrueFxVO;

import areri.lecture.domain.LectureVO;
import areri.user.dao.UserDao;

import areri.user.controller.LoginController;



/**
 * <pre>
 * areri.lecture.dao 
 *    |_ lectureDao.java
 * 
 * </pre>
 * @date : 2019. 12. 11. 오후 4:11:48
 * @version : 
 * @author : 최재훈
 */
public class LectureDao implements WorkDiv,Comparator<LectureVO>{
	private List<LectrueFxVO> lectureBook = new ArrayList<LectrueFxVO>();
	/**전체강의정보 csv파일*/
	//private final String ADD_FILE = "src\\areri\\lecture\\dao\\lecture.csv";
	private final String ADD_FILE = "C:\\Users\\sist\\git\\Register-For-Class\\ARERI\\lecture.csv";
	/**개별 사용자 아이디*/
	public String student_ID = LoginController.studentID;
	//public String student_ID ="201922456";//임시 학번
	/**개별 사용자 이름*/
	public String student_NAME = LoginController.studentNAME;
	//public String student_NAME = "홍길동";//임시 이름
	
	private final String ADD_Professor_FILE = "src\\areri\\lecture\\dao\\professor.csv";
	
	/**
	 *	생성자로 CSV파일 Read
	 */
	public LectureDao() {
		lectureBook = readFile(ADD_FILE);
	}
	
		
	/**
	 * 강의 정보조회 
	 * 개인 로그인후에 강의요일로 조회
	 * @author 최재훈
	 * @param dto
	 * @return List<?>
	 */
	public List<?> do_searchDay(DTO dto){
		List<LectrueFxVO> list = new ArrayList<LectrueFxVO>();
		LectrueFxVO inVO = (LectrueFxVO) dto;
		
		for(int i=0; i<lectureBook.size();i++) {
			LectrueFxVO vsVO = lectureBook.get(i);
			String major_code=vsVO.getLectureCode().substring(0, 2);//강의코드2개(전공)
			
			if(major_code.equals(inVO.getSearchWord())|| major_code.equals("99")) {
				if(vsVO.getLectureDay().matches(".*" +inVO.getSearchDiv() +".*")) {
					list.add(vsVO);
				}
			}
		}
		
		return list;
	}
	
	
	/**
	 * 강의 정보조회 
	 * 개인 로그인후에 강의코드로 조회
	 * @author 최재훈
	 * @param dto
	 * @return List<?>
	 */
	public List<?> do_searchCode(DTO dto){
		List<LectrueFxVO> list = new ArrayList<LectrueFxVO>();
		LectrueFxVO inVO = (LectrueFxVO) dto;
		
		for(int i=0; i<lectureBook.size();i++) {
			LectrueFxVO vsVO = lectureBook.get(i);
			String major_code=vsVO.getLectureCode().substring(0, 2);//강의코드2개(전공)
			
			if(major_code.equals(inVO.getSearchWord())|| major_code.equals("99")) {
				if(vsVO.getLectureCode().matches(inVO.getSearchDiv() +".*")) {
					list.add(vsVO);
				}
			}
		}
		
		return list;
	}
	

	/**
	 * CSV파일에 기록
	 * 강의정보의 내용을 CSV파일에 저장한다.
	 * @author 최재훈
	 * @param path
	 * @return int
	 */
	public int saveFile(String path) {
		int cnt = 0;
		FileWriter writer =null;
		BufferedWriter bw = null;
		File file = new File(path);

		try {
			writer = new FileWriter(file);
			bw     = new BufferedWriter(writer);
			
			
			for(int i=0;i<lectureBook.size();i++) {
				LectrueFxVO vo =lectureBook.get(i);
				StringBuilder sb = new StringBuilder(200);
				sb.append(vo.getLectureCode() + ",");
				sb.append(vo.getLecturName() + ",");
				sb.append(vo.getProfessorCode() + ",");
				sb.append(vo.getLectureDay() + ",");
				sb.append(vo.getLectureStartTime() + ",");
				sb.append(vo.getLectureEndTime() + ",");
				sb.append(vo.getLectureRoom() + ",");
				sb.append(vo.getLectureStudent()  + ",");
				sb.append(vo.getLecturePlan() + ",");
				sb.append(vo.getStarSum() + ",");
				sb.append(vo.getStarPeople() + ",");
				sb.append(vo.getStarAvg());
				//마지막 라인에 "\n"제거
				if((i+1) != lectureBook.size()) {
					sb.append("\n");
				}
				cnt++;
				bw.write(sb.toString());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
	    } finally {
	    	if(bw!=null) {
	    		try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
	
		return cnt; 		
	}
	
	
	
	/**
	 * 전공번호에 따라 학과전공조회(전공 + 교양)
	 * ex)전공 앞자리 2개11은  일본어학과, 전공 앞자리 2개 22 는 전자공학과 
	 *    개인사용자학번의 중간2자리가 학고번호이므로 잘라서 사용
	 * ex)학번:201922456일때 22가 학과번호가 됨(전자공학과)
	 * @author 최재훈
	 * @param 
	 * @return LectrueFxVO
	 */
	public LectrueFxVO getSearchData(){
		LectrueFxVO outVO = null;
		outVO = new LectrueFxVO();
		String user_major = student_ID.trim().substring(4, 6); 		//전공으로 구분키로 입력
		outVO.setSearchWord(user_major); 							
		return outVO;
	}
	
	
	/**
	 * 학과(User)별 이름으로 전공검색, 교양도 같이 출력한다.
	 * 이름으로 강의를 검색한다.  단 교양강의의 이름이 전공이름과 비슷한부분이 있을 수 있으므로 교양도 같이 검색한다.
	 * ex)전공=일본어Level1, 교양=일본사회의 이해    
	 * 	    일본만 검색시 전굥,교양 이름같은거 다 출력
	 * @author 최재훈
	 * @param dto
	 * @return List<?>
	 */
	public List<?> do_searchName(DTO dto){
		List<LectrueFxVO> list = new ArrayList<LectrueFxVO>();
		LectrueFxVO inVO = (LectrueFxVO) dto;
		
		for(int i=0; i<lectureBook.size();i++) {
			LectrueFxVO vsVO = lectureBook.get(i);
			String major_code=vsVO.getLectureCode().substring(0, 2); 	//강의코드2개(전공)
			
			if(major_code.equals(inVO.getSearchWord())|| major_code.equals("99")) {
				if(vsVO.getLecturName().matches(".*" +inVO.getSearchDiv() +".*")) {
					list.add(vsVO);
				}
			}
		}
		
		return list;
	}
	
	
	
	/**
	 * 교양 강의조회
	 * 교양은 모든학과에서 공통이므르 있는 교양들 전체 출력굥,교양 이름같은거 다 출력
	 * @author 최재훈
	 * @param dto
	 * @return List<?>
	 */
	public List<?> do_liberal(DTO dto){
		List<LectrueFxVO> list = new ArrayList<LectrueFxVO>();
		LectrueFxVO inVO = (LectrueFxVO) dto;
		for(int i=0; i<lectureBook.size();i++) {
			LectrueFxVO vsVO = lectureBook.get(i);
			String major_code=vsVO.getLectureCode().substring(0, 2); 	//강의코드2개(전공)
			
			if(major_code.equals("99")) {
				list.add(vsVO);
			}
		}
		
		return list;
	}
	

	
	/**
	 * 학과(User)별 전공조회
	 * 개인사용자학번의 중간2자리가 학고번호이므로 잘라서 사용
	 * ex)학번:201922456일때 22가 학과번호가 됨(전자공학과)
	 * @author 최재훈
	 * @param dto
	 * @return List<?>
	 */
	public List<?> do_major(DTO dto){
		List<LectrueFxVO> list = new ArrayList<LectrueFxVO>();
		LectrueFxVO inVO = (LectrueFxVO) dto;
		for(int i=0; i<lectureBook.size();i++) {
			LectrueFxVO vsVO = lectureBook.get(i);
			String major_code=vsVO.getLectureCode().substring(0, 2); 	//강의코드2개(전공)
			
			if(major_code.equals(inVO.getSearchWord())) {
				list.add(vsVO);
			}
		}
		
		return list;
	}
	
	
	/**
	 * 별점평균(별잡합/누적인원)이 높은 5개 과목출력
	 * 개인 로그인 후 학과별 별접평균이 높은 5개 과목 출력함으로써 인기과목 5개를 출력
	 * ex)학번:201922456일때 22가 학과번호가 됨(전자공학과)
	 * @author 최재훈
	 * @param dto
	 * @return List<?>
	 */
	public List<?> do_StarAvg(DTO dto){ 
		List<LectrueFxVO> list = new ArrayList<LectrueFxVO>();
		LectrueFxVO inVO = (LectrueFxVO) dto;
		for(int i=0; i<lectureBook.size();i++) {
			LectrueFxVO vsVO = lectureBook.get(i);
			String major_code=vsVO.getLectureCode().substring(0, 2); 	//강의코드2개(전공)
			
			if(major_code.equals(inVO.getSearchWord())) {				
				list.add(vsVO);
			}
		}
		
		Collections.sort(list, new Comparator<LectrueFxVO>() {
			
			
			@Override
			public int compare(LectrueFxVO o1, LectrueFxVO o2) {
				if((int)((Double.valueOf(o1.getStarAvg())*100/10))>(int)((Double.valueOf(o2.getStarAvg())*100/10))) {
					return -1;
				} else if((int)((Double.valueOf(o1.getStarAvg())*100/10))<(int)((Double.valueOf(o2.getStarAvg())*100/10))) {
					return 1;
				} else {
					return 0;
					
				}
			}
			
		});
		List<LectrueFxVO> list1 = list.subList(0,5);
		
		return list1;
	}
	

	/**
	 * csv 파일을 읽기
	 * CSV파일을 읽는다.(테이블 뷰에 바로 적용하기위하여 FxVO형태로 csv파일을 읽는다) 
	 * @author 최재훈
	 * @param dto
	 * @return List<LectrueFxVO>
	 */
	public List<LectrueFxVO> readFile(String filePath){
		List<LectrueFxVO> lectureData = new ArrayList<LectrueFxVO>();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line ="";
			while((line=br.readLine()) != null) {
				
				String[] dataArray =line.split(",");
				LectrueFxVO vo = new LectrueFxVO(dataArray[0],dataArray[1],dataArray[2],dataArray[3],
						dataArray[4],dataArray[5],dataArray[6],dataArray[7],dataArray[8],
						dataArray[9],dataArray[10],dataArray[11]);
				lectureData.add(vo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return lectureData;
	}
	
	

	/**
	 * CSV파일에 저장
	 * 수강인원을 1증가시켜 lecture.csv파일에 저장한다.
	 * @author 최재훈
	 * @param dto
	 * @return int
	 */
	@Override
	public int do_save(DTO dto) {
		int flag =0;
		LectrueFxVO vo =(LectrueFxVO)dto; 		
		int index=vo.getLectureStudent().indexOf("/");
		String currentPerson =vo.getLectureStudent().substring(0, index);
		String fullPerson = vo.getLectureStudent().substring(index+1);
		String AfterPerson = String.valueOf(Integer.parseInt(currentPerson)+1);

		vo.setLectureStudent(AfterPerson + "/" +fullPerson);
		lectureBook.add(vo);
		int saveCnt = saveFile(ADD_FILE); 	
		return flag;
	}


	@Override
	public int do_update(DTO dto) {
		return 0;
	}


	
	/**
	 * List<LectrueFxVO>에서 삭제
	 * List<LectrueFxVO>에서 강의코드가 같으면 삭제한다.
	 * @author 최재훈
	 * @param dto
	 * @return int
	 */
	@Override
	public int do_delete(DTO dto) {
		LectrueFxVO inVO = (LectrueFxVO) dto;
		int flag = 0;
		for(int i=lectureBook.size()-1;i>=0; i--) {
			LectrueFxVO vsVO = lectureBook.get(i); 		//꺼내서 담기
			if(vsVO.getLectureCode().equals(inVO.getLectureCode())) {
				lectureBook.remove(i);
				flag++;
				break;
			}
		}
		return flag;	//지워지는 개수
	}

	
	@Override
	public DTO do_selectOne(DTO dto) {
		return null;
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 학과(User)별 강의조회(전공 + 교양)
	 * 2. 처리내용 : 학과별로 전체강의를 조회한다.(전공과 교양이 같이 출력된다.)
	 * </pre>
	 * @Method Name : do_retrieve
	 * @date : 2019. 12. 20.
	 * @author : sist128
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 20.		sist128				최종 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see areri.cmn.WorkDiv#do_retrieve(areri.cmn.DTO)
	 * @param dto
	 * @return List<?>
	 */
	
	/**
	 * 학과(User)별 강의조회(전공 + 교양)
	 * 학과별로 전체강의를 조회한다.(전공과 교양이 같이 출력된다.)
	 * @author 최재훈
	 * @param dto
	 * @return List<?>
	 */
	@Override
	public List<?> do_retrieve(DTO dto) {
		List<LectrueFxVO> list = new ArrayList<LectrueFxVO>();
	
		LectrueFxVO inVO = (LectrueFxVO) dto;

		for(int i=0; i<lectureBook.size();i++) {
			LectrueFxVO vsVO = lectureBook.get(i);
			String major_code=vsVO.getLectureCode().substring(0, 2); 	//강의코드2개(전공)
			
			if(major_code.equals("99")) {
				list.add(vsVO);
			}
			
			if(major_code.equals(inVO.getSearchWord())) {
				list.add(vsVO);
			}
			
		}
	
		return list;
	}

	@Override
	public int compare(LectureVO o1, LectureVO o2) {
			return 0;
	}
	
	
}
