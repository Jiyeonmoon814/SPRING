package ncontroller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {
	
	private NoticeDao noticedao;
	@Autowired
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}
	/*
	 	  method  의  리턴타입이 [String] 이면  [리턴값] 은   [뷰의 이름] 으로 사용된다
	 	  public ModelAndView notices()....
	 	  {
	 	  	    ModelAndView mv = new ModelAndView();
	  			mv.addObject("list", list);
	  			mv.setViewName("notice.jsp");
	 	  }
	      public String notices(Model model)....
	 	  {
	 	  	  model.addAttribute("list",list);  //view 까지 forward >> ${list}
	 	  	  return "notice.jsp"
	 	  }
	 */
	//글목록보기
	@RequestMapping("notice.htm")  //    /customer/notice.htm
	public String notices(String pg , String f , String q , Model model) {
		
				//default
				int page=1;
				String field="TITLE";
				String query ="%%";
				
				if(pg != null && !pg.equals("")) {
					page = Integer.parseInt(pg);
				}
				
				if(f != null && !f.equals("")) {
					field = f;
				}
				
				if(q != null && !q.equals("")) {
					query = q;
				}
				
				//DAO 작업
				List<Notice>  list = null;
				try {
					      list = noticedao.getNotices(page, field, query);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				model.addAttribute("list", list); // view까지 forward 되요
				//notice.jsp
				//<c:forEach items="${list}" var="n">
		        //localhost:8090/website/customer/list.do
				//customer폴더안에  .. notice.jsp
		 return "notice.jsp";
	}
	//글상세보기
	@RequestMapping("noticeDetail.htm") //    /customer/noticeDetail.htm
	public String noticeDetail(String seq , Model model) {
		 //작성해보세요
		 Notice notice= null;
		 try {
			   notice = noticedao.getNotice(seq);
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		 
		 model.addAttribute("notice", notice);
		 
		 return "noticeDetail.jsp";
	}
	
	//글쓰기 화면 GET
	@RequestMapping(value="noticeReg.htm",method=RequestMethod.GET)
	public String form() {
		return "noticeReg.jsp";
	}
	
	//글쓰기 처리 POST (파일업로드)
	@RequestMapping(value="noticeReg.htm",method=RequestMethod.POST)
	public String noticeReg(Notice n , HttpServletRequest request) throws IOException, ClassNotFoundException, SQLException {
		   //실제 글쓰기 처리
		   //private CommonsMultipartFile file; 
		   String filename = n.getFile().getOriginalFilename();
		   String path = request.getServletContext().getRealPath("/customer/upload");
		   
		   String fpath = path + "\\" + filename;
		   FileOutputStream fs = new FileOutputStream(fpath);
		   fs.write(n.getFile().getBytes());
		   fs.close();
		   
		   //파일명
		   n.setFileSrc(filename);
		   
		   //실제 DAO 연동
		   noticedao.insert(n);
		   
		   //글을 쓰면 >> 리스트 (location.href  or  redirect)
		   //새로운 요청 ....
		   //Spring : redirect:notice.htm
		   //Servlet , jsp :  location.href  or   response.sendredirect
		   
		   
		   return "redirect:notice.htm";
	   }
	
	//글수정하기 (화면 : select .... where seq=?) : GET   : seq (parameter)
	//noticedao.getNotice(seq)
	//Model model  >> 화면 >> 데이터 >> noticeEdit.jsp
	//public String noticeEdit(String seq , Model model)
	//글수정 화면 get 
		@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.GET)
		public String noticeEditPage(String seq , Model model) {
			 Notice notice= null;
			 try {
				   notice = noticedao.getNotice(seq);
			} catch (Exception e) {
				 System.out.println(e.getMessage());
			}
			 
			 model.addAttribute("notice", notice);
			 
			 return "noticeEdit.jsp";
		}
		
		
	//글수정하기(처리 : update..... where seq=?) : POST 
	//parameter Notice n >> Insert  동일  >> 무조건 파일 첨부 하는 형태로
	//return "redirect:noticeDetail.htm?seq="+n.getSeq();
	//public String noticeEdit(Notice n , HttpServletRequest request) 
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.POST)
	public String noticeEdit(Notice n, HttpServletRequest request) throws IOException, ClassNotFoundException, SQLException {
		
		String filename = n.getFile().getOriginalFilename();
		String path = request.getServletContext().getRealPath("/customer/upload");
		   
		String fpath = path + "\\" + filename;
		FileOutputStream fs = new FileOutputStream(fpath);
		fs.write(n.getFile().getBytes());
		fs.close();
		   
		//파일명
		n.setFileSrc(filename);
		   
		//실제 DAO 연동
		noticedao.update(n);
		   
		return "redirect:noticeDetail.htm?seq="+n.getSeq();
	}
	
	
	
	//글삭제하기 (페이지 새로 요청)
	//hint) location.href 
	//return "redirect:notice.htm"
	
	@RequestMapping("noticeDel.htm")
	public String noticeDel(String seq) throws ClassNotFoundException, SQLException {
		noticedao.delete(seq);
		return "redirect:notice.htm";
	}
	
				
		//글쓰기 화면 (noticeReg.htm)
		//@RequestMapping(value="noticeReg.htm" , method=RequestMethod.GET)
	
	
}

