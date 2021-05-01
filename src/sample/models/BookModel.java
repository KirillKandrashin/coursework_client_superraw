package sample.models;

import sample.utils.ApiSessionBook;

import java.util.ArrayList;

public class BookModel {
    ArrayList<Book> booksList = new ArrayList<>();
    private ArrayList<BookModel.DataChangedListener> dataChangedListeners = new ArrayList<>();
    private ApiSessionBook apiSessionBook = new ApiSessionBook();

    public interface DataChangedListener {
        void dataChanged(ArrayList<Book> booksList);
    }

    public void addDataChangedListener(BookModel.DataChangedListener listener) {
        this.dataChangedListeners.add(listener);
    }

    public void load() {
        booksList.clear();

        booksList.addAll(apiSessionBook.getAllfromTable("/books"));
        this.emitDataChanged();
    }

    public void add(String json) {
        apiSessionBook.createBook(json);

        for (BookModel.DataChangedListener listener : dataChangedListeners) {
            listener.dataChanged(booksList);
        }
    }

    public void edit(Book ed_book) {
        String json = ed_book.toJson();
        apiSessionBook.editBook(ed_book.getId(), json);

        for (BookModel.DataChangedListener listener : dataChangedListeners) {
            listener.dataChanged(booksList);
        }
    }

    private void emitDataChanged(){
        for (BookModel.DataChangedListener listener: dataChangedListeners) {
            listener.dataChanged(booksList);
        }
    }
}
