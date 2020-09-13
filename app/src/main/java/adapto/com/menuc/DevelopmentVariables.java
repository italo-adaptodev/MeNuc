package adapto.com.menuc;

public enum DevelopmentVariables {

//    FORUM("Forum-Teste"),
//    RESPOSTAS("Respostas-Teste");
    FORUM("Forum"),
    RESPOSTAS("Respostas");


    private String stringValue;
    private int intValue;
    DevelopmentVariables(String toString) {
        stringValue = toString;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
