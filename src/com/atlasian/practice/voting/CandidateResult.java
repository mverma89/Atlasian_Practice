package com.atlasian.practice.voting;

public class CandidateResult implements Comparable<CandidateResult>{

    private Candidate candidate;
    private int[] voteValue;

    public CandidateResult(Candidate candidate, int maxCandidates) {
        this.candidate = candidate;
        this.voteValue = new int[maxCandidates];
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public int[] getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(int[] voteValue) {
        this.voteValue = voteValue;
    }

    @Override
    public int compareTo(CandidateResult c2) {

        for(int i=0; i< voteValue.length; i++){
            int diff = c2.getVoteValue()[i] - this.voteValue[i];

            if(diff != 0){
                return diff;
            }
        }
        return c2.getCandidate().getCandidateName().compareTo(this.candidate.getCandidateName());
    }
}
