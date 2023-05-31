package com.atlasian.practice.voting;

import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(JMockit.class)
public class VotingServiceTest {

    @Test
    public void testFindWinner(){
        Candidate c1 = new Candidate("Sumitra");
        Candidate c2 = new Candidate("Mohit");
        Candidate c3 = new Candidate("Vandna");

        VotingService votingService = new VotingService(Arrays.asList(c1, c2, c3), 3);
        votingService.addVote(new Vote(Arrays.asList(c1, c2, c3)));
        votingService.addVote(new Vote(Arrays.asList(c1, c3, c2)));
        votingService.addVote(new Vote(Arrays.asList(c1, c2, c3)));
        votingService.addVote(new Vote(Arrays.asList(c2, c1, c3)));
        votingService.addVote(new Vote(Arrays.asList(c2, c3, c1)));
        votingService.addVote(new Vote(Arrays.asList(c3, c2, c1)));

        new Verifications(){
            {
                Candidate winner = votingService.findWinner();

                Assert.assertNotNull(winner);
                Assert.assertEquals("Sumitra", winner.getCandidateName());
            }
        };
    }

    @Test
    public void testFindWinnerTieUsecase(){
        Candidate c1 = new Candidate("Sumitra");
        Candidate c2 = new Candidate("Mohit");
        Candidate c3 = new Candidate("Vandna");

        VotingService votingService = new VotingService(Arrays.asList(c1, c2, c3), 3);
        votingService.addVote(new Vote(Arrays.asList(c1, c2, c3)));
        votingService.addVote(new Vote(Arrays.asList(c1, c3, c2)));
        votingService.addVote(new Vote(Arrays.asList(c1, c2, c3)));
        votingService.addVote(new Vote(Arrays.asList(c2, c1, c3)));
        votingService.addVote(new Vote(Arrays.asList(c2, c3, c1)));
        votingService.addVote(new Vote(Arrays.asList(c2, c1, c3)));
        votingService.addVote(new Vote(Arrays.asList(c3, c2, c1)));

        new Verifications(){
            {
                Candidate winner = votingService.findWinner();

                Assert.assertNotNull(winner);
                Assert.assertEquals("Mohit", winner.getCandidateName());
            }
        };
    }
}
