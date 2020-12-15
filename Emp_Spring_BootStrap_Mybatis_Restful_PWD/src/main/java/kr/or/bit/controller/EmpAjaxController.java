package kr.or.bit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import kr.or.bit.service.EmpAjaxService;
import kr.or.bit.vo.Emp;

@Controller
public class EmpAjaxController {
	
	@Autowired
	private View jsonview;
	
	@Autowired
	private EmpAjaxService empajaxservice;
	
	@RequestMapping(value="/ShowEmpListAjax.member")
	public String showEmpList() {
		return "ajax/EmpList";
	}
	
	@RequestMapping(value="/EmpListAjax.member")
	public View empList(Model model) {
		List<Emp> list = empajaxservice.showEmpList();
		model.addAttribute("emp", list);
		return jsonview;
	}
	
	@RequestMapping(value="/UpdateEmpAjax.member",method=RequestMethod.GET)
	public View updateEmp(String empno, Model model) {
		model.addAttribute("emp", empajaxservice.editEmp(empno));
		return jsonview;
	}
	
	@RequestMapping(value="/UpdateEmpAjax.member",method=RequestMethod.POST)
	public View updateEmp(Emp emp, Model model) {
		empajaxservice.updateEmp(emp);
		List<Emp> list = empajaxservice.showEmpList();
		model.addAttribute("emp", list);
		return jsonview;
	}
	
	@RequestMapping("/DeleteEmpAjax.member")
	public View deleteEmp(String empno, Model model) {
		empajaxservice.deleteEmp(empno);
		List<Emp> list = empajaxservice.showEmpList();
		model.addAttribute("emp", list);
		return jsonview;
	}
	
	
	@RequestMapping("/insertAjax.member")
	public View insertEmp(Emp emp, Model model) {
		empajaxservice.insertEmp(emp);
		List<Emp> list = empajaxservice.showEmpList();
		model.addAttribute("emp", list);
		return jsonview;
	}
	
	
	@RequestMapping("/searchAjax.member")
	public View memberSearch(String name, Model model) {
		System.out.println("ename: " + name);
		List<Emp> list = empajaxservice.searchAjax(name);
		model.addAttribute("emp",list);
		return jsonview;
	}
}
