
package com.voidK.gitview.models.gitqueryrepo;

public class License {

    private String key;
    private String name;
    private String url;
    private String spdxId;
    private String nodeId;
    private String htmlUrl;


    public License() {
    }

    public License(String key, String name, String url, String spdxId, String nodeId, String htmlUrl) {
        super();
        this.key = key;
        this.name = name;
        this.url = url;
        this.spdxId = spdxId;
        this.nodeId = nodeId;
        this.htmlUrl = htmlUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpdxId() {
        return spdxId;
    }

    public void setSpdxId(String spdxId) {
        this.spdxId = spdxId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

}
