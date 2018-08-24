import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class BattleSimulation {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] in = br.readLine().toCharArray();
        StringBuilder out = new StringBuilder();

        Map<Character, Character> map = new HashMap<>();
        map.put('R','S');
        map.put('B','K');
        map.put('L','H');

        if (in.length < 3){
            for(char c : in){
                out.append(map.get(c));
            }
        } else {
            int i = 2;
            int sum = in[0]+in[1]+in[2];
            while (i < in.length){
                if (sum == 224){

                }
            }
        }
    }
}
