package com.br.texo.apitexo.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.persistence.*;
import java.time.LocalDate;

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
    private String  producer   = "";
    @Column(name = "winner")
    private String  winner      = "";

    /**
     * Representa a data(ano) mais recente em que ganhou
     */
    @Transient
    private int followingWin = 0;

    /**
     * Representa a data(ano) mais antiga que ganhou
     */
    @Transient
    private  int previousWin = LocalDate.now().getYear() + 1;

    @Transient
    private int qtdWinners = 0;

    /**
     * Representa o intervalo de anos entre o ano mais recente e o ano mais antigo
     */
    @Transient
    private int interval = 0;

    public int getQtdWinners()
    {
        return qtdWinners;
    }

    public void setQtdWinners(int qtdWinners)
    {
        this.qtdWinners = qtdWinners;
    }

    public int getGreaterDate()
    {
        return followingWin;
    }

    public void setGreaterDate(int greaterDate)
    {
        this.followingWin = greaterDate;
    }

    public int getMinorDate()
    {
        return previousWin;
    }

    public void setMinorDate(int minorDate)
    {
        this.previousWin = minorDate;
    }

    public int getDateInterval()
    {
        return interval;
    }

    public void setDateInterval(int dateInterval)
    {
        this.interval = dateInterval;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getStudio()
    {
        return studio;
    }

    public void setStudio(String studio)
    {
        this.studio = studio;
    }

    public String getProducers()
    {
        return producer;
    }

    public void setProducers(String producers)
    {
        this.producer = producers;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }

    @Override
    public String toString()
    {
        return "{"+"producer"   +":"+this.getProducers()        +","    +
                "interval"      +":"+this.getDateInterval()     +","    +
                "previousWin"   +":"+this.getGreaterDate()      +","    +
                "followingWin"  +":"+this.getMinorDate()        +"}";
    }// toString

    public ObjectNode toJsonObject()
    {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode jsonNodes = objectMapper.createObjectNode();

        jsonNodes.put("producer",       this.getProducers());
        jsonNodes.put("interval",       this.getDateInterval());
        jsonNodes.put("previousWin",    this.getGreaterDate());
        jsonNodes.put("followingWin",   this.getMinorDate());

        return jsonNodes;
    }// toJsonObject
}
