package sample.models;

import sample.utils.ApiSessionVisitor;

import java.util.ArrayList;

public class VisitorModel {
    ArrayList<Visitor> visitorsList = new ArrayList<>();
    private ArrayList<VisitorModel.DataChangedListener> dataChangedListeners = new ArrayList<>();
    private ApiSessionVisitor apiSessionVisitor = new ApiSessionVisitor();

    public interface DataChangedListener {
        void dataChanged(ArrayList<Visitor> visitorsList);
    }

    public void addDataChangedListener(VisitorModel.DataChangedListener listener) {
        this.dataChangedListeners.add(listener);
    }

    public void load() {
        visitorsList.clear();

        visitorsList.addAll(apiSessionVisitor.getAllfromTable("/visitors"));
        this.emitDataChanged();
    }

    public void add(String json) {
        ApiSessionVisitor apiSessionVisitor = new ApiSessionVisitor();
        apiSessionVisitor.createVisitor(json);

        for (VisitorModel.DataChangedListener listener : dataChangedListeners) {
            listener.dataChanged(visitorsList);
        }
    }

  //  public void edit(Visitor ed_visitor) {
  //      ApiSessionVisitor apiSessionVisitor = new ApiSessionVisitor();
  //      String json = new Visitor(ed_visitor.getTitle(), ed_visitor.getType(),ed_visitor.getGenre(), ed_visitor.getNumber_of_copies(), ed_visitor.getAuthors_link(), ed_visitor.getPublishers_link(), ed_visitor.getSection_link()).toJson();
  //      apiSessionVisitor.editVisitor(ed_visitor.getId(), json);

//        for (VisitorModel.DataChangedListener listener : dataChangedListeners) {
  //          listener.dataChanged(visitorsList);
   //     }
   // }

    private void emitDataChanged(){
        for (VisitorModel.DataChangedListener listener: dataChangedListeners) {
            listener.dataChanged(visitorsList);
        }
    }
}
