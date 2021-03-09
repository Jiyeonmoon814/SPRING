package kr.or.bit.dao;

import java.util.List;
import java.util.Map;

import kr.or.bit.dto.EmpDto;

public interface EmpDao {
	public List<EmpDto> selectAllList();
	public EmpDto selectByEmpno(String empno) ;
	public void insertEmp(EmpDto emp) ;
	public void updateEmp(EmpDto emp);
	public void deleteEmp(String empno);
	public List<EmpDto> searchList(Map<String, Integer> map);
	public List<EmpDto> searchforsal(String min_sal, String max_sal);
	public List<EmpDto> selectByEmpnoByLike(String empno);

}
