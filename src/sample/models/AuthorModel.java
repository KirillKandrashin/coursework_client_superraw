package sample.models;

import sample.utils.ApiSessionAuthor;

import java.util.ArrayList;

public class AuthorModel {
    ArrayList<Author> authorsList = new ArrayList<>();
    private ArrayList<AuthorModel.DataChangedListener> dataChangedListeners = new ArrayList<>();
    private ApiSessionAuthor apiSessionAuthor = new ApiSessionAuthor();

    public interface DataChangedListener {
        void dataChanged(ArrayList<Author> authorsList);
    }

    public void addDataChangedListener(AuthorModel.DataChangedListener listener) {
        this.dataChangedListeners.add(listener);
    }

    public void load() {
        authorsList.clear();

        authorsList.addAll(apiSessionAuthor.getAllfromTable("/authors"));
        this.emitDataChanged();
    }

    public void add(String json) {
        apiSessionAuthor.createAuthor(json);

        for (AuthorModel.DataChangedListener listener : dataChangedListeners) {
            listener.dataChanged(authorsList);
        }
    }

    public void edit(Author ed_author) {
        String json = ed_author.toJsonPut();
        apiSessionAuthor.editAuthor(ed_author.getId(), json);

        for (AuthorModel.DataChangedListener listener : dataChangedListeners) {
            listener.dataChanged(authorsList);
        }
    }

    private void emitDataChanged(){
        for (AuthorModel.DataChangedListener listener: dataChangedListeners) {
            listener.dataChanged(authorsList);
        }
    }
}
