package adapto.com.menuc.Fragmentos.Forum.Model;

import java.util.List;

public class Resposta {

    private String autor;
    private List<String> respostas;

    public Resposta() {
    }

    public Resposta(String autor, List<String> respostas) {
        this.autor = autor;
        this.respostas = respostas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
    }
}
