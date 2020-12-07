package kr.or.bit.dao;

import kr.or.bit.vo.KoreaMember;

public interface KoreaMemberDao {
	//회원가입
	public int insertMember(KoreaMember koreamember);
	//아이디 중복 검사
	public int idCheck(String id);
	//로그인
	public int login(String id, String pwd);
	//멤버 정보 
	public KoreaMember getMember(String id);
	//회원수정
	public int updateMember(KoreaMember koreamember);
}
