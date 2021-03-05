package kr.or.bit.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import kr.or.bit.dto.EmpDto;
import kr.or.bit.service.EmpService;

@Controller
public class EmpController {
	private EmpService empService;
	
	@Autowired
	private View jsonview;

	@Autowired
	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	@RequestMapping(value = "/EmpManagement.do", method = RequestMethod.GET)
	public String selectAllList(Model model) {
		List<EmpDto> list = empService.selectAllList();
		model.addAttribute("empList", list);
		return "EmpManagement_Main";
	}

	@RequestMapping("/EmpSearch.do")
	public String search(HttpServletRequest request, Model model) {
		List<EmpDto> searchList = empService.search(request);
		model.addAttribute("searchList", searchList);
		return "EmpSearch";
	}

	@RequestMapping(value = "search.do")
	public @ResponseBody EmpDto selectByEmpno(String empno) {
		EmpDto empdto = empService.selectByEmpno(empno);
		return empdto;
	}
	
	@RequestMapping(value="SearchByEmpno.do")
	public @ResponseBody List<EmpDto> searchByEmpnoByLike(String empno){
	    List<EmpDto> list = empService.selectByEmpnoByLike(empno);
	    return list;
	}

	@RequestMapping(value = "EmpRegister.do", method = RequestMethod.GET)
	public String insertEmpPage() {
		System.out.println("InsertEmpPage");
		return "EmpRegister";
	}

	@RequestMapping(value = "EmpRegister.do", method = RequestMethod.POST)
	public String insertEmp(EmpDto empdto, HttpServletRequest request) {
		// 넘어올때 널값 있으면 안됨 바로 400 얄짤 없음
		empService.insertEmp(empdto, request);
		return "redirect:EmpManagement.do";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "EmpEdit.do", method = RequestMethod.POST)
	public String updateEmp(EmpDto empdto, BindingResult result, HttpServletRequest request) {

		empService.updateEmp(empdto, request);
		return "redirect:EmpManagement.do";
	}

	// 포스트핸들러인터셉트
	@RequestMapping(value = "EmpEdit.do", method = RequestMethod.GET)
	public String updateEmpPage(String empno, Model model) {
		System.out.println("updateEmpPage");
		return "EmpEdit";
	}

	// 포스트핸들러인터셉트
	@RequestMapping(value = "EmpDetail.do", method = RequestMethod.GET)
	public String DetailPage(String empno, Model model) {
		System.out.println("EmpDetailPage");
		
		return"EmpDetail";
	}
 
	@RequestMapping(value = "EmpDelete.do", method = RequestMethod.GET)
	public String deleteEmp(String empno, Model model) {
		empService.deleteEmp(empno);
		return "redirect:EmpManagement.do";
	}
	
	//연봉으로 검색하기 
	@RequestMapping(value="searchforsal.do")
	public View searchforsal(String min_sal, String max_sal,Model model){
		
		List<EmpDto> list = null;
		try {
			list = empService.searchforsal(min_sal,max_sal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		model.addAttribute("emp", list);
		
		return jsonview;  //private View jsonview 타입으로 리턴
	}
	
	

}
