
package com.voidK.gitview.models.GitQueryRepoModel;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Owner {

    private String login;
    private Integer id;
    private String node_id;
    private String avatar_url;
    private String url;
    private String received_events_url;
    private String type;
    private String html_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private Boolean site_admin;

    public Owner(String login, Integer id, String node_id, String avatar_url, String url, String received_events_url, String type, String html_url, String subscriptions_url, String organizations_url, String repos_url, Boolean site_admin) {
        this.login = login;
        this.id = id;
        this.node_id = node_id;
        this.avatar_url = avatar_url;
        this.url = url;
        this.received_events_url = received_events_url;
        this.type = type;
        this.html_url = html_url;
        this.subscriptions_url = subscriptions_url;
        this.organizations_url = organizations_url;
        this.repos_url = repos_url;
        this.site_admin = site_admin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public Boolean getSite_admin() {
        return site_admin;
    }

    public void setSite_admin(Boolean site_admin) {
        this.site_admin = site_admin;
    }

    // TO Convert the object for room database
    public static class TypeConverterOwner{
        Gson gson = new Gson();
        @TypeConverter
        public Owner toOwnerList(String data){
            if(data == null) return null;

            Type type = new TypeToken<Owner>() {}.getType();
            return gson.fromJson(data, type);
        }

        @TypeConverter
        public String fromOwnerList(Owner owner){
            return gson.toJson(owner);
        }
    }
}


