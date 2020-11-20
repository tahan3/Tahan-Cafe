package by.epam.mtlcwtchr.ecafe.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Reservation implements Serializable {

    private static final long serialVersionUID = 9067919397164539704L;

    private int id;
    private Hall reservedHall;
    private Date reservationDate;
    private Date contactTime;

    private String contactPhone;

    public Reservation(){}
    public Reservation(int id, Hall reservedHall, Date reservationDate, Date contactTime, String contactPhone) {
        this(reservedHall, reservationDate, contactTime, contactPhone);
        this.id = id;
    }

    public Reservation(Hall reservedHall, Date reservationDate, Date contactTime, String contactPhone) {
        this.reservedHall = reservedHall;
        this.reservationDate = reservationDate;
        this.contactTime = contactTime;
        this.contactPhone = contactPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hall getReservedHall() {
        return reservedHall;
    }

    public void setReservedHall(Hall reservedHall) {
        this.reservedHall = reservedHall;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getContactTime() {
        return contactTime;
    }

    public void setContactTime(Date contactTime) {
        this.contactTime = contactTime;
    }

    public boolean isExpired() {
        return new Date().after(reservationDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id &&
                Objects.equals(reservedHall, that.reservedHall) &&
                Objects.equals(reservationDate, that.reservationDate) &&
                Objects.equals(contactTime, that.contactTime) &&
                Objects.equals(contactPhone, that.contactPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservedHall, reservationDate, contactTime, contactPhone);
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "id=" + id +
                ", reservedHall=" + reservedHall +
                ", reservationDate=" + (Objects.nonNull(reservationDate) ? new SimpleDateFormat("<<MM/dd/yyyy>>").format(reservationDate) : "unset") +
                ", contactTime=" + (Objects.nonNull(contactTime) ? new SimpleDateFormat("<<HH:mm:ss>>").format(contactTime) : "unset") +
                ", contactPhone=" + contactPhone +
                '}';
    }
}
