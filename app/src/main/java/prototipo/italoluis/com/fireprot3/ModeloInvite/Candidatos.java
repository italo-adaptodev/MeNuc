package prototipo.italoluis.com.fireprot3.ModeloInvite;

public class Candidatos  {
    private String nome_candidato, email_candidato, nome_padrinho;

    public Candidatos() {
    }

    public Candidatos(String nome_candidato, String email_candidato, String nome_padrinho, String email_padrinho) {
        this.nome_candidato = nome_candidato;
        this.email_candidato = email_candidato;
        this.nome_padrinho = nome_padrinho;

    }

    public String getNome_candidato() {
        return nome_candidato;
    }

    public void setNome_candidato(String nome_candidato) {
        this.nome_candidato = nome_candidato;
    }

    public String getEmail_candidato() {
        return email_candidato;
    }

    public void setEmail_candidato(String email_candidato) {
        this.email_candidato = email_candidato;
    }

    public String getNome_padrinho() {
        return nome_padrinho;
    }

    public void setNome_padrinho(String nome_padrinho) {
        this.nome_padrinho = nome_padrinho;
    }


}
