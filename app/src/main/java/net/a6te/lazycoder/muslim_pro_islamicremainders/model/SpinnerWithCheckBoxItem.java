package net.a6te.lazycoder.muslim_pro_islamicremainders.model;

public class SpinnerWithCheckBoxItem {
    private int id;
    private String title;
    private boolean selected;

    public SpinnerWithCheckBoxItem(int id, String title, boolean selected) {
        this.id = id;
        this.title = title;
        this.selected = selected;
    }

    public SpinnerWithCheckBoxItem(String title, boolean selected) {
        this.title = title;
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
