public class Phrases {
    static String gamePhrase = "";

    static String playingPhrase = "";

    public void setGamePhrase(String phrase) {
        gamePhrase = phrase;
        playingPhrase = "";
        for (int i = 0; i < gamePhrase.length(); i++) {
            if (gamePhrase.charAt(i) == ' ') {
                playingPhrase += " ";
            }
            else {
                playingPhrase += "_";
            }
        }
    }

    public String getGamePhrase(){
        return gamePhrase;
    }

    public String getPlayingPhrase(){
        return playingPhrase;
    }

    public boolean findLetters(String letter) throws MultipleLettersException{
        if (letter.length() > 1) {
            throw (new MultipleLettersException());
        } 
        int index = gamePhrase.indexOf(letter);
        while (index >= 0) {
            playingPhrase = playingPhrase.substring(0,index) + letter + playingPhrase.substring(index+1);
            index = gamePhrase.indexOf(letter, index + 1);
        }
        return playingPhrase.indexOf("_") < 0;
    }

    
}
