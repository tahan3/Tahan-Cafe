package by.epam.mtlcwtchr.ecafe.dao.builder.limiter;

public enum LogicConcatenator {

    AND,
    OR;

    @Override
    public String toString() {
        return switch (this){
            case AND -> "AND";
            case OR -> "OR";
        };
    }

}
