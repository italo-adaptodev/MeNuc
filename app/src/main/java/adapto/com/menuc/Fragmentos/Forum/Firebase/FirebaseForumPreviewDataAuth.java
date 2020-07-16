package adapto.com.menuc.Fragmentos.Forum.Firebase;

public class FirebaseForumPreviewDataAuth {

    private String fTitulo, fDataPostagem, fAutor,key;
    private int fQtdComentarios;

    public FirebaseForumPreviewDataAuth(String forumPreviewTitulo, String forumPreviewData,
                                        String forumPreviewAutor, int forumPreviewQtdRespostas) {
        this.fTitulo = forumPreviewTitulo;
        this.fDataPostagem = forumPreviewData;
        this.fAutor = forumPreviewAutor;
        this.fQtdComentarios = forumPreviewQtdRespostas;
    }

    public FirebaseForumPreviewDataAuth() {
    }


    public String getfTitulo() {
        return fTitulo;
    }

    public void setfTitulo(String fTitulo) {
        this.fTitulo = fTitulo;
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

    public int getfQtdComentarios() {
        return fQtdComentarios;
    }

    public void setfQtdComentarios(int fQtdComentarios) {
        this.fQtdComentarios = fQtdComentarios;
    }

    public String getKey(){return key;}
}

