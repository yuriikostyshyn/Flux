
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name="account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccountPK id;

	private BigInteger idBank;

	//bi-directional many-to-one association to Currency
    @ManyToOne
	@JoinColumn(name="idCurrency")
	private Currency currency;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="account")
	private List<Transaction> transactions;

    public Account() {
    }

	public AccountPK getId() {
		return this.id;
	}

	public void setId(AccountPK id) {
		this.id = id;
	}
	
	public BigInteger getIdBank() {
		return this.idBank;
	}

	public void setIdBank(BigInteger idBank) {
		this.idBank = idBank;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}