package fish.eyebrow.queryj;

import fish.eyebrow.queryj.querytree.QueryTreeItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QueryTreeItemTest {
    @Test
    void findTopLevelItem() {
        ArrayList<QueryTreeItem> queryTreeItems = new ArrayList<>();
        QueryTreeItem.Query example = new QueryTreeItem.Query("example", "", "", "", null);
        queryTreeItems.add(example);

        QueryTreeItem found = QueryTreeItem.findQueryTreeItem(queryTreeItems, "example");

        assertEquals(example, found);
    }

    @Test
    void findTopLevelItem_whenNestedExists() {
        ArrayList<QueryTreeItem> queryTreeItems = new ArrayList<>();
        ArrayList<QueryTreeItem> exampleChildren = new ArrayList<>();

        QueryTreeItem.QueryGroup example = new QueryTreeItem.QueryGroup("example", null, exampleChildren);
        QueryTreeItem.Query foo = new QueryTreeItem.Query("foo", "", "", "", example);
        queryTreeItems.add(example);
        exampleChildren.add(foo);

        QueryTreeItem found = QueryTreeItem.findQueryTreeItem(queryTreeItems, "example");

        assertEquals(example, found);
    }

    @Test
    void findNestedItem() {
        ArrayList<QueryTreeItem> queryTreeItems = new ArrayList<>();
        ArrayList<QueryTreeItem> exampleChildren = new ArrayList<>();

        QueryTreeItem.QueryGroup example = new QueryTreeItem.QueryGroup("example", null, exampleChildren);
        QueryTreeItem.Query foo = new QueryTreeItem.Query("foo", "", "", "", example);
        queryTreeItems.add(example);
        exampleChildren.add(foo);

        QueryTreeItem found = QueryTreeItem.findQueryTreeItem(queryTreeItems, "example.foo");

        assertEquals(foo, found);
    }

    @Test
    void cannotFindNested_badTopLevel() {
        ArrayList<QueryTreeItem> queryTreeItems = new ArrayList<>();
        ArrayList<QueryTreeItem> exampleChildren = new ArrayList<>();

        QueryTreeItem.QueryGroup examples = new QueryTreeItem.QueryGroup("examples", null, exampleChildren);
        queryTreeItems.add(examples);
        exampleChildren.add(new QueryTreeItem.Query("foo", "", "", "", examples));

        QueryTreeItem found = QueryTreeItem.findQueryTreeItem(queryTreeItems, "example.foo");

        assertNull(found);
    }

    @Test
    void cannotFindNested_badBottomLevel() {
        ArrayList<QueryTreeItem> queryTreeItems = new ArrayList<>();
        ArrayList<QueryTreeItem> exampleChildren = new ArrayList<>();

        QueryTreeItem.QueryGroup examples = new QueryTreeItem.QueryGroup("example", null, exampleChildren);
        queryTreeItems.add(examples);
        exampleChildren.add(new QueryTreeItem.Query("foo", "", "", "", examples));

        QueryTreeItem found = QueryTreeItem.findQueryTreeItem(queryTreeItems, "example.bar");

        assertNull(found);
    }
}
