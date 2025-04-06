package seedu.address.model.person;

/**
 * Represents the result of a person similarity check.
 */
public class PersonSimilarity {
    public final boolean isSame;
    public final boolean isLikelySame;

    /**
     * Constructs a PersonSimilarity object.
     *
     * @param isSame Whether the two persons are the same.
     * @param isLikelySame Whether the two persons are likely the same.
     */
    public PersonSimilarity(boolean isSame, boolean isLikelySame) {
        this.isSame = isSame;
        this.isLikelySame = isLikelySame;
    }
}
