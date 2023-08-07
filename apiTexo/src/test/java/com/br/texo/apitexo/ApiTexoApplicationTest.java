package com.br.texo.apitexo;

import com.br.texo.apitexo.mensagens.Mensagens;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.net.URISyntaxException;
//import io.restassured.RestAssured.*;
//import		io.restassured.matcher.RestAssuredMatchers.*;
import		org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiTexoApplicationTest {

	@Autowired ApiTexoApplication  	apiTexoApplication;

	private final String 	PATH_CSV_FILE_WRONG		= "/home/renan/desenvolvimento/workspace/api/texoIT/ApiTextoIT/apitexo/docs/movieçlist.csv";

	/**
	 * Testa se exist exceção lançada quando há algum problema para carregar o arquivo csv
	 */
	@Test()
	public void verifyExceptionWhenOpenCsvFile()
	{
		ApiTexoApplication apiTexoApplication1 = new ApiTexoApplication();

		Exception exception = assertThrows(URISyntaxException.class, ()->{
			apiTexoApplication1.openCsvFile("../;/../");
		});

		assertEquals(Mensagens.MSN_PROBLEM_BY_OPEN_CSV, exception.getMessage().replace(":", "").trim());
	}// verifyExceptionWhenOpenCsvFile


}
