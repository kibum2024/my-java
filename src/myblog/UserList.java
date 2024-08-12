package myblog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserList {
	private List<User> users;
	
	public UserList() {
		users = new ArrayList<>();
	}
	
	public User findUserId(String paramId, String paramPassword) {
		Optional<User> foundUser = users.stream()
				.filter(user -> user.getUserId().equals(paramId))
				.findFirst();
		
		if (foundUser.isPresent()) {
			User user = foundUser.get();
			if (user.getUserPassword().equals(paramPassword)) {
				System.out.println("정상적으로 로그인 되었습니다.");
				return foundUser.get();
			} else {
				System.out.println("비밀번호가 틀립니다. 다시 입력하세요.");
				return null;
			}
		} else {
			System.out.println(paramId + "님은 가입된 회원이 아닙니다. 회원가입을 하시기바랍니다.");
		}
			
		return foundUser.orElse(null);
	}
	
	public void addUserInfo(String paramId, String paramName, String paramPassword) {
		Optional<User> foundUser = users.stream()
				.filter(user -> user.getUserId().equals(paramId))
				.findFirst();
		
		if (foundUser.isPresent()) {
			System.out.println("이미 등록된 회원입니다. 로그인하시기바랍니다.");
		} else {
			User user = new User(paramId, paramName, paramPassword);
			users.add(user);
			System.out.println(paramName + "님은 회원가입을 축하드립니다.");
		}
	}

}
