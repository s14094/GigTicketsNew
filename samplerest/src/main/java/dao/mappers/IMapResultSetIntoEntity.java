package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IMapResultSetIntoEntity<TEntity> {

	public TEntity map(ResultSet rs) throws SQLException;

}
