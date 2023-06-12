package dtos;

import java.util.ArrayList;

public class UltimateJokeDTO {

    private String id;
    private ArrayList<String> ultimateJokeList;

    public UltimateJokeDTO(DadJokeDTO dadJokeDTO, ChuckJokeDTO chuckJokeDTO) {
        this.id = dadJokeDTO.getId()+" && "+chuckJokeDTO.getId();
        ultimateJokeList =  new ArrayList<>();
        addJokes(chuckJokeDTO.getValue());
        addJokes(dadJokeDTO.getJoke());

    }

    public UltimateJokeDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getJokes() {
        return ultimateJokeList;
    }

    public void addJokes(String joke) {
        this.ultimateJokeList.add(joke);
    }


    @Override
    public String toString() {
        return "UltimateJokeDTO{" +
                "id='" + id + '\'' +
                ", jokes=" + ultimateJokeList +
                '}';
    }
}
