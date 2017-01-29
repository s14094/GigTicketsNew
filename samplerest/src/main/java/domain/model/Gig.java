package domain.model;

public class Gig implements IHaveId {

	private int id;
	private String category;
	private String title;
	private String description;

	public Gig(Integer id, String category, String title, String description) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.description = description;
	}

	public Gig() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
