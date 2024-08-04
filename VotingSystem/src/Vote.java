public class Vote {
    private Candidate candidate;
    public Vote(Candidate candidate) {
        this.candidate = candidate;
        candidate.addVotes();
    }
public Candidate getCandidate() {
    return candidate;
 } 
}
