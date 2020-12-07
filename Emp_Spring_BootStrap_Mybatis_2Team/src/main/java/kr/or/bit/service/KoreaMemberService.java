package kr.or.bit.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.bit.dao.KoreaMemberDao;
import kr.or.bit.vo.KoreaMember;

@Service
public class KoreaMemberService {
private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public int insertMember(KoreaMember koreamember) {
		int result = 0;
		KoreaMemberDao kmDao = sqlsession.getMapper(KoreaMemberDao.class);
		result=kmDao.insertMember(koreamember);
		return result;
	}
	
	public List<KoreaMember> showKmList(){
		KoreaMemberDao kmDao = sqlsession.getMapper(KoreaMemberDao.class);
		List<KoreaMember> kmlist = kmDao.getKmList();
		return kmlist;
	}
}
