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
public class EmpAjaxService {
	private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public List<Emp> showEmpList(){
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		List<Emp> emplist = empdao.getEmpAllList();
		return emplist;
	}
	
	public Emp editEmp(String empno) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		// 요청 받은 empno 검색 후 emp에 저장
		Emp emp = empdao.getDetailEmp(empno);
		return emp;
	}
	
	public int updateEmp(Emp emp) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		return empdao.updateEmp(emp);
	}
	
	public int insertEmp(Emp emp) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		return empdao.insertEmp(emp);
	}
	
	public int deleteEmp(String empno) {
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		int result = empdao.deleteEmp(empno);
		return result;
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
		return "redirect:/ShowEmpList.member";
	}
	
	
	public List<Emp> searchAjax(String name){
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		List<Emp> emplist = empdao.searchAjax(name);
		return emplist;
	}
	
}
