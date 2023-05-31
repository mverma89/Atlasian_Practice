package com.atlasian.practice.voting;

import java.util.List;

public class Vote {

    private List<Candidate> candidatesPreference;
    public Vote(List<Candidate> candidatesPreference) {
        this.candidatesPreference = candidatesPreference;
    }

    public List<Candidate> getCandidatesPreference() {
        return candidatesPreference;
    }
}
