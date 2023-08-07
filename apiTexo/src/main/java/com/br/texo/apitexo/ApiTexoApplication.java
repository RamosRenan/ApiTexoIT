package com.br.texo.apitexo;

import com.br.texo.apitexo.entities.MovieEntity;
import com.br.texo.apitexo.mensagens.Mensagens;
import com.br.texo.apitexo.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@SpringBootApplication
@PropertySource({"classpath:config.properties"})
@EnableTransactionManagement
public class ApiTexoApplication {

	@Autowired
	Environment propertiesOfApp;

	@Autowired
	MovieService movieService;

	// variavel que valida se o arquivo csv foi carregado e lido corretamente
	private boolean 		csvSucessLoaded 								= false;
	private String 			PATH_UNIFORM_RESOURCE_IDENTIFIER_CSV_DEFAULT 	= ""  ;	// absolutePath: "/home/renan/desenvolvimento/workspace/api/texoIT/ApiTextoIT/apitexo/docs/movielist.csv";
	private FileReader 		fileReader 										= null;
	private MovieEntity     movieEntity 									= null;
	private static ApiTexoApplication configurableApplicationContext 		= null;
	private Map<String, Integer> 	mapHeaderColumn 						= null;
	private List<MovieEntity> 		movieEntityList							= null;

	private Map<String, MovieEntity> movieEntityMapWinners					= null;

	public static void main(String[] args)
	{
		ApplicationContext configurableApplicationContext_1 =  SpringApplication.run(ApiTexoApplication.class, args);

		ApiTexoApplication.configurableApplicationContext = configurableApplicationContext_1.getBean(ApiTexoApplication.class);
	}

	/**
	 * Responsável por carregar o arquivo csv
	 */
	@Bean
	public void readCsvFile() throws IOException, URISyntaxException, Exception
	{
		this.movieEntityList =  new ArrayList<MovieEntity>();

		String envSoName = "";

		if(System.getProperty("os.name").toLowerCase().contains("windows"))
			envSoName = "csvLocationWin";
		else if (System.getProperty("os.name").toLowerCase().contains("linux"))
			envSoName =  "csvLocationLin";
		else
			throw new RuntimeException("Não foi possível determinar o sistema operacioonal ...");

		try
		{
			openCsvFile(propertiesOfApp.getProperty(envSoName).replace("\\", "/").replace(" ", "%20"));

			if (this.csvSucessLoaded)
			{
				/**
				 * csvformat
				 *
				 * Header/Columns: year, title, studios, producers, winner
				 * Caso as colunas no arquivo csv não sigam esta ordem faço a devida classificação da
				 * coluna com o valor para cada linha do arquivo,
				 * Adota-se como padrão ';' como delimitador
 				 */
				CSVParser csvMovieList = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(this.fileReader);

				this.mapHeaderColumn = new HashMap<String, Integer>();

				int index 	= 0;
				String sss 	= "";
				for (Object ss : csvMovieList.getHeaderNames().toArray())
				{
					switch (sss = ((String) ss).toLowerCase())
					{
						case "year":
							mapHeaderColumn.put(sss, index++);
							break;
						case "title":
							mapHeaderColumn.put(sss, index++);
							break;
						case "studios":
							mapHeaderColumn.put(sss, index++);
							break;
						case "producers":
							mapHeaderColumn.put(sss, index++);
							break;
						case "winner":
							mapHeaderColumn.put(sss, index++);
							break;
						default:
							throw new Exception(Mensagens.MSN_INCONSISTENCE_CSV_HEADER);
					}
				}

				csvMovieList.getRecords().forEach((row)->{
					CSVRecord k = row;
					this.movieEntity =  new MovieEntity();
					this.movieEntity.setId(0);
					this.movieEntity.setProducers	((String)  (k.toMap().values().toArray()[this.mapHeaderColumn.get("producers").intValue()]	));
					this.movieEntity.setStudio		((String)  (k.toMap().values().toArray()[this.mapHeaderColumn.get("studios").intValue()]	));
					this.movieEntity.setTitle		((String)  (k.toMap().values().toArray()[this.mapHeaderColumn.get("title").intValue()]		));
					this.movieEntity.setYear		(Integer.valueOf(((String) k.toMap().values().toArray()[this.mapHeaderColumn.get("year").intValue()])));
					this.movieEntity.setWinner		((String)  (k.toMap().values().toArray()[this.mapHeaderColumn.get("winner").intValue()]		));

					this.movieEntityList.add(this.movieEntity);
				});
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

	@Bean
	public void insertListMoviesIntoMovieTable()
	{
		movieService.saveAll(this.movieEntityList);
	}

	/**
	 * Obtem os 50 primeiros registros, percorre o resultad, converte para Json e printa na tela o Json obtido
	 */
	@Bean
	public void showMaxAndMinWinners()
	{
		this.movieEntityMapWinners = new HashMap<String, MovieEntity>();

		movieService.findByWinner().forEach((obj)->{
			String[] producers = obj.getProducers().split("\\s?,?\\s+(?i)and\\s|\\s?,\\s");

			for (String productor:producers)
			{
				productor = productor.trim();
				if(this.movieEntityMapWinners.get(productor) != null)
				{
					int o = this.movieEntityMapWinners.get(productor).getQtdWinners();
					this.movieEntityMapWinners.get(productor).setQtdWinners(o + 1);

					if (obj.getYear() > this.movieEntityMapWinners.get(productor).getGreaterDate()) {
						this.movieEntityMapWinners.get(productor).setGreaterDate(obj.getYear());
					}

					if (obj.getYear() < this.movieEntityMapWinners.get(productor).getMinorDate()  ) {
						this.movieEntityMapWinners.get(productor).setMinorDate(obj.getYear());
					}
					this.movieEntityMapWinners.get(productor).setDateInterval(this.movieEntityMapWinners.get(productor)
							.getGreaterDate() - this.movieEntityMapWinners.get(productor)
							.getMinorDate());
				}
				else
				{
					MovieEntity movieEntity1 = new MovieEntity();
					movieEntity1.setProducers(productor);
					movieEntity1.setQtdWinners(1);
					movieEntity1.setStudio(obj.getStudio());
					movieEntity1.setGreaterDate(obj.getYear());
					movieEntity1.setMinorDate(obj.getYear());
					this.movieEntityMapWinners.put(productor, movieEntity1);
				}
			}
		});

		Optional<MovieEntity> max = this.movieEntityMapWinners.values().stream().filter((oo)->{return oo.getQtdWinners() > 1;}).max(new Comparator<MovieEntity>()
		{
			@Override
			public int compare(MovieEntity o1, MovieEntity o2)
			{
				if(o1 != null && o2 != null && o1.getDateInterval() > o2.getDateInterval())
					return 1;
				else
					return -1;
			}
		}
		);
		Optional<MovieEntity> min = this.movieEntityMapWinners.values().stream().filter((oo)->{return oo.getQtdWinners() > 1;}).min(new Comparator<MovieEntity>()
		{
			 @Override
			 public int compare(MovieEntity o1, MovieEntity o2) {
				 if(o1 != null && o2 != null && o1.getDateInterval() > o2.getDateInterval())
					 return 1;
				 else
					 return -1;

			 }
		}
		);
	}

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

		System.out.println(">>> path csv file: "+path);
		try
		{
			if (!path.isBlank())
				this.fileReader 	= new FileReader(new File(new URI("file:///" + path)));
			else
				this.fileReader 	= new FileReader(new File(new URI("file:///" + apiTexoApplication.PATH_UNIFORM_RESOURCE_IDENTIFIER_CSV_DEFAULT)));

			this.csvSucessLoaded 	= true;
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException(Mensagens.MSN_PROBLEM_BY_OPEN_CSV);
		}
		catch (FileNotFoundException e)
		{
			throw  new FileNotFoundException(Mensagens.MSN_PROBLEM_BY_OPEN_CSV);
		}
		catch (URISyntaxException e)
		{
			throw new URISyntaxException("", Mensagens.MSN_PROBLEM_BY_OPEN_CSV);
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
