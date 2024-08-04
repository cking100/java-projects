import java.util.ArrayList;
import java.util.List;
public class VotingSystem {
    private List<Candidate> candidates;
    public VotingSystem() {
        this.candidates = new ArrayList<>();
    }
public void addCandidate(Candidate candidate) {
    candidates.add(candidate);
}
public void voteForCandidate( String candidateName) {
    for (Candidate candidate : candidates) {
        if(candidate.getName().equalsIgnoreCase(candidateName)) {
            new Vote(candidate);
            return;
        }
    }
    System.out.println("candidate not found" + candidateName);

}
public List<Candidate> getCandidates() {
    return candidates;
}
public void displayResults() {
    System.out.println("voting results");
    for (Candidate candidate : candidates) {
        System.out.println(candidate.getName()+ "(" + candidate.getParty() + "):" + candidate.getVotes() + "votes");
    }
}

}
