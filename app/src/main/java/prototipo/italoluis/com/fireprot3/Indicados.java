package prototipo.italoluis.com.fireprot3;

public class Indicados {

    private String nomeIndicado;
    private String emailIndicado;
    private String nomePadrinho;
    private String emailPadrinho;
    private boolean isAutor;

    public Indicados() {
        this.isAutor = false;
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

    public void setAutor(boolean autor) {
        isAutor = autor;
    }


}