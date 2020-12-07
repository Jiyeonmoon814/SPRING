package kr.or.bit.dao;

import java.util.List;

import kr.or.bit.vo.Emp;

public interface EmpDao {

	public List<Emp> getEmpAllList();

	public int getEmpAllCount();
	
	public List<Emp> getPagedEmpList(int currpage, int pagesize);
	
	public int insertAccount(Emp emp);
	
	//추가(12.02)
	public int insertEmp(Emp emp);
	
	public String IdIsExist(String id);
	
	public Emp getDetailEmp(String empno);
	
	public int updateEmp(Emp e);
	
	public int deleteEmp(String empno);
	
	public Emp getEmpByEmpno(int empno);
	
	public List<Emp> searchMember(Emp emp);
	
	public List<Emp> searchAjax(String name);
	
	
	
}
