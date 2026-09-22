/*Yolanda Exalus
COP 3330
Professor Paredes
Assignemt 5
*/
public class HonorsStudent extends UndergraduateStudent {

    // Fields (Attributes) 

    // Stores the thesis title for the honors student
    private String thesisTitle;

    // Fixed-size array of committee member names (maximum 3)
    private String[] committee;

    // Tracks how many committee members are currently assigned (≤ 3)
    private int committeeCount;

    //  Constructors 

    /* 
     Creates a new HonorsStudent with a given name.
     Initializes thesis title to empty and committee to empty array.
    */
    public HonorsStudent(String name) {
        super(name);                // Call parent constructor to set the student's name
        this.thesisTitle = "";      // Start with empty thesis title
        this.committee = new String[3]; // Create an array with space for 3 committee members
        this.committeeCount = 0;    // Initially, no members
    }

    /*
     Converts an existing UndergraduateStudent into an HonorsStudent.
     Copies inherited fields and initializes new honors-specific fields.
    */
    public HonorsStudent(UndergraduateStudent ob) {
        super(ob);                  // Use the parent’s copy constructor
        this.thesisTitle = "";      // Honors-specific data starts empty
        this.committee = new String[3];
        this.committeeCount = 0;
    }

    /*
     Copy constructor — creates a deep copy of another HonorsStudent.
    */
    public HonorsStudent(HonorsStudent ob) {
        super(ob); // Copy all fields from the parent (like name, courses, etc.)

        // Deep copy of HonorsStudent-specific fields
        this.thesisTitle = ob.getThesisTitle();
        this.committee = new String[3];
        this.committeeCount = ob.getCommitteeCount();

        // Deep copy committee member names
        for (int i = 0; i < this.committeeCount; i++) {
            this.committee[i] = ob.getCommittee()[i];
        }
    }
        //Methods 

    /**
     Adds a committee member by name.
     Only adds if there is space (max 3 members).
    */
    public boolean addCommitteeMember(String name) {
        if (committeeCount < 3) {
            committee[committeeCount] = name;
            committeeCount++;
            return true;
        }
        return false; // No space left in the committee
    }

    /* Returns the current thesis title. */
    public String getThesisTitle() {
        return this.thesisTitle;
    }

    /* Updates the student's thesis title. */
    public void setThesisTitle(String title) {
        this.thesisTitle = title;
    }

    /* Returns the reference to the committee array. */
    public String[] getCommittee() {
        return this.committee;
    }

    /** Returns the number of committee members currently stored. */
    public int getCommitteeCount() {
        return this.committeeCount;
    }

    /* Prints each committee member name on its own line. */
    public void printCommittee() {
        for (int i = 0; i < committeeCount; i++) {
            System.out.println(committee[i]);
        }
    }

    /* Honors students pay half the regular undergraduate tuition rate. */
    @Override
    public double getRate() {
        return super.getRate() / 2.0;
    }

    /* Creates and returns a deep copy of this HonorsStudent. */
    @Override
    public HonorsStudent duplicate() {
        return new HonorsStudent(this);
    }
}
