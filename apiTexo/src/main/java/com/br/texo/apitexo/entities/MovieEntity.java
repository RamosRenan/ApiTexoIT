package com.br.texo.apitexo.entities;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class MovieEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     id;

    @Column(name = "year_movie")
    private int     year        = 0;

    @Column(name = "title")
    private String  title       = "";

    @Column(name = "studio")
    private String  studio      = "";

    @Column(name = "producers")
    private String  producers   = "";
    @Column(name = "winner")
    private String  winner      = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
