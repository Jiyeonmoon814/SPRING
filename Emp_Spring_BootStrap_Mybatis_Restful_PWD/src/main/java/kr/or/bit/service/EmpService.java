package kr.or.bit.service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.vo.Emp;

@Service
public class EmpService {
	private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public Map<String,Object> showEmpList(String ps, String cp){
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		if(ps == null || ps.trim().equals("")) {	// ps값이 없는 경우 기본 페이지 크기 지정
			ps = "5";
		}
		if(cp == null || cp.trim().equals("")) {	// cp값이 없는 경우 현재 페이지 지정
			cp = "1";
		}
		
		int pagesize = Integer.parseInt(ps);
		int currpage = Integer.parseInt(cp);
		int totalcount = empdao.getEmpAllCount();
		int pagecount = 0;
		
		if (totalcount % pagesize == 0) {
			pagecount = totalcount / pagesize;
		} else {
			pagecount = (totalcount / pagesize) + 1;
		}
		List<Emp> emplist = empdao.getPagedEmpList(currpage, pagesize);
		
		Map<String,Object> map = new HashMap<>();
		map.put("emplist", emplist);
		map.put("pagesize", pagesize);
		map.put("pagecount", pagecount);
		map.put("currpage", currpage);
		
		return map;
	}
	
	public Emp editEmp(String empno) {
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		// 요청 받은 empno 검색 후 emp에 저장
		Emp emp = empdao.getDetailEmp(empno);
		return emp;
	}
	
	public String updateEmp(Emp emp) {
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		String msg = "";
		String url = "";
		
		if(empdao.updateEmp(emp) > 0) {
			//msg = emp.getEname()+" 정보 수정이 완료되었습니다.";
			url = "/ShowEmpList.member";
		}else {
			//msg = "Update Failed";
			url = "/EditEmp.member?empno="+emp.getEmpno();
		}
		
		return "redirect:" + url;
	}
	
	public String deleteEmp(String empno) {
		
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		String msg = "";
		String url = "";
		
		int result = empdao.deleteEmp(empno);
		System.out.println(result);
		
		if(result > 0) {
			msg = "정상 삭제되었습니다.";
		}else {
			msg = "삭제 실패.";
		}
		
		url="/ShowEmpList.member";
		
		return "redirect:" + url;
	}
	
	
	public String empFileUpload(Emp emp, HttpServletRequest request) {
		
		CommonsMultipartFile file = emp.getFile();
		System.out.println("file:" + file);
		String filename = file.getOriginalFilename();
		//String path = request.getServletContext().getRealPath("/upload");
		String path = "C:\\develop\\Spring\\Labs\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Emp_Spring_BootStrap_Mybatis_2Team\\upload";
		
		String fpath = path + "\\" + filename;

		try {
			if (!filename.equals("")) { // 실 파일 업로드
				FileOutputStream fs = new FileOutputStream(fpath);
				fs.write(file.getBytes());
				fs.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		emp.setFilename(filename);
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		//String file = (String) fileNames.nextElement();
		//System.out.println("file : " + file);
		
		empdao.insertAccount(emp);

		// 여기까지 수행하면 upload 폴더에 파일이 저장
		return "redirect:/index.member";
	}
	
	
	
	public String joinok(Emp emp, HttpServletRequest request) {
		
		List<CommonsMultipartFile> files = emp.getFiles();
		List<String> filenames = new ArrayList<String>(); // 파일명관리

		if (files != null && files.size() > 0) { // 최소 1개의 업로드가 있다면
			for (CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				System.out.println(request.getServletPath());
				//String path = request.getServletContext().getRealPath("/upload");
				String path = "C:\\develop\\Spring\\Labs\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Emp_Spring_BootStrap_Mybatis_2Team\\upload";
				
				String fpath = path + "\\" + filename;
				
				try {
					if (!filename.equals("")) { // 실 파일 업로드
						FileOutputStream fs = new FileOutputStream(fpath);
						fs.write(multifile.getBytes());
						fs.close();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				filenames.add(filename); // 파일명을 별도 관리 (DB insert)
				System.out.println("filelist size: " + filenames.size());
			}

		}

		// DB 파일명 저장
		emp.setFilename(filenames.get(0));
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		try {
			empdao.insertAccount(emp);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/index.htm"; // 문자열로 리턴
		
	}
	
	public List<Emp> memberSearch(Emp emp){
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		List<Emp> emplist = empdao.searchMember(emp);
		
		return emplist;
	}
	
}
