package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.mappers.IMapResultSetIntoEntity;
import dao.repositories.IRepository;
import dao.uow.Entity;
import dao.uow.IUnitOfWork;
import dao.uow.IUnitOfWorkRepository;
import domain.model.IHaveId;

public abstract class RepositoryBase<TEntity extends IHaveId> implements IRepository<TEntity>, IUnitOfWorkRepository {

	protected Connection connection;

	protected PreparedStatement insert;
	protected PreparedStatement selectById;
	protected PreparedStatement update;
	protected PreparedStatement delete;
	protected PreparedStatement selectAll;
	protected IUnitOfWork uow;
	protected IMapResultSetIntoEntity<TEntity> mapper;

	public Connection getConnection() {
		return connection;
	}

	protected RepositoryBase(Connection connection, IMapResultSetIntoEntity<TEntity> mapper, IUnitOfWork uow) {
		this.connection = connection;
		this.uow = uow;
		try {
			this.mapper = mapper;
			createTableIfnotExists();
			insert = connection.prepareStatement(insertSql());
			selectById = connection.prepareStatement(selectByIdSql());
			update = connection.prepareStatement(updateSql());
			delete = connection.prepareStatement(deleteSql());
			selectAll = connection.prepareStatement(selectAllSql());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<TEntity> getAll() {
		try {
			ResultSet rs = selectAll.executeQuery();
			List<TEntity> result = new ArrayList<TEntity>();
			while (rs.next()) {
				result.add(mapper.map(rs));
			}
			return result;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public TEntity get(int personId) {
		try {
			selectById.setInt(1, personId);
			ResultSet rs = selectById.executeQuery();
			while (rs.next()) {
				return mapper.map(rs);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public void add(TEntity entity) {
		Entity ent = new Entity(this);
		ent.setEntity(entity);
		uow.markAsNew(ent);

	}

	public void delete(TEntity entity) {
		Entity ent = new Entity(this);
		ent.setEntity(entity);
		uow.markAsDeleted(ent);
	}

	public void update(TEntity entity) {
		Entity ent = new Entity(this);
		ent.setEntity(entity);
		uow.markAsChanged(ent);
	}

	public void persistUpdate(Entity entity) {
		try {
			TEntity ent = (TEntity) entity.getEntity();
			setUpdate(ent);
			update.setInt(3, ent.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void persistAdd(Entity entity) {
		try {
			setInsert((TEntity) entity.getEntity());
			insert.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void persistDelete(Entity entity) {
		try {
			delete.setInt(1, ((TEntity) entity.getEntity()).getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected String selectByIdSql() {
		return "SELECT * FROM " + tableName() + " WHERE id=?";
	}

	protected String deleteSql() {
		return "DELETE FROM " + tableName() + " WHERE id=?";
	}

	protected String selectAllSql() {
		return "SELECT * FROM " + tableName();
	}

	private void createTableIfnotExists() throws SQLException {
		Statement createTable = this.connection.createStatement();

		boolean tableExists = false;

		ResultSet rs = connection.getMetaData().getTables(null, null, null, null);

		while (rs.next()) {
			if (rs.getString("Table_Name").equalsIgnoreCase(tableName())) {
				tableExists = true;
				break;
			}
		}
		if (!tableExists)
			createTable.executeUpdate(createTableSql());
	}

	protected abstract String insertSql();

	protected abstract String updateSql();

	protected abstract void setUpdate(TEntity entity) throws SQLException;

	protected abstract void setInsert(TEntity entity) throws SQLException;

	protected abstract String createTableSql();

	protected abstract String tableName();
}
