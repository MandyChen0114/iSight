package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adaptor.RecordAdaptorImp;
import adaptor.UserAdaptorImp;
import model.Record;
import model.User;

/**
 * Servlet implementation class iSight
 */
public class ISightQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int GET_USER_INFO = 1;
	private static final int CREATE_NEW_USER = 2;
	private static final int UPDATE_USER_INFO = 3;
	private static final int GET_ALL_RECORDS_BY_USER_ID = 4;
	private static final int CREATE_NEW_RECORD = 5;
	private static final int DELETE_RECORD = 6;
	
	private UserAdaptorImp userAdaptorImp = new UserAdaptorImp();
	private RecordAdaptorImp recordAdaptorImp = new RecordAdaptorImp();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISightQuery() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryIdStr = request.getParameter("queryId");
		
		int queryId = Integer.parseInt(queryIdStr);
		
		String result = "";
		
		switch (queryId) {
		case GET_USER_INFO:
			int id = Integer.valueOf(request.getParameter("id"));
			result = userAdaptorImp.getUserInfo(id);
			break;
		case CREATE_NEW_USER:
			String userName = request.getParameter("userName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			int age = Integer.valueOf(request.getParameter("age"));
			String phoneNum = request.getParameter("phoneNum");
			result = userAdaptorImp.createNewUser(userName, email, password, age, phoneNum);
			break;
		case UPDATE_USER_INFO:
			User user = new User();
			user.setUserName(request.getParameter("userName"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setAge(Integer.valueOf(request.getParameter("age")));
			user.setPhoneNum(request.getParameter("phoneNum"));
			user.setId(Integer.valueOf(request.getParameter("id")));
			result = userAdaptorImp.updateUserInfo(user);
			break;
		case GET_ALL_RECORDS_BY_USER_ID:
			int userId = Integer.parseInt(request.getParameter("userId"));
			result = recordAdaptorImp.getAllRecordsByUserId(userId);
			break;
		case CREATE_NEW_RECORD:
			Record record = new Record();
			record.setUserId(Integer.parseInt(request.getParameter("userId")));
			record.setTestId(Integer.parseInt(request.getParameter("testId")));
			record.setTimestamp(request.getParameter("timestamp"));
			record.setTimestamp(request.getParameter("result"));
			result = recordAdaptorImp.createNewRecord(record);
			break;
		case DELETE_RECORD:
			userId = Integer.parseInt(request.getParameter("userId"));
			result = recordAdaptorImp.deleteRecord(userId);
			break;
		default:
			break;
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print(result);
	}
}
