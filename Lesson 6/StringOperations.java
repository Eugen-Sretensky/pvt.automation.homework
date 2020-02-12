package example;

public class StringOperations {

    public boolean containsC(String str1, String str2) {
        return str1.contains(str2);
    }

    public boolean contentEqualsC(String str1, String str2) {
        return str1.contentEquals(str2);
    }

    public boolean isEmptyC(String str1) {
        return str1.isEmpty();
    }

    public int lastIndexOfC(String str1, String str2) {
        return str1.lastIndexOf(str2);
    }

    public String replaceC(String str1, String str2, String str3) throws InputValueIsNullException {
        if ((str1 == null)||(str2 == null)||(str3 == null)){
        throw new InputValueIsNullException("Вы не ввели значение!");
        }else{
        return str1.replace(str2, str3);}
    }
}
