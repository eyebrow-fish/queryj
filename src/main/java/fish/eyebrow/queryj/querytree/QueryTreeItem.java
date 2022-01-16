package fish.eyebrow.queryj.querytree;

import java.util.ArrayList;
import java.util.Objects;

sealed public interface QueryTreeItem {
    String getName();
    void setName(String name);
    QueryTreeItem getParent();

    final class QueryGroup implements QueryTreeItem {
        private String name;
        private QueryTreeItem parent;
        private ArrayList<QueryTreeItem> children;

        public QueryGroup(String name, QueryTreeItem parent, ArrayList<QueryTreeItem> children) {
            this.name = name;
            this.parent = parent;
            this.children = children;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public QueryTreeItem getParent() {
            return parent;
        }

        public void setParent(QueryTreeItem parent) {
            this.parent = parent;
        }

        public ArrayList<QueryTreeItem> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<QueryTreeItem> children) {
            this.children = children;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (QueryGroup) obj;
            return Objects.equals(this.name, that.name) &&
                   Objects.equals(this.parent, that.parent) &&
                   Objects.equals(this.children, that.children);
        }
    }

    final class Query implements QueryTreeItem {
        private String name;
        private String method;
        private String url;
        private String body;
        private QueryTreeItem parent;

        public Query(String name, String method, String url, String body, QueryTreeItem parent) {
            this.name = name;
            this.method = method;
            this.url = url;
            this.body = body;
            this.parent = parent;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public QueryTreeItem getParent() {
            return parent;
        }

        public void setParent(QueryTreeItem parent) {
            this.parent = parent;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Query) obj;
            return Objects.equals(this.name, that.name) &&
                   Objects.equals(this.method, that.method) &&
                   Objects.equals(this.url, that.url) &&
                   Objects.equals(this.body, that.body) &&
                   Objects.equals(this.parent, that.parent);
        }
    }

    static QueryTreeItem findQueryTreeItem(ArrayList<QueryTreeItem> search, String qualifiedName) {
        if (qualifiedName == null) return null;

        String[] levels = qualifiedName.split("\\.");
        if (levels.length < 1) return null;

        String nextQualifiedName = levels[0];

        for (QueryTreeItem queryTreeItem : search) {
            if (queryTreeItem.getName().equals(nextQualifiedName)) {
                if (levels.length > 1 && queryTreeItem instanceof (QueryTreeItem.QueryGroup queryGroup)) {
                    return findQueryTreeItem(
                            queryGroup.getChildren(),
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
