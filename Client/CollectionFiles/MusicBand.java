package CollectionFiles;

import java.time.LocalDate;

public class MusicBand {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    //private static HashSet<Long> idList = new HashSet<Long>();
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Значение поля должно быть больше 0
    private long singlesCount; //Значение поля должно быть больше 0
    private Long albumsCount; //Значение поля должно быть больше 0
    private MusicGenre genre; //Поле может быть null
    private Album bestAlbum; //Поле может быть null
    public MusicBand(){}
    public MusicBand(String name){
        this.id = Long.valueOf((long) (Math.random() * 9223372036854775806L) + 1);
        this.creationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public long getSinglesCount() {
        return singlesCount;
    }

    public Long getAlbumsCount() {
        return albumsCount;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Album getBestAlbum() {
        return bestAlbum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setSinglesCount(long singlesCount) {
        this.singlesCount = singlesCount;
    }

    public void setAlbumsCount(Long albumsCount) {
        this.albumsCount = albumsCount;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setBestAlbum(Album bestAlbum) {
        this.bestAlbum = bestAlbum;
    }
    public String toString(){
        return
                "---> " + name + " <---" + "\n" +
                "ID: " + Long.valueOf(id).toString() + "\n" +
                "Coordinates: " + coordinates.toString() + "\n" +
                "Creation date: " + creationDate.toString() + "\n" +
                "Number of participants: " + Long.valueOf(numberOfParticipants) + "\n" +
                "Singles count: " + Long.valueOf(singlesCount) + "\n" +
                "Albums count: " + Long.valueOf(albumsCount) + "\n" +
                "Genre: " + genre.toString() + "\n" +
                "Best albom: " + bestAlbum.toString();
                        

    }


}
