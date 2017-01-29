package dao.repositories;

import java.util.List;

import domain.model.Gig;

public interface IGigRepository extends IRepository<Gig> {

	public List<Gig> withCategory(String category);

	public List<Gig> withTitle(String title);

	public List<Gig> withDescription(String description);

}
