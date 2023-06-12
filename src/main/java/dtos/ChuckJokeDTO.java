package dtos;

public class ChuckJokeDTO {

    // PARAMETERS: they NEED to match the data fetched from the api.
    private String id;
    private String value;

    // null args Constructor
    public ChuckJokeDTO() {
    }
    // Constructor
    public ChuckJokeDTO(String id, String value) {
        this.id = id;
        this.value = value;

    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setJoke(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ChuckJokeDTO{" +
                "id=" + id +
                ", joke='" + value + '\'' +
                '}';
    }
}
