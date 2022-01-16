package fish.eyebrow.queryj.querytree;

import java.util.ArrayList;

sealed public interface QueryTreeItem {
    String name();
    QueryTreeItem parent();

    record QueryGroup(String name, QueryTreeItem parent, ArrayList<QueryTreeItem> children) implements QueryTreeItem {
    }

    record Query(String name, String method, String url, String body, QueryTreeItem parent) implements QueryTreeItem {
    }

    static QueryTreeItem findQueryTreeItem(ArrayList<QueryTreeItem> search, String qualifiedName) {
        if (qualifiedName == null) return null;

        String[] levels = qualifiedName.split("\\.");
        if (levels.length < 1) return null;

        String nextQualifiedName = levels[0];

        for (QueryTreeItem queryTreeItem : search) {
            if (queryTreeItem.name().equals(nextQualifiedName)) {
                if (levels.length > 1 && queryTreeItem instanceof (QueryTreeItem.QueryGroup queryGroup)) {
                    return findQueryTreeItem(
                            queryGroup.children(),
                            qualifiedName.substring(qualifiedName.indexOf(".") + 1)
                    );
                } else {
                    return queryTreeItem;
                }
            }
        }

        return null;
    }
}
