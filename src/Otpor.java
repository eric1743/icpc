import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Otpor {

    static int idx = 0;
    static String spec;
    static List<Double> rs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        rs = Arrays.stream(br.readLine().split(" "))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
        spec = br.readLine();

        System.out.println(process());
    }

    static Double process() {
        List<Double> res = new ArrayList<>();
        boolean series = true;

        loop:
        while (true) {
            idx++;
            switch (spec.charAt(idx)) {
                case '(':
                    res.add(process());
                    break;
                case 'R':
                    res.add(rs.get(spec.charAt(++idx) - 49));
                    break;
                case '|':
                    series = false;
                    break;
                case ')':
                    break loop;
            }
        }

        double ret = 0;
        if (series) {
            for (Double d : res) {
                ret += d;
            }
        } else {
            for (Double d : res) {
                ret += 1 / d;
            }
            ret = 1 / ret;
        }
        return ret;
    }
}


