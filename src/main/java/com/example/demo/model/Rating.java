package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Rating {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private User user;

        @ManyToOne
        private Hall hall;

        private Character grade;

        public Rating() {
        }

        public Rating(Long id, User user, Hall hall, Character grade){
            this.id=id;
            this.user=user;
            this.hall=hall;
            this.grade=grade;
        }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Character getGrade() {
        return grade;
    }

    public void setGrade(Character grade) {
        this.grade = grade;
    }

    public Rating(User user, Hall hall, Character gradeChar){
            this.user=user;
            this.hall=hall;
            this.grade=gradeChar;
    }
}
