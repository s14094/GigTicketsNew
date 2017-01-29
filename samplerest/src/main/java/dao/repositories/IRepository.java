package dao.repositories;

import java.util.List;

import domain.model.IHaveId;

public interface IRepository<TEntity extends IHaveId> {

	public List<TEntity> getAll();

	public TEntity get(int personId);

	public void update(TEntity entity);

	public void add(TEntity entity);

	public void delete(TEntity entity);

}