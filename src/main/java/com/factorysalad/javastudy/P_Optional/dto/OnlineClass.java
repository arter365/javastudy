package com.factorysalad.javastudy.P_Optional.dto;

import java.util.Optional;

public class OnlineClass {
    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    // Optional은 리턴타입으로만 사용하는 것을 권장한다.
    public Optional<Progress> getProgress() {
        // Optional.of()는 null이 아닌 값이 들어오는 것을 전재로 한다.
        return Optional.ofNullable(progress);   // null일 수 있는 값을 Optional로 감싼다. null을 리턴하고 싶을 때는 Optional.empty()를 리턴한다.
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
