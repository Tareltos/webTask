package by.tareltos.webtask.builder;

public class AbstractCandiesFactory {

    private String schemaName;

    public AbstractCandiesBuilder createCandyBuilder(String typeParser) {

        ParserType type = ParserType.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new CandiesDOMBuilder();
            case SAX:
                return new CandiesSAXBuilder();
            case STAX:
                return new CandiesStAXBuilder();
            case MARSHALLER:
                UnMarshalWithXSDBuilder bl =new UnMarshalWithXSDBuilder();
                bl.setSchemaName(schemaName);
                return bl;
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
}
