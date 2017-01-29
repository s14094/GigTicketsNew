package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.model.Gig;

public class GigMapper implements IMapResultSetIntoEntity<Gig> {
	public Gig map(ResultSet rs) throws SQLException {
		Gig gig = new Gig();
		gig.setId(rs.getInt("id"));
		gig.setCategory(rs.getString("category"));
		gig.setTitle(rs.getString("title"));
		gig.setDescription(rs.getString("description"));
		return gig;
	}
}
