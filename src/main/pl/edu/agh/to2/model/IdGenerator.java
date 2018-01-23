package pl.edu.agh.to2.model;

public class IdGenerator {
    private static IdGenerator generator = null;

    public static IdGenerator getGenerator() {
        if (generator == null)
            generator = new IdGenerator();

        return generator;
    }

    private int count;

    private IdGenerator() {
        count = 1;
    }

    public int generateId() {
        return count++;
    }

    public void reset(){count = 1;}
}
