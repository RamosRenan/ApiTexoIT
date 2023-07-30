package com.br.texo.apiTexo.entities;

import javax.persistence.Entity;

@Entity
public class Movie
{
    private int     year        = 0;
    private String  title       = "";
    private String  studio      = "";
    private String  producers   = "";
    private String  winner      = "";
}
