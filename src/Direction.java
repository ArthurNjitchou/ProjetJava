public enum Direction {
    NORTH(2),
    SOUTH(0),
    EAST(3),
    WEST(1);

    private int frameLineNumber;

    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }

    // Méthode pour récupérer la valeur entière
    public int getFrameLineNumber() {
        return frameLineNumber;
    }
}