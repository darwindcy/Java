import java.util.Objects;

public class Move {
    Tile fromTile;
    Tile toTile;

    public Move(Tile tile1, Tile tile2){
        this.fromTile = tile1;
        this.toTile = tile2;
    }

    public Tile getFromTile() {
        return fromTile;
    }

    public Tile getToTile() {
        return toTile;
    }

    public String toString(){
        return "[" + fromTile.getX() + ", " + fromTile.getY() + "]" + " to " +
                "[" + toTile.getX() + ", " + toTile.getY() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Move))
            return false;
        Move move = (Move) obj;
        return (this.fromTile == move.fromTile) && (this.toTile == move.toTile);
    }

    @Override
    public int hashCode() {
        //return super.hashCode();
        return Objects.hash(fromTile, toTile);
    }
}
