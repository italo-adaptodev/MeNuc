package prototipo.italoluis.com.fireprot3.Models;

public class Indicados {

    private String nomeIndicado;
    private String emailIndicado;
    private String nomePadrinho;
    private String emailPadrinho;
    private boolean isAuthor;


    public Indicados() {

    }

    public Indicados(String nomeIndicado, String emailIndicado, String emailPadrinho, Boolean isAuthor) {
        setNomeIndicado(nomeIndicado);
        setEmailIndicado(emailIndicado);
        setEmailPadrinho(emailPadrinho);
        setAuthor(isAuthor);

    }

    public String getNomeIndicado() {
        return nomeIndicado;
    }

    public void setNomeIndicado(String nomeIndicado) {
        this.nomeIndicado = nomeIndicado;
    }

    public String getEmailIndicado() {
        return emailIndicado;
    }

    public void setEmailIndicado(String emailIndicado) {
        this.emailIndicado = emailIndicado;
    }

    public String getNomePadrinho() {
        return nomePadrinho;
    }

    public void setNomePadrinho(String nomePadrinho) {
        this.nomePadrinho = nomePadrinho;
    }

    public String getEmailPadrinho() {
        return emailPadrinho;
    }

    public void setEmailPadrinho(String emailPadrinho) {
        this.emailPadrinho = emailPadrinho;
    }


    public boolean isAuthor() {
        return isAuthor;
    }

    public void setAuthor(boolean author) {
        isAuthor = author;
    }
}