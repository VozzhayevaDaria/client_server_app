package CollectionFiles;

import java.util.HashMap;

public enum MusicGenre {
    ROCK{
        @Override
        public String toString() {
            return "Rock";
        }
    },
    PROGRESSIVE_ROCK{
        @Override
        public String toString() {
            return "Progressive rock";
        }
    },
    POST_PUNK{
        @Override
        public String toString() {
            return "Post punk";
        }
    },
    POST_ROCK{
        @Override
        public String toString() {
            return "Post rock";
        }
    },
    PUNK_ROCK{
        @Override
        public String toString() {
            return "Punk rock";
        }
    };
    public static Integer getGenreId(MusicGenre genre){
        HashMap<MusicGenre, Integer> genres = new HashMap<>();
        genres.put(MusicGenre.ROCK, 1);
        genres.put(MusicGenre.PROGRESSIVE_ROCK, 2);
        genres.put(MusicGenre.POST_PUNK, 3);
        genres.put(MusicGenre.POST_ROCK, 4);
        genres.put(MusicGenre.PUNK_ROCK, 5);
        return genres.get(genre);
    }
    public static String getGenreName(String num){
        HashMap<String, String> genres = new HashMap<>();
        genres.put("1", MusicGenre.ROCK.toString());
        genres.put("2", MusicGenre.PROGRESSIVE_ROCK.toString());
        genres.put("3", MusicGenre.POST_PUNK.toString());
        genres.put("4", MusicGenre.POST_ROCK.toString());
        genres.put("5", MusicGenre.PUNK_ROCK.toString());
        return genres.get(num);
    }
}
