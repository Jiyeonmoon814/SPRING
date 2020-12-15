package kr.or.bit.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.bit.dao.MemberDao;
import kr.or.bit.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public String insertMember(Member member) {
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		member.setPwd(this.bCryptPasswordEncoder.encode(member.getPwd()));
		
		String url = "";
		if(memberdao.insertMember(member) > 0) {
			url = "/index.member";
		}else {
			url = "/signup.member";
		}
		return url;
	}
	
}
