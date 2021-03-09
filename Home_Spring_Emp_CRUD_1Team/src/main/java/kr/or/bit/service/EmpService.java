package kr.or.bit.service;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.EmpDto;

@Service
public class EmpService {
	// DB작업
	   // Mybatis >> Root IOC >> DI
	   private SqlSession sqlsession;

	   @Autowired
	   public void setSqlsession(SqlSession sqlsession) {
	      this.sqlsession = sqlsession;
	      System.out.println(this.sqlsession);
	   }
	   
	   public List<EmpDto> selectAllList(){
		   List<EmpDto> list = null;
			// mapper 를 통한 인터페이스 연결
			EmpDao empdao = sqlsession.getMapper(EmpDao.class);
			list = empdao.selectAllList();
			return list;
	   }
	   
	// 검색
	   public List<EmpDto> search(HttpServletRequest request){
	      int keyValue = Integer.parseInt(request.getParameter("search"));
	      int empno = request.getParameter("empno") == null ? 0 : 1;
	      int deptno = request.getParameter("deptno")  == null ? 0 : 1;
	      int mgr = request.getParameter("mgr")  == null ? 0 : 1;
	      
	      Map<String, Integer> paramMap = new HashMap<String, Integer>();
	      paramMap.put("keyValue", keyValue);
	      paramMap.put("empno", empno);
	      paramMap.put("deptno", deptno);
	      paramMap.put("mgr", mgr);
	      for (Map.Entry<String, Integer> m : paramMap.entrySet()) {
	         System.out.println(m.getKey() + "/" + m.getValue() + "-");
	      }
	      EmpDao dao = sqlsession.getMapper(EmpDao.class);
	      List<EmpDto> resultList = dao.searchList(paramMap);
	      return resultList;
	   }
	   
	   public EmpDto selectByEmpno(String empno) {
		   EmpDto empdto=null;
		   EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		   empdto = empdao.selectByEmpno(empno);
		   return empdto;
	   }
	   
	   public List<EmpDto> selectByEmpnoByLike(String empno) {
		    List<EmpDto> empdtoList=null;
		    EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		    empdtoList = empdao.selectByEmpnoByLike(empno);
		    return empdtoList;
		}
	   
	   
	   public void updateEmp(EmpDto empdto, HttpServletRequest request) {
		   CommonsMultipartFile multifile = empdto.getFile();
			String filename = multifile.getOriginalFilename();
			String path = request.getServletContext().getRealPath("/upload");
			System.out.println(path);
			String fpath = path + "/" + filename;
			System.out.println(empdto.getHiredate());

			if (!filename.equals("")) {
				// 실 파일 업로드
				try {
					FileOutputStream fs = new FileOutputStream(fpath);
					fs.write(multifile.getBytes());
					fs.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			empdto.setFilename(filename);
		   EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		  empdao.updateEmp(empdto);
	   }
	   
	   public void insertEmp(EmpDto emp,HttpServletRequest request) {
			CommonsMultipartFile multifile =emp.getFile();
			String filename  = multifile.getOriginalFilename();
			String path = request.getServletContext().getRealPath("/upload");
			System.out.println(path);
			String fpath = path + "/" + filename;
			
			if(!filename.equals("")) {
				//실 파일 업로드
				try {
					FileOutputStream fs = new FileOutputStream(fpath);
					fs.write(multifile.getBytes());
					fs.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			emp.setFilename(filename);
		   EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		  empdao.insertEmp(emp);
	   }
	   public void deleteEmp(String empno) {
		   EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		  empdao.deleteEmp(empno);
	   }
	   
	   @Transactional
	   public List<EmpDto> searchforsal(String min_sal, String max_sal) throws Exception{
		   
		   EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		   List<EmpDto> list = null;
		   if(Integer.parseInt(min_sal) > Integer.parseInt(max_sal) && min_sal.equals("0")) {
			   Exception error =new ArithmeticException();
			   throw error;
		   }else {
			   list=empdao.searchforsal(min_sal, max_sal);
		   }
		   
		   
		   return list;
	   }
	   
	   public List<EmpDto> searchAjax(String empno) {
			EmpDao dao = sqlsession.getMapper(EmpDao.class);
			List<EmpDto> resultList = dao.selectByEmpnoByLike(empno);
			return resultList;
		}
}
