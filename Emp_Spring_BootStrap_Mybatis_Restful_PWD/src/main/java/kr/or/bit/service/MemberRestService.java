package kr.or.bit.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.bit.dao.MemberDao;
import kr.or.bit.vo.Member;

@Service
public class MemberRestService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public List<Member> listMember() throws Exception {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		return memberdao.getMemberList();
	}
	
	public Member readMember(String userid) throws Exception {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		return memberdao.getMemberByUserid(userid);
	}
	
	public void updateMember(Member member) throws Exception {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		memberdao.updateMember(member);
	}

	public void removeMember(String userid) throws Exception {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		memberdao.deleteMember(userid);	
	}
	
	public void registerMember(Member member) throws Exception {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		memberdao.insertMember(member);
	}

	public List<Member> searchMemberRest(String userid){
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		return memberdao.searchMemberRest(userid);
	}

	public String checkId(String userid) {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		System.out.println("userid: " + userid);
		String result = "";
		try {
			if(memberdao.checkId(userid).equals(userid)) {
				System.out.println("useridcheck:" + userid);
				result = "true";
			}else {
				System.out.println("checkfalse");
				result = "false";
			}
		} catch (Exception e) {
			System.out.println("responsedata : false");
		}
		return result;
	}



}
