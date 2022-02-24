package fish.eyebrow.queryj.persist.item;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

sealed public interface QueryTreeItem extends Cloneable {
    String getName();
    void setName(String name);
    Object clone() throws CloneNotSupportedException;

    final class QueryGroup implements QueryTreeItem {
        private String name;
        private ArrayList<QueryTreeItem> children;

        public QueryGroup(String name, ArrayList<QueryTreeItem> children) {
            this.name = name;
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

        public ArrayList<QueryTreeItem> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<QueryTreeItem> children) {
            this.children = children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            QueryGroup that = (QueryGroup) o;
            return Objects.equals(name, that.name) && Objects.equals(children, that.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children);
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    final class Query implements QueryTreeItem {
        private String name;
        private String method;
        private String url;
        private String body;
        private Map<String, String> headers;

        public Query(
                String name,
                String method,
                String url,
                String body,
                Map<String, String> headers
        ) {
            this.name = name;
            this.method = method;
            this.url = url;
            this.body = body;
            this.headers = headers;
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

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Query query = (Query) o;
            return Objects.equals(name, query.name) && Objects.equals(method, query.method) &&
                   Objects.equals(url, query.url) && Objects.equals(body, query.body) &&
                   Objects.equals(headers, query.headers);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, method, url, body, headers);
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
