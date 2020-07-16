package adapto.com.menuc.MainActivities.DetalharFirebase;

public class FirebaseForumComentarioDataAuth {

    public String comentarioNomeUsuario, comentarioTexto, postagemKey;

    public FirebaseForumComentarioDataAuth(String respostaNome, String respostaTexto, String key){
        this.comentarioNomeUsuario = respostaNome;
        this.comentarioTexto = respostaTexto;
        this.postagemKey = key;
    }

    public FirebaseForumComentarioDataAuth(){}

    public String getComentarioNomeUsuario() {
        return comentarioNomeUsuario;
    }

    public void setComentarioNomeUsuario(String comentarioNomeUsuario) {
        this.comentarioNomeUsuario = comentarioNomeUsuario;
    }

    public String getComentarioTexto() {
        return comentarioTexto;
    }

    public void setComentarioTexto(String comentarioTexto) {
        this.comentarioTexto = comentarioTexto;
    }

    public String getPostagemKey() {
        return postagemKey;
    }

    public void setPostagemKey(String postagemKey) {
        this.postagemKey = postagemKey;
    }
}
