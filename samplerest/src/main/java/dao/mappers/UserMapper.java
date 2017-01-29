package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.model.User;

public class UserMapper implements IMapResultSetIntoEntity<User> {

	public User map(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setSurname(rs.getString("surname"));
		return user;
	}

}
