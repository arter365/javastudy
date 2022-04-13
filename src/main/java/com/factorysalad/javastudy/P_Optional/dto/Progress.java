package com.factorysalad.javastudy.P_Optional.dto;

import java.time.Duration;

public class Progress {

    private Duration studyDuration;
    private boolean finished;

    public Duration getStudyDuration() {
        return studyDuration;
    }

    public void setStudyDuration(Duration studyDuration) {
        this.studyDuration = studyDuration;
    }
}
