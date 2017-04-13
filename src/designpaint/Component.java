package designpaint;

import java.util.List;

public interface Component {
    public GroupListItem toListItem();
    public List<Component> toFlatList();
}
