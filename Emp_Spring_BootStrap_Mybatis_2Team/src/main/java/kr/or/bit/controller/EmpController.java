package kr.or.bit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.bit.service.EmpService;
import kr.or.bit.vo.Emp;

@Controller
public class EmpController {
	
	private EmpService empservice;
	@Autowired
	public void setEmpservice(EmpService empservice) {
		this.empservice = empservice;
	}
	
	@RequestMapping("/index.member")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/ShowEmpList.member")
	public String showEmpList(String ps, String cp, Model model) {
		
		Map<String,Object> map = empservice.showEmpList(ps, cp);
		model.addAttribute("map", map);
		
		return "EmpList";
	}
	
	@RequestMapping(value = "/EditEmp.member",method = RequestMethod.GET )
	public String editEmp(String empno, Model model) {
		System.out.println("empno:" + empno);
		Emp emp = empservice.editEmp(empno);
		model.addAttribute("emp",emp);
		return "EditEmp";
	}
	
	@RequestMapping("/UpdateEmp.member")
	public String updateEmp(Emp emp) {
		
		String redirect = empservice.updateEmp(emp);
		return redirect;
	}
	
	@RequestMapping("/DeleteEmp.member")
	public String deleteEmp(String empno) {
		
		String redirect = empservice.deleteEmp(empno);
		return redirect;
	}
	
	
	@RequestMapping("/upload.member")
	public String empFileUpload(Emp emp, HttpServletRequest request) {
		String redirect = empservice.empFileUpload(emp, request);
		return redirect;
	}
	
	
	@RequestMapping("/ChartEmp.member")
	public String chartEmp() {
		return "EmpChart";
	}
	
	@RequestMapping(value="/Joinform.member",method=RequestMethod.GET)
	public String joinForm() {
		return "EmpSignup";
	}
	
	@RequestMapping("/join.member")
	public String joinok(Emp emp, HttpServletRequest request) {
		String redirect = empservice.joinok(emp, request);
		return redirect;
	}
	
	
	@RequestMapping("/search.member")
	public String memberSearch(Emp emp, Model model) {
		System.out.println("ename: " + emp.getEname());
		List<Emp> list = empservice.memberSearch(emp);
		model.addAttribute("list",list);
		return "EmpSearch";
	}
}
