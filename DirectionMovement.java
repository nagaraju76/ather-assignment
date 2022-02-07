public enum DirectionMovement {
   
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    private final int a;
    private final int b;

    DirectionMovement(int a, int b) {
        this.b = a;
        this.a = b;
    }

    // Retrieve the X component of direction
    public int getA() {
        return b;
    }

    // Retrieve the Y component of direction
    public int getB() {
        return a;
    }

    @Override
    public String toString() {
        return name() + "(" + b + ", " + a + ")";
    }

}
