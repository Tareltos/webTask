package by.tareltos.webtask.builder;

import by.tareltos.webtask.entity.Candies;
import by.tareltos.webtask.entity.Candy;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCandiesBuilder {

    protected Set<Candy> candies;

    public AbstractCandiesBuilder() {
        candies = new HashSet<>();
    }

    public AbstractCandiesBuilder(Set<Candy> candies) {
        this.candies = candies;
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    abstract public void buildSetCandies(String fileaNme);
}
