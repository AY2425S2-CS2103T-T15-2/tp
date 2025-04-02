package seedu.address.model.person;

/**
 * Contains result for a person check.
 */
public class ContainsResult {
    public final boolean isDuplicate;
    public final boolean isSimilar;

    /**
     * Constructs a ContainsResult object.
     *
     * @param isDuplicate Whether the person is a duplicate.
     * @param isSimilar Whether the person is similar.
     */
    public ContainsResult(boolean isDuplicate, boolean isSimilar) {
        this.isDuplicate = isDuplicate;
        this.isSimilar = isSimilar;
    }
}
