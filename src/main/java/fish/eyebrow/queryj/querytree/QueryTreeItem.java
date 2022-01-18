package fish.eyebrow.queryj.querytree;

import java.util.Objects;

sealed public interface QueryTreeItem {
    String getName();
    void setName(String name);

    final class QueryGroup implements QueryTreeItem {
        private String name;

        public QueryGroup(String name) {
            this.name = name;
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
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (QueryGroup) obj;
            return Objects.equals(this.name, that.name);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    final class Query implements QueryTreeItem {
        private String name;
        private String method;
        private String url;
        private String body;

        public Query(String name, String method, String url, String body) {
            this.name = name;
            this.method = method;
            this.url = url;
            this.body = body;
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
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Query) obj;
            return Objects.equals(this.name, that.name) &&
                   Objects.equals(this.method, that.method) &&
                   Objects.equals(this.url, that.url) &&
                   Objects.equals(this.body, that.body);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
