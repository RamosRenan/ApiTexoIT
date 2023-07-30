package com.br.texo.apiTexo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiTexoApplicationTest {

	@Autowired ApiTexoApplication  	apiTexoApplication;
	private final String 	MSN_PROBLEM_BY_OPEN_CSV	= "O caminho informado não pode ser verificado, veirifque o camiho informado ou o nome do arquivo ou a paermissão da pasta ou do arquivo";

	private final String 	PATH_CSV_FILE_WRONG		= "/home/renan/desenvolvimento/workspace/api/texoIT/ApiTextoIT/apiTexo/docs/movieçlist.csv";

	/**
	 * Testa se exceção lançada quando há algum problema para carregar o arquivo csv
	 */
	@Test()
	public void verifyExceptionWhenOpenCsvFile()
	{
		ApiTexoApplication apiTexoApplication1 = new ApiTexoApplication();

		Exception exception = assertThrows(URISyntaxException.class, ()->{
			apiTexoApplication1.openCsvFile("../;/../");
		});

		assertEquals(MSN_PROBLEM_BY_OPEN_CSV, exception.getMessage().replace(":", "").trim());
	}// verifyExceptionWhenOpenCsvFile
}
