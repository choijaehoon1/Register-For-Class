/**
 *<pre>
 * Class Name : LectureAdminDao
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
package areri.admin.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import areri.admin.domain.SearchVO;
import areri.cmn.DTO;
import areri.cmn.WorkDiv;
import areri.lecture.domain.LectrueFxVO;
import areri.lecture.domain.LectureVO;
import areri.userpage.domain.StudentVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

	/**
	 * <pre>
	 * areri.admin.dao
	 *    |_ LectureAdmin.java
	 * 
	 * </pre>
	 * @date : 2019. 12. 6. 오후 12:07:30
	 * @version : 
	 * @author : 신찬미
	 */
public class LectureAdminDao implements WorkDiv {
	
	public static List<LectureVO> lecture = new ArrayList<LectureVO>();
	public static final String FILE_PATH="D:\\HR_20191130\\04_SPRING\\workspace\\ARERI\\lecture.csv";
	public static ObservableList  lectureTableViewList=FXCollections.observableArrayList();

	//public String studentID ="999999999";
	
	public LectureAdminDao() {
		lecture=readFile(FILE_PATH);
		disp(lecture);
	}
	


	
	
	
	/**
	 * lecture.csv파일에서 데이터를 읽어 List<LectureVO> 생성
	 * @author : 신찬미
	 * @param filePath
	 * @return
	 */
	public List<LectureVO> readFile(String filePath){
		List<LectureVO> lectureData=new ArrayList<LectureVO>();
		BufferedReader br=null;
		try {
			FileReader fr=new FileReader(filePath);
			br=new BufferedReader(fr);
			String line = "";
			while( (line=br.readLine())!=null ) {
				String[] dataArray = line.split(",");
				LectureVO vo = new LectureVO(dataArray[0],dataArray[1],dataArray[2],dataArray[3],
						dataArray[4],dataArray[5],dataArray[6],dataArray[7],dataArray[8],
						dataArray[9],dataArray[10],dataArray[11]);
				lectureData.add(vo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null !=br) {
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
	 * List를 콘솔에 출력
	 * @author : 신찬미
	 * @param list
	 */
		public void disp(List<LectureVO> list) {
			for(LectureVO vo :list) {
				//System.out.println(vo);
			}
		}
		

		
		/**
		 * 쉼표로 구분된 데이터를 입력 받아 LectureVO 돌려 준다.
		 * @author : 신찬미
		 * @param dataScanner
		 * @return
		 */
		public LectureVO getInputLectureCode(Scanner dataScanner){
			LectureVO outVO = null;
			dataScanner = new Scanner(System.in);
			//System.out.println("강의코드 데이터 입력ex)111111>>");
			String inputData = dataScanner.nextLine();
			inputData = inputData.trim();//앞뒤 공간 삭제
			outVO  =new LectureVO();
			outVO.setLectureCode(inputData);
			//System.out.println("입력:"+outVO);
			return outVO;
		}
		
		
		/**
		 * 쉼표로 구분된 데이터를 입력 받아 LectureVO 돌려 준다.
		 * @author : 신찬미
		 * @param dataScanner
		 * @return
		 */
		public LectureVO getSeachData(Scanner dataScanner){
			LectureVO outVO = null;
			dataScanner = new Scanner(System.in);
			//System.out.println("검색 데이터 입력(1->강의이름,2->요일, 3->강의실, ex)2,월요일>>");
			String inputData = dataScanner.nextLine();
			inputData = inputData.trim();//앞뒤 공간 삭제
			
			String[] dataArray = inputData.split(",");
			outVO  =new LectureVO();
			outVO.setSearchDiv(dataArray[0]);
			outVO.setSearchWord(dataArray[1]);
			
			//System.out.println("입력:"+outVO);
			return outVO;
		}
		
		
		
		/**
		 * List에 있는 Data를 파일에 기록
		 * @author : 신찬미
		 * @param path
		 * @return
		 */
		public int saveFile(String path)  {
			int cnt = 0;
			FileWriter writer =null;
			BufferedWriter  bw=null;
			File file=new File(path);
			
			try {
				writer = new FileWriter(file);
				bw     = new BufferedWriter(writer);
				
				//--------------------------------------
				for(int i=0;i<lecture.size();i++) {
					LectureVO vo =lecture.get(i);
					
					StringBuilder sb=new StringBuilder();
					sb.append(vo.getLectureCode()+",");
					sb.append(vo.getLecturName()+",");
					sb.append(vo.getProfessorCode()+",");
					sb.append(vo.getLectureDay()+",");
					sb.append(vo.getLectureStartTime()+",");
					sb.append(vo.getLectureEndTime()+",");
					sb.append(vo.getLectureRoom()+",");
					sb.append(vo.getLectureStudent()+",");
					sb.append(vo.getLecturePlan()+",");
					sb.append(vo.getStarSum()+",");
					sb.append(vo.getStarPeople()+",");
					sb.append(vo.getStarAvg());
					
					
					
					
					//마지막 라인에 "\n" 제거
					if((i+1)!=lecture.size()) {
						sb.append("\n");
					}
					
					cnt++;
					bw.write(sb.toString());
					
				}
			//--------------------------------------
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					
					if(null !=bw) {
						bw.close();
					}					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return cnt;
		}	
	

		
	/**
	 * 목록,파일 저장
	 * @author : 신찬미
	 * @Method do_save
	 */
	@Override
	public int do_save(DTO dto) {
		LectureVO vo =(LectureVO) dto;
		int flag = 0;
	
		
		boolean saveflag = lecture.add(vo);
		//int saveCnt = saveFile(FILE_PATH);
		//System.out.println(saveCnt+"건 저장 되었습니다.");
		if(saveflag ==true) {
			flag++;
		}
		
		return flag;
	}


	/**
	 * 목록,파일 업데이트
	 * @author : 신찬미
	 * @Method do_update
	 */
	@Override
	public int do_update(DTO dto) {
		int flag = 0;
		LectureVO modVO = (LectureVO) dto;
		//if(isLectureCodeExists(modVO)) {
			flag = do_delete(modVO);
			if(flag==1) {
				flag=flag+do_save(modVO);
		//	}
			
		}
		return flag;
	}
		
		
	
	
	/**
	 * 목록,파일 삭제
	 * @author : 신찬미
	 * @Method do_delete
	 */
	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		LectureVO delVO = (LectureVO) dto;
		//반드시 역순으로 찾고 삭제 해야함.
		for(int i=lecture.size()-1;i>=0;i--) {
			LectureVO vsVO = lecture.get(i);
			if(delVO.getLectureCode().equals(vsVO.getLectureCode())) {
				lecture.remove(i);
				flag++;
				break;
			}
		}
		
		//파일에 저장
		//saveFile(FILE_PATH);
		System.out.println(flag);
		return flag;
	
	}
	
	
	/**
	 * 데이터 존재 유무 확인,Key는 강의코드
	 * @author : 신찬미
	 * @Method isLectureCodeExists
	 * @param iscode
	 * @return
	 */
	public boolean isLectureCodeExists(String iscode){
		boolean flag = false;
		LectureVO vsVO = null;
		//System.out.println("입력 lecture 코드===>"+iscode);
		for(int i=0;i<lecture.size();i++) {
//			vsVO = lecture.get(i);
			//System.out.println("비교 강의코드 ===>"+vsVO.getLectureCode());				
			if(lecture.get(i).getLectureCode().equals(iscode) == true) {
				flag = true;
			}
		}
		return flag;
	}	
	

	/**
	 * 설명 : 데이터 사용가능 확인, [사용중인 강의실]
	 * @author : 신찬미
	 * @Method isLectureroomExists
	 * @param isroom
	 * @param day
	 * @param lectureCode
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public boolean isLectureroomExistsAdd(String isroom, String day, String starttime, String endtime){
		boolean flag = false;
		
		for(int i=0;i<lecture.size();i++) {
			LectureVO vsVO = lecture.get(i);
			if(vsVO.getLectureRoom().equals(isroom)
					&&vsVO.getLectureDay().equals(day)
					&&(Integer.parseInt(vsVO.getLectureEndTime().substring(0,2))) > (Integer.parseInt(starttime.substring(0,2)))
					&&((Integer.parseInt(vsVO.getLectureStartTime().substring(0,2))) < (Integer.parseInt(endtime.substring(0,2))))) {
				flag = true;
				break;
			}
		}
		return flag;
	}	
	
	public boolean isLectureroomExistsModi(String isroom, String day, String lectureCode, String starttime, String endtime){
		boolean flag = false;
		
		for(int i=0;i<lecture.size();i++) {
			LectureVO vsVO = lecture.get(i);
			if(vsVO.getLectureRoom().equals(isroom)
					&&vsVO.getLectureDay().equals(day)
					&&(!vsVO.getLectureCode().equals(lectureCode))
					&&(Integer.parseInt(vsVO.getLectureEndTime().substring(0,2))) > (Integer.parseInt(starttime.substring(0,2)))
					&&((Integer.parseInt(vsVO.getLectureStartTime().substring(0,2))) < (Integer.parseInt(endtime.substring(0,2))))) {
				flag = true;
				break;
			}
		}
		return flag;
	}	
	
	
	/**
	 * 설명 : 강의 시작시간이 끝 시간보다 늦지 않게 
	 * @author : 신찬미
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public boolean	lectureStartTimeExists(String starttime, String endtime){
		boolean flag = false;
		
		for(int i=0;i<lecture.size();i++) {
			LectureVO vsVO = lecture.get(i);
			if(((Integer.parseInt(starttime.substring(0,2))) > (Integer.parseInt(endtime.substring(0,2))))) {
				flag = true;
				break;
			}
		}
		return flag;
	}	
	
	
	/**
	 * 데이터 사용가능 확인, [수업중인 교수]
	 * @author : 신찬미
	 * @Method isProfessorCodeExists
	 * @param pc
	 * @param day
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public boolean isProfessorCodeExists(String pc, String day,  String starttime, String endtime){
		boolean flag = false;
		
		for(int i=0;i<lecture.size();i++) {
			LectureVO vsVO = lecture.get(i);
			if(vsVO.getProfessorCode().equals(pc)
					&&vsVO.getLectureDay().equals(day)
					&&(Integer.parseInt(vsVO.getLectureEndTime().substring(0,2))) > (Integer.parseInt(starttime.substring(0,2)))
					&&((Integer.parseInt(vsVO.getLectureStartTime().substring(0,2))) < (Integer.parseInt(endtime.substring(0,2))))) {
				flag = true;
				break;
			}
		}
		return flag;
	}		
	

	



	@Override
	public DTO do_selectOne(DTO dto) {
		LectureVO outVO = null;
		LectureVO inVO  = (LectureVO) dto;
		
		for(int i=0;i<lecture.size();i++) {
			LectureVO vsVO = lecture.get(i);
			if(vsVO.getLectureCode().equals(inVO.getLectureCode())) {
				outVO = vsVO;
				break;
			}
		}
		
		return outVO;
	}



	
	/**
	 * 검색 (강의코드,강의명,요일)
	 * @author : 신찬미
	 * @Method do_codenameday
	 * @param dto
	 * @return
	 */
	public List<?> do_codenameday(DTO dto) {
		List<LectureVO>list = new ArrayList<LectureVO>();
		SearchVO inVO = (SearchVO) dto;
//		.: 임의의 문자 [단 ‘'는 넣을 수 없습니다.]
//		*: 앞 문자가 0개 이상의 개수가 존재할 수 있습니다.
		
		if(inVO.getSearchDiv().equals("1")) {
			for(LectureVO vo : lecture) {
				if(vo.getLectureCode().matches(".*"+inVO.getSearchWord()+".*")) {
					list.add(vo);
				}
			}
		}else if(inVO.getSearchDiv().equals("2")) {
			for(LectureVO vo : lecture) {
				if(vo.getLecturName().matches(".*"+inVO.getSearchWord()+".*")) {
					list.add(vo);
				}
			}	
		}else if(inVO.getSearchDiv().equals("3")) {
			for(LectureVO vo : lecture) {
				if(vo.getLectureDay().matches(".*"+inVO.getSearchWord()+".*")) {
					list.add(vo);
				}
		
			}
		}
		return list;
	}
	

	
	
	/**
	 * 검색 (전체,전공,교양) 
	 * @author : 신찬미
	 * @Method do_allmajircls
	 * @param dto
	 * @return
	 */
	public List<?> do_allmajircls(DTO dto) {
		List<LectureVO>list = new ArrayList<LectureVO>();
		SearchVO inVO = (SearchVO) dto;
//		.: 임의의 문자 [단 ‘'는 넣을 수 없습니다.]
//		*: 앞 문자가 0개 이상의 개수가 존재할 수 있습니다.		
		
		 if(inVO.getSearchDiv().equals("1")) {
			for(LectureVO vo : lecture) {
				if(!(vo.getLectureCode().matches("99"+".*"))) {
					//시작하는 코드가 99가 아닌 -> 전공
					list.add(vo);
				}
			}	
		}else if(inVO.getSearchDiv().equals("2")) {
			for(LectureVO vo : lecture) {
				if(vo.getLectureCode().matches("99"+".*")) {
					//시작하는 코드가 99 -> 교양
					list.add(vo);
				}
		
			}
		}
		return list;
	}






	@Override
	public List<?> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}//Main
