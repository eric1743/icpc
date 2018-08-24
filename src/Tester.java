import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Tester {
    public static void main(String[] args) {
        String filterOn = "tcin,location,tcin,location";
        Set<String> filters = Arrays.stream(filterOn.split(",")).distinct().collect(Collectors.toSet());

        for(String s : filters){
            System.out.println(s);
        }
    }

}
