package dtos;

public class DadJokeDTO {

    private String id;
    private String joke;
    private int status;


    public DadJokeDTO(){}

    public DadJokeDTO(String id, String joke, int status) {
        this.id = id;
        this.joke = joke;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DadJokeDTO{" +
                "id='" + id + '\'' +
                ", joke='" + joke + '\'' +
                ", status=" + status +
                '}';
    }
}
