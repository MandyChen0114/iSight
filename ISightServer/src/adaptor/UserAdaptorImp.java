package adaptor;

import com.google.gson.Gson;

import db.DBAdaptor;
import model.User;

/**
 * @author Zuowei
 *
 */
public class UserAdaptorImp implements UserAdaptor {

	@Override
	public String getUserInfo(int id) {
		User user = DBAdaptor.getUserInfo(id);
		user.getClass();
		String result = new Gson().toJson(user);
		return result;
	}

	@Override
	public String createNewUser(String userName, String email, String password, int age, String phoneNum) {
		User user = new User();
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(password);
		user.setAge(age);
		user.setPhoneNum(phoneNum);
		boolean result = DBAdaptor.createNewUser(user);
		if (result) {
			String response = new Gson().toJson(user);
			return response;
		}
		user = new User();
		user.setId(0);
		String response = new Gson().toJson(user);
		return response;
	}

	@Override
	public String updateUserInfo(User user) {
		boolean result = DBAdaptor.updateUserInfo(user);
		return result + "";
	}

}
