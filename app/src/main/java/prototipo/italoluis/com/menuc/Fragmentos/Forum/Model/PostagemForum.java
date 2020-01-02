package prototipo.italoluis.com.menuc.Fragmentos.Forum.Model;

import java.util.List;

public class PostagemForum {

    private String fAutor, fTitulo, fCorpoDaPostagem;
    private List<Resposta> fRespostas;

    public PostagemForum(){ }

    public PostagemForum (String autor, String corpoDaPostagem, List<Resposta> respostas, String titulo){
        this.fAutor = autor;
        this.fCorpoDaPostagem = corpoDaPostagem;
        this.fRespostas = respostas;
        this.fTitulo = titulo;
    }

    public String getfAutor() {
        return fAutor;
    }

    public void setfAutor(String fAutor) {
        this.fAutor = fAutor;
    }

    public String getfCorpoDaPostagem() {
        return fCorpoDaPostagem;
    }

    public void setfCorpoDaPostagem(String fCorpoDaPostagem) {
        this.fCorpoDaPostagem = fCorpoDaPostagem;
    }

    public List<Resposta> getfRespostas() {
        return fRespostas;
    }

    public void setfRespostas(List<Resposta> fRespostas) {
        this.fRespostas = fRespostas;
    }

    public String getfTitulo() {
        return fTitulo;
    }

    public void setfTitulo(String fTitulo) {
        this.fTitulo = fTitulo;
    }
}
