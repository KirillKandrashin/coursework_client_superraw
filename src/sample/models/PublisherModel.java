package sample.models;

import sample.utils.ApiSessionPublisher;

import java.util.ArrayList;

public class PublisherModel {
    ArrayList<Publisher> publishersList = new ArrayList<>();
    private ArrayList<PublisherModel.DataChangedListener> dataChangedListeners = new ArrayList<>();
    private ApiSessionPublisher apiSessionPublisher = new ApiSessionPublisher();

    public interface DataChangedListener {
        void dataChanged(ArrayList<Publisher> publishersList);
    }

    public void addDataChangedListener(PublisherModel.DataChangedListener listener) {
        this.dataChangedListeners.add(listener);
    }

    public void load() {
        publishersList.clear();

        publishersList.addAll(apiSessionPublisher.getAllfromTable("/publishers"));
        this.emitDataChanged();
    }

    public void add(String json) {
        apiSessionPublisher.createPublisher(json);

        for (PublisherModel.DataChangedListener listener : dataChangedListeners) {
            listener.dataChanged(publishersList);
        }
    }

    public void edit(Publisher ed_publisher) {
        String json = ed_publisher.toJsonPut();
        apiSessionPublisher.editPublisher(ed_publisher.getId(), json);

        for (PublisherModel.DataChangedListener listener : dataChangedListeners) {
            listener.dataChanged(publishersList);
        }
    }

    private void emitDataChanged(){
        for (PublisherModel.DataChangedListener listener: dataChangedListeners) {
            listener.dataChanged(publishersList);
        }
    }
}

