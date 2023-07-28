package com.javainterns.bookingroom.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USERSB")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @OneToMany(targetEntity = Booking.class)
    private List<Booking> bookingList;

    public User() {
    }


    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List getBookingList(){
        return bookingList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBookingList(List bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this,name,this,email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bookingList=" + bookingList +
                '}';
    }
}
