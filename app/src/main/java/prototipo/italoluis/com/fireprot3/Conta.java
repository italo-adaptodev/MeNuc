package prototipo.italoluis.com.fireprot3;

public class Conta {

    String contaId;
    String contaNome;
    String contaEmail;
    String contaSenha;

    public Conta(){

    }

    public Conta(String contaId, String contaNome, String contaEmail, String contaSenha) {
        this.contaId = contaId;
        this.contaNome = contaNome;
        this.contaEmail = contaEmail;
        this.contaSenha = contaSenha;
    }

    public String getContaId() {
        return contaId;
    }

    public String getContaNome() {
        return contaNome;
    }

    public String getContaEmail() {
        return contaEmail;
    }

    public String getContaSenha() {
        return contaSenha;
    }
}
