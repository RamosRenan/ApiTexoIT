package com.br.texo.apitexo;

import com.br.texo.apitexo.mensagens.Mensagens;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.FileNotFoundException;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.RestAssured.*;
import		io.restassured.matcher.RestAssuredMatchers.*;
import		org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

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
	 * Verifica o resultado da chamada a api na url /minAndMaxByDateMovie
	 */
	@Test
	public void verifyMovieWinnersMinByDateIntervalMovie()
	{
		RestAssured.get("/minAndMaxByDateMovie").then().body("min.interval", hasItems(1));
	}

	/**
	 * Teste de integração
	 * Verifica o resultado da chamada a api na url /minAndMaxByDateMovie
	 */
	@Test
	public void verifyMovieWinnersMaxByDateIntervalMovie()
	{
		RestAssured.get("/minAndMaxByDateMovie").then().body("max.interval", hasItems(13));
	}

	/**
	 *  Teste de integração
	 *  Testando dados do ganhador que levou menos tempo entre dois prêmios ou mais
	 */
	@Test
	public void verifyDataWinnerMin()
	{
		RestAssured.get("/minAndMaxByDateMovie").then().body("min[0].producer",	 	equalTo("Joel Silver"));
		RestAssured.get("/minAndMaxByDateMovie").then().body("min[0].interval", 	equalTo(1));
		RestAssured.get("/minAndMaxByDateMovie").then().body("min[0].previousWin", 	equalTo(1990));
		RestAssured.get("/minAndMaxByDateMovie").then().body("min[0].followingWin", equalTo(1991));
	}

	/**
	 *  Teste de integração
	 *  Testando dados do ganhador que levou mais tempo entre dois prêmios ou mais
	 */
	@Test
	public void verifyDataWinnerMax()
	{
		RestAssured.get("/minAndMaxByDateMovie").then().body("max[0].producer",	 	equalTo("Matthew Vaughn"));
		RestAssured.get("/minAndMaxByDateMovie").then().body("max[0].interval", 	equalTo(13));
		RestAssured.get("/minAndMaxByDateMovie").then().body("max[0].previousWin", 	equalTo(2002));
		RestAssured.get("/minAndMaxByDateMovie").then().body("max[0].followingWin", equalTo(2015));
	}
}//class
