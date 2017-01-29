package dao.uow;

public class Entity {

	private EntityState state;
	IUnitOfWorkRepository repo;

	public Entity(IUnitOfWorkRepository repo) {
		super();
		this.repo = repo;
		this.state = EntityState.Unchanged;
	}

	Object entity;

	public <TEntity> void setEntity(TEntity entity) {
		this.entity = entity;
	}

	public Object getEntity() {
		return this.entity;
	}

	public EntityState getState() {
		return state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}

	public void persist() {
		switch (this.state) {
		case Changed:
			repo.persistUpdate(this);
			break;
		case Deleted:
			repo.persistDelete(this);
			break;
		case New:
			repo.persistAdd(this);
			break;
		case Unchanged:
			break;
		case Unknown:
			break;
		default:
			break;
		}
	}

}
