package domain.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries(
		{
			@NamedQuery(name= "reader.all", query=" SELECT r FROM Reader r")
		})
public class Ticket implements IHaveId {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)


	private int id;
	private String name;
	private String date;
	private String location;
	private int price;
	private int quantity;
	private Gig information;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Gig getInformation() {
		return information;
	}

	public void setInformation(Gig information) {
		this.information = information;
	}

}