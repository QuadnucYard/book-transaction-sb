package team.wuse.koob.service;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import team.wuse.koob.dao.UserDAO;
import team.wuse.koob.dto.UserDTO;
import team.wuse.koob.entity.AdminRole;
import team.wuse.koob.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	@Autowired
	AdminRoleService adminRoleService;
	@Autowired
	AdminUserRoleService adminUserRoleService;

	public List<UserDTO> list() {
		List<User> users = userDAO.findAll();

		// Find all roles in DB to enable JPA persistence context.
//        List<AdminRole> allRoles = adminRoleService.findAll();

		List<UserDTO> userDTOS = users
				.stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());

		userDTOS.forEach(u -> {
			List<AdminRole> roles = adminRoleService.listRolesByUser(u.getUsername());
			u.setRoles(roles);
		});

		return userDTOS;
	}

	public boolean isExist(String username) {
		User user = userDAO.findByUsername(username);
		return null != user;
	}

	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	public User get(int uid) {
		return userDAO.getById(uid);
	}

	public User get(String username, String password) {
		return userDAO.getByUsernameAndPassword(username, password);
	}

	public int register(User user) {
		String username = user.getUsername();
		String name = user.getName();
		String phone = user.getPhone();
		String email = user.getEmail();
		String password = user.getPassword();

		username = HtmlUtils.htmlEscape(username);
		user.setUsername(username);
		name = HtmlUtils.htmlEscape(name);
		user.setName(name);
		phone = HtmlUtils.htmlEscape(phone);
		user.setPhone(phone);
		email = HtmlUtils.htmlEscape(email);
		user.setEmail(email);
		user.setEnabled(true);

		if (username.equals("") || password.equals("")) {
			return 0;
		}

		boolean exist = isExist(username);

		if (exist) {
			return 2;
		}

		// 默认生成 16 位盐
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		int times = 2;
		String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

		user.setSalt(salt);
		user.setPassword(encodedPassword);

		userDAO.save(user);

		return 1;
	}

	public void updateUserStatus(User user) {
		User userInDB = userDAO.findByUsername(user.getUsername());
		userInDB.setEnabled(user.isEnabled());
		userDAO.save(userInDB);
	}

	public User resetPassword(String username, String pwd) {
		User userInDB = userDAO.findByUsername(username);
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		int times = 2;
		userInDB.setSalt(salt);
		String encodedPassword = new SimpleHash("md5", pwd, salt, times).toString();
		userInDB.setPassword(encodedPassword);
		return userDAO.save(userInDB);
	}

	public User resetPassword(User user, String pwd) {
		return resetPassword(user.getUsername(), pwd);
	}

	public void editUser(User user) {
		User userInDB = userDAO.findByUsername(user.getUsername());
		userInDB.setName(user.getName());
		userInDB.setPhone(user.getPhone());
		userInDB.setEmail(user.getEmail());
		userDAO.save(userInDB);
		adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
	}

	public void deleteById(int id) {
		userDAO.deleteById(id);
	}
}