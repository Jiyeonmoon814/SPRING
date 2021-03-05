package kr.or.bit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.bit.dto.EmpDto;
import kr.or.bit.service.EmpService;

@RestController
public class EmpAjaxRestController {
	
	@Autowired
	private EmpService empService;
	
	@RequestMapping(value="searchByEmpno.do")
	public List<EmpDto> ajaxResponseBody(String empno){
		List<EmpDto> list = empService.searchAjax(empno);
		
		
		return list;
	}
}