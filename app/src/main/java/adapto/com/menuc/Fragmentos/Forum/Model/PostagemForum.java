package adapto.com.menuc.Fragmentos.Forum.Model;

public class PostagemForum {

    private String fAutor;
    private String fTitulo;
    private String fCorpoDaPostagem;
    private String key;
    private String fDataPostagem;
    private int fQtdComentarios;
    private String fEmailAutor;

    public PostagemForum(){ }

    public PostagemForum(String autor, String titulo, String corpoDaPostagem, int QtdComentarios, String dataPostagem, String emailAutor){
        this.fAutor = autor;
        this.fCorpoDaPostagem = corpoDaPostagem;
        this.fTitulo = titulo;
        this.fQtdComentarios = QtdComentarios;
        this.fDataPostagem = dataPostagem;
        this.fEmailAutor = emailAutor;
    }

    public String getfDataPostagem() {
        return fDataPostagem;
    }

    public void setfDataPostagem(String fDataPostagem) {
        this.fDataPostagem = fDataPostagem;
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

    public int getfQtdComentarios() {
        return fQtdComentarios;
    }

    public void setfQtdComentarios(int fQtdComentarios) {
        this.fQtdComentarios = fQtdComentarios;
    }

    public String getfTitulo() {
        return fTitulo;
    }

    public void setfTitulo(String fTitulo) {
        this.fTitulo = fTitulo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getfEmailAutor(){return fEmailAutor;}

    public void setfEmailAutor(String emailAutor) { this.fEmailAutor = emailAutor;}
}
