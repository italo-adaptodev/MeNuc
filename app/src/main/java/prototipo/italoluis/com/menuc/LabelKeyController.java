package prototipo.italoluis.com.menuc;

public class LabelKeyController {

    public String getLabelKeyBaseFisica() {
        return "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts?labels=BASES+FÍSICAS&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    }

    public String getLabelKey2Radiofarmacia() {
        return "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts?labels=RADIOFARMÁCIA&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    }

    public String getLabelKeyRadionuclideos() {
        return "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts?labels=RADIONUCLÍDEOS&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    }

    public String getLabelKeyEquips() {
        return "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts?labels=EQUIPAMENTOS&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    }

    public String getLabelKeyDiagnoTerapia() {
        return "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts?labels=DIAGNÓSTICO+E+TERAPIA&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    }

    public String getLabelKeyRadioProtec() {
        return "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts?labels=PROTECÃO+RADIOLÓGICA&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    }

    public String getLabelKeyLegisl() {
        return "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts?labels=LEGISLAÇÃO&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    }

    public String getLabelKeyOutros() {
        return "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts?labels=OUTROS&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    }

    public String getQueryParameterPesquisa(String keyword){
        return "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/posts/search?q=" + keyword +"&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    }

}
