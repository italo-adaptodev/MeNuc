package prototipo.adapto.com.menuc.Models;

public class Indicados {

    private String nomeIndicado;
    private String emailIndicado;
    private String nomePadrinho;
    private String emailPadrinho;
    private boolean isAutor;


    public Indicados() {

    }

    public Indicados(String nomeIndicado, String emailIndicado, String emailPadrinho, Boolean isAutor) {
        setNomeIndicado(nomeIndicado);
        setEmailIndicado(emailIndicado);
        setEmailPadrinho(emailPadrinho);
        setAutor(isAutor);

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


    public boolean isAutor() {
        return isAutor;
    }

    public void setAutor(boolean author) {
        isAutor = author;
    }
}