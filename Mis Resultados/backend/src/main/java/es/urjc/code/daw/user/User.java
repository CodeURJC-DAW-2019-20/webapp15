package es.urjc.code.daw.user;

import javax.persistence.*;

import java.util.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Entity
@Table(name="user")
public class User{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	//Aqui todos los elementos de la tabla del modelo
	private String name;
	private String surname;
	
	@Column(unique=true)
	private String email;
	
	@Column(name="password")
	private String passwordHash;
	private String fav_team;
	private Integer acc_balance;
	
	/*@Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] profilePicture;*/
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	
	public  User() {
	}
	
	public User(String name, String surname, String email, String fav_team, String password, String... roles) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.fav_team = fav_team;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		/*this.profilePicture = profilePicture;*/
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return this.surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return this.passwordHash;
	}
	public void setpasswordHash(String password) {
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
	}
	public String getFav_team() {
		return this.fav_team;
	}
	public void setFav_team(String fav_team) {
		this.fav_team = fav_team;
	}
	public Integer getAcc_balance() {
		return acc_balance;
	}
	public void setAcc_balance(Integer acc_balance) {
		this.acc_balance = acc_balance;
	}
	
	/*public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }*/
	
	public List<String> getRoles() {
		return roles;
	}
	@ManyToMany
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", passwordHash=" + passwordHash
				+ ", fav_team=" + fav_team + ", acc_balance=" + acc_balance + ", roles=" + roles + "]";
	}

}
