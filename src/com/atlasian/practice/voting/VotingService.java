package com.atlasian.practice.voting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotingService {

    private Integer totalParticipatedCandidates;
    private Map<Candidate, CandidateResult> candidateVotes;

    public VotingService(List<Candidate> candidates, Integer totalParticipatedCandidates) {
        this.totalParticipatedCandidates = totalParticipatedCandidates;
        this.candidateVotes = new HashMap<>();
        for(Candidate candidate : candidates){
            this.candidateVotes.put(candidate, new CandidateResult(candidate, totalParticipatedCandidates));
        }
    }

    public void addVote(Vote vote){

        for(int i=0; i< totalParticipatedCandidates; i++){
            candidateVotes.get(vote.getCandidatesPreference().get(i)).getVoteValue()[i]++;
        }
    }

    public Candidate findWinner(){
        return candidateVotes.values().stream().sorted().findFirst().get().getCandidate();
    }
}
