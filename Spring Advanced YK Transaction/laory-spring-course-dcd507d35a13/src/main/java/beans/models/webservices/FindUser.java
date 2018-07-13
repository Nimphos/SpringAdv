package beans.models.webservices;

public class FindUser {
    private String findType;
    private String inputValue;

    public FindUser() {
    }

    public FindUser(String findType, String inputValue) {
        this.findType = findType;
        this.inputValue = inputValue;
    }

    public String getFindType() {
        return findType;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setFindType(String findType) {
        this.findType = findType;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }
}
