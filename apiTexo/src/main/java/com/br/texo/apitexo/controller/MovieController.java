package com.br.texo.apitexo.controller;

import com.br.texo.apitexo.entities.MovieEntity;
import com.br.texo.apitexo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MovieController
{
    @Autowired
    Environment propertiesOfApp;

    @Autowired
    MovieService movieService;

    private Map<String, MovieEntity>    movieEntityMapWinners	= null;

    private Map<String, Object>    minAndMaxWinners        = null;

    /**
     * Responsavel por obter o filme com o maior e menor intervalo de tempo
     *
     * @return ist<MovieEntity>
     */
    @GetMapping(value = "/minAndMaxByDateMovie")
    public ResponseEntity<Map<String, Object>> getMaxAndMinWinnersByDate() throws IOException
    {
        this.minAndMaxWinners  = new HashMap<>();

        this.setDateMinAndMaxAndIntervalToEachMovie();

        List<MovieEntity>       max = this.filterMaxDateMovie();

        List<MovieEntity>       min = this.filterMinDateMovie();

        this.minAndMaxWinners.put("max", max.stream().map(MovieEntity::toJsonObject).collect(Collectors.toList()));

        this.minAndMaxWinners.put("min", min.stream().map(MovieEntity::toJsonObject).collect(Collectors.toList()));

        return new ResponseEntity<>(this.minAndMaxWinners, HttpStatus.OK);
    }// getsMaxAndMinWinners

    /**
     * Considera-se que cada tupla possui uma string com um ou mais produtores
     * consisera-se por default que os demilitadores são ',', 'and', 'AND'
     * Utiliza-se regex
     * @param txt
     * @return
     */
    private String[] decomposeStrigByDefaultDelimiters(String txt)
    {
//        System.out.println(">>>> text decomposed: "+Arrays.toString(txt.split(propertiesOfApp.getProperty("delimiter", "\\s?,?\\s+(?i)and\\s|\\s?,\\s"))));
        return txt.split("\\s?,?\\s+(?i)and\\s|\\s?,\\s");
    }// decomposeStrigByDefaultDelimiters

    private void setDateMinAndMaxAndIntervalToEachMovie()
    {
        this.movieEntityMapWinners = new HashMap<String, MovieEntity>();

        this.movieService.findByWinner().forEach((obj) -> {
            // obtem o resultado decomposto com os nomes desta tupla
            String[] producers = decomposeStrigByDefaultDelimiters(obj.getProducers());

            for (String productor : producers)
            {
                productor = productor.trim();
                if (this.movieEntityMapWinners.get(productor) != null)
                {
                    int o = this.movieEntityMapWinners.get(productor).getQtdWinners();
                    this.movieEntityMapWinners.get(productor).setQtdWinners(o + 1);

                    if (obj.getYear() > this.movieEntityMapWinners.get(productor).getGreaterDate()) {
                        this.movieEntityMapWinners.get(productor).setGreaterDate(obj.getYear());
                    }

                    if (obj.getYear() < this.movieEntityMapWinners.get(productor).getMinorDate()) {
                        this.movieEntityMapWinners.get(productor).setMinorDate(obj.getYear());
                    }
                    this.movieEntityMapWinners.get(productor).setDateInterval(this.movieEntityMapWinners.get(productor)
                            .getGreaterDate() - this.movieEntityMapWinners.get(productor)
                            .getMinorDate());

//                    System.out.println(this.movieEntityMapWinners.get(productor).toString());
                }
                else
                {
                    MovieEntity movieEntity1 = new MovieEntity();
                    movieEntity1.setProducers(productor);
                    movieEntity1.setMinorDate(obj.getYear());
                    movieEntity1.setGreaterDate(obj.getYear());
                    movieEntity1.setQtdWinners(1);
                    this.movieEntityMapWinners.put(productor, movieEntity1);
                }
            }
        });

        // printa o map e movie winners
        this.printWinners();
    }// setDateMinAndMaxAndIntervalToEachMovie

    private void printWinners()
    {
        System.out.println("\n ****************** WINNERS ****************** \n");
        this.movieEntityMapWinners.forEach((prod, winner)->{
            System.out.println(winner.toString());
        });
    }

    private List<MovieEntity> filterMaxDateMovie()
    {
        int rr = this.movieEntityMapWinners.values().stream().filter((oo) -> oo.getQtdWinners() > 1)
                .map(MovieEntity::getDateInterval).max(new Comparator<>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        if (o1 != null && o2 != null && o1 > o2)
                            return 1;
                        else
                            return -1;
                    }
                }).get();

        List<MovieEntity> allLongestWinners = this.movieEntityMapWinners.values().stream().filter((oo) -> {
            return oo.getDateInterval() == rr;
        }).collect(Collectors.toList());

        return allLongestWinners;
    }// filterMaxMovie

    private List<MovieEntity> filterMinDateMovie()
    {
        int rr = this.movieEntityMapWinners.values().stream().filter((oo) -> oo.getQtdWinners() > 1)
                .map(MovieEntity::getDateInterval).min(new Comparator<>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        if (o1 != null && o2 != null && o1 > o2)
                            return 1;
                        else
                            return -1;
                    }
                }).get();

        System.out.println("\n >>>> min first: "+rr);

        List<MovieEntity> allFasterWinners = this.movieEntityMapWinners.values().stream().filter((oo) -> {
            return oo.getDateInterval() == rr;
        }).collect(Collectors.toList());

        System.out.println("\n>>>>>> allFasterWinners : "+ Arrays.toString(allFasterWinners.toArray()));

        return allFasterWinners;

//        return this.movieEntityMapWinners.values().stream().filter((oo) -> {
//            return oo.getQtdWinners() > 1;
//        }).min(new Comparator<MovieEntity>()
//               {
//                   @Override
//                   public int compare(MovieEntity o1, MovieEntity o2) {
//                       if (o1 != null && o2 != null && o1.getDateInterval() > o2.getDateInterval())
//                           return 1;
//                       else
//                           return -1;
//
//                   }
//               }
//        );
    }// filterMinMovie

}// MovieController
