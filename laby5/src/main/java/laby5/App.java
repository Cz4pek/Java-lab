package laby5;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class App {
    private App() {
    }
    public void printList(Object[] list, String title){
        System.out.println(title);
        for (Object object : list) {
            System.out.println(object);
        }
    }
    public void printShortNames(Object[] list){
        for (Object object : list) {
            int temp = object.toString().lastIndexOf('.');
            if(temp >= 0 ) System.out.println(object.toString().substring(0, temp).isBlank()? "blank" : object.toString().substring(0, temp));
            else System.out.println(object);
        }
    }
    public List<String> findExt(String[] list, String ext){
        ArrayList<String> result = new ArrayList<>();
        for (String string : list) {
            if(string.endsWith(ext)) result.add(string);
        }
        return result;
    }
    public List<String> findCont(String[] list,  String subs){
        ArrayList<String> result = new ArrayList<>();
        for (String string : list) {
            if(string.contains(subs)) result.add(string);
        }
        return result;
    }
    public static void main(String[] args) {
        App x =new App();
        File currentdir = new File(".");
        String[] content = currentdir.list();
        x.printList(content, "----lista nieposortowana----");
        Arrays.sort(content);
        x.printList(content, "----lista posortowana----");
        System.out.println();
        x.printShortNames(content);
        System.out.println();
        x.printList(x.findExt(content, ".xml").toArray(),"---- pliki z rozszerzeniem .xml ----"); 
        System.out.println();
        x.printList(x.findCont(content, "i").toString().replaceAll("[\\[\\]]", "").split(","),"---- pliki z rozszerzeniem zawierające litere i ----"); 
        System.out.println();
        x.printShortNames(x.findCont(content, "i").toArray());
        int temp = Arrays.binarySearch(content, "src");
        System.out.println(temp + ": " + content[temp]);
    }
}
