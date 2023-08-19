package com.br.texo.apitexo;

import com.br.texo.apitexo.mensagens.Mensagens;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import io.restassured.RestAssured.*;
import		io.restassured.matcher.RestAssuredMatchers.*;
import		org.hamcrest.Matchers.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiTexoApplicationTest
{
	@Autowired 		ApiTexoApplication  	apiTexoApplication;

	// teste para um caminho errado para o arquivo csv
	private final 	String 					PATH_CSV_FILE_WRONG	= "/workspace/api/texoIT/ApiTextoIT/apitexo/docs/movieçlist.csv";

	/**
	 * Testa se exist exceção lançada quando há algum problema para carregar o arquivo csv
	 */
	@Test()
	public void verifyExceptionWhenOpenCsvFile()
	{
		ApiTexoApplication apiTexoApplication1 = new ApiTexoApplication();

		Exception exception = assertThrows(FileNotFoundException.class, ()->{
			apiTexoApplication1.openCsvFile(this.PATH_CSV_FILE_WRONG);
		});

		assertEquals(Mensagens.MSN_PROBLEM_BY_OPEN_CSV, exception.getMessage().replace(":", "").trim());
	}// verifyExceptionWhenOpenCsvFile

	/**
	 * Teste de integração
	 *
	 * Verifica o resultado da chamada a api na url /minAndMaxByDateMovie
	 */
	@Test
	public void verifyMovieWinnersMinByDateIntervalMovie()
	{
		RestAssured.get("/minAndMaxByDateMovie").then().body("min.interval", hasItems(1));
	}

	/**
	 * Teste de integração
	 *
	 * Verifica o resultado da chamada a api na url /minAndMaxByDateMovie
	 */
	@Test
	public void verifyMovieWinnersMaxByDateIntervalMovie()
	{
		RestAssured.get("/minAndMaxByDateMovie").then().body("max.interval", hasItems(13));
	}

}//class
