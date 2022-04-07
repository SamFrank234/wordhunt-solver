import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {
    public static ArrayList<String> dictionary = new ArrayList();
    public static ArrayList<Pair> pairs = new ArrayList();

    public static void makeDictionary() throws IOException {
        BufferedReader dictReader = new BufferedReader(new FileReader("dict.txt"));
        for (String curWord = dictReader.readLine(); curWord != null; curWord = dictReader.readLine()) {
            dictionary.add(curWord);
        }
        pairs.add(new Pair('A',0,10754));
        pairs.add(new Pair('B',10755,20722));
        pairs.add(new Pair('C',20723,37310));
        pairs.add(new Pair('D',37311,47710));
        pairs.add(new Pair('E',47711,54884));
        pairs.add(new Pair('F', 54885,62020));
        pairs.add(new Pair('G', 62021,67893));
        pairs.add(new Pair('H',67894,74386));
        pairs.add(new Pair('I',74387,81009));
        pairs.add(new Pair('J',81010,82488));
        pairs.add(new Pair('K',82489,84343));
        pairs.add(new Pair ('L',84344,89657));
        pairs.add(new Pair ('M',89658,99599));
        pairs.add(new Pair ('N',99600,104049));
        pairs.add(new Pair('0',104050,110093));
        pairs.add(new Pair('P',110094,125156));
        pairs.add(new Pair('Q',125157,126005));
        pairs.add(new Pair('R',126006,136522));
        pairs.add(new Pair('S',136523,156251));
        pairs.add(new Pair('T',156251,165248));
        pairs.add(new Pair('U',165249,170473));
        pairs.add(new Pair('V',170474,173332));
        pairs.add(new Pair('W',173333,177252));
        pairs.add(new Pair('X',177253,177402));
        pairs.add(new Pair('Y',177403,177989));
        pairs.add(new Pair('Z',177990,178588));

    }

    public static boolean isValidWord(String word) {
        int low = 0;
        int high = dictionary.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (word.compareTo(dictionary.get(mid)) == 0) {
                return true;
            }
            if (word.compareTo(dictionary.get(mid)) > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static boolean isBeginWord(String str){
        char beg = str.charAt(0);
        int count =0;
        int start=0;
        int end=0;
        for (char let = 'A'; let<='Z';let++) {
            if (let==beg){
                start = pairs.get(count).getBegin();
                end = pairs.get(count).getEnd();
            }
            count++;
        }
       for (int i=start; i<=end; i++){
            if (dictionary.get(i).length()<str.length()){
                continue;
            }
           if (dictionary.get(i).substring(0,str.length()).equals(str)){
               return true;
            }
        }
        return false;
    }
}
