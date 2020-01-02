package prototipo.italoluis.com.menuc.Fragmentos.Forum.Firebase;

public class FirebaseForumPreviewDataAuth {

    private String _forumPreviewTitulo, _forumPreviewData, _forumPreviewAutor, _forumPreviewQtdRespostas;

    public FirebaseForumPreviewDataAuth(String forumPreviewTitulo, String forumPreviewData,
                                        String forumPreviewAutor, String forumPreviewQtdRespostas) {
        this._forumPreviewTitulo = forumPreviewTitulo;
        this._forumPreviewData = forumPreviewData;
        this._forumPreviewAutor = forumPreviewAutor;
        this._forumPreviewQtdRespostas = forumPreviewQtdRespostas;
    }

    public FirebaseForumPreviewDataAuth() {
    }

    public String get_forumPreviewTitulo() {
        return _forumPreviewTitulo;
    }

    public void set_forumPreviewTitulo(String _forumPreviewTitulo) {
        this._forumPreviewTitulo = _forumPreviewTitulo;
    }

    public String get_forumPreviewData() {
        return _forumPreviewData;
    }

    public void set_forumPreviewData(String _forumPreviewData) {
        this._forumPreviewData = _forumPreviewData;
    }

    public String get_forumPreviewAutor() {
        return _forumPreviewAutor;
    }

    public void set_forumPreviewAutor(String _forumPreviewAutor) {
        this._forumPreviewAutor = _forumPreviewAutor;
    }

    public String get_forumPreviewQtdRespostas() {
        return _forumPreviewQtdRespostas;
    }

    public void set_forumPreviewQtdRespostas(String _forumPreviewQtdRespostas) {
        this._forumPreviewQtdRespostas = _forumPreviewQtdRespostas;
    }
}

