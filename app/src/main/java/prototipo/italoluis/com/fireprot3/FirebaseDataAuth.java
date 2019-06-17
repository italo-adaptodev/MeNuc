package prototipo.italoluis.com.fireprot3;

public class FirebaseDataAuth {

    String emailIndicado, nomeIndicado, nomePadrinho;



    public FirebaseDataAuth(String emailIndicado, String nomeIndicado, String nomePadrinho) {
        this.emailIndicado = emailIndicado;
        this.nomeIndicado = nomeIndicado;
        this.nomePadrinho = nomePadrinho;

    }

    public FirebaseDataAuth() {
    }

    public String getEmailIndicado() {
        return emailIndicado;
    }

    public void setEmailIndicado(String emailIndicado) {
        this.emailIndicado = emailIndicado;
    }

    public String getNomeIndicado() {
        return nomeIndicado;
    }

    public void setNomeIndicado(String nomeIndicado) {
        this.nomeIndicado = nomeIndicado;
    }

    public String getNomePadrinho() {
        return nomePadrinho;
    }

    public void setNomePadrinho(String nomePadrinho) {
        this.nomePadrinho = nomePadrinho;
    }


}
