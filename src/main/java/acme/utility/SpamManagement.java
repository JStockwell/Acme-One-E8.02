package acme.utility;

public class SpamManagement {
	
	public boolean checkSpam(final String text, final String terms, final double threshold) {
		boolean result = false;
		
		final String checkedText = text.replaceAll("[^a-zA-Z0-9]", "");
		final String[] checkingTerms = terms.replaceAll("[^a-zA-Z0-9,]", "").split(",");
		
		for(final String term : checkingTerms) {
			if(checkedText.contains(term)) {
				result = true;
			}
		}
		
		return result;
	}

}
