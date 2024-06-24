package servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.LinkedHashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet/reserve")
public class RESERVEServlet extends HttpServlet  implements Serializable {
	private static final long serialVersionUID = 1L;
	// 存放 的歷史紀錄
	private List<Map<String, Object>> reserveList = new CopyOnWriteArrayList<>();
	
	// 查詢使用
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
				
				// 回應內容:
				//resp.getWriter().print(reserveList);
				// 回應內容(含 HTML):
		resp.getWriter().print("<html>");
		resp.getWriter().print("<head>");
		resp.getWriter().print("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css\">");
		resp.getWriter().print("</head>");
		resp.getWriter().print("<body style=\"padding: 15px\">");
		resp.getWriter().print("<table class=\"pure-table pure-table-bordered\">");
		
		resp.getWriter().print("<thead>");
		resp.getWriter().print("<tr>");
		resp.getWriter().print("<th>Name</th>");
		resp.getWriter().print("<th>Email</th>");
		resp.getWriter().print("<th>Phone</th>");
		resp.getWriter().print("<th>People</th>");
		resp.getWriter().print("<th>Time</th>");
		resp.getWriter().print("<th>Note</th>");
		
		resp.getWriter().print("</tr>");
		resp.getWriter().print("</thead>");
				
		resp.getWriter().print("<tbody>");
				
		reserveList.forEach(map -> {
				try {
				    resp.getWriter().print("<tr>");
					resp.getWriter().print("<td>" + map.get("name") + "</td>");
					resp.getWriter().print("<td>" + map.get("email") + "</td>");
					resp.getWriter().print("<td>" + map.get("phone") + "</td>");
					resp.getWriter().print("<td>" + map.get("people") + "</td>");
					resp.getWriter().print("<td>" + map.get("time") + "</td>");
					resp.getWriter().print("<td>" + map.get("note") + "</td>");
				    resp.getWriter().print("</tr>");
				 } catch (Exception e) {
					 e.printStackTrace();	
				 }
		 });
				
		 resp.getWriter().print("</tbody>");
				
		 resp.getWriter().print("</table>");
		 resp.getWriter().print("<p>");
		 resp.getWriter().print("<a href=\"/JavaWeb/bmi.html\" class=\"pure-button pure-button-primary\">回上一頁<a>");
		 resp.getWriter().print("</body>");
		 resp.getWriter().print("</html>");
	}

	// 表單使用

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 接收參數
		String userName = req.getParameter("userName");
		String userEmail = req.getParameter("userEmail");
		String userPhone = req.getParameter("userPhone");
		String people = req.getParameter("people");
		String time = req.getParameter("time");
		String note = req.getParameter("note");
		
		
		// 建立 Map 集合放置表單與reserve的內容
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("name", userName);
		map.put("email", userEmail);
		map.put("phone", userPhone);
		map.put("people", people);
		map.put("time", time);
		map.put("note", note);
		
		// 將資料儲存到reserveList 集合中
		reserveList.add(map);
		
		// 回應內容:
		 resp.getWriter().print("預約成功，感謝您的預約！");
	        resp.sendRedirect(req.getContextPath() + "/servlet/reserve");
	    }
	}	
