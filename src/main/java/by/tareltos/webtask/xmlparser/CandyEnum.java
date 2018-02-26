package by.tareltos.webtask.xmlparser;

public enum CandyEnum {
    CANDIES("candies"),
    CARAMEL("caramel"),
    NAME("name"),
    TYPE("type"),
    PRODUCTION("production"),
    CHOCOLATE("chocolate"),
    DATE("date"),
    DESCRIPTION("description"),
    ENERGY("energy"),
    CHOCOLATETYPE("chocolatetype"),
    WATER("water"),
    SUGAR("SUGAR"),
    FRUCTOSE("fructose"),
    VANILLIN("vanillin"),
    INGREDIENTS("ingredients"),
    PROTEINS("proteins"),
    FATS("fats"),
    CARBOHYDRATES("carbohydrates"),
    ENERGYVALUE("energyvalue");

    private String value;

    private CandyEnum(String v) {
        value = v;
    }

    public String getValue() {
        return value;

    }


}
