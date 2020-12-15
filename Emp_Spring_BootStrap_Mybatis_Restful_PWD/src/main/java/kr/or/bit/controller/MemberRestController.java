package kr.or.bit.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import kr.or.bit.service.MemberRestService;
import kr.or.bit.vo.Emp;
import kr.or.bit.vo.Member;

@RestController
//@RequestMapping("/members")
public class MemberRestController {

	
	@Autowired
	private MemberRestService service;
	
	//날짜 String을 Date타입으로 변환 
	//해당 Controller로 들어오는 요청에 대해
	//추가적인 설정을 하고 싶을 때 사용할 수 있다.
	//또한 모든 요청전에 InitBinder를 선언한 메소드가 실행된다.
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="/members.member", method=RequestMethod.GET)
	public List<Member> listMember() {
		List<Member> memberList = null;
		try {
			memberList=service.listMember();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	//아이디 검색
	@RequestMapping(value="/members/search.member",method=RequestMethod.POST)
	public List<Member> memberSearch(String userid) {
		System.out.println("userid: " + userid);
		List<Member> list = service.searchMemberRest(userid);
		return list;
	}
	
	// 조회 
	@RequestMapping(value="/members/{userid}.member", method=RequestMethod.GET)
	public Member viewMember(@PathVariable("userid") String userid) {
		Member member = null;
		try {
			member = service.readMember(userid);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	// Update 시작 
	@RequestMapping(value="/members/{userid}/update.member", method= {RequestMethod.GET })
	public Member editMember(@PathVariable("userid") String userid) {
		Member member = null;
		try {
			member = service.readMember(userid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	
	// Update 
	@RequestMapping(value="/members/{userid}.member", method= {RequestMethod.PUT })
    public ResponseEntity<String> editMember(@PathVariable("userid") String userid, @RequestBody Member member) {
        
		ResponseEntity<String> entity = null;
        try {
            
            service.updateMember(member);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }
		
		
	// Delete
	@RequestMapping(value="/members/{userid}.member", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteMember(@PathVariable("userid") String userid) {
		
		ResponseEntity<String> entity = null;

		System.out.println("userid : " + userid);
		
		try {
			service.removeMember(userid);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	// Insert
	@RequestMapping(value="/members/new.member", method= {RequestMethod.POST })
    public ResponseEntity<String> insertMember(@RequestBody Member member) {
		System.out.println("memberDto" + member.toString());
		ResponseEntity<String> entity = null;
		
        try {
            service.registerMember(member);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(entity);
        
        return entity;
    }
	
	@RequestMapping(value="/members/Idcheck.member",method = RequestMethod.GET)
	public String idcheck(String userid) {
		System.out.println("controller: " + userid);
		String result = service.checkId(userid);
		return result;
	}


}
