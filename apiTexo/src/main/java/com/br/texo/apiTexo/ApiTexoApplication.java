package com.br.texo.apiTexo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@SpringBootApplication
@PropertySource({"classpath:config.properties"})
public class ApiTexoApplication {

	@Autowired
	Environment propertiesOfApp;

	// variavel que valida se o arquivo csv foi carregado e lido corretamente
	private boolean 		csvSucessLoaded 								= false;
	private String 			PATH_UNIFORM_RESOURCE_IDENTIFIER_CSV_DEFAULT 	= "";// absolutePath: "/home/renan/desenvolvimento/workspace/api/texoIT/ApiTextoIT/apiTexo/docs/movielist.csv";
	private FileReader 		fileReader 										= null;
	private final String 	MSN_PROBLEM_BY_OPEN_CSV							= "O caminho informado não pode ser verificado, veirifque o camiho informado ou o nome do arquivo ou a paermissão da pasta ou do arquivo";

	private static ApiTexoApplication configurableApplicationContext 		;

	public static void main(String[] args)
	{
		ApplicationContext configurableApplicationContext_1 =  SpringApplication.run(ApiTexoApplication.class, args);

		ApiTexoApplication.configurableApplicationContext = configurableApplicationContext_1.getBean(ApiTexoApplication.class);

	}

	/**
	 * Responsável por carregar o arquivo csv
	 */
	@Bean
	public void loadCsvFile() throws IOException, URISyntaxException {
		try
		{
			openCsvFile(propertiesOfApp.getProperty("csvLocation"));

			if (this.csvSucessLoaded)
			{
				/**
				 * csvformat
				 *
				 * Header/Columns: year(index:0), title(index:1), studios(index:2), producers(index:3), winner(index:4)
 				 */
				CSVParser csvMovieList = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(this.fileReader);

				for (Object ss : csvMovieList.getHeaderNames().toArray())
				{
					System.out.println(">>>>>>>>><<<<<<<<<< : row header: "+ss);
				}

				csvMovieList.getRecords().forEach((row)->{
					System.out.println(row);
					System.out.println(row.getRecordNumber());
					CSVRecord k = row;
					System.out.println(k.toMap().values());
					System.out.println(k.toMap().values().toArray()[0]);
					System.out.println(k.toMap());
					System.out.println("--------->>>>>>> <<<<<<<<<--------\n");
				});

				System.out.println("**********************************************************");

				CSVParser pp = CSVParser.parse(this.fileReader, CSVFormat.RFC4180);
				for (CSVRecord rr: pp) {
					System.out.println(rr.getCharacterPosition());
					System.out.println(rr.get(0));
					System.out.println(rr.get(1));
				}
			}
		}
		catch (URISyntaxException  | FileNotFoundException e)
		{
			throw e;
		}
		catch (IOException e)
		{
			throw e;
		}
	}// loadCsvFile

	/**
	 * Responsável por abrir o arquivo csv
	 *
	 * @param  path
	 * @throws URISyntaxException
	 * @throws FileNotFoundException
	 */
	public void openCsvFile(String path) throws IllegalArgumentException, FileNotFoundException, URISyntaxException
	{
		ApiTexoApplication apiTexoApplication =  new ApiTexoApplication();
		apiTexoApplication.PATH_UNIFORM_RESOURCE_IDENTIFIER_CSV_DEFAULT = path;
		try
		{
			if (!path.isBlank())
				this.fileReader 	= new FileReader(new File(new URI("file", null, path, null)));
			else
			{
				this.fileReader 	= new FileReader(new File(new URI("file", null, apiTexoApplication.PATH_UNIFORM_RESOURCE_IDENTIFIER_CSV_DEFAULT, null)));
			}
			this.csvSucessLoaded 	= true;
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException(MSN_PROBLEM_BY_OPEN_CSV);
		}
		catch (FileNotFoundException e)
		{
			throw  new FileNotFoundException(MSN_PROBLEM_BY_OPEN_CSV);
		}
		catch (URISyntaxException e)
		{
			throw new URISyntaxException("", MSN_PROBLEM_BY_OPEN_CSV);
		}
	}// openCsvFile

	/**
	 * Responasavel por verificar se o arquivo csv foi aberto corretamente
	 */
	private boolean isCsvSucessLoaded()
	{
		if (this.csvSucessLoaded)
		{
			boolean success = this.csvSucessLoaded;

			this.csvSucessLoaded = false;

			return success;
		}
		else
			return false;
	}// isCsvSucessLoaded
}
