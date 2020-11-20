package by.epam.mtlcwtchr.ecafe.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Order implements Entity, Serializable {

    private static final long serialVersionUID = -9194822421646989651L;

    private int id;
    private Client customer;

    private Date orderDate;
    private boolean isPaid = false;
    private boolean isPrepared = false;
    private boolean isTaken = false;
    private int clientMark;
    private String clientComment;

    private final ArrayList<Meal> meals = new ArrayList<>();

    public Order(){ }
    public Order(Client customer, Date orderDate){
        this.customer = customer;
        this.orderDate = orderDate;

    }
    public Order(int id, Client customer, Date orderDate, boolean isPaid, boolean isPrepared, boolean isTaken){
        this(customer, orderDate);
        this.id = id;
        this.isPaid = isPaid;
        this.isPrepared = isPrepared;
        this.isTaken = isTaken;
    }
    public Order(int id, Client customer, Date orderDate, boolean isPaid, boolean isPrepared, boolean isTaken, int clientMark, String clientComment){
        this(id, customer, orderDate, isPaid, isPrepared, isTaken);
        this.clientMark = clientMark;
        this.clientComment = clientComment;
    }
    public Order(Client customer){
        this.customer = customer;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }

    public void removeMeal(Meal meal){
        meals.remove(meal);
    }

    public void removeMeal(int mealId) {
        meals.stream().filter(meal->meal.getId()==mealId).findAny().ifPresent(this::removeMeal);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getClientMark() {
        return clientMark;
    }

    public void setClientMark(int clientMark) {
        this.clientMark = clientMark;
    }

    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    public boolean isBlank(){
        return meals.isEmpty();
    }

    public int getSize() {
        return meals.size();
    }

    public int getTotalPrice() {
        return meals
                .stream()
                .mapToInt(Meal::getPrice)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                isPaid == order.isPaid &&
                isPrepared == order.isPrepared &&
                isTaken == order.isTaken &&
                clientMark == order.clientMark &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(clientComment, order.clientComment) &&
                Objects.equals(meals, order.meals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, orderDate, isPaid, isPrepared, isTaken, clientMark, clientComment, meals);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", ordered by " + customer.getName() +
                ", ordered at " + new SimpleDateFormat("<<MM/dd/yyyy hh:mm:ss a>>").format(orderDate) +
                ", status=" + (isPaid ? "paid" : "not paid") +
                ", " + (isPrepared ? "prepared" : "preparing") +
                ", " + (isTaken ? "taken" : "waiting") +
                ", contains meals: " +
                meals.toString() +
                ", client mark=" + clientMark +
                ", client comment='" + clientComment + '\'' +
                '}'
                ;
    }

}
