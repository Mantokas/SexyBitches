/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.baraksoft.summersystem.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Žygimantas
 */
@Entity
@Table(name = "reservations")
@NamedQueries({ @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"), @NamedQuery(name = "Reservation.findById", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
		@NamedQuery(name = "Reservation.findByDateFrom", query = "SELECT r FROM Reservation r WHERE r.dateFrom = :dateFrom"), @NamedQuery(name = "Reservation.findByDateTo", query = "SELECT r FROM Reservation r WHERE r.dateTo = :dateTo"),

		@NamedQuery(name = "Reservation.findByIsArchived", query = "SELECT r FROM Reservation r WHERE r.archived = :isArchived") })
public class Reservation implements IEntity<Integer> {
	private static final long serialVersionUID = -1807482193504644095L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "date_from")
	private LocalDate dateFrom;
	@Basic(optional = false)
	@NotNull
	@Column(name = "date_to")
	private LocalDate dateTo;
	@Basic(optional = false)
	@NotNull
	@Column(name = "price")
	private BigDecimal price;
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Basic(optional = false)
	@NotNull
	@Column(name = "number")
	private String nr;
	@Basic(optional = false)
	@NotNull
	@Column(name = "is_archived")
	private boolean archived;
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;

	@JoinColumn(name = "summerhouse_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Summerhouse summerhouse;

	@JoinTable(name = "reservation_services", joinColumns = { @JoinColumn(name = "reservation_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "service_id", referencedColumnName = "id") })
	@ManyToMany
	private List<Service> serviceList;

	@Version
	private Integer version;

	public Reservation() {
	}

	public Reservation(Integer id) {
		this.id = id;
	}

	public Reservation(Integer id, LocalDate dateFrom, LocalDate dateTo, boolean archived) {
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.archived = archived;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Summerhouse getSummerhouse() {
		return summerhouse;
	}

	public void setSummerhouse(Summerhouse summerhouse) {
		this.summerhouse = summerhouse;
	}

	public Integer getVersion() {
		return version;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "lt.baraksoft.summersystem.model.Reservation[ id=" + id + " ]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Reservation that = (Reservation) o;

		return nr.equals(that.nr);

	}

	@Override
	public int hashCode() {
		return nr.hashCode();
	}
}
