package adaptor;

import model.User;

/**
 * @author Zuowei
 *
 */
public interface UserAdaptor {
	
	public String getUserInfo(int id);
	
	public String createNewUser(String userName, String email, String password, int age, String phoneNum);
	
	public String updateUserInfo(User user);
	
}
