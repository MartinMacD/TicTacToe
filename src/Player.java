public class Player {
    private String pieceType;
    public String playerName;

    /**
     return players piece type
     @return Piece type of player
     **/
    public String getPieceType() {
        return pieceType;
    }

    /**
     Set players piece type
     @param pieceType (X or O)
     **/
    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    /**
     return players name
     @return Players name
     **/
    public String getPlayerName() {
        return playerName;
    }

    /**
     Set players piece type
     @param playerName
     **/
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
