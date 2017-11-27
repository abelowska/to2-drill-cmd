package java.pl.edu.agh.to2.Model;

public class Pair<F,S>
{
    private final F first;

    private final S second;

    Pair(F first, S second)
    {
        this.first = first;
        this.second = second;
    }

    public F getFirst()
    {
        return first;
    }

    public S getSecond()
    {
        return second;
    }
}