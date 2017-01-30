package rest.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDto {


		private int id;
		private String username;
		private String surname;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

	}
