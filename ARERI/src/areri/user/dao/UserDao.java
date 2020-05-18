package areri.user.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import areri.user.domain.StudentVO;
import areri.user.controller.JoinController;
import areri.user.controller.LoginController;

/**
 * <pre>
 * areri.user.dao
 *    |_ UserDao.java
 * 
 * </pre>
 * 
 * @date : 2019. 12. 6. 오후 3:45:42
 * @version :
 * @author : 박지수
 */
public class UserDao {


	private List<StudentVO> studentList = new ArrayList<StudentVO>();
	private Alert alert = new Alert(AlertType.INFORMATION);
	
	public UserDao() {
		studentList = readFile(LoginController.FILE_PATH);
		
	}
	

	/**
	 * <pre>
	 * 1. 설명 : 파일 읽기 메소드
	 * 2. 처리내용 :
	 * </pre>
	 * 
	 * @Method Name : readFile
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param filePath
	 */	
	public List<StudentVO> readFile(String filePath) {
		List<StudentVO> studentData = new ArrayList<StudentVO>();
		BufferedReader br = null;

		try {
			FileReader fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] dataArray = line.split(",");
				StudentVO vo = new StudentVO(dataArray[0], dataArray[1], dataArray[2], dataArray[3]);

				studentData.add(vo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		return studentData;
	}

	/**
	 * <pre>
	 * 1. 설명 : 파일 저장 메소드
	 * 2. 처리내용 :
	 * </pre>
	 * 
	 * @Method Name : saveFile
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param path
	 */	
	public int saveFile(String path) {
		int cnt = 0;
		FileWriter writer = null;
		BufferedWriter bw = null;
		File file = new File(path);

		try {
			writer = new FileWriter(file);
			bw = new BufferedWriter(writer);

			// --------------------------------------
			for (int i = 0; i < studentList.size(); i++) {
				StudentVO vo = studentList.get(i);
				
				StringBuilder sb = new StringBuilder();
				sb.append(vo.getStudentID() + ",");
				sb.append(vo.getStudentPW() + ",");
				sb.append(vo.getStudentName() + ",");
				sb.append(vo.getStudentEmail());

				// 마지막 라인에 "\n" 제거
				if ((i + 1) != studentList.size()) {
					sb.append("\n");
				}

				cnt++;
				bw.write(sb.toString());

			}
			// --------------------------------------
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != bw) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}


	/**
	 * <pre>
	 * 1. 설명 : 로그인(아이디 확인) 메서드
	 * 2. 처리내용 : 입력받은 아이디가 데이터와 일치하는지 확인
	 * </pre>
	 * 
	 * @Method Name : logIn_IdCheck
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param id
	 */
	public boolean logIn_IdCheck(String id) {
		boolean login = false;

		//studentList에서 입력받은 id와 같은 id가 있는지 확인
		for (int i = 0; i < studentList.size(); i++) {
			StudentVO vsVO = studentList.get(i);
			if(vsVO.getStudentID().equals(id)) {
				login = true;
				break;
			} 

		}
		return login;
	}
	
	
	/**
	 * <pre>
	 * 1. 설명 : 로그인(비밀번호 확인) 메서드
	 * 2. 처리내용 : 입력받은 아이디와 비밀번호가 데이터와 일치하는지 확인
	 * </pre>
	 * 
	 * @Method Name : logIn_PwCheck
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param id,pw
	 */
	public boolean logIn_PwCheck(String id, String pw) {
		boolean login = false;

		//studentList에서 입력받은 id, pw와 같은 데이터가 있는지 확인, 둘 중 하나라도 다르면 login는 false
		for (int i = 0; i < studentList.size(); i++) {
			StudentVO vsVO = studentList.get(i);
			if(vsVO.getStudentID().equals(id) && vsVO.getStudentPW().equals(pw)){
				login = true;
				break;
			} 

		}
		return login;
	}

	/**
	 * <pre>
	 * 1. 설명 : 회원가입(아이디확인) 메서드
	 * 2. 처리내용 : 입력받은 아이디가 존재하는지 확인
	 * </pre>
	 * 
	 * @Method Name : isIdExists
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param id
	 */
	public boolean isIdExists(String id) {
		boolean exist = false;
	
		//studentList에서 입력받은 id와 같은 id가 있는지 확인
		for (int i = 0; i < studentList.size(); i++) {
			StudentVO vsVO = studentList.get(i);
			
			if (vsVO.getStudentID().equals(id)) {
				exist = true;
				alert.setHeaderText("이미 가입되어있는 학번입니다.");
				alert.showAndWait();
			}
		}
		
		return exist;
	}
	
	
	/**
	 * <pre>
	 * 1. 설명 : 회원가입(아이디 규칙확인) 메서드
	 * 2. 처리내용 : 아이디 유효성 검사
	 * </pre>
	 * 
	 * @Method Name : isIdTrue
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param id
	 */
	public boolean isIdTrue(String id) {
		boolean idTrue = false;
		
		//아이디 확인 정규식
		String idPattern = "^(?=.*[0-9]).{9}$";
		Matcher match = Pattern.compile(idPattern).matcher(id);

		if (!match.matches()) {
			idTrue = false;
		} else if (!(id.substring(4, 6).equals("11")) && id.substring(4, 6).equals("22") ) {
			idTrue = true;
		} else if (id.substring(4, 6).equals("11") && !(id.substring(4, 6).equals("22"))) {
			idTrue = true;
		} else if (!(id.substring(4, 6).equals("11")) && !(id.substring(4, 6).equals("22"))) {
			idTrue = false;
		} else if (id.contains(" ")) {
			idTrue = false;
		} else {
			idTrue = true;		
		}	
		
		if(idTrue == false) {
			alert.setHeaderText("잘못된 학번을 입력하였습니다.");
			alert.showAndWait();
		}
		
		return idTrue;
	}
	
	
	/**
	 * <pre>
	 * 1. 설명 : 회원가입(비번 규칙확인) 메서드
	 * 2. 처리내용 : 비밀번호 유효성 검사
	 * </pre>
	 * 
	 * @Method Name : isPwTrue
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param pw
	 */
	public boolean isPwTrue(String pw) {
		boolean pwTrue = false;

		//비밀번호 확인 정규식(영문소문자, 숫자, 특수기호 포함 8~12자리)
		String pwPattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z]).{8,12}$";
		Matcher match = Pattern.compile(pwPattern).matcher(pw);

		if (!match.matches()) {
			pwTrue = false;			
		} else if (pw.contains(" ")) {
			pwTrue = false;
		} else {
			pwTrue = true;
		}
		
		if(pwTrue == false) {
			alert.setHeaderText("비밀번호 규칙을 확인하세요.");
			alert.showAndWait();
		}

		return pwTrue;
	}
	
	
	/**
	 * <pre>
	 * 1. 설명 : 회원가입(비밀번호 확인) 메서드
	 * 2. 처리내용 : 비밀번호 1,2가 같은지 확인
	 * </pre>
	 * 
	 * @Method Name : isPwSame
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param pw,pw2
	 */
	public boolean isPwSame(String pw, String pw2) {
		boolean pwSame = true;
		
		//비밀번호 확인
		if (!(pw.equals(pw2))) {
			pwSame = false;
			alert.setHeaderText("비밀번호 1,2를 확인하세요");
			alert.showAndWait();
		}
		return pwSame;
	}
	
	
	/**
	 * <pre>
	 * 1. 설명 : 회원가입(이메일 규칙확인) 메서드
	 * 2. 처리내용 : 이메일 유효성 검사
	 * </pre>
	 * 
	 * @Method Name : isEmailTrue
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param email
	 */
	public boolean isEmailTrue(String email) {
		boolean eSame = true;
		
		//이메일 형식 확인 정규식
		String ePattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
		Matcher match = Pattern.compile(ePattern).matcher(email);
		
		if (!match.matches()) {
			eSame = false;		
		} else if (email.contains(" ")) {
			eSame = false;
		} else {
			eSame = true;
		}
		
		if(eSame == false) {
			alert.setHeaderText("이메일 형식을 확인하세요.");
			alert.showAndWait();
		}
		return eSame;
	}
	
	
	/**
	 * <pre>
	 * 1. 설명 : 이름 메서드
	 * 2. 처리내용 : 로그인한 회원의 이름 가져오기
	 * </pre>
	 * 
	 * @Method Name : getStudentName
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param id
	 */
	public String getStudentName(String id) {
		String studentName = null; 
		
		//studentList에서 입력받은 id와 같은 id가 있는지 확인
		for (int i = 0; i < studentList.size(); i++) {
			StudentVO vsVO = studentList.get(i);
			if(vsVO.getStudentID().equals(id)) {
				studentName = vsVO.getStudentName();
				break;
			}
		}	
		return studentName;
	}
	

	/**
	 * <pre>
	 * 1. 설명 : 정보 저장 메소드
	 * 2. 처리내용 :
	 * </pre>
	 * 
	 * @Method Name : do_save
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param args
	 */
	public int do_save(String id, String pw, String name, String email) {
		int flag = 0;
		StudentVO  inVO = new StudentVO();
		
		//StudentVO타입의 객체인 inVO의 Id, PW, 이름, 이메일을 입력받은 정보들로 set
		inVO.setStudentID(id);
		inVO.setStudentPW(pw);
		inVO.setStudentName(name);
		inVO.setStudentEmail(email);
		
		//입력받은 정보를 넣어준 inVO를 studnetList에 더해주기
		studentList.add(inVO);
		flag = saveFile(LoginController.FILE_PATH);
		return flag;
	}

	/**
	 * <pre>
	 * 1. 설명 : pw찾기(아이디 확인) 메서드
	 * 2. 처리내용 : 입력받은 아이디가 데이터와 일치하는지 확인
	 * </pre>
	 * 
	 * @Method Name : pwFind
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param args
	 */
	public boolean pwFind_IdCheck(String id) {
		boolean pwfind = false;

		//studentList에서 입력받은 id와 같은 id가 있는지 확인
		for (int i = 0; i < studentList.size(); i++) {
			StudentVO vsVO = studentList.get(i);
			 if (vsVO.getStudentID().equals(id)){
				pwfind = true;

				break;
			}
		}
		return pwfind;
	}
	
	
	/**
	 * <pre>
	 * 1. 설명 : pw찾기(이메일 확인) 메서드
	 * 2. 처리내용 : 입력받은 이메일이 데이터와 일치하는지 확인
	 * </pre>
	 * 
	 * @Method Name : pwFind
	 * @date : 2019. 12. 6.
	 * @author : 박지수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 12. 6.		박지수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param args
	 */
	public boolean pwFind_EmailCheck(String id, String email) {
		boolean pwfind = false;
		
		//studentList에서 입력받은 id, email과 같은 데이터가 있는지 확인, 둘 중 하나라도 다르면 pwfind는 false
		for (int i = 0; i < studentList.size(); i++) {
			StudentVO vsVO = studentList.get(i);
			if (vsVO.getStudentID().equals(id) &&vsVO.getStudentEmail().equals(email)) {
				pwfind = true;
				String pw = vsVO.getStudentPW();
				alert.setHeaderText("비밀번호는 "+pw+"입니다.");
				alert.showAndWait();
				break;
			}
		}
		return pwfind;
	}

}
