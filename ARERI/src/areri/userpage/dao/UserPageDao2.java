/**
 *<pre>
 * areri.userpage.dao
 * Class Name : UserPageDao2
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
 */package areri.userpage.dao;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import areri.cmn.DTO;
import areri.cmn.WorkDiv;
import areri.lecture.domain.LectureVO;
import areri.userpage.Const;
import areri.userpage.domain.CompleteLectureVO;
import areri.userpage.domain.PutLectureVO;
import areri.userpage.domain.StudentVO;

/**
 * <pre>
 * areri.userpage.dao
 *    |_ UserPageDao2.java
 * 
 * </pre>
 * @date : 2019. 12. 18.
 * @version : 
 * @author : 주정현
 */
public class UserPageDao2 implements WorkDiv{

	private List<PutLectureVO> putlecture = new ArrayList<PutLectureVO>();
	private List<CompleteLectureVO> completelecture = new ArrayList<CompleteLectureVO>();
	private final String PUTLECTURE_FILE = "D:\\HR_20191130\\04_SPRING\\workspace\\ARERI\\putlecture.csv";
	private final String LECTURE_FILE = "D:\\HR_20191130\\04_SPRING\\workspace\\ARERI\\lecture.csv";
	private final String COMPLETELECTURE_FILE = "D:\\HR_20191130\\04_SPRING\\workspace\\ARERI\\completelecture.csv";
	
	@SuppressWarnings("unchecked")
	public UserPageDao2() {
		PutLectureVO vo = new PutLectureVO();
		putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);
		CompleteLectureVO com = new CompleteLectureVO();
		completelecture = (List<CompleteLectureVO>)readFile(COMPLETELECTURE_FILE,com);
	}
		
	/**
	 *  수강장바구니,수강신청,강의정보 별로 파일 불러오기
	 * @author 주정현
	 * @param String (path)파일경로
	 * @param Object (ob)불러올 형식별 
	 * @return List
	 */
	public List<?> readFile(String path,Object ob){
		List<Object> putlectureData=new ArrayList<>();
		BufferedReader br = null;
		try {
			FileReader fr=new FileReader(path);
			br=new BufferedReader(fr);
			String line = "";
			while( (line=br.readLine())!=null ) {
				
				String[] dataArray = line.split(",");
				if(ob instanceof LectureVO) {
					LectureVO vo = new LectureVO(dataArray[0],dataArray[1],dataArray[2],dataArray[3],
							dataArray[4],dataArray[5],dataArray[6],dataArray[7],dataArray[8],
							dataArray[9],dataArray[10],dataArray[11]);
					putlectureData.add(vo);
				}else if(ob instanceof PutLectureVO) {
					if(dataArray.length==1 ) {
						PutLectureVO vo=new PutLectureVO(dataArray[0]);
						putlectureData.add(vo);
					}else {
						PutLectureVO vo=new PutLectureVO(dataArray[0],dataArray[1]);
						putlectureData.add(vo);
					}
				}else if(ob instanceof CompleteLectureVO) {
					if(dataArray.length==1) {
						CompleteLectureVO vo = new CompleteLectureVO(dataArray[0]);
						putlectureData.add(vo);
					}else {
						CompleteLectureVO vo = new CompleteLectureVO(dataArray[0],dataArray[1]);
						putlectureData.add(vo);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null !=br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return putlectureData;
	}
	
	/**
	 *  회원가입시 Putlecture와 completeLecture에 학번저장
	 * @author 주정현
	 * @param String (studentId)학번
	 * @return 
	 */
	 public void joinStudentIdSave(String studentId) {
		PutLectureVO vo = new PutLectureVO();
		putlecture =(List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);
		putlecture.add(new PutLectureVO(studentId));
		insetList(PUTLECTURE_FILE,putlecture);
		
		CompleteLectureVO cvo = new CompleteLectureVO();
		completelecture =(List<CompleteLectureVO>) readFile(COMPLETELECTURE_FILE,vo);
		completelecture.add(new CompleteLectureVO(studentId));
		insetList(COMPLETELECTURE_FILE,completelecture);

	 }
	
	 /**
		 *  내 강의 코드에 맞는 강의를 화면 출력
		 * @author 주정현
		 * @param String (code)강의 코드
		 * @return List
		 */
	public List<LectureVO> myLecture(String code) {
		List<PutLectureVO> list = new ArrayList<>();
		PutLectureVO vo = new PutLectureVO();
		putlecture =(List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);
		for(int i=0;i<putlecture.size();i++) {
		if(code.equals(Const.studentID)) {
			list.add(putlecture.get(i));
			}
		}
		
		String str = "";
		String[] strmylecture = null;
		if(!list.isEmpty()) { 
			for(int i = 0 ; i < list.size() ; i++) {
				str=list.get(i).getLectureCode();
			}
		}
		if(str!=null&&!str.equals("")) { 
			strmylecture = str.split(";");
		}
		if(strmylecture !=null) {
		LectureVO vo2 = new LectureVO();
		List<LectureVO> lecture = (List<LectureVO>) readFile(LECTURE_FILE,vo2);
		List<LectureVO> mylecutre = new ArrayList<>();
		for(int i=0; i<strmylecture.length;i++) {
			for(int j=0; j<lecture.size();j++) {
				if(strmylecture[i].equals(lecture.get(j).getLectureCode())) {
					mylecutre.add(lecture.get(j));
				}
			}
		}
		return mylecutre; 
		}return null;
	}

	/**
	 *  수강신청 완료한 강의 가져오기
	 * @author 주정현
	 * @param String (code)학번
	 * @return List
	 */
	public List<LectureVO> myCompleteLecture(String code) {
		List<CompleteLectureVO> list = new ArrayList<>();
		CompleteLectureVO vo = new CompleteLectureVO();
		completelecture =(List<CompleteLectureVO>) readFile(COMPLETELECTURE_FILE,vo);
		for(int i=0;i<completelecture.size();i++) {
		if(code.equals(completelecture.get(i).getStudentId())) {
			list.add(completelecture.get(i));
			}
		}
		String str = "";
		String[] strmylecture = null;

		if(!list.isEmpty()) { 
			for(int i = 0 ; i < list.size() ; i++) {
				str=list.get(i).getLectureCode();
			}
		}
		if(str!=null&&!str.equals("")) { 
			strmylecture = str.split(";");
		}
		if(strmylecture !=null) {
		LectureVO vo2 = new LectureVO();
		List<LectureVO> lecture = (List<LectureVO>) readFile(LECTURE_FILE,vo2);
		List<LectureVO> mylecutre = new ArrayList<>();
		for(int i=0; i<strmylecture.length;i++) {
			for(int j=0; j<lecture.size();j++) {
				if(strmylecture[i].equals(lecture.get(j).getLectureCode())) {
					mylecutre.add(lecture.get(j));
					}
				}
			}
		return mylecutre; 
		}
	return null;
	}
	
	/**
	 *  완료된 강의 취소시
	 * @author 주정현
	 * @param String (code)강의코드
	 * @return 
	 */
	public void comLectureDelete(String code) {
		CompleteLectureVO vo = new CompleteLectureVO();
		PutLectureVO vo2 = new PutLectureVO();
		List<CompleteLectureVO> completetLecture = (List<CompleteLectureVO>) readFile(COMPLETELECTURE_FILE,vo);
		List<CompleteLectureVO> tmp = new ArrayList<>();
		for(int i = 0 ; i < completetLecture.size();i++) {
			if(Const.studentID.equalsIgnoreCase(completetLecture.get(i).getStudentId())) {
				tmp.add(completetLecture.get(i)); //tmp 저장
				completetLecture.remove(i); // 전체에서 내꺼뻄
			}
		}
		if(!tmp.isEmpty()) {
			String[] str = tmp.get(0).getLectureCode().replace(Const.studentID+",", "").split(";");
			String tmp1= null;
			StringBuffer sb = new StringBuffer();
			for(int j = 0 ; j < str.length ;j++) {
				if(code.equalsIgnoreCase(str[j])) {
					tmp1=str[j];
				}else {
					sb.append(str[j]+";");
				}
			}
			
			if(sb.toString().equals("")) {
				tmp.get(0).setLectureCode("");
			}else {
			tmp.get(0).setLectureCode(sb.toString().substring(0, sb.toString().length()-1));
			}
			completetLecture.add(tmp.get(0));
		
			List<PutLectureVO> putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo2);			
			for(int i = 0 ; i < putlecture.size();i++) {
				if(Const.studentID.equalsIgnoreCase(putlecture.get(i).getStudentId())) {
					if((putlecture.get(i).getLectureCode())!=null) {
						putlecture.get(i).setLectureCode(putlecture.get(i).getLectureCode()+";"+tmp1);
					}else {
						putlecture.get(i).setLectureCode(tmp1);	
					}
				}
			}
			insetList(PUTLECTURE_FILE,putlecture);
			insetList(COMPLETELECTURE_FILE,completetLecture);
			
		}
		putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo2);
		completelecture = (List<CompleteLectureVO>)readFile(COMPLETELECTURE_FILE,vo);
	}
	
	/**
	 *  강의조회에서 선택한 강의  putlecture.csv에 저장
	 * @author 주정현
	 * @param String (code)파일경로
	 * @return 
	 */
	//장바구니에 추가
	public void attending(String code) {
		PutLectureVO vo = new PutLectureVO();
		List<PutLectureVO> putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);			
		for(int i = 0 ; i < putlecture.size();i++) {
			if(Const.studentID.equalsIgnoreCase(putlecture.get(i).getStudentId())) {
				if((putlecture.get(i).getLectureCode())!=null) {
					putlecture.get(i).setLectureCode(putlecture.get(i).getLectureCode()+";"+code);
				}else {
					putlecture.get(i).setLectureCode(code);	
				}
			}
		}
		insetList(PUTLECTURE_FILE,putlecture);
		
		this.putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);
	}
	

	/**
	 *  장바구니에서 제거
	 * @author 주정현
	 * @param String (code)강의코드
	 * @return List
	 */
	public void lectureDelete(String code) {
		PutLectureVO vo = new PutLectureVO();
		List<PutLectureVO> putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE, vo);
		List<PutLectureVO> tmp = new ArrayList();
		for(int i=0; i<putlecture.size();i++) {
			if(Const.studentID.equals(putlecture.get(i).getStudentId())) {
				tmp.add(putlecture.get(i));
				putlecture.remove(i);
			}
		}
		if(!tmp.isEmpty()) {
			String[] str = tmp.get(0).getLectureCode().replace(Const.studentID+",", "").split(";");
			String tmp1= null;
			StringBuffer sb = new StringBuffer();
			for(int j = 0 ; j < str.length ;j++) {
				if(code.equalsIgnoreCase(str[j])) {
					tmp1=str[j];
				}else {
					sb.append(str[j]+";");
				}
			}
			if(sb.toString().equals("")) {
				tmp.get(0).setLectureCode("");
			}else {
				tmp.get(0).setLectureCode(sb.toString().substring(0, sb.toString().length()-1));
			}
			putlecture.add(tmp.get(0));
			insetList(PUTLECTURE_FILE,putlecture);
		}
		this.putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);
	}
	
	/**
	 *  Putlecture의 강의를 지우고, Completelecture에 강의 추가
	 * @author 주정현
	 * @param String (code)강의코드
	 * @return List
	 */
	public void lectureSave(String code) {
		PutLectureVO vo = new PutLectureVO();
		CompleteLectureVO vo2 = new CompleteLectureVO();
		List<PutLectureVO> putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);
		List<PutLectureVO> tmp = new ArrayList<>();
		for(int i = 0 ; i < putlecture.size();i++) {
			if(Const.studentID.equalsIgnoreCase(putlecture.get(i).getStudentId())) {
				tmp.add(putlecture.get(i)); //tmp 저장
				putlecture.remove(i); // 전체에서 내꺼뻄
			}
		}
		if(!tmp.isEmpty()) {
			String[] str = tmp.get(0).getLectureCode().replace(Const.studentID+",", "").split(";");
			String tmp1= null;
			StringBuffer sb = new StringBuffer();
			for(int j = 0 ; j < str.length ;j++) {
				if(code.equalsIgnoreCase(str[j])) {
					tmp1=str[j];
				}else {
					sb.append(str[j]+";");
				}
		}
			if(sb.toString().equals("")) {
				tmp.get(0).setLectureCode("");
			}else {
			tmp.get(0).setLectureCode(sb.toString().substring(0, sb.toString().length()-1));
			}
			putlecture.add(tmp.get(0));
			
			List<CompleteLectureVO> completelecture = (List<CompleteLectureVO>) readFile(COMPLETELECTURE_FILE,vo2);			
			for(int i = 0 ; i < putlecture.size();i++) {
				if(Const.studentID.equalsIgnoreCase(completelecture.get(i).getStudentId())) {
					if((completelecture.get(i).getLectureCode())!=null) {
						completelecture.get(i).setLectureCode(completelecture.get(i).getLectureCode()+";"+tmp1);
					}else {
						completelecture.get(i).setLectureCode(tmp1);	
					}
				}
			}
			
			insetList(PUTLECTURE_FILE,putlecture);
			insetList(COMPLETELECTURE_FILE,completelecture);
			
		}
		this.putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);
		completelecture = (List<CompleteLectureVO>)readFile(COMPLETELECTURE_FILE,vo2);

	}
	
	/**
	 *  putlecture.csv에서 해당하는 강의 삭제
	 * @author 주정현
	 * @param String (lectureCode)강의코드
	 * @return 
	 */
	public void putLectureDelete(String lectureCode) {
		PutLectureVO vo = new PutLectureVO();
		List<PutLectureVO> putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);
		List<PutLectureVO> tmp = new ArrayList<>();
		for(int i = 0 ; i < putlecture.size();i++) {
			if(Const.studentID.equalsIgnoreCase(putlecture.get(i).getStudentId())) {
				tmp.add(putlecture.get(i)); //tmp 저장
				putlecture.remove(i); // 전체에서 내꺼뻄
			}
		}
		if(!tmp.isEmpty()) {
			String[] str = tmp.get(0).getLectureCode().replace(Const.studentID+",", "").split(";");
			String tmp1= null;
			StringBuffer sb = new StringBuffer();
			for(int j = 0 ; j < str.length ;j++) {
				if(lectureCode.equalsIgnoreCase(str[j])) {
					tmp1=str[j];
				}else {
					sb.append(str[j]+";");
				}
		}
			if(sb.toString().equals("")) {
				tmp.get(0).setLectureCode("");
			}else {
			tmp.get(0).setLectureCode(sb.toString().substring(0, sb.toString().length()-1));
			}
			putlecture.add(tmp.get(0));
			insetList(PUTLECTURE_FILE,putlecture);
		}
		this.putlecture = (List<PutLectureVO>) readFile(PUTLECTURE_FILE,vo);
	}
	
	/**
	 *  수강장바구니,수강신청,강의정보 별로 파일 불러오기
	 * @author 주정현
	 * @param List<Object> (list)사용된 강의장바구니 또는 수강신청완료
	 * @return List
	 */
	public List<String> save(List<Object> list) {
		List<String> li = new ArrayList<>();
		for(int i = 0 ; i< list.size() ; i++) {
			if(list.get(i) instanceof PutLectureVO) {
	            PutLectureVO vo = (PutLectureVO) list.get(i);
	            if(vo.getLectureCode() != null) {
	            	li.add(vo.getStudentId()+","+vo.getLectureCode());
	            }else {
					 li.add(vo.getStudentId());
				 }
				}else if(list.get(i) instanceof CompleteLectureVO) { 
					CompleteLectureVO vo = (CompleteLectureVO) list.get(i);
					if(vo.getLectureCode() != null) {
						li.add(vo.getStudentId()+","+vo.getLectureCode());
					}else {
						li.add(vo.getStudentId());
				 }
			}
		}
		return li;
	}
	
	/**
	 *  해당 형식에 맞춰서 저장
	 * @author 주정현
	 * @param String (fileName)파일경로
	 * @param Object (list)저장할 list
	 * @return 
	 */
	public void insetList(String fileName,Object list) {
        try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileName),Charset.forName("UTF-8"));){
        	List<String> str = new ArrayList<>();
        	if(list instanceof List<?>) {
    			str = save((List<Object>)list);
    		}
        	for(int i = 0 ; i < str.size() ; i++) {
        		bw.write(str.get(i));
        		if(!(i == str.size()-1)) {
        		bw.newLine();
        		}
        	}
        	bw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
	}
	
	/**
	 *  자기 시간표 string 배열(10X6)로 출력
	 * @author 주정현
	 * @param 
	 * @param  
	 * @return String[][]
	 */
	public String[][] TimeTable() {
		String Name = null;
		String day = null;
		int startTime =0;
		int endTime = 0;
		
		List<LectureVO> list = myCompleteLecture(Const.studentID);
		String[][] timeTable = new String[9][5];
		for(int i =0; i<list.size();i++) { // 자기 강좌 출력
			if(list.get(i).getLectureDay().equals("월요일")) {
				startTime = Integer.valueOf(list.get(i).getLectureStartTime().split(":")[0]) -9; // 0 <- 9:00 ~ 9 <- 18:00
				endTime = Integer.valueOf(list.get(i).getLectureEndTime().split(":")[0]) -9;	// 0 <- 9:00 ~ 9 <- 18:00
				
				for(int j = startTime;j<endTime;j++) {
					timeTable[j][0] = list.get(i).getLecturName();
				}
			}else if(list.get(i).getLectureDay().equals("화요일")) {
				startTime = Integer.valueOf(list.get(i).getLectureStartTime().split(":")[0])-9 ; // 0 <- 9:00 ~ 9 <- 18:00
				endTime = Integer.valueOf(list.get(i).getLectureEndTime().split(":")[0])-9 ;	// 0 <- 9:00 ~ 9 <- 18:00
				for(int j = startTime;j<endTime;j++) {
					timeTable[j][1] = list.get(i).getLecturName();
				}
			}else if(list.get(i).getLectureDay().equals("수요일")) {
				startTime = Integer.valueOf(list.get(i).getLectureStartTime().split(":")[0]) -9; // 0 <- 9:00 ~ 9 <- 18:00
				endTime = Integer.valueOf(list.get(i).getLectureEndTime().split(":")[0]) -9;	// 0 <- 9:00 ~ 9 <- 18:00
				for(int j = startTime;j<endTime;j++) {
					timeTable[j][2] = list.get(i).getLecturName();
				}
			}else if(list.get(i).getLectureDay().equals("목요일")) {
				startTime = Integer.valueOf(list.get(i).getLectureStartTime().split(":")[0]) -9; // 0 <- 9:00 ~ 9 <- 18:00
				endTime = Integer.valueOf(list.get(i).getLectureEndTime().split(":")[0]) -9;	// 0 <- 9:00 ~ 9 <- 18:00
				for(int j = startTime;j<endTime;j++) {
					timeTable[j][3] = list.get(i).getLecturName();
				}
			}else{//금요일
				startTime = Integer.valueOf(list.get(i).getLectureStartTime().split(":")[0]) -9; // 0 <- 9:00 ~ 9 <- 18:00
				endTime = Integer.valueOf(list.get(i).getLectureEndTime().split(":")[0]) -9;	// 0 <- 9:00 ~ 9 <- 18:00
				for(int j = startTime;j<endTime;j++) {
					timeTable[j][4] = list.get(i).getLecturName();
				}
			}
		}// 자기 강좌만큼 돌림
		for(int i=0;i<9;i++) {
			for(int j=0;j<5;j++	) {
				if(!timeTable.equals("")) {
				}else {
				}
			}
		}
		
		return timeTable;
	}
	
	@Override
	public int do_save(DTO dto) {
	return 0;
	}

	@Override
	public int do_update(DTO dto) {
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		return 0;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		return null;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return null;
	}
	
	

}
