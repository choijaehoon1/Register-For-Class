/**
 *<pre>
 * areri.userpage.dao
 * Class Name : UserInfoDao
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
package areri.userpage.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import areri.cmn.DTO;
import areri.cmn.WorkDiv;
import areri.userpage.Const;
import areri.userpage.domain.StudentVO;

/**
 * <pre>
 * areri.userpage.dao
 *    |_ UserInfoDao.java
 * 
 * </pre>
 * @date : 2019. 12. 18.
 * @version : 
 * @author : 주정현
 */
public class UserInfoDao implements WorkDiv {
	
	public UserInfoDao() {
	}
	
	
	/**
	 *  Student.csv의 자료 불러오기
	 * @author 주정현
	 * @param (filepath)파일 경로
	 * @return 
	 */
	public void UserInforeadFile(String filepath) {
		BufferedReader br=null;
		try {
			FileReader fr = new FileReader(filepath);
			br = new BufferedReader(fr);
			String line =""; //라인별로 들어오는 데이터의 저장
			while((line=br.readLine()) !=null){
				String[] data = line.split(",");//한줄씩 가저온 데이터
				StudentVO outVO = new StudentVO(data[0],data[1],data[2],data[3]);
				//System.out.println(outVO);
				Const.studentList.add(outVO);
			}
					
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(br !=null)
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *  Student.csv에 자료 저장하기
	 * @author 주정현
	 * @param (path)파일 경로
	 * @return i
	 */
	public int saveFile(String path) {
		BufferedWriter bw = null;
		int i = 0;
		try {
			FileWriter fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			
			Iterator<StudentVO> iter = Const.studentList.iterator();
			while(iter.hasNext()==true) {
				StudentVO sVO = iter.next();
				StringBuilder sb = new StringBuilder();
				sb.append(sVO.getId()+",");
				sb.append(sVO.getPassword()+",");
				sb.append(sVO.getName()+",");
				sb.append(sVO.getEmail());
				bw.write(sb.toString());
				//마지막라인 "\n"제거
				i++;
				if(i!=Const.studentList.size()) {
					bw.newLine();
				}
				bw.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}
	
	/**
	 *  입력받은 학번 list 형채로 출력
	 * @author 주정현
	 * @param (id)학번
	 * @return list
	 */
	public List<StudentVO> myList(String id){
		List<StudentVO> list = new ArrayList<StudentVO>();
		StudentVO inVO=null;
		UserInforeadFile(Const.FILE_PATH);
		int i; // 어떤 리스트인지 기억하기 위해서
		for(i=0;i<Const.studentList.size();i++) {
		if(Const.studentList.get(i).getId().equals(id)){
			inVO = Const.studentList.get(i);
			list.add(inVO);
			return list;
			}
		}
		return null;
	}
	
	
	@Override
	public int do_save(DTO dto) {
		StudentVO  inVO = (StudentVO) dto;
		int orgCnt   = Const.studentList.size();
		boolean flag = Const.studentList.add(inVO);
		int saveCnt = this.saveFile(Const.FILE_PATH);
		
		return saveCnt - orgCnt;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		StudentVO inVO = (StudentVO) dto;
		int flag = 0;
		for(int i=Const.studentList.size()-1;i>=0;i--) {
			StudentVO vsVO = Const.studentList.get(i);
			if(vsVO.getId().equals(inVO.getId())) {
				Const.studentList.remove(i);
				flag++;
				break;
			}
		}
		return flag;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		StudentVO inVO = (StudentVO) dto;
		StudentVO outVO = null;
		for(int i=Const.studentList.size()-1;i>=0;i--) {
			StudentVO vsVO = Const.studentList.get(i);
			if(vsVO.getId().equals(inVO.getId())) {
				Const.studentList.remove(i);
				outVO = vsVO;
				break;
			}
		}
		return outVO;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}



}
