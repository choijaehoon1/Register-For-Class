///**
// *<pre>
// * areri.lecture.dao
// * Class Name : lectureDaoMain.java
// * Description : 
// * Modification Information
// * 
// *   수정일      수정자              수정내용
// *  ---------   ---------   -------------------------------
// *  2019-12-05           최초생성
// *
// * @author 개발프레임웍크 실행환경 ARERI
// * @since 2019-12-05 
// * @version 1.0
// * 
// *
// *  Copyright (C) by ARERI All right reserved.
// * </pre>
// */
//
//package areri.lecture.dao;
//
//import java.util.List;
//import java.util.Scanner;
//
//import areri.lecture.domain.LectrueFxVO;
//import areri.lecture.domain.LectureVO;
//
//
///**
// * <pre>
// * areri.lecture.dao 
// *    |_ lectureDaoMain.java
// * 
// * </pre>
// * @date : 2019. 12. 5. 오후 5:56:12
// * @version : 
// * @author : sist128
// */
//public class LectureDaoMain {
//	
//
//	/**
//	 * <pre>
//	 * 1. 설명 : 
//	 * 2. 처리내용 : 
//	 * </pre>
//	 * @Method Name : main
//	 * @date : 2019. 12. 5.
//	 * @author : sist128
//	 * @history : 
//	 *	-----------------------------------------------------------------------
//	 *	변경일				작성자						변경내용  
//	 *	----------- ------------------- ---------------------------------------
//	 *	2019. 12. 5.		sist128				최초 작성 
//	 *	-----------------------------------------------------------------------
//	 * 
//	 * @param args
//	 */
//	
//	public static void main(String[] args) {
//		LectureDao dao = new LectureDao();
//		String input01 ="";
//		Scanner scanner = new Scanner(System.in);
//		Scanner dataScanner = null;
//		LectureVO vo;
//		LectrueFxVO vo1;
//		List<LectureVO> list;
//		do {
//			System.out.println("1:개인별조회,2:개인별교양조회,"
//					+ "3:개인별전공조회,4:이름으로 검색(전공,교양),5:장바구니에 담기,"
//					+ "6:강의정보조회(강의계획서출력),7:별접높은과목출력,Q:종료>>");
//			input01 = scanner.nextLine().toUpperCase();
//			
//			if(!input01.equalsIgnoreCase("1") && !input01.equalsIgnoreCase("2")
//				&& !input01.equalsIgnoreCase("3") && !input01.equalsIgnoreCase("4")
//				&& !input01.equalsIgnoreCase("5") && !input01.equalsIgnoreCase("6")
//				&& !input01.equalsIgnoreCase("7") &&!input01.equalsIgnoreCase("Q")) {
//				System.out.println("Command를 다시 입력하세요");
//				continue;
//			}
//			switch(input01) {
//			case "1":		//학과(User)별 강의조회(전공 + 교양)
//				vo1 = dao.getSearchData();
//				list = (List<LectureVO>) dao.do_retrieve(vo1); //캐스팅
////				dao.dispUser(list);
//				break;
//			case "2":		//학과(User)별 교양조회
//				vo1 = dao.getSearchData();
//				list = (List<LectureVO>) dao.do_liberal(vo1);
////				dao.dispUser(list);
//				break;
//			case "3":		//학과(User)별 전공조회
//				vo1 = dao.getSearchData();
//				list = (List<LectureVO>) dao.do_major(vo1);
////				dao.dispUser(list);
//				break;
//			case "4":		//학과(User)별 전공검색, 교양은 전체출력
////				vo = dao.getSubjectSearchData(dataScanner);
//				list = (List<LectureVO>) dao.do_searchName(vo);
////				dao.dispUser(list);
//				break;
//			case "5":		//장바구니담기(선택한열 새로운 csv파일에 추가해서 넘김) 수강인원 1증가
//				dataScanner = new Scanner(System.in);
//				System.out.println("장바구니에 담을 데이터를 입력 >>ex)21111007,자바999,003,목요일,15:00,17:00,103,12/30,ssssss,20,5,4");
//				String inputData = dataScanner.nextLine();
//				inputData = inputData.trim(); 	
//
//				
//				String[] dataArray = inputData.split(",");
//				LectureVO lvo = new LectureVO(dataArray[0],dataArray[1],dataArray[2],dataArray[3]
//						,dataArray[4],dataArray[5],dataArray[6],dataArray[7],dataArray[8]
//								,dataArray[9],dataArray[10],dataArray[11]);
//				
//				dao.do_save(lvo);
//				break;
//			case "6":		//강의정보조회(개인 로그인후에 강의코드로 조회)
//							//여러정보가 필요하므로 필요한 정보 출력
//				vo = dao.getLecturePlanSearchData(dataScanner);
//				list = (List<LectureVO>) dao.do_searchCode(vo);
////				dao.dispLecturePlan(list);
//				break;
//			case "7":		//로그인에 따라 학과별 별점합이 높은 5개 과목출력
//				vo1 = dao.getSearchData();
//				list = (List<LectureVO>) dao.do_StarAvg(vo1);
////				dao.dispStarSum(list);
//				break;
//				default:
//					break;
//			}
//			  
//		} while(!input01.equalsIgnoreCase("Q"));
//		System.out.println("프로그램 종료되었습니다.");
//		
//		
//	}
//
//}
