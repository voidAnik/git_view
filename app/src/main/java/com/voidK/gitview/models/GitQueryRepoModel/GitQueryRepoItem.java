
package com.voidK.gitview.models.GitQueryRepoModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "query_repositories")
public class GitQueryRepoItem implements Serializable {

    @PrimaryKey
    private Integer id;
    private String node_id;
    private String name;
    private String full_name;
    private Owner owner;
    private Boolean _private;
    private String html_url;
    private String description;
    private Boolean fork;
    private String url;
    private String created_at;
    private String updated_at;
    private String pushed_at;
    private String homepage;
    private Integer size;
    private Integer stargazers_count;
    private Integer watchers_count;
    private String language;
    private Integer forks_count;
    private Integer open_issues_count;
    private String master_branch;
    private String default_branch;
    private Integer score;
    private String collaborators_url;
    private String downloads_url;
    private String git_url;
    private String clone_url;
    private Integer watchers;
    private String visibility;

    public GitQueryRepoItem(Integer id, String node_id, String name, String full_name, Owner owner, Boolean _private, String html_url, String description, Boolean fork, String url, String created_at, String updated_at, String pushed_at, String homepage, Integer size, Integer stargazers_count, Integer watchers_count, String language, Integer forks_count, Integer open_issues_count, String master_branch, String default_branch, Integer score, String collaborators_url, String downloads_url, String git_url, String clone_url, Integer watchers, String visibility) {
        this.id = id;
        this.node_id = node_id;
        this.name = name;
        this.full_name = full_name;
        this.owner = owner;
        this._private = _private;
        this.html_url = html_url;
        this.description = description;
        this.fork = fork;
        this.url = url;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.pushed_at = pushed_at;
        this.homepage = homepage;
        this.size = size;
        this.stargazers_count = stargazers_count;
        this.watchers_count = watchers_count;
        this.language = language;
        this.forks_count = forks_count;
        this.open_issues_count = open_issues_count;
        this.master_branch = master_branch;
        this.default_branch = default_branch;
        this.score = score;
        this.collaborators_url = collaborators_url;
        this.downloads_url = downloads_url;
        this.git_url = git_url;
        this.clone_url = clone_url;
        this.watchers = watchers;
        this.visibility = visibility;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean get_private() {
        return _private;
    }

    public void set_private(Boolean _private) {
        this._private = _private;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Integer stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public Integer getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(Integer watchers_count) {
        this.watchers_count = watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }

    public Integer getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(Integer open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public String getMaster_branch() {
        return master_branch;
    }

    public void setMaster_branch(String master_branch) {
        this.master_branch = master_branch;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCollaborators_url() {
        return collaborators_url;
    }

    public void setCollaborators_url(String collaborators_url) {
        this.collaborators_url = collaborators_url;
    }

    public String getDownloads_url() {
        return downloads_url;
    }

    public void setDownloads_url(String downloads_url) {
        this.downloads_url = downloads_url;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
